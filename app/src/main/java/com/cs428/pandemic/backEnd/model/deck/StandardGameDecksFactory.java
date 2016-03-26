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
public class StandardGameDecksFactory implements IGameDecksFactory, ICardFactory
{
    ICardFactory cardFact;
    
    public StandardGameDecksFactory()
    {
        cardFact = new CardFactory();
    }

    @Override
    public IGameDecksHolder createGameDeck() 
    {
        return new GameDecksHolder();
    }

    @Override
    public IPlayerCard createPlayerCityCard(String city, DiseaseType type)
    {
        return cardFact.createPlayerCityCard(city, type);
    }

    @Override
    public IPlayerCard createPlayerEpidemicCard() 
    {
        return cardFact.createPlayerEpidemicCard();
    }

    @Override
    public IPlayerCard createPlayerEventCard(String name, String description) 
    {
        return cardFact.createPlayerEventCard(name, description);
    }

    @Override
    public CityCardParams createCityParams(String name, DiseaseType type) 
    {
        return cardFact.createCityParams(name, type);
    }

    @Override
    public IPlayerCard createPlayerCityCard(CityCardParams params) 
    {
        return cardFact.createPlayerCityCard(params);
    }

    @Override
    public EventCardParams createEventCardParams(String name, String description) 
    {
        return cardFact.createEventCardParams(name, description);
    }

    @Override
    public IPlayerCard createPlayerEventCard(EventCardParams params) 
    {
        return cardFact.createPlayerEventCard(params);
    }

    @Override
    public IInfectionCard createInfectCard(CityCardParams parameters) 
    {
        return cardFact.createInfectCard(parameters);
    }

    @Override
    public IInfectionCard createInfectCard(String city, DiseaseType type) 
    {
        return cardFact.createInfectCard(city, type);
    }
}
