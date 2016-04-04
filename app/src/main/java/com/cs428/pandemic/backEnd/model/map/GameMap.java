package com.cs428.pandemic.backEnd.model.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameMap implements IGameMap
{

	private HashMap<String, ICity> cities;
	private int research_stations_remaining;
	private final int STARTING_RESEARCH_STATION_COUNT = 6;
	
	public GameMap(){

		this.cities = new HashMap<String, ICity>();
		this.research_stations_remaining = 6;
	}

	public GameMap(HashMap<String, ICity> c){

		this.cities = c;

		// Double check starting number
		this.research_stations_remaining = 6;
	}

	public ICity getCity(String city_name){

		return cities.get(city_name);
	}


	public void removeResearchStation(String cityName){

		if (this.cities.get(cityName).hasResearchStation()) {

			this.cities.get(cityName).removeResearchStation();
			research_stations_remaining++;
		}
	}


	@Override
	public int getRemainingResearchStationCount(){

		return research_stations_remaining;
	}

	public boolean placeResearchStation(String cityName) {
		
		boolean result = false;
		
		if(research_stations_remaining > 0){
			research_stations_remaining--;
			this.cities.get(cityName).addResearchStation();
			result = true;
		}
		
		return result;
	}

	@Override // TODO Write unit tests for this
	public List<String> getResearchStationLocations() {
		List<String> researchStationLocations = new ArrayList<String>();
		
		for(Map.Entry<String, ICity> cityEntry : cities.entrySet()){
			if(cityEntry.getValue().hasResearchStation()){
				researchStationLocations.add(cityEntry.getKey());
			}
		}
		
		return researchStationLocations;
	}

	@Override
	public int getNumberOfResearchStationLocations() {
		return STARTING_RESEARCH_STATION_COUNT - research_stations_remaining;
	}

	@Override
	public List<String> getAllOtherLocations(String location) {
		List<String> allOtherLocations = new ArrayList<String>();
		
		for(Map.Entry<String, ICity> city : cities.entrySet()){
			String cityName = city.getKey();
			if(!location.equals(cityName)){
				allOtherLocations.add(cityName);
			}
		}
		
		return allOtherLocations;
	}
}