package com.cs428.pandemic.frontEnd.startSequence;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cs428.pandemic.R;

public class PlayerNamesFragment extends Fragment {

	private Button mRecentPlayersButton;
	private Button mNextButton;
	private EditText mPlayerOne;
	private EditText mPlayerTwo;
	private EditText mPlayerThree;
	private EditText mPlayerFour;
	
	private String[] players;
	
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
			v = inflater.inflate(R.layout.fragment_two_players, parent, false);
			instantiateViewWidgets(v, 2);
		} else if(numberPlayers == 3) {
			v = inflater.inflate(R.layout.fragment_three_players, parent, false);
			instantiateViewWidgets(v, 3);
		} else {
			v = inflater.inflate(R.layout.fragment_four_players, parent, false);
			instantiateViewWidgets(v, 4);
		}
		

		mRecentPlayersButton = (Button)v.findViewById(R.id.recent_players_button);
		mRecentPlayersButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// open up dialog fragment w/ list of recent number of players
				
				// if they select a name from list, check to see if there are any empty player names
					// if there are, set the first empty slot as that person
			}
		});
		
		mNextButton = (Button)v.findViewById(R.id.next_button);
		mNextButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				boolean namesSet = true;
				for(int i = 0; i < players.length; i++) {
					if(players[i] == null || players[i] == "")
						namesSet = false;
				}
				
				if(namesSet) {
					MainMenuActivity act = (MainMenuActivity) getActivity();
					act.setPlayerList(players);
					act.replaceFragment(act.DIFFICULTY);
				} else {
					Toast.makeText(getActivity(), "Must give all players a name!", Toast.LENGTH_LONG).show();
				}
			}
		});
		
		
		return v;
	}
	
	// this method is called for any # of players in game to reduce code duplication
	public void instantiateViewWidgets(View v, int numPlayers) {
		players = new String[numPlayers];
		
		mPlayerOne = (EditText)v.findViewById(R.id.player_one_name);
		mPlayerOne.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
			public void afterTextChanged(Editable s) {}
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				players[0] = s.toString();
			}
		});
		
		mPlayerTwo = (EditText)v.findViewById(R.id.player_two_name);
		mPlayerTwo.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
			public void afterTextChanged(Editable s) {}
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				players[1] = s.toString();
			}				
		});
		
		if(numPlayers > 2) {
			mPlayerThree = (EditText)v.findViewById(R.id.player_three_name);
			mPlayerThree.addTextChangedListener(new TextWatcher() {
				@Override
				public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
				public void afterTextChanged(Editable s) {}
				public void onTextChanged(CharSequence s, int start, int before, int count) {
					players[2] = s.toString();
				}				
			});
			
			if(numPlayers == 4) {
				mPlayerFour = (EditText)v.findViewById(R.id.player_four_name);
				mPlayerFour.addTextChangedListener(new TextWatcher() {
					@Override
					public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
					public void afterTextChanged(Editable s) {}
					public void onTextChanged(CharSequence s, int start, int before, int count) {
						players[3] = s.toString();
					}				
				});
			}
		}
	}	
}
