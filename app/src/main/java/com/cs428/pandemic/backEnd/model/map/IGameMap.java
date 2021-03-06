package com.cs428.pandemic.backEnd.model.map;

import java.util.List;

public interface IGameMap
{
	
	/**
	* Gets the City object corresponding to the given city_name
	* @param city_name the name of the city of retrieve
	*/
	ICity getCity(String city_name);

	/**
	 * Removes a research station from the pile to be placed on a city
	 * @param cityName The name of the city where the research station is to be placed
	 * @return true if there are enough research stations left in the game to place, otherwise false 
	 */
	boolean placeResearchStation(String cityName);

	// May not need this since removed research stations 
	// will immediately be placed on the board?
	/**
	*
	*/
	//Boolean removeResearchStation();

	/**
	* @return the number of unplaced research stations
	*/
	int getRemainingResearchStationCount();

	/**
	 * Gathers all of the city names (if any) with a research station in a list, and returns that list
	 * @return a list of strings representing the names of cities with a research location
	 */
	List<String> getResearchStationLocations();
	
	/**
	 * Returns the number of research station locations currently on the board 
	 * @return the number of research station locations currently on the board
	 */
	int getNumberOfResearchStationLocations();
	
	/**
	 * Returns a list of all of the locations, excluding the provided location
	 * @param location The location to be excluded
	 * @return a list of all of the locations, excluding the provided location
	 */
	List<String> getAllOtherLocations(String location); 
	
}