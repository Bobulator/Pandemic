package com.cs428.pandemic.frontEnd.gamePlay.boardFragment;

import java.util.HashMap;

/**
 * This class stores the results from the CityParser for convenient and quick access. Specifically,
 * this class stores each city mapped to their respective (relative) x and y coordinates.
 * Created by Chad on 3/30/2016.
 */
public class Cities {

    private class Coordinates {
        private float relative_x;
        private float relative_y;

        public Coordinates(float relative_x, float relative_y) {
            this.relative_x = relative_x;
            this.relative_y = relative_y;
        }

        public float getRelative_x() {
            return relative_x;
        }

        public float getRelative_y() {
            return relative_y;
        }
    }

    private HashMap<String, Coordinates> cityMap;

    public Cities() {
        cityMap = new HashMap<>();
    }

    public void addCity(String city, float rel_x, float rel_y) {
        cityMap.put(city, new Coordinates(rel_x, rel_y));
    }

    public float getRelativeX(String city) {
        return cityMap.get(city).getRelative_x();
    }

    public float getRelativeY(String city) {
        return cityMap.get(city).getRelative_y();
    }
}
