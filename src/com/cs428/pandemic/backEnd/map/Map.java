package com.cs428.pandemic.backEnd.map;

import java.util.*;

public class Map
{
	private Map<String:City> cities;
	private int research_stations_remaining;

	public Map()
	{
		cities = new HashMap<String:City>();
		research_stations_remaining = 6;
	}

	public City getCity(String city_name)
	{
		return null;
	}

	public Boolean placeResearchStation()
	{
		if (research_stations_remaining > 0)
		{
			research_stations_remaining--;
			return true;
		}

		return false;
	}

	// May not need this since removed research stations 
	// will immediately be placed on the board?
	public Boolean removeResearchStation()
	{
		if (research_stations_remaining < 6)
		{
			research_stations_remaining++;
			return true;
		}

		return false;
	}

	public int getResearchStationCount()
	{
		return research_stations_remaining;
	}
}