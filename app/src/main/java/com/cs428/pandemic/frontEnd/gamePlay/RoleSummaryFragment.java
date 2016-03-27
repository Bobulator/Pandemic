package com.cs428.pandemic.frontEnd.gamePlay;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cs428.pandemic.R;
import com.cs428.pandemic.frontEnd.dataTransferObjects.UI_Player;
import com.cs428.pandemic.frontEnd.enums.Role;
import com.cs428.pandemic.frontEnd.gamePlay.boardFragment.BoardFragment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Hayden on 3/17/2016.
 */
public class RoleSummaryFragment extends DialogFragment {

    private Map<Role, Integer> roleMap = new HashMap<>();

    @SuppressWarnings("unchecked")
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        initializeRoleMap();

        // Retrieve the current list of players from the parent fragment (BoardFragment)
        List<UI_Player> players = ((BoardFragment)getTargetFragment()).getPlayers();

        int numbPlayers = players.size();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View v;

        if(numbPlayers == 2) {
            //set the appropriate view
            v = getActivity().getLayoutInflater().inflate(R.layout.dialog_role_summary_2, null);
            setDialogPlayerNames(v, players);
        } else if(numbPlayers == 3) {
            //set the appropriate view
            v = getActivity().getLayoutInflater().inflate(R.layout.dialog_role_summary_3, null);
            setDialogPlayerNames(v, players);
        } else {
            //set the appropriate view
            v = getActivity().getLayoutInflater().inflate(R.layout.dialog_role_summary_4, null);
            setDialogPlayerNames(v, players);
        }

        builder.setView(v)
                .setPositiveButton(R.string.play, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // do nothing on button click
                    }
                });

        return builder.create();
    }

    public void setDialogPlayerNames(View v, List<UI_Player> players) {
        for(int i = 0; i < players.size(); i++) {
            if(i == 0) {
                ((TextView) v.findViewById(R.id.player_one_name)).setText(players.get(i).getPlayerName());
                ((ImageView) v.findViewById(R.id.player_one_role)).setImageResource(roleMap.get(players.get(i).getPlayerRole()));
            }
            else if(i == 1) {
                ((TextView) v.findViewById(R.id.player_two_name)).setText(players.get(i).getPlayerName());
                ((ImageView) v.findViewById(R.id.player_two_role)).setImageResource(roleMap.get(players.get(i).getPlayerRole()));
            }
            else if(i == 2) {
                ((TextView) v.findViewById(R.id.player_three_name)).setText(players.get(i).getPlayerName());
                ((ImageView) v.findViewById(R.id.player_three_role)).setImageResource(roleMap.get(players.get(i).getPlayerRole()));
            }
            else {
                ((TextView) v.findViewById(R.id.player_four_name)).setText(players.get(i).getPlayerName());
                ((ImageView) v.findViewById(R.id.player_four_role)).setImageResource(roleMap.get(players.get(i).getPlayerRole()));
            }
        }
    }

    public void initializeRoleMap() {
        roleMap.put(Role.CONTAINMENT_SPECIALIST, R.drawable.card_role_contingencyplanner);
        roleMap.put(Role.DISPATCHER, R.drawable.card_role_dispatcher);
        roleMap.put(Role.MEDIC, R.drawable.card_role_medic);
        roleMap.put(Role.OPERATIONS_EXPERT, R.drawable.card_role_operationsexpert);
        roleMap.put(Role.RESEARCHER, R.drawable.card_role_researcher);
        roleMap.put(Role.SCIENTIST, R.drawable.card_role_scientist);
    }
}
