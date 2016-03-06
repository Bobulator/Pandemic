package com.cs428.pandemic.frontEnd.startSequence;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import android.content.Context;

public class JSONSerializer {

	private Context mContext;
	private String mSavedPlayersFilename;
	
	public JSONSerializer(Context c, String file) {
		mContext = c;
		mSavedPlayersFilename = file;
	}
	
	public void savePlayerList(ArrayList<String> players) throws JSONException, IOException {
		JSONArray array = new JSONArray();
		for (String p : players)
			array.put(p);
		
		Writer writer = null;
		try {
			OutputStream out = mContext.openFileOutput(mSavedPlayersFilename, Context.MODE_PRIVATE);
			writer = new OutputStreamWriter(out);
			writer.write(array.toString());
		} finally {
			if (writer != null)
				writer.close();
		}
	}
	
	public ArrayList<String> loadPlayerList() throws JSONException, IOException {
		ArrayList<String> players = new ArrayList<String>();
		BufferedReader reader = null;
		try {
			InputStream in = mContext.openFileInput(mSavedPlayersFilename);
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
		} finally {
			if (reader != null)
				reader.close();
		}
		return players;
	}
	
}
