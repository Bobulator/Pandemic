package com.cs428.pandemic.frontEnd;

import com.cs428.pandemic.frontEnd.dataTransferObjects.UI_Card;
import com.cs428.pandemic.frontEnd.dataTransferObjects.UI_City;
import com.cs428.pandemic.frontEnd.dataTransferObjects.UI_Disease;
import com.cs428.pandemic.frontEnd.dataTransferObjects.UI_DrawnCards;
import com.cs428.pandemic.frontEnd.dataTransferObjects.UI_Player;
import com.cs428.pandemic.frontEnd.dataTransferObjects.UI_SharedKnowledge;
import com.cs428.pandemic.frontEnd.enums.Difficulty;

import java.util.List;
import java.util.Map;

/**
 * Created by Chad Bacon on 2/16/2016.
 * TODO: javadocs
 */
public interface IModelInterface {

    // CanDo Methods
    // These methods request the model to determine whether a given action is possible during the
    // current model state. The model should not change as a result of any of these calls; these
    // methods only exist so the UI knows whether or not to highlight respective action buttons.
    boolean canMove();
    boolean canFly();
    boolean canShareKnowledge();
    boolean canTreatDisease();
    boolean canCureDisease();
    boolean canBuildResearchStation();
    boolean canPass();

    // Data Access Methods
    // These methods only query the model for particular data; the state of the model should never
    // change as a result of these method calls.
    List<UI_Player> startGame(List<String> players, Difficulty difficulty);
    Map<String, UI_City> getCityData();
    List<String> getResearchStationLocations();
    Map<Integer, String> getPawnLocations();
    int getRemainingResearchStations();
    List<UI_Disease> getRemainingDiseaseCubes();
    int getOutBreakCounter();
    int getInfectionRate();
    UI_Player getNextPlayer();
    List<UI_Card> getPlayerHand(int playerID);
    List<UI_Card> getInfectionDiscardedCards();
    List<UI_Card> getPlayerDiscardedCards();
    int getRemainingActions();
    List<String> getConnectedCities(String city);
    List<String> getCitiesToFlyTo();
    UI_SharedKnowledge getShareableKnowledge(int playerID);
    List<String> getRoleActions();
    ICommandObject getActionObject(String action);

    // Do Methods
    // These methods request changes to the model state. Most of the methods have a void return
    // type; it is assumed that the model was successful in carrying out the requested action.
    void movePlayer(int playerID, String city);
    void flyPlayer(int playerID, String cityCard, String destinationCity);
    void discardCard(int playerID, String cardName);
    void shareKnowledge(int givingPlayerID, int receivingPlayerID, String cardName);
    void treatDisease(String color);
    void cureDisease(String color, List<String> cards);
    void buildResearchStation(String city);
    void playEventCard(int playerID, String card);
    void pass();
    UI_DrawnCards drawCards();
    void endTurn();
}
