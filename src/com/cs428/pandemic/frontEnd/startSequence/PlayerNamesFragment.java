package com.cs428.pandemic.frontEnd.startSequence;

import java.util.ArrayList;
import java.util.Locale;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cs428.pandemic.R;

public class PlayerNamesFragment extends Fragment {

	private String TAG = "PlayerNamesFragment";
	private Button mRecentPlayersButton;
	private Button mNextButton;
	private EditText mPlayerOne;
	private EditText mPlayerTwo;
	private EditText mPlayerThree;
	private EditText mPlayerFour;
	
	private static final int REQUEST_PLAYER = 0;
	private static final String DIALOG_PLAYER = "player";
	
	private ArrayList<String> mPlayers;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);	// I don't want an options menu on this fragment
    }
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
            Bundle savedInstanceState) {
		final int numberPlayers = ((MainMenuActivity) getActivity()).getNumberPlayers();
		View v;
		
		if (numberPlayers == 2) {
			v = inflater.inflate(R.layout.fragment_two_players, parent, false);
			instantiateViewWidgets(v, 2);
		} else if (numberPlayers == 3) {
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
				Log.i(TAG, "Size of Current Player Array: " + mPlayers.size());
				Log.i(TAG, "Number of Players to play: " + numberPlayers);
				if (mPlayers.size() < numberPlayers || mPlayers.contains("")) {
					FragmentManager fm = getActivity().getFragmentManager();
					SavedPlayerListFragment dialog = SavedPlayerListFragment.newInstance(mPlayers);
					dialog.setTargetFragment(PlayerNamesFragment.this, REQUEST_PLAYER);
					dialog.show(fm, DIALOG_PLAYER);
				} else
					Toast.makeText(getActivity(), "You have already filled all players.", Toast.LENGTH_LONG).show();
			}
		});
		
		mNextButton = (Button)v.findViewById(R.id.next_button);
		mNextButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MainMenuActivity act = (MainMenuActivity) getActivity();
				boolean namesSet = false;
				if (mPlayers.size() == act.getNumberPlayers() && !mPlayers.contains(""))
					namesSet = true;
				
				if (namesSet) {
					act.setPlayerList(mPlayers);
					act.replaceFragment(MainMenuActivity.DIFFICULTY);
				} else 
					Toast.makeText(getActivity(), "Must give all players a name!", Toast.LENGTH_LONG).show();
			}
		});
		
		
		return v;
	}
	
	// this method is called for any # of players in game to reduce code duplication
	public void instantiateViewWidgets(View v, int numPlayers) {
		mPlayers = new ArrayList<String>(numPlayers);
		
		mPlayerOne = (EditText)v.findViewById(R.id.player_one_name);
		mPlayerOne.setFilters(new InputFilter[] {new InputFilter.AllCaps()});
		mPlayerOne.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
			public void afterTextChanged(Editable s) {}
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if (mPlayers.size() >= 1)
					mPlayers.remove(0);
				mPlayers.add(0,s.toString().toUpperCase(Locale.US));
			}
		});
		
		mPlayerTwo = (EditText)v.findViewById(R.id.player_two_name);
		mPlayerTwo.setFilters(new InputFilter[] {new InputFilter.AllCaps()});
		mPlayerTwo.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
			public void afterTextChanged(Editable s) {}
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if (mPlayers.size() >= 2)
					mPlayers.remove(1);
				mPlayers.add(1, s.toString().toUpperCase(Locale.US));
			}				
		});
		
		if(numPlayers > 2) {
			mPlayerThree = (EditText)v.findViewById(R.id.player_three_name);
			mPlayerThree.setFilters(new InputFilter[] {new InputFilter.AllCaps()});
			mPlayerThree.addTextChangedListener(new TextWatcher() {
				@Override
				public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
				public void afterTextChanged(Editable s) {}
				public void onTextChanged(CharSequence s, int start, int before, int count) {
					if (mPlayers.size() >= 3)
						mPlayers.remove(2);
					mPlayers.add(2, s.toString().toUpperCase(Locale.US));
				}				
			});
			
			if(numPlayers == 4) {
				mPlayerFour = (EditText)v.findViewById(R.id.player_four_name);
				mPlayerFour.setFilters(new InputFilter[] {new InputFilter.AllCaps()});
				mPlayerFour.addTextChangedListener(new TextWatcher() {
					@Override
					public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
					public void afterTextChanged(Editable s) {}
					public void onTextChanged(CharSequence s, int start, int before, int count) {
						if (mPlayers.size() >= 4)
							mPlayers.remove(3);
						mPlayers.add(3, s.toString().toUpperCase(Locale.US));
					}				
				});
			}
		}
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == Activity.RESULT_CANCELED) {
			Log.i(TAG, "Saved Player List canceled");
			return;
		} else if (resultCode == Activity.RESULT_OK) {
			MainMenuActivity act = (MainMenuActivity) getActivity();
			String name = (String)data.getSerializableExtra(SavedPlayerListFragment.EXTRA_PLAYERS);
			
			if ((act.getNumberPlayers() > mPlayers.size() && !name.equals("")) || mPlayers.contains("")) {
				int pos = mPlayers.size() + 1;
				
				if (mPlayers.contains("")) {
					for (int i = 0; i < mPlayers.size(); i++) {
						if (mPlayers.get(i).equals("")) {
							pos = i + 1;
							break;
						}
					}
				}
				
				switch (pos) {
					case 1:
						mPlayerOne.setText(name);
						break;
					case 2:
						mPlayerTwo.setText(name);
						break;
					case 3:
						mPlayerThree.setText(name);
						break;
					case 4:
						mPlayerFour.setText(name);
						break;
				}
				Log.i(TAG, "Added saved player " + name + ", as player # " + pos);
				act.refreshUI(this);
			} else
				Toast.makeText(getActivity(), "All player positions already full", Toast.LENGTH_LONG).show();
		} 
	}
}
