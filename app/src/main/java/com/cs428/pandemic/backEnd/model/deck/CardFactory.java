/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEnd.model.deck;

import com.cs428.pandemic.backEnd.model.gamestate.DiseaseType;

/**
 * Concrete implementation of the ICardFactory interface
 * @author James
 */
public class CardFactory implements ICardFactory
{
    @Override
    public IPlayerCard createPlayerCityCard(final String city, final DiseaseType type)
    {
        return new ICityCard()
        {
            @Override
            public String getName() 
            {
                return city;
            }

            @Override
            public DiseaseType getDiseaseType() {
                return type;
            }

            @Override
            public PlayerCardType getType() 
            {
                return PlayerCardType.CITY;
            }

            @Override
            public boolean equalsIdentifier(String identifier) 
            {
                return city.equals(identifier);
            }
        };
    }

    @Override
    public IPlayerCard createPlayerEpidemicCard() 
    {
        return new IEpidemicCard()
        {
            @Override
            public PlayerCardType getType() 
            {
                return PlayerCardType.EPIDEMIC;
            }

            @Override
            public boolean equalsIdentifier(String identifier) 
            {
                return "Epidemic".equals(identifier);
            }
        };
    }

    @Override
    public IPlayerCard createPlayerEventCard(String nm, String description) 
    {
        final String name = nm;
        final String descript = description;
        return new IEventCard()
        {
            @Override
            public String getName() 
            {
                return name;
            }

            @Override
            public String getDescription() 
            {
                return descript;
            }

            @Override
            public PlayerCardType getType() 
            {
                return PlayerCardType.EVENT;
            }

            @Override
            public boolean equalsIdentifier(String identifier) 
            {
                return name.equals(identifier);
            }
        };
    }

    @Override
    public IInfectionCard createInfectCard(String city, DiseaseType type)
    {
        final String cityVal = city;
        final DiseaseType color = type;
        return new IInfectionCard()
        {
            @Override
            public String getName() 
            {
                return cityVal;
            }
            
            @Override
            public DiseaseType getColor()
            {
                return color;
            }

            @Override
            public boolean equalsIdentifier(String identifier) 
            {
                return cityVal.equals(identifier);
            }
        };
    }

    @Override
    public CityCardParams createCityParams(String name, DiseaseType type) 
    {
        return new CityCardParams(name,type);
    }

    @Override
    public IPlayerCard createPlayerCityCard(CityCardParams params) 
    {
        return createPlayerCityCard(params.getCityName(),params.getColor());
    }

    @Override
    public EventCardParams createEventCardParams(String name, String description) 
    {
        return new EventCardParams(name,description);
    }

    @Override
    public IPlayerCard createPlayerEventCard(EventCardParams params) 
    {
        return createPlayerEventCard(params.getEventName(), params.getEventDescription());
    }

    @Override
    public IInfectionCard createInfectCard(CityCardParams parameters) 
    {
        return createInfectCard(parameters.getCityName(),parameters.getColor());
    }
}
