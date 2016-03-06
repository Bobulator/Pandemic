package com.cs428.pandemic.frontEnd.startSequence;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.cs428.pandemic.R;
import com.cs428.pandemic.frontEnd.gamePlay.GamePlayActivity;

public class MainMenuActivity extends Activity {

	public static final String RULES = "RulesFragment";
	public static final String NUMBERPLAYERS = "NumberOfPlayersFragment";
	public static final String PLAYERNAMES = "PlayerNamesFragment";
	public static final String DIFFICULTY = "DifficultyLevelFragment";
	public static final String GAMEOVERVIEW = "GameOverviewFragment";
	private int numberOfPlayers = 0;
	private ArrayList<String> playerList;
	private String difficultyLevel;
	
	@SuppressLint("InlinedApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
        	View decorView = getWindow().getDecorView();
        	int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        	decorView.setSystemUiVisibility(uiOptions);
        	ActionBar actionBar = getActionBar();
        	actionBar.hide();
        }
		
		
		setContentView(R.layout.activity_main_menu);
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new MainMenuFragment()).commit();
		}
	}
	
	public void replaceFragment(String className) {
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		switch (className) {
			case NUMBERPLAYERS:
				transaction.replace(R.id.container, new NumberOfPlayersFragment());
				break;
			case PLAYERNAMES:
				transaction.replace(R.id.container, new PlayerNamesFragment());
				break;
			case DIFFICULTY:
				transaction.replace(R.id.container, new DifficultyLevelFragment());
				break;
			case RULES:
				transaction.replace(R.id.container, new RulesFragment());
				break;
			case GAMEOVERVIEW:
				transaction.replace(R.id.container, new GameOverviewFragment());
				break;
			default:
				return;
		}
		
		transaction.addToBackStack(null);
		transaction.commit();
	}
	
	public void refreshUI(Fragment fragment) {
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.container, fragment);
		transaction.commit();
	}
	
	public void startGameActivity() {
		PlayerListLab playerLab = PlayerListLab.get(this);
		// updating the savedPlayerList
		for (int i = 0; i < playerList.size(); i++)
			playerLab.addPlayer(playerList.get(i));
		
		// saving the updated savedPlayerList on background thread
		AsyncTask<Activity, Void, Void> savePlayerList = new AsyncTask<Activity, Void, Void>() {
			protected Void doInBackground(Activity... params) {
				for (Activity a : params) {
					PlayerListLab.get(a).savePlayerList();
				}
				return null;
			}
		};
		savePlayerList.execute(this);
		
		Intent i = new Intent(this, GamePlayActivity.class);
		startActivity(i);
	}
	
	public int getNumberPlayers() {
		return numberOfPlayers;
	}
	
	public void setNumberPlayers(int num) {
		numberOfPlayers = num;
	}
	
	public ArrayList<String> getPlayerList() {
		return playerList;
	}
	
	public void setPlayerList(ArrayList<String> players) {
		playerList = players;
	}
	
	public String getDifficulty() {
		return difficultyLevel;
	}
	
	public void setDifficulty(String difficulty) {
		difficultyLevel = difficulty;
	}
	
}
