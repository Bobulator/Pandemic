package com.cs428.pandemic.frontEnd.startSequence;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cs428.pandemic.R;

public class SavedPlayerListFragment extends DialogFragment {
	
	private String TAG = "SavedPlayerListFragment";
	public static final String EXTRA_PLAYERS = "com.cs428.pandemic.players";
	private ArrayList<String> mCurrentPlayers;
	private ArrayList<String> mSavedPlayers;
	private String mAddPlayer;
	
	public static SavedPlayerListFragment newInstance(ArrayList<String> players) {
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_PLAYERS, players);
		
		SavedPlayerListFragment fragment = new SavedPlayerListFragment();
		fragment.setArguments(args);
		return fragment;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		mAddPlayer = "";
		mCurrentPlayers = (ArrayList<String>)getArguments().getSerializable(EXTRA_PLAYERS);
		mSavedPlayers = PlayerListLab.get(getActivity()).getPlayers();
		
		View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_list_players, null);
		
		final ListView listView = (ListView)v.findViewById(android.R.id.list);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				String name = mSavedPlayers.get(position);
				if (!mCurrentPlayers.contains(name)) 
					mAddPlayer = name;
				else 
					Toast.makeText(getActivity(), "This player has already been entered, please choose another player", Toast.LENGTH_LONG).show();
			}
		});
		
		listView.setAdapter(new PlayerAdapter(mSavedPlayers));
		
		listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
		listView.setMultiChoiceModeListener(new MultiChoiceModeListener() {
			@Override
			public boolean onCreateActionMode(ActionMode mode, Menu menu) {
				MenuInflater inflater = mode.getMenuInflater();
				inflater.inflate(R.menu.player_list_delete, menu);
				return true;
			}
			@Override
			public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
				switch (item.getItemId()) {
					case R.id.menu_item_delete:
						PlayerAdapter adapter = (PlayerAdapter) listView.getAdapter();
						PlayerListLab playerLab = PlayerListLab.get(getActivity());
						for (int i = adapter.getCount() - 1; i >= 0; i--) {
							if (listView.isItemChecked(i)) 
								playerLab.removePlayer(adapter.getItem(i));
						}
						mode.finish();
						playerLab.savePlayerList();
						return true;
					default:
						return false;
				}
			}
			@Override
			public boolean onPrepareActionMode(ActionMode mode, Menu menu) {return false;}
			public void onDestroyActionMode(ActionMode mode) {}
			public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {}
		});

		int i = 0;
		for (; i < mCurrentPlayers.size(); i++) {
			if (mCurrentPlayers.get(i).equals(""))
				break;
		}

		String title = "";
		switch (i){
			case 0:
				title = getString(R.string.select_one);
				break;
			case 1:
				title = getString(R.string.select_two);
				break;
			case 2:
				title = getString(R.string.select_three);
				break;
			case 3:
				title = getString(R.string.select_four);
				break;
		}
		
		return new AlertDialog.Builder(getActivity())
			.setView(v).setTitle(title)
			.setNegativeButton(android.R.string.cancel,
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							sendResult(Activity.RESULT_CANCELED, null);
						}
					}).setPositiveButton(android.R.string.ok, 
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog, int which) {
									if (!mAddPlayer.equals(""))
										sendResult(Activity.RESULT_OK, mAddPlayer);
									else
										sendResult(Activity.RESULT_CANCELED, null);
								}
							})
			.create();
	}
	
	private void sendResult(int result, String playerAdd) {
		if(getTargetFragment() == null) 
			return;
		
		Intent i = new Intent();
		switch (result) {
			case Activity.RESULT_OK:
				i.putExtra(EXTRA_PLAYERS, playerAdd);
				getTargetFragment().onActivityResult(getTargetRequestCode(), result, i);
				break;
			case Activity.RESULT_CANCELED:
				getTargetFragment().onActivityResult(getTargetRequestCode(), result, i);
				break;
		}
	}
	
	private class PlayerAdapter extends ArrayAdapter<String> {
		
		public PlayerAdapter(ArrayList<String> players) {
			super(getActivity(), 0, players);
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) 
				convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_player, null);
			
			String name = getItem(position);
			TextView nameTextView = (TextView) convertView.findViewById(R.id.player_list_item_name);
			nameTextView.setText(name);
			
			TextView numberTextView = (TextView) convertView.findViewById(R.id.player_list_item_number);
			if (mCurrentPlayers.contains(name)) {
				int num = mCurrentPlayers.indexOf(name) + 1;
				String text = "P" + num;
				numberTextView.setText(text);
				Log.d(TAG, "Player Number " + num + " should be showing next to " + name);
			} else 
				numberTextView.setText("");
			
			return convertView;
		}
	}
	
}
