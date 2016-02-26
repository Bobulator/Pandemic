package com.cs428.pandemic.frontEnd.startSequence;

import com.cs428.pandemic.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class NumberOfPlayersFragment extends Fragment {

	private Button mTwoPlayerButton;
	private Button mThreePlayerButton;
	private Button mFourPlayerButton;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);	// I don't want an options menu on this fragment
    }
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
            Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_number_players, parent, false);
		
		mTwoPlayerButton = (Button)v.findViewById(R.id.twoPlayerButton);
		mTwoPlayerButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				setNumber(2);
			}
		});
        
		mThreePlayerButton = (Button)v.findViewById(R.id.threePlayerButton);
		mThreePlayerButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				setNumber(3);
			}
		});
		
		mFourPlayerButton = (Button)v.findViewById(R.id.fourPlayerButton);
		mFourPlayerButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				setNumber(4);
			}
		});
		
		return v;
	}
	
	public void setNumber(int num) {
		MainMenuActivity act = (MainMenuActivity) getActivity();
		act.setNumberPlayers(num);
		act.replaceFragment(act.PLAYERNAMES);
	}
}
