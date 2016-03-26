package com.cs428.pandemic.frontEnd.dataTransferObjects;

import com.cs428.pandemic.frontEnd.enums.DiseaseColor;

/**
 * Created by Chad Bacon on 2/16/2016.
 *
 * This class will contain all of the information needed for the UI to draw disease information to
 * the HUD. Note that in the case of an exterminated disease the UI will simply erase data
 * associated with that disease from the HUD since that disease can no longer be significant to
 * gameplay.
 */
public class UI_Disease {

    /**
     * The color of the disease.
     */
    private DiseaseColor diseaseColor;

    /**
     * Indicates whether or not the disease has been cured.
     */
    private boolean cured = false;

    /**
     * The number of unplaced disease cubes of the given color.
     */
    private int count = 24;

    /**
     * If this constructor is used, cured will default to false and count will default to 24.
     * @param diseaseColor The color to be associated with this disease.
     */
    public UI_Disease(DiseaseColor diseaseColor) {
        this.diseaseColor = diseaseColor;
    }

    /**
     *
     * @param diseaseColor The color to be associated with this disease.
     * @param cured Whether or not this disease is cured. (false if no, true if yes)
     * @param count The number of unplaced disease cubes of this disease's color.
     */
    public UI_Disease(DiseaseColor diseaseColor, boolean cured, int count) {
        this.diseaseColor = diseaseColor;
        this.cured = cured;
        this.count = count;
    }

    /**
     *
     * @return The color of this disease.
     */
    public DiseaseColor getDiseaseColor() {
        return diseaseColor;
    }

    /**
     *
     * @return A boolean indicating whether or not this disease has been cured.
     * (false if no, true if yes)
     */
    public boolean isCured() {
        return cured;
    }

    /**
     *
     * @return The number of unplaced disease cubes of this disease's color.
     */
    public int getCount() {
        return count;
    }
}