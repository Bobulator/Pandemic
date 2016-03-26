/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEnd.model.deck;

import com.cs428.pandemic.backEnd.command.ICommand;
import com.cs428.pandemic.backEnd.model.gamestate.DiseaseType;

/**
 * Card within the Infection Deck
 * @author James
 */
public interface IInfectionCard extends ICard
{
    /**
     * The city name
     * @return 
     */
    String getName();
    
    /**
     * The color of the city
     * @return 
     */
    DiseaseType getColor();
}
