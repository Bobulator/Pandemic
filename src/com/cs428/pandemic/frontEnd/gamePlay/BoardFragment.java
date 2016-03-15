package com.cs428.pandemic.frontEnd.gamePlay;

import android.app.Fragment;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
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

    private ImageView mBoard;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);	// I don't want an options menu on this fragment
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_game_board, parent, false);
        mBoard = (ImageView) v.findViewById(R.id.board_image_view);
        mBoard.setOnTouchListener(this);

        // Display the game board
        mBoard.setImageBitmap(
                // Use a bitmap to scale the image resource down so as not to use too much memory
                // The width and height were arbitrarily chosen as placeholders until we can find
                // a more consistent way to determine what size we should draw the image
                decodeSampledBitmapFromResource(getResources(), R.drawable.game_board_nocities,
                        400, 400));

        return v;
    }

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

    public int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of the image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

}
