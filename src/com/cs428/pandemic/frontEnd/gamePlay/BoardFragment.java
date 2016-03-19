package com.cs428.pandemic.frontEnd.gamePlay;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.cs428.pandemic.R;

/**
 * Created by Hayden on 3/11/2016.
 *
 * This Fragment handles the drawing of the game board. Since the board we're using is large, it's
 * necessary to reduce the sampling size so as to avoid running out of memory; this class will
 * draw the largest image possible so as to fill the dimensions of the screen without wasting any
 * memory.
 */
public class BoardFragment extends Fragment implements View.OnTouchListener {

	// 3 possible states
    static final int NONE = 0;
    static final int DRAG = 1;
    static final int ZOOM = 2;
    int mode = NONE;
    
    Matrix matrix = new Matrix();
    Matrix savedMatrix = new Matrix();
    
    // Remember some things for zooming
    PointF start = new PointF();
    PointF mid = new PointF();
    double oldDist = 1.0;
	
    private static final String TAG = "Touch";
    
    private ImageView boardImageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_board, parent, false);

        WindowManager wm = (WindowManager) getActivity().getSystemService(getActivity().WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        // Display the game board
        boardImageView = (ImageView) view.findViewById(R.id.board_image_view);
        boardImageView.setOnTouchListener(this);
        boardImageView.setImageBitmap(
                // Use a bitmap to scale the image resource down so as not to use too much memory.
                // The width and height were arbitrarily chosen as placeholders until we can find
                // a more consistent way to determine what size we should draw the image.
                // decodeSampledBitmapFromResource(getResources(), R.drawable.game_board_nocities, size.x, size.y));
                decodeSampledBitmapFromResource(getResources(), R.drawable.game_board_nocities, 800, 400));

        displayPlayerRolesDialog();

        return view;
    }

    /**
     * Construct a Bitmap of the given resource that is as small as possible while remaining
     * larger than the given width and height.
     *
     * @param res The resources from which the image is located.
     * @param resId The specific id of the image to generate a Bitmap for.
     * @param reqWidth The width constraint.
     * @param reqHeight The height constraint.
     * @return A Bitmap such that the width and height are as small as possible while remaining
     * larger than the reqWidth and reqHeight.
     */
    public Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    /**
     * Determine the extent to which the Bitmap dimensions should be reduced from the original image
     * size using the given constraints. The reduction is determined such that the original image is
     * reduced to be as small as possible while remaining larger than the given width and height.
     *
     * @param options The Bitmap options from which the original image dimensions are retrieved.
     * @param reqWidth The width constraint.
     * @param reqHeight The height constraint.
     *
     * @return The sample size by which the original image should be reduced.
     */
    public int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of the image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        // Calculate the largest inSampleSize value that is a power of 2 and keeps both
        // height and width larger than the requested height and width.
        while ((height / inSampleSize) > reqHeight
                && (width / inSampleSize) > reqWidth) {
            inSampleSize *= 2;
        }
        inSampleSize /= 2;

        return inSampleSize;
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

    public void displayPlayerRolesDialog() {
        FragmentManager fm = getActivity().getFragmentManager();
        RoleSummaryFragment dialog = new RoleSummaryFragment();
        dialog.setTargetFragment(BoardFragment.this, 0);
        dialog.show(fm, "roles");
    }
}
