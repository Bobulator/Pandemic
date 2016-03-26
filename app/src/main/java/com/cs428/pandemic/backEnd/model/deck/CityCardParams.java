/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEnd.model.deck;

import com.cs428.pandemic.backEnd.model.gamestate.DiseaseType;

/**
 *
 * @author James
 */
public class CityCardParams 
{
    private String cityName;
    private DiseaseType color;
    
    public CityCardParams(String name, DiseaseType type)
    {
        cityName = name;
        color = type;
    }

    public String getCityName() 
    {
        return cityName;
    }

    public DiseaseType getColor() 
    {
        return color;
    }
}
