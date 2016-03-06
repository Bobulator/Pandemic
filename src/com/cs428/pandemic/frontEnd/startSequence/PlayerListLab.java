package com.cs428.pandemic.frontEnd.startSequence;

import java.util.ArrayList;

import android.content.Context;

public class PlayerListLab {
//	private String TAG = "PlayerListLab";
	private static final String PLAYER_LIST_FILENAME = "Players.json";
	
	private ArrayList<String> mPlayers;
	private JSONSerializer mSerializer;
	
	private static PlayerListLab sPlayerListLab;
	private Context mAppContext;
	
	private PlayerListLab(Context appContext) {
		mAppContext = appContext;
		mSerializer = new JSONSerializer(mAppContext, PLAYER_LIST_FILENAME);
		
		try {
			mPlayers = mSerializer.loadPlayerList();
		} catch (Exception e) {
			mPlayers = new ArrayList<String>();
		}
	}
	
	public static PlayerListLab get(Context c) {
		if (sPlayerListLab == null)
			sPlayerListLab = new PlayerListLab(c.getApplicationContext());
		return sPlayerListLab;
	}
	
	public boolean savePlayerList() {
		try {
			mSerializer.savePlayerList(mPlayers);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public void addPlayer(String player) {
		if (!mPlayers.contains(player))
			mPlayers.add(player);
	}
	
	public void removePlayer(String player) {
		if (mPlayers.contains(player))
			mPlayers.remove(player);
	}
	
	public ArrayList<String> getPlayers() {
		return mPlayers;
	}	
}
