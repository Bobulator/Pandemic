package com.cs428.pandemic.backEnd.model.prevData;

import com.cs428.pandemic.backEnd.model.prevData.CityName;
import com.cs428.pandemic.backEnd.model.prevData.PlayerCardType;

/**
 * Created by brandt on 5/13/15.
 */
public class CityCard implements IPlayerCard {

    CityName city;
    int population;

    public CityCard(CityName city, int population) {
        this.city = city;
        this.population = population;
    }

    public CityName getCity() {
        return city;
    }

    public int getPopulation() {
        return population * 1000;
    }

    @Override
    public PlayerCardType getType() {
        return PlayerCardType.CITY;
    }
}
