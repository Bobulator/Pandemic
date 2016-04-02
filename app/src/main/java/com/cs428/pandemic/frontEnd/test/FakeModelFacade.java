package com.cs428.pandemic.frontEnd.test;

import com.cs428.pandemic.frontEnd.ICommandObject;
import com.cs428.pandemic.frontEnd.IModelInterface;
import com.cs428.pandemic.frontEnd.IUI_Updater;
import com.cs428.pandemic.frontEnd.dataTransferObjects.UI_Card;
import com.cs428.pandemic.frontEnd.dataTransferObjects.UI_City;
import com.cs428.pandemic.frontEnd.dataTransferObjects.UI_Disease;
import com.cs428.pandemic.frontEnd.dataTransferObjects.UI_DrawnCards;
import com.cs428.pandemic.frontEnd.dataTransferObjects.UI_Player;
import com.cs428.pandemic.frontEnd.dataTransferObjects.UI_SharedKnowledge;
import com.cs428.pandemic.frontEnd.enums.Difficulty;
import com.cs428.pandemic.frontEnd.enums.DiseaseColor;
import com.cs428.pandemic.frontEnd.enums.Role;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Chad Bacon on 2/18/2016.
 *
 * This class is used for testing various UI components. Methods in this class will return
 * hard-coded results while keeping track of some rudimentary data (like the players) to
 * simulate game flow. The only things this keeps track of is the players, the current player,
 * and how many actions the current player has. Note that when a player takes an action all it does
 * is decrement the current actions count.
 */
public class FakeModelFacade implements IModelInterface {
    private Role[] roles = {Role.MEDIC, Role.SCIENTIST, Role.DISPATCHER, Role.RESEARCHER};
    private int currentPlayer = 0;
    private int currentActions = 4;
    private boolean hasActions = true;
    private ArrayList<UI_Player> uiPlayers = new ArrayList<>();

    @Override
    public boolean canMove() {
        return hasActions;
    }

    @Override
    public boolean canFlyCharter() {
        return hasActions;
    }

    @Override
    public boolean canFlyDirect() {
        return hasActions;
    }

    @Override
    public boolean canFlyShuttle() {
        return hasActions;
    }

    @Override
    public boolean canShareKnowledge() {
        return hasActions;
    }

    @Override
    public boolean canTreatDisease() {
        return hasActions;
    }

    @Override
    public boolean canCureDisease() {
        return hasActions;
    }

    @Override
    public boolean canBuildResearchStation() {
        return hasActions;
    }

    @Override
    public boolean canPass() {
        return hasActions;
    }

    @Override
    public List<UI_Player> startGame(List<String> players, String difficulty, IUI_Updater uiUpdater) {
        // Verify that the UI sent a valid list of players
        assert (players != null && players.size() >= 2 && players.size() <= 4);

        // Verify that the difficulty is not null
        assert (difficulty != null);

        // Verify that the uiUpdater is not null
        assert (uiUpdater != null);

        // Validate the difficulty sent by the UI. An invalid difficulty has no effect on this
        // class; in such a case a message will be sent to the console and then things will
        // continue as normal.
        try {
            Difficulty.stringToEnum(difficulty);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println("Invalid difficulty parameter: " + difficulty);
        }

        // Give each player an id 0-3
        int id = 0;

        for (String player : players) {
            // Validate each player's name
            assert (player != null && !player.isEmpty());

            // Assign each player an arbitrary role, based on their id
            UI_Player uip = new UI_Player(id, player, roles[id]);
            uiPlayers.add(uip);
            id++;
        }

        return uiPlayers;
    }

    @Override
    public Map<String, UI_City> getCityData() {
        HashMap<String, UI_City> cities = new HashMap<>();
        // Blue
        cities.put("SanFrancisco", new UI_City("SanFrancisco", DiseaseColor.BLUE, null, Arrays.asList("Tokyo", "Manila", "Chicago", "LosAngeles")));
        cities.put("Chicago", new UI_City("Chicago", DiseaseColor.BLUE, null, Arrays.asList("SanFrancisco", "LosAngeles", "MexicoCity", "Atlanta", "Montreal")));
        cities.put("Atlanta", new UI_City("Atlanta", DiseaseColor.BLUE, null, Arrays.asList("Chicago", "Miami", "Washington")));
        cities.put("Montreal", new UI_City("Montreal", DiseaseColor.BLUE, null, Arrays.asList("Chicago", "Washington", "NewYork")));
        cities.put("Washington", new UI_City("Washington", DiseaseColor.BLUE, null, Arrays.asList("Atlanta", "Miami", "NewYork", "Montreal")));
        cities.put("NewYork", new UI_City("NewYork", DiseaseColor.BLUE, null, Arrays.asList("Montreal", "Washington", "Madrid", "London")));
        cities.put("Madrid", new UI_City("Madrid", DiseaseColor.BLUE, null, Arrays.asList("NewYork", "SaoPaulo", "Algiers", "Paris", "London")));
        cities.put("London", new UI_City("London", DiseaseColor.BLUE, null, Arrays.asList("NewYork", "Madrid", "Paris", "Essen")));
        cities.put("Paris", new UI_City("Paris", DiseaseColor.BLUE, null, Arrays.asList("Madrid", "Algiers", "Milan", "Essen", "London")));
        cities.put("Essen", new UI_City("Essen", DiseaseColor.BLUE, null, Arrays.asList("London", "Paris", "Milan", "StPetersburg")));
        cities.put("Milan", new UI_City("Milan", DiseaseColor.BLUE, null, Arrays.asList("Paris", "Istanbul", "Essen")));
        cities.put("StPetersburg", new UI_City("StPetersburg", DiseaseColor.BLUE, null, Arrays.asList("Essen", "Istanbul", "Moscow")));
        // Yellow
        cities.put("LosAngeles", new UI_City("LosAngeles", DiseaseColor.YELLOW, null, Arrays.asList("Sydney", "MexicoCity", "Chicago", "SanFrancisco")));
        cities.put("MexicoCity", new UI_City("MexicoCity", DiseaseColor.YELLOW, null, Arrays.asList("LosAngeles", "Lima", "Bogota", "Miami", "Chicago")));
        cities.put("Lima", new UI_City("Lima", DiseaseColor.YELLOW, null, Arrays.asList("MexicoCity", "Santiago", "Bogota")));
        cities.put("Santiago", new UI_City("Santiago", DiseaseColor.YELLOW, null, Arrays.asList("Lima")));
        cities.put("Bogota", new UI_City("Bogota", DiseaseColor.YELLOW, null, Arrays.asList("MexicoCity", "Lima", "BuenosAires", "SaoPaulo", "Miami")));
        cities.put("Miami", new UI_City("Miami", DiseaseColor.YELLOW, null, Arrays.asList("Atlanta", "MexicoCity", "Bogota", "Washington")));
        cities.put("BuenosAires", new UI_City("BuenosAires", DiseaseColor.YELLOW, null, Arrays.asList("Bogota", "SaoPaulo")));
        cities.put("SaoPaulo", new UI_City("SaoPaulo", DiseaseColor.YELLOW, null, Arrays.asList("Bogota", "BuenosAires", "Lagos", "Madrid")));
        cities.put("Lagos", new UI_City("Lagos", DiseaseColor.YELLOW, null, Arrays.asList("SaoPaulo", "Kinshasha", "Khartoum")));
        cities.put("Kinshasha", new UI_City("Kinshasha", DiseaseColor.YELLOW, null, Arrays.asList("Lagos", "Johannesburg", "Khartoum")));
        cities.put("Johannesburg", new UI_City("Johannesburg", DiseaseColor.YELLOW, null, Arrays.asList("Kinshasha", "Khartoum")));
        cities.put("Khartoum", new UI_City("Khartoum", DiseaseColor.YELLOW, null, Arrays.asList("Lagos", "Kinshasha", "Johannesburg", "Cairo")));
        // Black
        cities.put("Algiers", new UI_City("Algiers", DiseaseColor.BLACK, null, Arrays.asList("Madrid", "Cairo", "Istanbul", "Paris")));
        cities.put("Cairo", new UI_City("Cairo", DiseaseColor.BLACK, null, Arrays.asList("Algiers", "Khartoum", "Baghdad", "Istanbul")));
        cities.put("Istanbul", new UI_City("Istanbul", DiseaseColor.BLACK, null, Arrays.asList("Milan", "Algiers", "Cairo", "Baghdad", "Moscow", "StPetersburg")));
        cities.put("Baghdad", new UI_City("Baghdad", DiseaseColor.BLACK, null, Arrays.asList("Istanbul", "Cairo", "Riyadh", "Karachi", "Tehran")));
        cities.put("Moscow", new UI_City("Moscow", DiseaseColor.BLACK, null, Arrays.asList("StPetersburg", "Istanbul", "Tehran")));
        cities.put("Riyadh", new UI_City("Riyadh", DiseaseColor.BLACK, null, Arrays.asList("Cairo", "Karachi", "Baghdad")));
        cities.put("Tehran", new UI_City("Tehran", DiseaseColor.BLACK, null, Arrays.asList("Moscow", "Baghdad", "Karachi", "Delhi")));
        cities.put("Karachi", new UI_City("Karachi", DiseaseColor.BLACK, null, Arrays.asList("Tehran", "Baghdad", "Riyadh", "Mumbai", "Delhi")));
        cities.put("Mumbai", new UI_City("Mumbai", DiseaseColor.BLACK, null, Arrays.asList("Karachi", "Chennai", "Delhi")));
        cities.put("Delhi", new UI_City("Delhi", DiseaseColor.BLACK, null, Arrays.asList("Tehran", "Karachi", "Mumbai", "Chennai", "Kolkata")));
        cities.put("Chennai", new UI_City("Chennai", DiseaseColor.BLACK, null, Arrays.asList("Delhi", "Mumbai", "Jakarta", "Bangkok", "Kolkata")));
        cities.put("Kolkata", new UI_City("Kolkata", DiseaseColor.BLACK, null, Arrays.asList("Delhi", "Chennai", "Bangkok", "HongKong")));
        // Red
        cities.put("Bangkok", new UI_City("Bangkok", DiseaseColor.RED, null, Arrays.asList("Kolkata", "Chennai", "Jakarta", "HoChiMinhCity", "HongKong")));
        cities.put("Jakarta", new UI_City("Jakarta", DiseaseColor.RED, null, Arrays.asList("Chennai", "Sydney", "HoChiMinhCity", "Bangkok")));
        cities.put("Beijing", new UI_City("Beijing", DiseaseColor.RED, null, Arrays.asList("Shanghai", "Seoul")));
        cities.put("Shanghai", new UI_City("Shanghai", DiseaseColor.RED, null, Arrays.asList("HongKong", "Taipei", "Tokyo", "Seoul", "Beijing")));
        cities.put("HongKong", new UI_City("HongKong", DiseaseColor.RED, null, Arrays.asList("Kolkata", "Bangkok", "HoChiMinhCity", "Manila", "Taipei", "Shanghai")));
        cities.put("HoChiMinhCity", new UI_City("HoChiMinhCity", DiseaseColor.RED, null, Arrays.asList("Bangkok", "Jakarta", "Manila", "Taipei", "Shanghai")));
        cities.put("Seoul", new UI_City("Seoul", DiseaseColor.RED, null, Arrays.asList("Beijing", "Shanghai", "Tokyo")));
        cities.put("Taipei", new UI_City("Taipei", DiseaseColor.RED, null, Arrays.asList("Shanghai", "HongKong", "Manila", "Osaka")));
        cities.put("Manila", new UI_City("Manila", DiseaseColor.RED, null, Arrays.asList("Taipei", "HongKong", "HoChiMinhCity", "Sydney", "SanFrancisco")));
        cities.put("Sydney", new UI_City("Sydney", DiseaseColor.RED, null, Arrays.asList("Manila", "Jakarta", "LosAngeles")));
        cities.put("Tokyo", new UI_City("Tokyo", DiseaseColor.RED, null, Arrays.asList("Seoul", "Shanghai", "Osaka", "SanFrancisco")));
        cities.put("Osaka", new UI_City("Osaka", DiseaseColor.RED, null, Arrays.asList("Tokyo", "Taipei")));

        return cities;
    }

    @Override
    public List<String> getResearchStationLocations() {
        // Locations were chosen based on the most congested cities; see getPawnLocations().
        ArrayList<String> cities = new ArrayList<>();
        cities.add("Atlanta");
        cities.add("Paris");
        cities.add("Washington");
        cities.add("HongKong");
        cities.add("Kolkata");
        return cities;
    }

    @Override
    public Map<Integer, String> getPawnLocations() {
        // Locations were chosen based on proximity to other cities so we could test optimal
        // piece placement in the most congested areas of the board. Note that while this function
        // is flexible to the number of players being tested, 4 players will provide the most
        // comprehensive test.
        String[] cities = {"Paris", "Washington", "HongKong", "Kolkata"};

        HashMap<Integer, String> pawnLocations = new HashMap<>();
        for (int i = 0; i < uiPlayers.size(); ++i) {
            pawnLocations.put(uiPlayers.get(i).getPlayerID(), cities[i]);
        }

        return pawnLocations;
    }

    @Override
    public int getRemainingResearchStations() {
        // Based on the getResearchStationLocations() method. But it can be any arbitrary value.
        return 1;
    }

    @Override
    public List<UI_Disease> getRemainingDiseaseCubes() {
        ArrayList<UI_Disease> ar = new ArrayList<>();
        ar.add(new UI_Disease(DiseaseColor.BLACK, true, 4));
        ar.add(new UI_Disease(DiseaseColor.BLUE));
        ar.add(new UI_Disease(DiseaseColor.RED, false, 10));
        ar.add(new UI_Disease(DiseaseColor.YELLOW, false, 20));
        return ar;
    }

    @Override
    public int getOutbreakCounter() {
        // Arbitrary value
        return 4;
    }

    @Override
    public int getInfectionRate() {
        // Arbitrary value
        return 3;
    }

    @Override
    public UI_Player getCurrentPlayer() {
        return uiPlayers.get(currentPlayer);
    }

    @Override
    public List<UI_Card> getPlayerHand(int playerID) {
        // Arbitrary hand with at least one of each color
        ArrayList<UI_Card> uiCards = new ArrayList<>();
        uiCards.add(new UI_Card("atlanta", DiseaseColor.BLUE));
        uiCards.add(new UI_Card("hongkong", DiseaseColor.RED));
        uiCards.add(new UI_Card("bangkok", DiseaseColor.RED));
        uiCards.add(new UI_Card("chennai", DiseaseColor.BLACK));
        uiCards.add(new UI_Card("paris", DiseaseColor.BLUE));
        uiCards.add(new UI_Card("mexico city", DiseaseColor.YELLOW));
        return uiCards;
    }

    @Override
    public List<UI_Card> getInfectionDiscardedCards() {
        // Arbitrary cards of each color
        ArrayList<UI_Card> uiCards = new ArrayList<>();
        uiCards.add(new UI_Card("sanfrancisco", DiseaseColor.BLUE));
        uiCards.add(new UI_Card("seoul", DiseaseColor.RED));
        uiCards.add(new UI_Card("delhi", DiseaseColor.BLACK));
        uiCards.add(new UI_Card("bogota", DiseaseColor.YELLOW));
        return uiCards;
    }

    @Override
    public List<UI_Card> getPlayerDiscardedCards() {
        // Arbitrary cards of each color
        ArrayList<UI_Card> uiCards = new ArrayList<>();
        uiCards.add(new UI_Card("osaka", DiseaseColor.RED));
        uiCards.add(new UI_Card("washington", DiseaseColor.BLUE));
        uiCards.add(new UI_Card("saopaulo", DiseaseColor.YELLOW));
        uiCards.add(new UI_Card("tokyo", DiseaseColor.RED));
        return uiCards;
    }

    @Override
    public int getPlayerCardCount() {
        // Arbitrary value
        return 23;
    }

    @Override
    public int getRemainingActions() {
        return currentActions;
    }

    @Override
    public List<String> getConnectedCities(String city) {
        // Assuming Hong Kong was the given city
        ArrayList<String> cities = new ArrayList<>();
        cities.add("shanghai");
        cities.add("taipei");
        cities.add("manila");
        cities.add("hochiminhcity");
        cities.add("bangkok");
        cities.add("kolkata");
        return cities;
    }

    @Override
    public List<String> getCharterFlightCities(String city) {
        // All but one arbitrary city @TODO: add cities
        ArrayList<String> cities = new ArrayList<>();
        cities.add("atlanta");
        return cities;
    }

    @Override
    public List<String> getDirectFlightCities() {
        // 7 arbitrary cities since that's the max amount of cities a player can fly direct to
        ArrayList<String> cities = new ArrayList<>();
        cities.add("lima");
        cities.add("sydney");
        cities.add("chicago");
        cities.add("essen");
        cities.add("moscow");
        cities.add("beijing");
        cities.add("lagos");
        return cities;
    }

    public List<String> getShuttleFlightCities() {
        // A few arbitrary cities
        ArrayList<String> cities = new ArrayList<>();
        cities.add("atlanta");
        cities.add("osaka");
        cities.add("khartoum");
        return cities;
    }

    @Override
    public UI_SharedKnowledge getShareableKnowledge(int playerID) {
        // Arbitrary cards
        ArrayList<UI_Card> gCards = new ArrayList<>();
        gCards.add(new UI_Card("miami", DiseaseColor.YELLOW));
        gCards.add(new UI_Card("madrid", DiseaseColor.BLUE));
        gCards.add(new UI_Card("riyadh", DiseaseColor.BLACK));
        gCards.add(new UI_Card("jakarta", DiseaseColor.RED));

        ArrayList<UI_Card> rCards = new ArrayList<>();
        rCards.add(new UI_Card("santiago", DiseaseColor.YELLOW));
        rCards.add(new UI_Card("istanbul", DiseaseColor.BLACK));
        return new UI_SharedKnowledge(gCards, rCards);
    }

    @Override
    public List<String> getRoleActions() {
        ArrayList<String> actions = new ArrayList<>();
        actions.add("Special action 1");
        actions.add("Special action 2");
        actions.add("How long do these need to be?");
        actions.add("And how long of an action can we handle?");
        return actions;
    }

    @Override
    public ICommandObject movePlayer() {
        return null;
    }

    @Override
    public ICommandObject doCharterFlight() {
        return null;
    }

    @Override
    public ICommandObject doDirectFlight() {
        return null;
    }

    @Override
    public ICommandObject doShuttleFlight() {
        return null;
    }

    @Override
    public ICommandObject discardCard() {
        return null;
    }

    @Override
    public ICommandObject shareKnowledge() {
        return null;
    }

    @Override
    public ICommandObject treatDisease() {
        return null;
    }

    @Override
    public ICommandObject cureDisease() {
        return null;
    }

    @Override
    public ICommandObject buildResearchStation() {
        return null;
    }

    @Override
    public ICommandObject playEventCard(int playerID, String card) {
        return null;
    }

    @Override
    public void pass() {

    }

    @Override
    public ICommandObject doSpecialRoleAction(String action) {
        return null;
    }

    @Override
    public void endTurn() {

    }

    @Override
    public UI_DrawnCards drawCards() {
        return null;
    }
}
