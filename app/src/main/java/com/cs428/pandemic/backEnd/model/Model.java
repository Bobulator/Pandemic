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
    private static String baseName = "Player ";
    private static String role = "Standard";
    private static List<CityCardParams> cities;
    private static List<EventCardParams> eventCards;


    public static void createInstance(int difficulty, List<String> playerNames){

        int numberOfPlayers = playerNames.size();

        CompositeModelFactory fact = new CompositeModelFactory();
        ICardFactory cardFact = new CardFactory();
        IGameModelBuilder builder = fact.createModelBuilder();
        IGameDecksHolder holder = fact.createGameDeck();
        cities = initCities(cardFact);
        eventCards = initEvents(cardFact);
        IGameMap map = initMap();
        holder.prepareDecks(difficulty, cities, eventCards);
        builder.setDeck(holder)
                .setMap(map)
                .setTracker(fact.createTurnTracker(numberOfPlayers))
                .setDiseaseCubes(new DiseaseCubes());

        ICity startingCity = map.getCity("Atlanta");

        for(int i = 0; i < numberOfPlayers; ++i)
        {
            builder.addPlayers(new Player(playerNames.get(i), i, startingCity, role));
        }

        instance = builder.createModel();

    }


    public static IGameModel getInstance()
    {
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

    private static void initDecks(int difficulty){

    }

    public static void main(String[] args)
    {
        IGameModel model = getInstance();
        model.getMap();
    }
}
