package com.cs428.pandemic.backEnd.model.map;

import java.util.List;

import com.cs428.pandemic.backEnd.model.gamestate.DiseaseType;

public interface ICity{

	/**
	 * @return the name of the city
	 */
	String getName();

	/**
	 * Determines whether the given city is directly connected to this city
	 * @param city_name the name of the city to check
	 */
	boolean isAdjacent(String city_name);

	/**
	 * Increases the protection level of this city against the given disease type
	 * @param t The diseasetype the city is being protected from
	 */
	void increaseProtection(DiseaseType t);

	/**
	 * Decreases the protection level of this city against the given disease type
	 * @param t The diseasetype to decrease protection from
	 */
	void decreaseProtection(DiseaseType t);

	/**
	 * Increases the protection level of this city for all disease colors
	 */
	void increaseAllProtections();

	/**
	 * Decreases the protection level of this city for all disease colors
	 */
	void decreaseAllProtections();

	/**
	 * Determines whether disease cubes of the given color should be placed in this city during infections and outbreaks
	 * @param t The DiseaseType being checked
	 * @return true if this city's protection level is above a predetermined threshold, otherwise false
	 */
	boolean isProtected(DiseaseType t);

	/**
	 * @return true if the city has a research station built in it, otherwise false
	 */
	boolean hasResearchStation();

	/**
	 * Places a research station in this city
	 */
	void addResearchStation();

	/**
	 * Removes a research station from this city
	 */
	void removeResearchStation();

	/**
	 * Places a new disease cube of the given type on this city
	 * Is this what this boolean is for?!
	 * @return true if adding the new disease cube triggers an outbreak, otherwise false
	 */
	boolean addDiseaseCube(DiseaseType t);

	/**
	 * Removes a disease cube of the given type from this city
	 * If removeAll is true, removes all disease cubes of the given type
	 * @return the number of disease cubes that was/were/is/are successfully removed
	 */
	int removeDiseaseCube(DiseaseType t, boolean removeAll);

	/**
	 * @return true if this city has previously outbroken on this turn, otherwise false
	 */
	boolean hasOutbroken();

	/**
	 * Signals that this city has outbroken in the current turn
	 */
	void setOutbreak();

	/**
	 * Signals that this city has not outbroken yet in the current turn
	 */
	void clearOutbreak();

	/**
	 * @return a list of ICIties that are adjacent to this city
	 */
	List<ICity> getAdjacentCities();

	/**
	 * @return a list of strings representing the names of the cities adjacent to this city
	 */
	List<String> getAdjacentCityNames();

	/**
	 * @return the number of cities adjacent to this city
	 */
	int getNumberOfAdjacentCities();

	/**
	 * @return true if this city has any disease cubes on it, false otherwise
	 */
	boolean hasDiseaseCubes();

	/**
	 * @return A list of the names of diseases that have at least 1 cube on this city
	 */
	List<String> getPresentDiseases();
}
