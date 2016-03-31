package com.cs428.pandemic.backEnd.model.prevData;

import com.cs428.pandemic.backEnd.model.prevData.CityName;

/**
 * Created by brandt on 5/13/15.
 */
public class InfectionCard {

    CityName city;

    public InfectionCard(CityName city) {
        this.city = city;
    }

    public CityName getCity() {
        return city;
    }
}
