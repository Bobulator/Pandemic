/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEnd.model.deck;

import com.cs428.pandemic.backEnd.model.gamestate.DiseaseType;

/**
 * Responsible for creating all cards
 * @author James
 */
public interface ICardFactory 
{
    /**
     * Creates the params object necessary for generating the city card
     * @param name of city
     * @param type color of disease {BLACK, RED, YELLOW, BLUE} 
     * @return the parameters generated
     */
    CityCardParams createCityParams(String name, DiseaseType type);

    /**
     * Creates a player city card
     * @param params needed for generating the city
     * @return the generated player card
     */
    IPlayerCard createPlayerCityCard(CityCardParams params);

    /**
     * Creates a player city card
     * @param city name (E.G. Washington D.C.)
     * @param type color of disease {BLACK, RED, YELLOW, BLUE}
     * @return the generated player card
     */
    IPlayerCard createPlayerCityCard(String city, DiseaseType type);
    
    /**
     * Creates a player epidemic card
     * @return the generated player card
     */
    IPlayerCard createPlayerEpidemicCard();
    
    /**
     * Generates the params object for specifying the values present in the event card
     * @param name of the event
     * @param description of the event
     * @return the generated event parameters
     */
    EventCardParams createEventCardParams(String name, String description);
    
    /**
     * Creates a player event card
     * @param params values that specify the data for the event
     * @return the generated event card
     */
    IPlayerCard createPlayerEventCard(EventCardParams params);
    
    /**
     * Creates a player event card
     * @param name of the event card (E.G. One Quiet Night)
     * @param description of the event card (E.G. Skip the next draw infect step)
     * @return the generated player card
     */
    IPlayerCard createPlayerEventCard(String name, String description);
    
    /**
     * Creates an infection card
     * @param parameters values that specify the Infection Creation Details
     * @return the generated infection card
     */
    IInfectionCard createInfectCard(CityCardParams parameters);

    /**
     * Creates an infection card
     * @param city name (E.G. Washington D.C.)
     * @param type color of disease {BLACK, RED, YELLOW, BLUE}
     * @return the generated infection card
     */
    IInfectionCard createInfectCard(String city, DiseaseType type);
}
