package com.cs428.pandemic.backEnd;

import com.cs428.pandemic.frontEnd.ICommandObject;
import com.cs428.pandemic.frontEnd.IModelInterface;
import com.cs428.pandemic.frontEnd.IUI_Updater;
import com.cs428.pandemic.frontEnd.dataTransferObjects.UI_Card;
import com.cs428.pandemic.frontEnd.dataTransferObjects.UI_City;
import com.cs428.pandemic.frontEnd.dataTransferObjects.UI_Disease;
import com.cs428.pandemic.frontEnd.dataTransferObjects.UI_DrawnCards;
import com.cs428.pandemic.frontEnd.dataTransferObjects.UI_Player;
import com.cs428.pandemic.frontEnd.dataTransferObjects.UI_SharedKnowledge;

import java.util.List;
import java.util.Map;

/**
 * Created by Chad Bacon on 6/4/2016.
 *
 * This Facade serves as the entry point to the model. All calls made by the UI to the model will
 * be done through the implemented methods from IModelInterface.
 */
public class ModelFacade implements IModelInterface {

    @Override
    public boolean canMove() {
        return false;
    }

    @Override
    public boolean canFlyCharter() {
        return false;
    }

    @Override
    public boolean canFlyDirect() {
        return false;
    }

    @Override
    public boolean canFlyShuttle() {
        return false;
    }

    @Override
    public boolean canShareKnowledge() {
        return false;
    }

    @Override
    public boolean canTreatDisease() {
        return false;
    }

    @Override
    public boolean canCureDisease() {
        return false;
    }

    @Override
    public boolean canBuildResearchStation() {
        return false;
    }

    @Override
    public boolean canPass() {
        return false;
    }

    @Override
    public List<UI_Player> startGame(List<String> players, String difficulty, IUI_Updater uiUpdater) {
        return null;
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
    public int getPlayerCardCount() {
        return 0;
    }

    @Override
    public int getRemainingActions() {
        return 0;
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
    public List<String> getShuttleFlightCities() {
        return null;
    }

    @Override
    public UI_SharedKnowledge getShareableKnowledge(int playerID) {
        return null;
    }

    @Override
    public Map<String, Boolean> getRoleActions() {
        return null;
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
