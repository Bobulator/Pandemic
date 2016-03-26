package com.cs428.pandemic.frontEnd.gamePlay;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ImageView;

import com.cs428.pandemic.R;
 
public class GameBoardView extends SurfaceView implements View.OnTouchListener {
 
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
    
    private double mScale;
	private PointF mWorldCenter;
	private PointF mWorldUpperLeft;
	
    private Bitmap mImage;
    private SurfaceHolder holder;
    
    private String TAG = "GameBoardView";
 
    public GameBoardView(Context context, Activity activity){
        super(context);
 
        mWorldUpperLeft = new PointF(0, 0);
        mScale = 1.0;
        
        holder = getHolder();
        holder.addCallback(new SurfaceHolder.Callback(){
            @SuppressLint("WrongCall")
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                Canvas canvas = holder.lockCanvas(null);
                onDraw(canvas);
                holder.unlockCanvasAndPost(canvas);
            }
 
            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
 
            }
 
            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
 
            }
        });
        
        WindowManager wm = (WindowManager) activity.getSystemService(activity.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size); // size.x, size.y are size of phone screen
        
        Log.i(TAG, "ScreenSIZE: (" + size.x + ", " + size.y + ")");
        
        ViewTreeObserver viewTreeObserver = this.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
          viewTreeObserver.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
//              v.getViewTreeObserver().removeOnGlobalLayoutListener(this);
              int width = GameBoardView.this.getWidth();
              int height = GameBoardView.this.getHeight();
              Log.d(TAG, "WindowSIZE: " + width + ", " + height);
            }
          });
        }
 
        mImage = BitmapFactory.decodeResource(getResources(), R.drawable.game_board_nocities);
 
    }
 
    @Override
    protected void onDraw(Canvas canvas){
        canvas.drawColor(Color.BLUE);
        canvas.drawBitmap(mImage, 10, 10, null);
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
 
}