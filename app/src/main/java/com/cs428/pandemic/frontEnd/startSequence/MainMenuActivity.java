package com.cs428.pandemic.frontEnd.startSequence;

import java.lang.reflect.Array;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.cs428.pandemic.R;
import com.cs428.pandemic.frontEnd.gamePlay.GamePlayActivity;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

public class MainMenuActivity extends Activity {

    public static final String EXTRA_PLAYERS = "players";
    public static final String EXTRA_DIFFICULTY = "difficulty";

	public static final String RULES = "RulesFragment";
	public static final String NUMBERPLAYERS = "NumberOfPlayersFragment";
	public static final String PLAYERNAMES = "PlayerNamesFragment";
	public static final String DIFFICULTY = "DifficultyLevelFragment";
	public static final String GAMEOVERVIEW = "GameOverviewFragment";

	private static final String PLAYER_LIST_FILENAME = "Players.json";

	private int numberOfPlayers = 0;
	private ArrayList<String> mCurrentPlayersList;
	private ArrayList<String> mSavedPlayersList;
	private String difficultyLevel;
	private JSONSerializer serializer;
	
	@SuppressLint("InlinedApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		serializer = new JSONSerializer();

		if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
        	View decorView = getWindow().getDecorView();
        	int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        	decorView.setSystemUiVisibility(uiOptions);
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
		// updating the savedPlayerList
		for (int i = 0; i < mCurrentPlayersList.size(); i++)
			addSavedPlayer(mCurrentPlayersList.get(i));
		
		// saving the updated savedPlayerList on background thread
		AsyncTask<Activity, Void, Void> savePlayerList = new AsyncTask<Activity, Void, Void>() {
			protected Void doInBackground(Activity... params) {
				for (Activity a : params) {
					try {
						serializer.savePlayerList();
					} catch (IOException e) {
						// do nothing
					}
				}
				return null;
			}
		};
		savePlayerList.execute(this);

		Intent intent = new Intent(this, GamePlayActivity.class);
		intent.putStringArrayListExtra(EXTRA_PLAYERS, mCurrentPlayersList);
		intent.putExtra(EXTRA_DIFFICULTY, difficultyLevel);
		startActivity(intent);
		this.finish();
	}
	
	public int getNumberPlayers() {
		return numberOfPlayers;
	}
	
	public void setNumberPlayers(int num) {
		numberOfPlayers = num;
	}
	
	public ArrayList<String> getmCurrentPlayersList() {
		return mCurrentPlayersList;
	}
	
	public void setmCurrentPlayersList(ArrayList<String> players) {
		mCurrentPlayersList = players;
	}
	
	public String getDifficulty() {
		return difficultyLevel;
	}
	
	public void setDifficulty(String difficulty) {
		difficultyLevel = difficulty;
	}

	public ArrayList<String> getSavedPlayerList() {
		if (mSavedPlayersList == null) {
			try {
				mSavedPlayersList = serializer.loadPlayerList();
			} catch (IOException e) {
				mSavedPlayersList = new ArrayList<>();
			}
		}
		return mSavedPlayersList;
	}

	public void addSavedPlayer(String player) {
		if (!mSavedPlayersList.contains(player))
			mSavedPlayersList.add(player);
	}

	public void removeSavedPlayer(String player) {
		if (mSavedPlayersList.contains(player))
			mSavedPlayersList.remove(player);
	}

	public void saveSavedPlayerList() {
		try {
			serializer.savePlayerList();
		} catch (IOException e) {
			// do nothing
		}
	}

	private class JSONSerializer {

		public JSONSerializer() {
		}

		public void savePlayerList() throws IOException {
			JSONArray array = new JSONArray();
			for (String p : mSavedPlayersList)
				array.put(p);

			Writer writer = null;
			try {
				OutputStream out = MainMenuActivity.this.openFileOutput(PLAYER_LIST_FILENAME, Context.MODE_PRIVATE);
				writer = new OutputStreamWriter(out);
				writer.write(array.toString());
			} catch (Exception e) {
				// do nothing
			} finally {
				if (writer != null)
					writer.close();
			}
		}

		public ArrayList<String> loadPlayerList() throws IOException {
			ArrayList<String> players = new ArrayList<>();
			BufferedReader reader = null;
			try {
				InputStream in = MainMenuActivity.this.openFileInput(PLAYER_LIST_FILENAME);
				reader = new BufferedReader(new InputStreamReader(in));
				StringBuilder jsonString = new StringBuilder();
				String line = null;
				while ((line = reader.readLine()) != null)
					jsonString.append(line);

				JSONArray array = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
				for (int i = 0; i < array.length(); i++)
					players.add((String) array.get(i));
			} catch (FileNotFoundException e) {
				// Ignore this one; it happens first time starting application
			} catch (JSONException e) {

			} finally {
				if (reader != null)
					reader.close();
			}
			return players;
		}
	}
}
