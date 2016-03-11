package com.cs428.pandemic.frontEnd.gamePlay;

import android.app.Fragment;
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

        mBoard = (ImageView)v.findViewById(R.id.board_image_view);
        mBoard.setOnTouchListener(this);
        return showBoard();
    }

    public ImageView showBoard() {
//        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.game_board_nocities);
        mBoard.setImageBitmap(decodeImage());
        return mBoard;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    public Bitmap decodeImage() {
        try {
            // Decode image size
            int resourceId = R.drawable.game_board_nocities;
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeResource(getResources(), resourceId, o);
            // The new size we want to scale to
            final int REQUIRED_SIZE = 100; // you are free to modify size as your requirement

            // Find the correct scale value. It should be the power of 2.
            int scale = 1;
            while (o.outWidth / scale / 2 >= REQUIRED_SIZE && o.outHeight / scale / 2 >= REQUIRED_SIZE)
                scale *= 2;

            // Decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            return BitmapFactory.decodeResource(getResources(), resourceId, o2);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;

    }
}
