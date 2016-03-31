package com.cs428.pandemic.backEnd.model.prevData;

import com.cs428.pandemic.backEnd.model.gamestate.DiseaseType;
import java.util.HashMap;
import java.util.HashSet;

import com.cs428.pandemic.backEnd.model.prevData.CityName;

/**
 * Created by brandt on 5/14/15.
 */
public class City {

    private String name;
    private int population;

    private DiseaseType type;
    private HashMap<DiseaseType, Integer> infectionLevel = new HashMap<>();
    private HashSet<City> neighbors = new HashSet<>();
    private HashSet<CityName> neighborNames = new HashSet<>();

    public City(DiseaseType type) {
        this.name = "";
        this.population = 0;
        this.type = type;
    }
    
    public City(String name, int population, DiseaseType type) {
        this.name = name;
        this.population = population;
        this.type = type;
    }

    public void addNeighbor(City city) {
        neighbors.add(city);
    }

    public void addNeighbor(CityName city) {
        neighborNames.add(city);
    }

    public int getDiseaseCount(DiseaseType type) {
        return infectionLevel.get(type);
    }

    public HashSet<CityName> getNeighbors() {
        return neighborNames;
    }

    public void addDisease(DiseaseType type) {
        infectionLevel.put(type, infectionLevel.get(type) + 1);
    }

    public DiseaseType getDiseaseType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
}
