package com.cs428.pandemic.frontEnd.dataTransferObjects;

import com.cs428.pandemic.frontEnd.enums.DiseaseColor;

import java.util.List;
import java.util.Map;

/**
 * Created by Chad Bacon on 2/16/2016.
 * TODO: javadocs
 */
public class UI_City {
    private String cityName;
    private DiseaseColor diseaseColor;
    private Map<DiseaseColor, Integer> diseaseCounts;
    private List<String> neighbors;

    public UI_City(String cityName, DiseaseColor diseaseColor, Map<DiseaseColor, Integer> diseaseCounts, List<String> neighbors) {
        this.cityName = cityName;
        this.diseaseColor = diseaseColor;
        this.diseaseCounts = diseaseCounts;
        this.neighbors = neighbors;
    }

    public String getCityName() {
        return cityName;
    }

    public DiseaseColor getDiseaseColor() {
        return diseaseColor;
    }

    public Map<DiseaseColor, Integer> getDiseaseCounts() {
        return diseaseCounts;
    }

    public List<String> getNeighbors() {
        return neighbors;
    }
}
