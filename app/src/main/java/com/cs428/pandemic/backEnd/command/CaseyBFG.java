package com.cs428.pandemic.backEnd.command;

import android.app.FragmentManager;

import com.cs428.pandemic.backEnd.model.IGameModel;
import com.cs428.pandemic.backEnd.model.Model;
import com.cs428.pandemic.backEnd.model.deck.*;
import com.cs428.pandemic.backEnd.model.gamestate.DiseaseType;
import com.cs428.pandemic.backEnd.model.gamestate.IInfectionTracker;
import com.cs428.pandemic.backEnd.model.map.ICity;
import com.cs428.pandemic.backEnd.model.player.IPlayer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.cs428.pandemic.backEnd.command.ICommandResult.ResultType.*;

public abstract class CaseyBFG {

    private HashMap<String, String> specialProperties = new HashMap<>();
    protected List<String> specialActions;

    public String getRoleName() {
        return "";
    }

    public String getUIElementPath() {
        return "";
    }

    protected IPlayer player;

    protected CaseyBFG(IPlayer player) {
        this.player = player;
    }

    // =====================
    // ==== CAN METHODS ====
    // =====================

    // TODO: add in a setup function that is called at the beginning of each players turn.
    // This function will, among other things, set the number of actions on the TurnTracker to 4

    /**
     * CanDo method for the Drive/Ferry movement action.
     *
     * @return true if they can drive the crap out of this ferry, otherwise false
     */
    public boolean canDriveFerry() {

        // Make sure the player's current location has at least one adjacent city
        if (player.getLocation().getNumberOfAdjacentCities() <= 0) {
            return false;
        }

        // Make sure the player has actions remaining
        if (!(Model.getInstance().getTurnTracker().getCurrentActionPoints() > 0)) {
            return false;
        }

        return true;
    }

    /**
     * CanDo method for the Direct Flight movement action. Verify that cityName
     * matches one of the player's current cards.
     *
     * @return true if they can fly the crap out of this direct flight, otherwise false
     */
    public boolean canFlyDirect() {

        // Make sure the player has at least 1 city card
        // TODO Make it so we can get the number of city cards (not just number of cards)
        if (!(player.getHand().size() > 0)) {
            return false;
        }

        // Make sure the player has actions remaining
        if (!(Model.getInstance().getTurnTracker().getCurrentActionPoints() > 0)) {
            return false;
        }

        return true;
    }

    /**
     * CanDo method for the Charter Flight movement action. Verifies that the
     * player has the card for the current city that they're in.
     *
     * @return true if they can fly the crap out of this charter flight, otherwise false
     */
    public boolean canFlyCharter() { //Parameter not necessary here; just the Player's location is germane.

        // Make sure the player has the card that corresponds to the player's location
        // Replace null with the player's current location when that functionality exists
        if (!player.getHand().hasCard(null)) {
            return false;
        }

        // Make sure the player has actions remaining
        if (!(Model.getInstance().getTurnTracker().getCurrentActionPoints() > 0)) {
            return false;
        }

        return true;
    }

    /**
     * CanDo method for the Shuttle Flight movement action. Verifies that the
     * player is currently in a city with a research station and that cityName
     * has a research station.
     *
     * @return true if they can shuttle the crap out of this flight, otherwise false
     */
    public boolean canFlyShuttle() {

        // Make sure the player's location has a research station
        if (!player.getLocation().hasResearchStation()) {
            return false;
        }

        // Make sure there is at least 1 other research station on the board (besides the one at the player's current location)
        if (Model.getInstance().getMap().getNumberOfResearchStationLocations() <= 1) {
            return false;
        }

        // Make sure the player has actions remaining
        if (!(Model.getInstance().getTurnTracker().getCurrentActionPoints() > 0)) {
            return false;
        }

        return true;
    }


    /**
     * Makes sure the player has actions remaining
     *
     * @return true if they can pass the crap out of this turn, otherwise false
     */
    public boolean canPass() {

        // Make sure the player has actions remaining
        if (!(Model.getInstance().getTurnTracker().getCurrentActionPoints() > 0)) {
            return false;
        }

        return true;
    }


    /**
     * Makes sure the player has the card associated with that player's location, the player's location does not currently
     * have a research station, and that the player has remaining actions
     *
     * @return true if they can build the crap out of this research station, otherwise false
     */
    public boolean canBuildStation() {

        // Make sure the player has the card associated with the player's location
        // TODO Make sure you can see if the player has this card with a String
        // Replace "null" with the player's location when this functionality exists
        if (!player.getHand().hasCard(null)) {
            return false;
        }

        // Check to see if the city already has a research station
        if (player.getLocation().hasResearchStation()) {
            return false;
        }

        // Make sure the player has actions remaining
        if (!(Model.getInstance().getTurnTracker().getCurrentActionPoints() > 0)) {
            return false;
        }

        // All checks passed!
        return true;
    }

    /**
     * Checks to see if the player can discover a cure. The player must be at a location with a research station, the player must
     * have at least 5 cards, and at least 5 of the player's cards must be the same color
     *
     * @return true if they can cure the crap out of this disease, otherwise false
     */
    public boolean canDiscoverCure() {

        // is the player in a city with a research station?
        if (!player.getLocation().hasResearchStation())
            return false;

        // Make sure the player has at least 5 cards, and that the player
        // has 5 red, 5 blue, 5 black, or 5 yellow cards
        // TODO player.getHand() does not work; we
        CardCollection<IPlayerCard> curCards = player.getHand();
        if (curCards.size() < 5 || (curCards.getColorCount(DiseaseType.RED) < 5 &&
                curCards.getColorCount(DiseaseType.BLUE) < 5 &&
                curCards.getColorCount(DiseaseType.BLACK) < 5 &&
                curCards.getColorCount(DiseaseType.YELLOW) < 5))
            return false;

        // Make sure the player has actions remaining
        if (!(Model.getInstance().getTurnTracker().getCurrentActionPoints() > 0)) {
            return false;
        }

        return true;
    }

    /**
     * Checks to see if the player's current location has at least 1 disease cube on it and that there are actions remaining.
     *
     * @return true if they can treat the crap out of this disease, otherwise false
     */
    public boolean canTreatDisease() {

        // Make sure the player's current location has at least 1 disease cube on it
        if (!(player.getLocation().hasDiseaseCubes())) {
            return false;
        }

        // Make sure the player has actions remaining
        if (!(Model.getInstance().getTurnTracker().getCurrentActionPoints() > 0)) {
            return false;
        }

        return true;
    }

    /**
     * This method is called from another player's (the player whose turn it is) canReceiveKnowledge
     * Check to see if this player's location is the same as the passed in location and they have a card to give
     * @param location The name of the city of the player (another role) calling this function
     * @return true if this player can give knowledge based on the calling player's location, otherwise false
     */
    public boolean canGiveKnowledgeToCurrentPlayer(String location){

        // Make sure the player has at least one city card
        // TODO change .size() to something that actually will work for just city cards
        if (player.getHand().size() <= 0) {

            return false;
        }

        if (!player.getLocation().getName().equals(location)){

            return false;
        }

        return true;
    }

    /**
     * Check to see if the player's location is being populated by any other players, that the player
     * has at least one card, and that there are actions remaining
     *
     * @return true if they can give the crap out of this knowledge, otherwise false
     */
    public boolean canGiveKnowledgeToAnyPlayer(Map<Integer, CommandBFG> playerRoleObjects) {

        // Make sure the player has enough action points
        if (Model.getInstance().getTurnTracker().getCurrentActionPoints() <= 0){

            return false;
        }

        // Make sure the player has at least one city card
        // TODO change .size() to something that actually will work for just city cards
        if (player.getHand().size() <= 0) {

            return false;
        }

        // Check all players other than the current player for the ability to receive knowledge
        for (Map.Entry<Integer, CommandBFG> role : playerRoleObjects.entrySet()) {

            // Check all other roles besides "this"
            if (role.getKey() != player.getPlayerIndex()) {

                // Check if this role can receive knowledge from the current player
                if (role.getValue().canReceiveKnowledgeFromCurrentPlayer(player.getLocation().getName())) {

                    return true;
                }

            }
        }

        return false;
    }

    /**
     * Check to see if the player's location is being populated by any other players, and that there are actions remaining
     *
     * @return true if they can give the crap out of this knowledge, otherwise false
     */
    public boolean canReceiveKnowledgeFromAnyPlayer(Map<Integer, CommandBFG> playerRoleObjects) {

        // Make sure the player has enough action points
        if (Model.getInstance().getTurnTracker().getCurrentActionPoints() <= 0){

            return false;
        }


        for (Map.Entry<Integer, CommandBFG> role : playerRoleObjects.entrySet()) {

            // Check all other roles besides "this"
            if (role.getKey() != player.getPlayerIndex()) {

                // Check if any role can give knowledge to the current player
                if (role.getValue().canGiveKnowledgeToCurrentPlayer(player.getLocation().getName())) {

                    return true;
                }

            }
        }

        return false;
    }

    /**
     * This method is called from another player's (the player whose turn it is) canGiveKnowledge
     * Check to see if this player's location is the same as the passed in location
     * @param location The name of the city of the player (another role) calling this function
     * @return true if this player can receive knowledge based on the calling player's location, otherwise false
     */
    public boolean canReceiveKnowledgeFromCurrentPlayer(String location){

       return (player.getLocation().getName().equals(location));
    }


    // Will return a list of the actions the player can do from their special actions list
    // will probably need an Object param also for additional data
    public abstract boolean canDoAnySpecialAction();


    // ====================
    // ==== DO METHODS ====
    // ====================
    public ICommand getDoDriveFerry(final String cityName) {
        return new ICommand() {
            @Override
            public ICommandResult execute(FragmentManager fm) {

                // Set the player's location to cityName
                player.setLocation(Model.getInstance().getMap().getCity(cityName));

                // Decrement action counter
                Model.getInstance().getTurnTracker().decrementActionPoints(1);

                return doSuccess("Drive the crap out of that ferry, John.");
            }
        };
    }

    /**
     * Performs the fly action by removing the card associated with the provided cityCardName from the player's
     * hand and discarding it, moving the player to the provided destination, and decrementing the action counter.
     *
     * @param destination The name of the city the player is moving to.
     * @return the return of the ICommand execute is as follows:
     * <ul>
     * <li> getResult: (SUCCESS) </li>
     * <li> getMessage: success message </li>
     * <li> getChainCommand: null </li>
     * <li> getData: germane information (if necessary) </li>
     * </ul>
     */
    public ICommand getDoFly(final String cityCardName, final String destination) {
        return new ICommand() {
            @Override
            public ICommandResult execute(FragmentManager fm) {

                // Remove the card represented by cityCardName from the player's hand
                IPlayerCard removedCard = player.removeCityCard(cityCardName);

                // Discard the removed card
                Model.getInstance().getGameDecks().addCard(removedCard, CardFamilyType.PLAYER, DeckType.DISCARD);

                // Set the player's location to destination
                player.setLocation(Model.getInstance().getMap().getCity(destination));

                // Decrement action counter
                Model.getInstance().getTurnTracker().decrementActionPoints(1);

                return doSuccess("Fly the crap out of it, John.");
            }
        };
    }

    /**
     * Performs the Direct Flight action by moving the player to the provided destination, removing
     * the card associated with the destination from the player's and discarding it, and decrementing
     * the action counter.
     *
     * @param destination The name of the city the player is moving to.
     * @return The return of the ICommand execute is as follows:
     * <ul>
     * <li> getResult: (SUCCESS) </li>
     * <li> getMessage: success message </li>
     * <li> getChainCommand: null </li>
     * <li> getData: germane information (if necessary) </li>
     * </ul>
     */
    public ICommand getDoDirectFlight(final String destination) {
        return new ICommand() {
            @Override
            public ICommandResult execute(FragmentManager fm) {

                // Set the player's location to destination
                player.setLocation(Model.getInstance().getMap().getCity(destination));

                // Remove the card associated with destination from the player's hand
                IPlayerCard removedCard = player.removeCityCard(destination);

                // Discard the removed card
                Model.getInstance().getGameDecks().addCard(removedCard, CardFamilyType.PLAYER, DeckType.DISCARD);

                // Decrement action counter
                Model.getInstance().getTurnTracker().decrementActionPoints(1);

                return doSuccess("Take the crap out of this direct flight, John.");
            }
        };
    }

    /**
     * Performs the Charter Flight action by moving the player to the provided destination,
     * removing the card associated with the player's current location from the player's hand
     * and discarding it, and decrementing the action counter.
     *
     * @param destination The name of the city the player is moving to.
     * @return The return of the ICommand execute is as follows:
     * <ul>
     * <li> getResult: (SUCCESS) </li>
     * <li> getMessage: success message </li>
     * <li> getChainCommand: null </li>
     * <li> getData: germane information (if necessary) </li>
     * </ul>
     */
    public ICommand getDoCharterFlight(final String destination) {

        return new ICommand() {
            @Override
            public ICommandResult execute(FragmentManager fm) {

                // Set the player's location to cityName
                player.setLocation(Model.getInstance().getMap().getCity(destination));

                // Discard the card associated with cityName
                IPlayerCard removedCard = player.removeCityCard(player.getLocation().getName());

                // Discard the removed card
                Model.getInstance().getGameDecks().addCard(removedCard, CardFamilyType.PLAYER, DeckType.DISCARD);

                // Decrement action counter
                Model.getInstance().getTurnTracker().decrementActionPoints(1);

                return doSuccess("Take the crap out of this direct flight, John.");
            }
        };
    }

    /**
     * Performs the Shuttle Flight action by moving the player to the provided destination
     * and decrementing the action counter.
     *
     * @param destination
     * @return The return of the ICommand execute is as follows:
     * <ul>
     * <li> getResult: (SUCCESS) </li>
     * <li> getMessage: success message </li>
     * <li> getChainCommand: null </li>
     * <li> getData: germane information (if necessary) </li>
     * </ul>
     */
    public ICommand getDoShuttleFlight(final String destination) {
        return new ICommand() {
            @Override
            public ICommandResult execute(FragmentManager fm) {

                // Set the player's location to destination
                player.setLocation(Model.getInstance().getMap().getCity(destination));

                // Decrement action counter
                Model.getInstance().getTurnTracker().decrementActionPoints(1);

                return doSuccess("Shuttle the crap out of this flight, John.");
            }
        };
    }

    /**
     * Decrements the player's action count by 1
     *
     * @return The return of the ICommand execute is as follows:
     * <ul>
     * <li> getResult: (SUCCESS) </li>
     * <li> getMessage: success message </li>
     * <li> getChainCommand: null </li>
     * <li> getData: germane information (if necessary) </li>
     * </ul>
     */
    public ICommand getDoPass() {

        return new ICommand() {
            @Override
            public ICommandResult execute(FragmentManager fm) {

                // Decrement action counter
                Model.getInstance().getTurnTracker().decrementActionPoints(1);

                return doSuccess("Pass the crap out of this, John.");
            }
        };

    }

    /**
     * Puts a research station on the player's location. If there are no research stations remaining, the player
     * chooses which city to remove a research station from. Removes the card associated with the player's location
     * from the player's hand and discards that card. Decrements the action counter.
     * <ul>
     * <li> getResult: (SUCCESS) </li>
     * <li> getMessage: success message </li>
     * <li> getChainCommand: null </li>
     * <li> getData: germane information (if necessary) </li>
     * </ul>
     */
    public ICommand getDoBuildStation() {

        return new ICommand() {
            @Override
            public ICommandResult execute(FragmentManager fm) {
                // Check to see if there are available research stations
                // If not, tell the GUI to have the player choose which research station to move
                // Add getCitiesWithResearchStations() to the GameMap
                if (Model.getInstance().getMap().getRemainingResearchStationCount() <= 0) {
                    List<String> researchStationLocations = Model.getInstance().getMap().getResearchStationLocations();
                    // GUI must have the player choose one of these locations to remove a research station from
                    // Return null if cancel is selected
                    String selectedLocation = "";

                    // Remove the research station from the selected location
                    Model.getInstance().getMap().getCity(selectedLocation).removeResearchStation();
                }

                // Place the research station at the player's location
                player.getLocation().addResearchStation();

                // Remove the card from the player's hand
                IPlayerCard removedCard = player.removeCityCard(player.getLocation().getName());

                // Discard the card
                Model.getInstance().getGameDecks().addCard(removedCard, CardFamilyType.PLAYER, DeckType.DISCARD);

                // Decrement the action points
                Model.getInstance().getTurnTracker().decrementActionPoints(1);

                return doSuccess("Build the crap out of this research station, John.");
            }
        };
    }

    ICommand getDoDiscoverCure(final DiseaseType diseaseType, final List<String> cityNames) {
        return new ICommand() {
            @Override
            public ICommandResult execute(FragmentManager fm) {

                for (String city : cityNames) {
                    // remove the cards from the player's hand
                    IPlayerCard removedCard = player.removeCityCard(city);
                    if (removedCard == null)
                        return doFailure("You did not have the city card that you tried to discard.");

                    // add the cards to the discard pile
                    Model.getInstance().getGameDecks().addCard(removedCard, CardFamilyType.PLAYER, DeckType.DISCARD);
                }

                // set the cure marker
                Model.getInstance().getGameState().getDiseaseData().cure(diseaseType);

                // check to see if the disease is eradicated
                // if so, set that marker
                try {
                    if (Model.getInstance().getDiseaseCubes().getDiseaseCount(diseaseType) == 24) {
                        Model.getInstance().getGameState().getDiseaseData().eradicate(diseaseType);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }

                // decrement the number of remaining actions
                Model.getInstance().getTurnTracker().decrementActionPoints(1);

                return doSuccess("Cure discoverd.");
            }
        };
    }

    ICommand getDoTreatDisease(final String diseaseType) {
        return null;
    }

    public ICommand getDoGiveKnowledge(final String cityName) {

        return new ICommand() {
            @Override
            public ICommandResult execute(FragmentManager fm) {
                //Remove the card from the
                IPlayerCard card = player.removeCityCard(cityName);
                //Put the card in the receiving player's hand
                //and check to see if the player has too many cards
                boolean tooManyCards = false;

                //Decrement action points
                Model.getInstance().getTurnTracker().decrementActionPoints(1);

                // TODO How do players discard? And how do we trigger it?


                return doSuccess("Shared the crap out of that knowledge, John", tooManyCards);
            }
        };
    }

    ICommand getDoReceiveKnowledge(final String cityName) {
        return null;
    }

    // will probably need an Object param also for additional data
    ICommand getDoSpecialAction(final String actionName) {
        return null;
    }

    public ICommand getIncrease() {
        return new ICommand() {
            @Override
            public ICommandResult execute(FragmentManager fm) {
                IInfectionTracker tracker = Model.getInstance().getGameState().getInfectionTracker();
                tracker.advanceTrack(1);
                final int newInfectionRate = tracker.getInfectionsPerTurn();
                return doSuccess("Infection Rate advanced by 1. Will now draw " + newInfectionRate + " infections per turn", newInfectionRate);
            }
        };
    }

    public ICommand getInfect() {
        return new ICommand() {
            @Override
            public ICommandResult execute(FragmentManager fm) {
                return doSuccess("Succeeded in infecting the stuffz", true);
            }
        };
    }

    public ICommand getIntensify() {
        return new ICommand() {
            @Override
            public ICommandResult execute(FragmentManager fm) {
                Model.getInstance().getGameDecks().intensify();
                return doSuccess("Shuffled Infection Discard into Infection Draw", true);
            }
        };
    }

    // Returns a list of all the special actions the player can perform
    public abstract List<String> getCanDoSpecialActions(String specialAction);

    public abstract ICommand getPerformSpecialAction(String specialAction);
    
    /*
    TODO: ALSO ADD...
        - outbreaks (3 steps in separate functions?)
        - infection
        - turn setup (that sets the number of remaining actions

     */
    // ========================
    // ==== HELPER METHODS ====
    // ========================

    protected ICommandResult canDoSuccess(final String message) {
        return new ICommandResult() {
            @Override
            public ResultType getResult() {
                return LEGAL;
            }

            @Override
            public String getMessage() {
                return message;
            }

            @Override
            public ICommand getChainCommand() {
                return null;
            }

            @Override
            public Object getData() {
                return null;
            }
        };
    }

    protected ICommandResult canDoFailure(final String message) {
        return new ICommandResult() {
            @Override
            public ResultType getResult() {
                return ResultType.ILLEGAL;
            }

            @Override
            public String getMessage() {
                return message;
            }

            @Override
            public ICommand getChainCommand() {
                return null;
            }

            @Override
            public Object getData() {
                return null;
            }
        };
    }

    protected ICommandResult doSuccess(final String message) {

        return new ICommandResult() {
            @Override
            public ResultType getResult() {
                return ResultType.SUCCESS;
            }

            @Override
            public String getMessage() {
                return message;
            }

            @Override
            public ICommand getChainCommand() {
                return null;
            }

            @Override
            public Object getData() {
                return null;
            }
        };
    }

    protected ICommandResult doFailure(final String message) {

        return new ICommandResult() {
            @Override
            public ResultType getResult() {
                return FAILURE;
            }

            @Override
            public String getMessage() {
                return message;
            }

            @Override
            public ICommand getChainCommand() {
                return null;
            }

            @Override
            public Object getData() {
                return null;
            }
        };
    }

    protected ICommandResult canDoSuccess(final String message, final Object data) {
        return new ICommandResult() {
            @Override
            public ResultType getResult() {
                return LEGAL;
            }

            @Override
            public String getMessage() {
                return message;
            }

            @Override
            public ICommand getChainCommand() {
                return null;
            }

            @Override
            public Object getData() {
                return data;
            }
        };
    }

    protected ICommandResult canDoFailure(final String message, final Object data) {
        return new ICommandResult() {
            @Override
            public ResultType getResult() {
                return ILLEGAL;
            }

            @Override
            public String getMessage() {
                return message;
            }

            @Override
            public ICommand getChainCommand() {
                return null;
            }

            @Override
            public Object getData() {
                return data;
            }
        };
    }

    protected ICommandResult doSuccess(final String message, final Object data) {
        return new ICommandResult() {
            @Override
            public ResultType getResult() {
                return SUCCESS;
            }

            @Override
            public String getMessage() {
                return message;
            }

            @Override
            public ICommand getChainCommand() {
                return null;
            }

            @Override
            public Object getData() {
                return data;
            }
        };
    }

    protected ICommandResult doFailure(final String message, final Object data) {
        return new ICommandResult() {
            @Override
            public ResultType getResult() {
                return FAILURE;
            }

            @Override
            public String getMessage() {
                return message;
            }

            @Override
            public ICommand getChainCommand() {
                return null;
            }

            @Override
            public Object getData() {
                return data;
            }
        };
    }

    public List<String> getSpecialActions() {
        return specialActions;
    }

}
