package com.cs428.pandemic.backEnd.map;

import java.util.*;

public class City
{
	private String name;
	private Map<String:City> adjacent_cities;
	private int protection_level;
	private Boolean research_station;

	// What is this enum?!
	private Map<DiseaseType:Integer> disease_cubes;

	private Boolean has_outbroken;

	// May need to pass in all parameters if game persistence is implemented
	public City(String name, Map<String:City> adjacent_cities)
	{
		initializeDiseases();

		this.name = name;
		this.adjacent_cities = adjacent_cities;
		this.protection_level = 0;
		this.research_station = false;
		this.has_outbroken = false;
	}

	private void initializeDiseases()
	{
		disease_cubes = new HashMap<DiseaseType:Integer>();
		// Iterate over disease enum and set corresponding value in map to 0
	}

	public String getName()
	{
		return this.name;
	}

	public Boolean isAdjacent(String city_name)
	{
		// Not positive on this syntax
		// return this.adjacent_cities.contains(city_name) 
		return false;
	}

	public void increaseProtection()
	{
		this.protection_level++;
	}

	public void decreaseProtection()
	{
		if (this.protection_level > 0)
		{
			this.protection_level--;
		}
	}

	public Boolean isProtected()
	{
		return (this.protection_level > 0);
	}

	public Boolean hasResearchStation()
	{
		return this.research_station;
	}

	public void addResearchStation()
	{
		this.research_station = true;
	}

	public void removeResearchStation()
	{
		this.research_station = false;
	}

	public Boolean addDiseaseCube(DiseaseType t)
	{
		// this.disease_cubes[t] += 1
		// check for chain reaction
		return false;
	}

	public Boolean removeDiseaseCube(DiseaseType t)
	{
		// this.disease_cubes[t] -= 1
		return false;
	}

	public Boolean hasOutbroken()
	{
		return this.has_outbroken;
	}

	public void setOutbreak()
	{
		this.has_outbroken = true;
	}

	public void clearOutbreak()
	{
		this.has_outbroken = false;
	}
}