package com.cs428.pandemic.frontEnd.dataTransferObjects;

import com.cs428.pandemic.frontEnd.enums.DiseaseColor;

import java.util.List;
import java.util.Map;

/**
 * Created by Chad Bacon on 2/16/2016.
 *
 * This class will contain all of the necessary data for the UI to draw cities, excluding their
 * pixel location in relation to the board image.
 */
public class UI_City {

    /**
     * The name of the city.
     */
    private String cityName;

    /**
     * The disease color associated with the city.
     */
    private DiseaseColor diseaseColor;

    /**
     * A map containing 0-4 disease colors paired with the number of disease cubes in the city
     * matching the color. For example, one pairing might be (RED, 2), indicating that there were 2
     * red disease cubes in the city. Disease colors that are currently not in the city can simply
     * be omitted from the map.
     */
    private Map<DiseaseColor, Integer> diseaseCounts;

    /**
     * A list containing the names of each city connected to this city. The UI uses this to know
     * which cities to connect on the board.
     */
    private List<String> neighbors;

    /**
     *
     * @param cityName The name of the city.
     * @param diseaseColor The disease color associated with the city.
     * @param diseaseCounts A map containing 0-4 disease colors paired with the number of disease
     *                      cubes in the city matching the color.
     * @param neighbors A list containing the names of each city connected to this city.
     */
    public UI_City(String cityName, DiseaseColor diseaseColor, Map<DiseaseColor, Integer> diseaseCounts, List<String> neighbors) {
        this.cityName = cityName;
        this.diseaseColor = diseaseColor;
        this.diseaseCounts = diseaseCounts;
        this.neighbors = neighbors;
    }

    /**
     *
     * @return The name of the associated city.
     */
    public String getCityName() {
        return cityName;
    }

    /**
     *
     * @return The disease color associated with this city.
     */
    public DiseaseColor getDiseaseColor() {
        return diseaseColor;
    }

    /**
     *
     * @return A map containing 0-4 disease colors paired with the number of disease cubes in the
     * city matching the color.
     */
    public Map<DiseaseColor, Integer> getDiseaseCounts() {
        return diseaseCounts;
    }

    /**
     *
     * @return A list containing the names of each city connected to this city.
     */
    public List<String> getNeighbors() {
        return neighbors;
    }
}
