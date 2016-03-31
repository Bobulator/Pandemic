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
import java.util.ArrayList;
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

    public static IGameModel getInstance(){
        // TODO: James implement this John
        if(instance == null) {
            CompositeModelFactory fact = new CompositeModelFactory();
            ICardFactory cardFact = new CardFactory();
            IGameModelBuilder builder = fact.createModelBuilder();
            IGameMap map = new GameMap();
            IGameDecksHolder holder = fact.createGameDeck();
            cities = Model.initCities(cardFact);
            eventCards = Model.initEvents(cardFact);
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

    private static List<CityCardParams> initCities(ICardFactory fact)
    {
        List<CityCardParams> retCities = new ArrayList<>();
        retCities.add(fact.createCityParams("City", DiseaseType.RED));
        return retCities;
    }

    private static List<EventCardParams> initEvents(ICardFactory fact)
    {
        List<EventCardParams> retCities = new ArrayList<>();
        retCities.add(fact.createEventCardParams("Event Name","Event Color"));
        return retCities;
    }
}
