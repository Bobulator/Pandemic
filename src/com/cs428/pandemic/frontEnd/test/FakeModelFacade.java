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
import com.cs428.pandemic.frontEnd.enums.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Chad Bacon on 2/18/2016.
 *
 * This class is used for testing various UI components. Methods in this class will return
 * hard-coded results while keeping track of some rudimentary data (like the current player) to
 * simulate game flow.
 */
public class FakeModelFacade implements IModelInterface{
    private int[] playerActions = {4, 4, 4, 4};
    private int currentPlayer = 0;

    @Override
    public boolean canMove() {
        return true;
    }

    @Override
    public boolean canFlyCharter() {
        return true;
    }

    @Override
    public boolean canFlyDirect() {
        return true;
    }

    @Override
    public boolean canShareKnowledge() {
        return true;
    }

    @Override
    public boolean canTreatDisease() {
        return true;
    }

    @Override
    public boolean canCureDisease() {
        return true;
    }

    @Override
    public boolean canBuildResearchStation() {
        return true;
    }

    @Override
    public boolean canPass() {
        return true;
    }

    @Override
    public List<UI_Player> startGame(List<String> players, String difficulty) {
        assert(players.size() >= 2 && players.size() <= 4);
        try {
            Difficulty.valueOf(difficulty);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println("Invalid difficulty parameter: " + difficulty);
        }

        // Give each player an id 0-3
        int id = 0;
        // Assign each player an arbitrary role, based on their id
        Role[] roles = {Role.MEDIC, Role.SCIENTIST, Role.DISPATCHER, Role.RESEARCHER};

        ArrayList<UI_Player> uiPlayers = new ArrayList<>();
        for(String player : players) {
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
        return 0;
    }

    @Override
    public List<UI_Disease> getRemainingDiseaseCubes() {
        return null;
    }

    @Override
    public int getOutbreakCounter() {
        return 0;
    }

    @Override
    public int getInfectionRate() {
        return 0;
    }

    @Override
    public UI_Player getCurrentPlayer() {
        return null;
    }

    @Override
    public List<UI_Card> getPlayerHand(int playerID) {
        return null;
    }

    @Override
    public List<UI_Card> getInfectionDiscardedCards() {
        return null;
    }

    @Override
    public List<UI_Card> getPlayerDiscardedCards() {
        return null;
    }

    @Override
    public int getRemainingActions() {
        return playerActions[currentPlayer];
    }

    @Override
    public List<String> getConnectedCities(String city) {
        return null;
    }

    @Override
    public List<String> getCharterFlightCities(String city) {
        return null;
    }

    @Override
    public List<String> getDirectFlightCities() {
        return null;
    }

    @Override
    public UI_SharedKnowledge getShareableKnowledge(int playerID) {
        return null;
    }

    @Override
    public List<String> getRoleActions() {
        return null;
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
