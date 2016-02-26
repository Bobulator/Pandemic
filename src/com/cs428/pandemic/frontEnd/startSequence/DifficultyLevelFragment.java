package com.cs428.pandemic.frontEnd.startSequence;

import com.cs428.pandemic.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class DifficultyLevelFragment extends Fragment {

	private final String NORMAL = "Normal";
	private final String HARD = "Hard";
	private final String INSANE = "Insanity";
	private Button mNormalButton;
	private Button mHardButton;
	private Button mInsaneButton;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);	// I don't want an options menu on this fragment
    }
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
            Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_difficulty, parent, false);
		
		mNormalButton = (Button)v.findViewById(R.id.normalDifficultyButton);
		mNormalButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setDifficulty(NORMAL);
			}
		});
		
		mHardButton = (Button)v.findViewById(R.id.hardDifficultyButton);
		mHardButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setDifficulty(HARD);
			}
		});
		
		mInsaneButton = (Button)v.findViewById(R.id.insaneDifficultyButton);
		mInsaneButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setDifficulty(INSANE);
			}
		});
		
		return v;
	}
	
	private void setDifficulty(String diff) {
		MainMenuActivity act = (MainMenuActivity) getActivity();
		act.setDifficulty(diff);
		act.replaceFragment(act.GAMEOVERVIEW);
	}
	
}
