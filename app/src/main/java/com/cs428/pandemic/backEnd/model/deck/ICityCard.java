/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEnd.model.deck;

import com.cs428.pandemic.backEnd.model.gamestate.DiseaseType;

/**
 * A subpart of the player card
 * @author James
 */
public interface ICityCard extends IPlayerCard
{
    /**
     * Returns the name of the city
     * @return 
     */
    String getName();

    /**
     * Returns the disease type associated with the city
     * @return The disease type as on of the DiseaseType enums
     */
    DiseaseType getDiseaseType();

    // TODO:
}
