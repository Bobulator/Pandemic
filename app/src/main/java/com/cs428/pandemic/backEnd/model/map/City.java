package com.cs428.pandemic.backEnd.model.map;

import com.cs428.pandemic.backEnd.model.gamestate.DiseaseType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class City implements ICity{

	private String name;
	private boolean research_station;

	private Map<DiseaseType, Integer> protection_levels;

	private boolean has_outbroken;
	private DiseaseType color;

	private Map<String, ICity> adjacent_cities;
	private Map<DiseaseType, Integer> disease_cubes;


	// Depending on persistence, may need a constructor with all parameters
	public City(String name, DiseaseType color, Map<String, ICity> adjacent_cities){

		this.name = name;
		this.color = color;

		if (name.equals("Atlanta")){

			research_station = true;
		}
		else{

			research_station = false;
		}

		initialize_protections();
		clearOutbreak();
		this.adjacent_cities = adjacent_cities;
		initialize_diseases();

	}

	private void initialize_protections(){

		this.protection_levels = new HashMap<DiseaseType, Integer>();

		for (DiseaseType d : DiseaseType.values()){

			protection_levels.put(d, 0);
		}
	}

	private void initialize_diseases(){

		this.disease_cubes = new HashMap<DiseaseType, Integer>();

		for (DiseaseType d : DiseaseType.values()){

			disease_cubes.put(d, 0);
		}
	}
	
	public String getName(){

		return name;
	}

	public DiseaseType getColor(){

		return color;
	}

	public boolean isAdjacent(String city_name){

		return adjacent_cities.containsKey(city_name);
	}

	public void increaseAllProtections(){

		for (DiseaseType d : DiseaseType.values()){

			increaseProtection(d);
		}
	}

	public void decreaseAllProtections(){

		for (DiseaseType d : DiseaseType.values()){

			decreaseProtection(d);
		}
	}

	public void increaseProtection(DiseaseType t){

		protection_levels.put(t, protection_levels.get(t) + 1);
	}

	public void decreaseProtection(DiseaseType t){

		if (protection_levels.get(t) > 0){

			protection_levels.put(t, protection_levels.get(t) - 1);
		}

	}

	public boolean isProtected(DiseaseType t){

		return protection_levels.get(t) > 0;
	}

	public boolean hasResearchStation(){

		return research_station;
	}


	public void addResearchStation(){

		research_station = true;
	}

	public void removeResearchStation(){

		research_station = false;
	}


	// Add a overloaded versions with the numbers to add/remove?

	public boolean addDiseaseCube(DiseaseType t){

		if (!isProtected(t)){

			disease_cubes.put(t, disease_cubes.get(t) + 1);
			return disease_cubes.get(t) == 3;
		}

		return false;
	}

	
	public boolean removeDiseaseCube(DiseaseType t){

		if (disease_cubes.get(t) > 0){

			disease_cubes.put(t, disease_cubes.get(t) - 1);
			return true;
		}

		return false;
	}

	
	public boolean hasOutbroken(){

		return has_outbroken;
	}

	
	public void setOutbreak(){

		has_outbroken = true;
	}

	
	public void clearOutbreak(){

		has_outbroken = false;
	}

	@Override
	public List<ICity> getAdjacentCities() {
		List<ICity> adjacentCities = new ArrayList<ICity>();
		
		for(Map.Entry<String, ICity> iCity : this.adjacent_cities.entrySet()){
			adjacentCities.add(iCity.getValue());
		}
		
		return adjacentCities;
	}

	@Override
	public List<String> getAdjacentCityNames() {
		List<String> adjacentCityNames = new ArrayList<String>();
		
		for(Map.Entry<String, ICity> iCity : this.adjacent_cities.entrySet()){
			adjacentCityNames.add(iCity.getKey());
		}
		
		return adjacentCityNames;
	}

	@Override
	public int getNumberOfAdjacentCities() {
		return this.adjacent_cities.size();
	}

	@Override
	public boolean hasDiseaseCubes() {
		
		for(Map.Entry<DiseaseType, Integer> entry : disease_cubes.entrySet()){
			if(entry.getValue() > 0){
				return true;
			}
		}
		
		return false;
	}
}
