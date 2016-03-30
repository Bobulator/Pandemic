package com.cs428.pandemic.frontEnd;

import com.cs428.pandemic.frontEnd.dataTransferObjects.UI_Card;
import com.cs428.pandemic.frontEnd.dataTransferObjects.UI_City;
import com.cs428.pandemic.frontEnd.dataTransferObjects.UI_Disease;
import com.cs428.pandemic.frontEnd.dataTransferObjects.UI_DrawnCards;
import com.cs428.pandemic.frontEnd.dataTransferObjects.UI_Player;
import com.cs428.pandemic.frontEnd.dataTransferObjects.UI_SharedKnowledge;

import java.util.List;
import java.util.Map;

/**
 * Created by Chad Bacon on 2/16/2016.
 *
 * This interface defines how the UI will make requests of the model. Outside of this (with the
 * possible exception of objects that implement the ICommandObject interface, which have their own
 * special dependencies) the UI will not have any dependencies on the model.
 */
public interface IModelInterface {

    // CanDo Methods
    // These methods request the model to determine whether a given action is possible during the
    // current model state. The model should not change as a result of any of these calls; these
    // methods only exist so the UI knows whether or not to highlight respective action buttons.

    /**
     * Requests whether or not a player can move from their current city to an adjacent, connected
     * city as an action.
     * @return True if yes, otherwise False.
     */
    boolean canMove();

    /**
     * Requests whether or not a player can fly from their current city using 'charter flight' as
     * an action.
     * @return True if yes, otherwise False.
     */
    boolean canFlyCharter();

    /**
     * Requests whether or not a player can use the 'direct flight' action.
     * @return True if yes, otherwise False.
     */
    boolean canFlyDirect();

    /**
     * Requests whether or not a player can use the 'shuttle flight' action.
     * @return True if yes, otherwise False.
     */
    boolean canFlyShuttle();

    /**
     * Requests whether or not a player can share knowledge in their current city with any player
     * as an action.
     * @return True if yes, otherwise False.
     */
    boolean canShareKnowledge();

    /**
     * Requests whether or not a player can treat a disease in their current city as an action.
     * @return True if yes, otherwise False.
     */
    boolean canTreatDisease();

    /**
     * Requests whether or not it is possible for a player to cure a disease as an action.
     * @return True if yes, otherwise False.
     */
    boolean canCureDisease();

    /**
     * Requests whether or not is is possible for a player to build a research station as an action.
     * @return True if yes, otherwise False.
     */
    boolean canBuildResearchStation();

    /**
     * Requests whether or not a player can pass as a valid action.
     * @return True if yes, otherwise False.
     */
    boolean canPass();

    // Data Access Methods
    // These methods only query the model for particular data; the state of the model should never
    // change as a result of these method calls.

    /**
     * Requests the model to start a new game.
     * @param players A list containing 2-4 player names.
     * @param difficulty The selected difficulty.
     * @return A list of UI_Players, each containing a player's ID, name, and role.
     */
    List<UI_Player> startGame(List<String> players, String difficulty, IUI_Updater uiUpdater);

    /**
     * Requests the model for each city's data, including its color, disease cube count, and
     * neighbors.
     * @return A Map with each city's name as a key and its corresponding UI_City object as a value.
     */
    Map<String, UI_City> getCityData();

    /**
     * Requests the model for each city that contains a research station.
     * @return A List containing the name of each city containing a research station.
     */
    List<String> getResearchStationLocations();

    /**
     * Requests the model for the location of each player's pawn.
     * @return A Map mapping a player's ID to the city where there pawn is.
     */
    Map<Integer, String> getPawnLocations();

    /**
     * Requests the model for the remaining number of research stations (the number of not-built
     * research stations)
     * @return The number of remaining research stations (0-5).
     */
    int getRemainingResearchStations();

    /**
     * Requests the model for the remaining disease cubes of each type.
     * @return A List of UI_Diseases, having a UI_Disease of each non-exterminated disease. NOTE:
     * Exterminated diseases should simply be omitted from this list.
     */
    List<UI_Disease> getRemainingDiseaseCubes();

    /**
     * Requests the model for the current Outbreak counter.
     * @return The current Outbreak counter (1-8).
     */
    int getOutbreakCounter();

    /**
     * Requests the model for the current Infection rate.
     * @return The current Infection rate (2-4).
     */
    int getInfectionRate();

    /**
     * Requests the model for the player that has the current turn. When a player ends their turn,
     * this function is called to determine which player has the next turn.
     * @return The UI_Player object corresponding to the current player whose turn it is.
     */
    UI_Player getCurrentPlayer();

    /**
     * Queries the model for the given player's hand.
     * @param playerID The ID of the player whose hand is needed.
     * @return A list of UI_Cards corresponding with the players hand.
     */
    List<UI_Card> getPlayerHand(int playerID);

    /**
     * Queries the model for all of the currently discarded Infection Cards. This does not include
     * cards that have been permanently removed from the game.
     * @return A list of UI_Cards corresponding to each currently discarded Infection Card.
     */
    List<UI_Card> getInfectionDiscardedCards();

    /**
     * Queries the model for all of the currently discarded Player Cards. This does not include
     * cards that have been permanently removed from the game.
     * @return A list of UI_Cards corresponding to each currently discarded Player Card.
     */
    List<UI_Card> getPlayerDiscardedCards();

    /**
     * Queries the model for the remaining number of player cards.
     * @return The number of remaining player cards.
     */
    int getPlayerCardCount();

    /**
     * Queries the model for the current player's remaining actions.
     * @return The number of the current player's remaning actions (0-4).
     */
    int getRemainingActions();

    /**
     * Queries the model for all of the given city's neighbors, or the cities that are adjacent and
     * connected to the given city.
     * @param city The name of the city whose neighbors are being requested.
     * @return A list containing the names of each connected city.
     */
    List<String> getConnectedCities(String city);

    /**
     * Queries the model for all of the cities a player can fly to via 'charter flight'. This is
     * a basically list of all the cities excluding the one the player is currently in.
     * @param city The current player's city.
     * @return A list containing all the cities the player can fly to via 'charter flight'.
     */
    List<String> getCharterFlightCities(String city);

    /**
     * Queries the model for all of the cities that a player can currently fly to via 'direct
     * flight'.
     * @return A list containing the names of each city a player can fly to via 'direct flight'.
     */
    List<String> getDirectFlightCities();

    /**
     * Queries the model for all possible shuttle flight destinations.
     * @return A list containing the names of each city a player can fly to via 'shuttle flight'.
     */
    List<String> getShuttleFlightCities();

    /**
     * Queries the model for all of the cards that a player can give OR receive using the 'Share
     * Knowledge' action.
     * @param playerID The player using the action.
     * @return A UI_SharedKnowledge object containing the cards a player can trade or receive.
     */
    UI_SharedKnowledge getShareableKnowledge(int playerID);

    /**
     * Queries the model for any and all role-exclusive actions a player can perform.
     * @return A list of strings that correspond to each special action a player can perform.
     */
    List<String> getRoleActions();

    // Do Methods
    // These methods request changes to the model state. Because every action is potentially
    // overridable, most methods will return a CommandObject which will take care of creating a
    // dialog to query input from the player as well as requesting specific changes in the model.

    /**
     * This method is called when a player wants to do a move or 'drive' action. For most players,
     * this is simply moving the player to a immediately connected city.
     * @return CommandObject that performs the move.
     */
    ICommandObject movePlayer();

    /**
     * Requests the model for a charter flight CommandObject.
     * @return CommandObject to perform the charter flight.
     */
    ICommandObject doCharterFlight();

    /**
     * Requests the model for a direct flight CommandObject.
     * @return CommandObject to perform the direct flight.
     */
    ICommandObject doDirectFlight();

    /**
     * Requests the model for a shuttle flight CommandObject.
     * @return CommandObject to perform the shuttle flight.
     */
    ICommandObject doShuttleFlight();

    /**
     * Requests the model for a discard card CommandObject.
     * @return CommandObject to perform discard card.
     */
    ICommandObject discardCard();


    /**
     * Requests the model for a share knowledge CommandObject.
     * @return CommandObject to perform share knowledge.
     */
    ICommandObject shareKnowledge();

    /**
     * Requests the model for a treat disease CommandObject.
     * @return CommandObject to perform treat disease.
     */
    ICommandObject treatDisease();

    /**
     * Requests the model for a cure disease CommandObject.
     * @return CommandObject to perform cure disease.
     */
    ICommandObject cureDisease();

    /**
     * Requests the model for a build research station CommandObject.
     * @return CommandObject to build a research station.
     */
    ICommandObject buildResearchStation();

    /**
     * Requests the model to play the given event card from the given player's hand.
     * @param playerID The ID of the player who is playing the card from their hand.
     * @param card The name of the event card that is being played.
     */
    ICommandObject playEventCard(int playerID, String card);

    /**
     * Requests the model to 'pass' for the current player. In effect, this method should only
     * decrement the current player's remaining actions.
     */
    void pass();

    /**
     * Requests the model for the Command Object associated with the given action name and executes
     * the return object.
     * @param action The name of the special action the player wishes to perform.
     * @return The CommandObject to be executed.
     */
    ICommandObject doSpecialRoleAction(String action);

    /**
     * Requests the model to begin the end turn phase. During this method the model will make calls
     * on the concerning each stage ending the turn, including the infection rate as well as when
     * the UI should request the model to draw cards and resolve any resulting epidemic cards.
     */
    void endTurn();

    /**
     * Requests the model to draw cards for the current player.
     * @return A UI_DrawnCards object containing the drawn cards as well as how many cards the
     * must discard (0-2).
     */
    UI_DrawnCards drawCards();


}