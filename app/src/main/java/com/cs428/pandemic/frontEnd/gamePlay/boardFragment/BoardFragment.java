package com.cs428.pandemic.frontEnd.gamePlay.boardFragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.cs428.pandemic.R;
import com.cs428.pandemic.frontEnd.IModelInterface;
import com.cs428.pandemic.frontEnd.dataTransferObjects.UI_Player;
import com.cs428.pandemic.frontEnd.gamePlay.GamePlayActivity;
import com.cs428.pandemic.frontEnd.gamePlay.RoleSummaryFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hayden on 3/11/2016.
 *
 * This Fragment handles the drawing of the game board. Since the board we're using is large, it's
 * necessary to reduce the sampling size so as to avoid running out of memory; this class will
 * draw the largest image possible so as to fill the dimensions of the screen without wasting any
 * memory.
 */
public class BoardFragment extends Fragment implements View.OnTouchListener, GestureDetector.OnGestureListener {

    public static String PLAYERS_ARGS = "players";
    public static String DIFFICULTY_ARGS = "difficulty";

	// 3 possible states
    private static final int NONE = 0;
    private static final int DRAG = 1;
    private static final int ZOOM = 2;
    private int mode = NONE;
    
    private Matrix matrix = new Matrix();
    private Matrix savedMatrix = new Matrix();
    
    // Remember some things for zooming
    private PointF start = new PointF();
    private PointF mid = new PointF();
    private double oldDist = 1.0;
	
    private static final String TAG = "BoardFragment";
    
    private ImageView mBoardImage;

    private IModelInterface mModelFacade;
    private BoardDrawer mBoardDrawer;

    private List<UI_Player> mPlayers;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setRetainInstance(true);
        mModelFacade = ((GamePlayActivity)getActivity()).getModelFacade();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_board, parent, false);

        WindowManager wm = (WindowManager) getActivity().getSystemService(getActivity().WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size); // size.x, size.y are size of phone screen

        // The boardDrawer class will do all of the necessary drawing to create the board.
        mBoardDrawer = new BoardDrawer(mModelFacade);

        // Use a bitmap to scale the image resource down so as not to use too much memory.
        // The width and height were arbitrarily chosen as placeholders until we can find
        // a more consistent way to determine what size we should draw the image.
        Bitmap bitmap = mBoardDrawer.createBitmap(getResources(), R.drawable.game_board_nocities, 200, 200);

        // Draw everything onto the bitmap
        mBoardDrawer.drawBoard(bitmap);

        // Display the game board
        mBoardImage = (ImageView) view.findViewById(R.id.board_image_view);
        mBoardImage.setOnTouchListener(this);
        // Attach the canvas to the ImageView
        mBoardImage.setImageDrawable(new BitmapDrawable(getResources(), bitmap));

        Bundle args = getArguments();
        mPlayers = startGame(args.getStringArrayList("players"), args.getString("difficulty"));
        displayPlayerRolesDialog();

        return view;
    }

    @Override
	public boolean onTouch(View v, MotionEvent event) {
		ImageView view = (ImageView) v;
        // make the image scalable as a matrix
        view.setScaleType(ImageView.ScaleType.MATRIX);
        double scale;
	        
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
	        case MotionEvent.ACTION_DOWN: //first finger down only
	            savedMatrix.set(matrix);
	            start.set(event.getX(), event.getY());
	            Log.d(TAG, "mode=DRAG" );
	            mode = DRAG;
	            break;
	        case MotionEvent.ACTION_UP: //first finger lifted
	        	break;
	        case MotionEvent.ACTION_POINTER_UP: //second finger lifted
	            mode = NONE;
	            Log.d(TAG, "mode=NONE" );
	            break;
	        case MotionEvent.ACTION_POINTER_DOWN: //second finger down
	            oldDist = spacing(event); // calculates the distance between two points where user touched.
	            Log.d(TAG, "oldDist=" + oldDist);
	            // minimal distance between both the fingers
	            if (oldDist > 5f) {
	                savedMatrix.set(matrix);
	                midPoint(mid, event); // sets the mid-point of the straight line between two points where user touched.
	                mode = ZOOM;
	                Log.d(TAG, "mode=ZOOM" );
	            }
	            break;
	
	        case MotionEvent.ACTION_MOVE:
	            if (mode == DRAG)
	            { //movement of first finger
	                matrix.set(savedMatrix);
	                if (view.getLeft() >= -392)
	                {
	                    matrix.postTranslate(event.getX() - start.x, event.getY() - start.y);
	                }
	            }
	            else if (mode == ZOOM) { //pinch zooming
	                double newDist = spacing(event);
	                Log.d(TAG, "newDist=" + newDist);
	                if (newDist > 5f) {
	                    matrix.set(savedMatrix);
	                    scale = newDist/oldDist; //thinking I need to play around with this value to limit it**
	                    matrix.postScale((float)scale, (float)scale, mid.x, mid.y);
	                }
	            }
	            break;
	    }
	    // Perform the transformation
	    view.setImageMatrix(matrix);
	
	    return true;
	}
	
	private double spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return Math.sqrt(x * x + y * y);
    }

    private void midPoint(PointF point, MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x / 2, y / 2);
    }

    private void displayPlayerRolesDialog() {
        FragmentManager fm = getActivity().getFragmentManager();
        RoleSummaryFragment dialog = new RoleSummaryFragment();
        dialog.setTargetFragment(BoardFragment.this, 0);
        dialog.show(fm, "roles");
    }

    private List<UI_Player> startGame(ArrayList<String> players, String difficulty) {
        return mModelFacade.startGame(players, difficulty, (GamePlayActivity) this.getActivity());
    }

    public List<UI_Player> getPlayers() { return mPlayers; }

    public void updateBoard() {

    }

	@Override
	public boolean onDown(MotionEvent e) {
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		((GamePlayActivity) getActivity()).toggleToolbar();
		return false;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		return false;
	}
}
