package com.cs428.pandemic.frontEnd.startSequence;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.cs428.pandemic.R;

public class MainMenuFragment extends Fragment {
	
	private Button mPlayButton;
	private Button mRulesButton;
	private Button mSettingsButton;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);	// I don't want an options menu on this fragment
    }
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
            Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_main_menu, parent, false);
		
		mPlayButton = (Button)v.findViewById(R.id.newGameButton);
		mPlayButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				((MainMenuActivity) getActivity()).replaceFragment(MainMenuActivity.NUMBERPLAYERS);
			}
		});
        
		mRulesButton = (Button)v.findViewById(R.id.gameRulesButton);
		mRulesButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				((MainMenuActivity) getActivity()).replaceFragment(MainMenuActivity.RULES);
			}
		});
		
		mSettingsButton = (Button)v.findViewById(R.id.settingsButton);
		mSettingsButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// show settings fragment
			}
		});
		
		return v;
	}	
}
