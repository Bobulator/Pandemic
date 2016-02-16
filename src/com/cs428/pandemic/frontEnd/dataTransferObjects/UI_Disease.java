package com.cs428.pandemic.frontEnd.dataTransferObjects;

import com.cs428.pandemic.frontEnd.enums.DiseaseColor;

/**
 * Created by Chad Bacon on 2/16/2016.
 * TODO: javadocs
 */
public class UI_Disease {
    private DiseaseColor diseaseColor;
    private boolean cured = false;
    private int count = 24;

    public UI_Disease(DiseaseColor diseaseColor) {
        this.diseaseColor = diseaseColor;
    }

    public UI_Disease(DiseaseColor diseaseColor, boolean cured, int count) {
        this.diseaseColor = diseaseColor;
        this.cured = cured;
        this.count = count;
    }

    public DiseaseColor getDiseaseColor() {
        return diseaseColor;
    }

    public boolean isCured() {
        return cured;
    }

    public int getCount() {
        return count;
    }
}
