package com.cs428.pandemic.frontEnd.startSequence;

import com.cs428.pandemic.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RulesFragment extends Fragment {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);	// I don't want an options menu on this fragment
    }
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
            Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_rules, parent, false);
		
		TextView text = (TextView)v.findViewById(R.id.rules_text);
		text.setText("Game Rules will go here");
		
		return v;
	}
	
}
