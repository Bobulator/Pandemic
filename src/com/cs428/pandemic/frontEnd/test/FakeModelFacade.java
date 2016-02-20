package com.cs428.pandemic.frontEnd.test;

import com.cs428.pandemic.frontEnd.ICommandObject;
import com.cs428.pandemic.frontEnd.IModelInterface;
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
public class FakeModelFacade implements IModelInterface{
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
    public List<UI_Player> startGame(List<String> players, String difficulty) {
        // Verify that the UI sent a valid list of players
        assert(players.size() >= 2 && players.size() <= 4);

        // Validate the difficulty sent by the UI. An invalid difficulty has no effect on this
        // class; in such a case a message will be sent to the console and then things will
        // continue as normal.
        try {
            Difficulty.valueOf(difficulty);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println("Invalid difficulty parameter: " + difficulty);
        }

        // Give each player an id 0-3
        int id = 0;

        for(String player : players) {
            // Validate each player's name
            assert(player != null && !player.isEmpty());

            // Assign each player an arbitrary role, based on their id
            UI_Player uip = new UI_Player(id, player, roles[id]);
            uiPlayers.add(uip);
            id++;
        }

        return uiPlayers;
    }

    @Override
    public Map<String, UI_City> getCityData() {
        return null;
    }

    @Override
    public List<String> getResearchStationLocations() {
        return null;
    }

    @Override
    public Map<Integer, String> getPawnLocations() {
        return null;
    }

    @Override
    public int getRemainingResearchStations() {
        // Arbitrary value
        return 5;
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
        uiCards.add(new UI_Card("hong kong", DiseaseColor.RED));
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
        uiCards.add(new UI_Card("san francisco", DiseaseColor.BLUE));
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
        uiCards.add(new UI_Card("sao paulo", DiseaseColor.YELLOW));
        uiCards.add(new UI_Card("tokyo", DiseaseColor.RED));
        return uiCards;
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
        cities.add("ho chi minh city");
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
        // Empty for now
        return new ArrayList<>();
    }

    @Override
    public void movePlayer(int playerID, String city) {}

    @Override
    public void flyPlayer(int playerID, String cityCard, String destinationCity) {}

    @Override
    public void discardCard(int playerID, List<String> cardNames) {}

    @Override
    public void shareKnowledge(int givingPlayerID, int receivingPlayerID, String cardName) {}

    @Override
    public void treatDisease(String diseaseColor) {}

    @Override
    public void cureDisease(String diseaseColor, List<String> cards) {}

    @Override
    public void buildResearchStation(String city) {}

    @Override
    public void playEventCard(int playerID, String card) {}

    @Override
    public void pass() {}

    @Override
    public ICommandObject executeActionObject(String action) {
        return null;
    }

    @Override
    public void endTurn() {}

    @Override
    public UI_DrawnCards drawCards() {
        return null;
    }
}
