package com.cs428.pandemic.frontEnd;

import com.cs428.pandemic.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class GameOverviewFragment extends Fragment {

	private Button mPlayButton;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);	// I don't want an options menu on this fragment
    }
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
            Bundle savedInstanceState) {
		int numberPlayers = ((MainMenuActivity) getActivity()).getNumberPlayers();
		View v;
		
		if(numberPlayers == 2) {
			v = inflater.inflate(R.layout.fragment_game_overview_2, parent, false);
			instantiateViewWidgets(v, 2);
		} else if(numberPlayers == 3) {
			v = inflater.inflate(R.layout.fragment_game_overview_3, parent, false);
			instantiateViewWidgets(v, 3);
		} else {
			v = inflater.inflate(R.layout.fragment_game_overview_4, parent, false);
			instantiateViewWidgets(v, 4);
		}
		
		return v;
	}
	
	public void instantiateViewWidgets(View v, int numPlayers) {
		MainMenuActivity act = (MainMenuActivity) getActivity();
		String[] players = act.getPlayerList();
		((TextView)v.findViewById(R.id.player_one_name)).setText(players[0]);
		((TextView)v.findViewById(R.id.player_two_name)).setText(players[1]);
		
		if(numPlayers > 2) {
			((TextView)v.findViewById(R.id.player_three_name)).setText(players[2]);
			if(numPlayers == 4)
				((TextView)v.findViewById(R.id.player_four_name)).setText(players[3]);
		}
		
		((TextView)v.findViewById(R.id.difficulty_level)).setText("Difficulty: " + act.getDifficulty());
		mPlayButton = (Button)v.findViewById(R.id.play_button);
		mPlayButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				((MainMenuActivity) getActivity()).startGameActivity();
			}
		});
	}

}
