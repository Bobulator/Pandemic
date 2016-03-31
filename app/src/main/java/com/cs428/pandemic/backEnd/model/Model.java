package com.cs428.pandemic.backEnd.model;

import com.cs428.pandemic.backEnd.model.deck.CardFactory;
import com.cs428.pandemic.backEnd.model.deck.CityCardParams;
import com.cs428.pandemic.backEnd.model.deck.EventCardParams;
import com.cs428.pandemic.backEnd.model.deck.ICardFactory;
import com.cs428.pandemic.backEnd.model.deck.IGameDecksHolder;
import com.cs428.pandemic.backEnd.model.disease.DiseaseCubes;
import com.cs428.pandemic.backEnd.model.gamestate.DiseaseType;
import com.cs428.pandemic.backEnd.model.map.GameMap;
import com.cs428.pandemic.backEnd.model.map.ICity;
import com.cs428.pandemic.backEnd.model.map.IGameMap;
import com.cs428.pandemic.backEnd.model.player.Player;
import com.cs428.pandemic.backEnd.model.prevData.Board;
import com.cs428.pandemic.backEnd.model.prevData.CityName;
import com.cs428.pandemic.backEnd.model.prevData.PlayerDeck;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

/**
 * Created by brandt on 3/23/16.
 */
public class Model {

    private static IGameModel instance = null;
    private static int numberOfPlayers = 5;
    private static int difficulty = 5;
    private static String baseName = "Player ";
    private static String role = "Standard";
    private static List<CityCardParams> cities;
    private static List<EventCardParams> eventCards;

    public static IGameModel getInstance()
    {
        if(instance == null) 
        {
            CompositeModelFactory fact = new CompositeModelFactory();
            ICardFactory cardFact = new CardFactory();
            IGameModelBuilder builder = fact.createModelBuilder();
            IGameDecksHolder holder = fact.createGameDeck();
            cities = initCities(cardFact);
            eventCards = initEvents(cardFact);
            IGameMap map = initMap();
            holder.prepareDecks(numberOfPlayers, cities, eventCards);
            builder.setDeck(holder)
                    .setMap(map)
                    .setTracker(fact.createTurnTracker(numberOfPlayers))
                    .setDiseaseCubes(new DiseaseCubes());
            List<String> locations = map.getAllOtherLocations("");
            Random rand = new Random();
            for(int i = 0; i < numberOfPlayers; ++i)
            {
                ICity city = map.getCity(locations.remove(rand.nextInt(locations.size())));
                builder.addPlayers(new Player(baseName + (i + 1),i,city,role));
            }
            instance = builder.createModel();
        }
        return instance;
    }
    
    private static IGameMap initMap()
    {
        GameMapBuilder builder = new GameMapBuilder();
        List<Object[]> cityVals = new Board().getCityValues();
        for(Object[] arr : cityVals)
        {
            List<String> adjacencies = new ArrayList<>();
            for(CityName cityName : (HashSet<CityName>)arr[2])
            {
                adjacencies.add(cityName.name());
            }
            builder.addCity((String)arr[0], (DiseaseType)arr[1], adjacencies);
        }
        return builder.createMap();
    }

    private static List<CityCardParams> initCities(ICardFactory fact)
    {
        return new Board().getCityCardParams(fact);
    }

    private static List<EventCardParams> initEvents(ICardFactory fact)
    {
        return new PlayerDeck().getEventCards(fact);
    }
    
    public static void main(String[] args)
    {
        IGameModel model = getInstance();
        model.getMap();
    }
}
