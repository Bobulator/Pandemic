package com.cs428.pandemic.frontEnd.gamePlay;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.cs428.pandemic.R;
import com.cs428.pandemic.frontEnd.dataTransferObjects.UI_Player;
import com.cs428.pandemic.frontEnd.gamePlay.boardFragment.BoardFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hayden on 3/17/2016.
 */
public class RoleSummaryFragment extends DialogFragment {

    //dummy lists only used for testing. The real list of players will be retrieved from the model
    private ArrayList<String> listOf2Players = new ArrayList<>();
    private ArrayList<String> listOf3Players = new ArrayList<>();
    private ArrayList<String> listOf4Players = new ArrayList<>();

    @SuppressWarnings("unchecked")
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        initializePlayerLists();

        // Retrieve the current list of players from the parent fragment (BoardFragment)
        List<UI_Player> players = ((BoardFragment)getTargetFragment()).getPlayers();

        int numbPlayers = listOf4Players.size();    //testing 2 player layout

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        if(numbPlayers == 2) {
            //set the appropriate view
            View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_role_summary_2, null);
            builder.setView(v)
                    .setPositiveButton(R.string.play, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            // do nothing on button click
                        }
                    });
            ((TextView) v.findViewById(R.id.player_one_name)).setText(listOf2Players.get(0));
            ((TextView) v.findViewById(R.id.player_two_name)).setText(listOf2Players.get(1));
        } else if(numbPlayers == 3) {
            //set the appropriate view
            View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_role_summary_3, null);
            builder.setView(v)
                    .setPositiveButton(R.string.play, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            // do nothing on button click
                        }
                    });
            ((TextView) v.findViewById(R.id.player_one_name)).setText(listOf3Players.get(0));
            ((TextView) v.findViewById(R.id.player_two_name)).setText(listOf3Players.get(1));
            ((TextView) v.findViewById(R.id.player_three_name)).setText(listOf3Players.get(2));
        } else {
            //set the appropriate view
            View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_role_summary_4, null);
            builder.setView(v)
                    .setPositiveButton(R.string.play, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            // do nothing on button click
                        }
                    });
            ((TextView) v.findViewById(R.id.player_one_name)).setText(listOf4Players.get(0));
            ((TextView) v.findViewById(R.id.player_two_name)).setText(listOf4Players.get(1));
            ((TextView) v.findViewById(R.id.player_three_name)).setText(listOf4Players.get(2));
            ((TextView) v.findViewById(R.id.player_four_name)).setText(listOf4Players.get(3));
        }

        return builder.create();
    }

    public void initializePlayerLists() {
        listOf2Players.add("Chad");
        listOf2Players.add("Casey");

        listOf3Players = (ArrayList<String>) listOf2Players.clone();
        listOf3Players.add("Hayden");

        listOf4Players = (ArrayList<String>) listOf3Players.clone();
        listOf4Players.add("Brandt");
    }
}
