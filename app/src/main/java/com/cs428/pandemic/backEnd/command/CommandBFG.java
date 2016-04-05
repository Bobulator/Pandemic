package com.cs428.pandemic.backEnd.command;

import android.app.FragmentManager;

import com.cs428.pandemic.backEnd.model.IGameModel;
import com.cs428.pandemic.backEnd.model.Model;
import com.cs428.pandemic.backEnd.model.deck.*;
import com.cs428.pandemic.backEnd.model.disease.TooManyDiseaseCubesException;
import com.cs428.pandemic.backEnd.model.gamestate.DiseaseType;
import com.cs428.pandemic.backEnd.model.gamestate.IInfectionTracker;
import com.cs428.pandemic.backEnd.model.map.ICity;
import com.cs428.pandemic.backEnd.model.player.IPlayer;
import com.cs428.pandemic.frontEnd.enums.Role;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class CommandBFG {

    private HashMap<String, String> specialProperties = new HashMap<>();
    protected List<String> specialActions;
    protected Role role;

    public Role getRoleName() {
        return role;
    }

    public String getUIElementPath() {
        return "";
    }

    protected IPlayer player;

    protected CommandBFG(IPlayer player, Role role) {

        this.player = player;
        this.role = role;
    }

    // region Can Do Methods
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

    //endregion

    // region Do Methods
    // ====================
    // ==== DO METHODS ====
    // ====================
    public ICommand getDoDriveFerry() {
        return new ICommand() {
            @Override
            public void execute(FragmentManager fm) {

                String currentLocation = player.getLocation().getName();

                // Use this list on the frame to give the user city options
                List<String> adjacentCities = Model.getInstance().getMap().getCity(currentLocation).getAdjacentCityNames();

                /* ****FRAME MAGIC**** */

                // Get city name from user
                String cityName = "";

                // Set the player's location to cityName
                player.setLocation(Model.getInstance().getMap().getCity(cityName));

                // Decrement action counter
                Model.getInstance().getTurnTracker().decrementActionPoints(1);
            }
        };
    }

    /**
     * Performs the fly action by optionally removing the card associated with the provided cityCardName from the player's
     * hand and discarding it, moving the player to the provided destination, and decrementing the action counter.
     *
     * @param destination the name of the city to fly to
     * @param discardCard true if the player should discard, false otherwise
     */
    protected void move(String destination, String discardCard) {

        if (discardCard != null) {

            // Remove the card represented by cityCardName from the player's hand
            IPlayerCard removedCard = player.removeCityCard(discardCard);

            // Discard the removed card
            Model.getInstance().getGameDecks().addCard(removedCard, CardFamilyType.PLAYER, DeckType.DISCARD);
        }

        // Set the player's location to destination
        player.setLocation(Model.getInstance().getMap().getCity(destination));

        // Decrement action counter
        Model.getInstance().getTurnTracker().decrementActionPoints(1);
    }

    /**
     * Performs the Direct Flight action by moving the player to the chosen destination, removing
     * the card associated with the destination from the player's hand and discarding it, and decrementing
     * the action counter.
     *
     * @return The ICommand the UI will call execute on
     */
    public ICommand getDoDirectFlight() {
        return new ICommand() {
            @Override
            public void execute(FragmentManager fm) {

                // Get list of city cards in player's hand
                // TODO Need to be able to get names of all cities in the player's hand other than the current location
                List<String> cityNames = null;

                /* ****FRAME MAGIC**** */

                // Get this from the user
                String destination = "";

                move(destination, destination);
            }
        };
    }

    /**
     * Performs the Charter Flight action by moving the player to the chosen destination,
     * removing the card associated with the player's current location from the player's hand
     * and discarding it, and decrementing the action counter.
     *
     */
    public ICommand getDoCharterFlight() {

        return new ICommand() {
            @Override
            public void execute(FragmentManager fm) {

                // Get names of all cities in the player's hand other than the current location
                String currentLocation = player.getLocation().getName();
                List<String> charterCityNames = Model.getInstance().getMap().getAllOtherLocations(currentLocation);

                /* ****FRAME MAGIC**** */

                // Get this from the user
                String destination = "";

                move(destination, currentLocation);
            }
        };
    }

    /**
     * Performs the Shuttle Flight action by moving the player to the chosen destination
     * and decrementing the action counter.
     *
     * @return The ICommand the UI will call execute on
     */
    public ICommand getDoShuttleFlight() {
        return new ICommand() {
            @Override
            public void execute(FragmentManager fm) {

                // Get list of research station locations to present to the user
                List<String> researchStations = Model.getInstance().getMap().getResearchStationLocations();

                /* ****FRAME MAGIC**** */

                // Get this from the user
                String destination = "";

                move(destination, null);
            }
        };
    }

    /**
     * Decrements the player's action count by 1
     *
     * @return The ICommand the UI will call execute on
     */
    public ICommand getDoPass() {

        return new ICommand() {
            @Override
            public void execute(FragmentManager fm) {

                // Decrement action counter
                Model.getInstance().getTurnTracker().decrementActionPoints(1);
            }
        };

    }

    /**
     * Puts a research station on the player's location. If there are no research stations remaining, the player
     * chooses which city to remove a research station from. Removes the card associated with the player's location
     * from the player's hand and discards that card. Decrements the action counter.
     *
     * @return The ICommand the UI will call execute on
     */
    public ICommand getDoBuildStation() {

        return new ICommand() {
            @Override
            public void execute(FragmentManager fm) {

                String currentLocation = player.getLocation().getName();

                // Try to place the research station on the map. If there are not enough in the pile
                // this method will return false
                // If placement fails, tell the GUI to have the player choose which research station to move
                // and then place the research station
                if (!Model.getInstance().getMap().placeResearchStation(currentLocation)) {

                    List<String> researchStationLocations = Model.getInstance().getMap().getResearchStationLocations();

                    /* ****FRAME MAGIC**** */

                    // GUI must have the player choose one of these locations to remove a research station from
                    // Return null if cancel is selected
                    String selectedLocation = "";

                    // Remove the research station from the selected location
                    Model.getInstance().getMap().removeResearchStation(selectedLocation);


                    // Place the research station at the player's location
                    Model.getInstance().getMap().placeResearchStation(currentLocation);
                }

                // Remove the card from the player's hand
                IPlayerCard removedCard = player.removeCityCard(player.getLocation().getName());

                // Discard the card
                Model.getInstance().getGameDecks().addCard(removedCard, CardFamilyType.PLAYER, DeckType.DISCARD);

                // Decrement the action points
                Model.getInstance().getTurnTracker().decrementActionPoints(1);
            }
        };
    }

    /**
     * Discovers a cure for the chosen disease type by choosing and
     * discarding 5 city cards of that color, then decrements the action counter.
     *
     * @return The ICommand the UI will call execute on
     */
    public ICommand getDoDiscoverCure() {
        return new ICommand() {
            @Override
            public void execute(FragmentManager fm) {

                // Show user frame of all disease types.
                // Types for which the player has enough city cards to cure the disease will be enabled

                List<String> cureableDiseases = new ArrayList<String>();

                for (DiseaseType d : DiseaseType.values()){

                    if (player.getHand().getColorCount(d) >= 5){

                        cureableDiseases.add(d.toString());
                    }
                }

                /* ****FRAME MAGIC**** */

                // Get this from the user
                String diseaseName = "";

                // Show user all city cards of diseaseType in their hand
                // TODO NEED FUNCTION FOR ALL CITY CARDS IN PLAYER'S HAND

                // Have user choose 5 cards to discard

                /* ****FRAME MAGIC**** */

                // Get this from the user
                List<String> discardCityNames = null;

                for (String city : discardCityNames) {
                    // remove the cards from the player's hand
                    IPlayerCard removedCard = player.removeCityCard(city);

                    // add the cards to the discard pile
                    Model.getInstance().getGameDecks().addCard(removedCard, CardFamilyType.PLAYER, DeckType.DISCARD);
                }

                // set the cure marker
                // TODO Make sure valueOf works with the string we are getting

                DiseaseType diseaseType = DiseaseType.getType(diseaseName);
                Model.getInstance().getGameState().getDiseaseData().cure(diseaseType);

                // check to see if the disease is eradicated
                // if so, set that marker
                try {

                    if (Model.getInstance().getDiseaseCubes().getDiseaseCount(diseaseType) == 24) {

                        Model.getInstance().getGameState().getDiseaseData().eradicate(diseaseType);
                    }
                }
                catch (Exception e) {

                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }

                // decrement the number of remaining actions
                Model.getInstance().getTurnTracker().decrementActionPoints(1);

            }
        };
    }

    /**
     * Before a disease is cured, remove 1 cube (of the player's choosing) from the player's current location for 1 action
     * After a disease is cured, remove all cubes of a single type from the player's current location for 1 action
     * @return The ICommand the UI will call execute on
     */
    public ICommand getDoTreatDisease() {

        return new ICommand() {
            @Override
            public void execute(FragmentManager fm) {

                // Get list of disease types on player's location
                // Maybe get count to display on popup
                List<String> presentDiseases = player.getLocation().getPresentDiseases();

                /* ****FRAME MAGIC**** */

                // Get this from the user
                String selectedDisease = "";

                int removedCubes = 0;

                if (Model.getInstance().getGameState().getDiseaseData().isCured(DiseaseType.getType(selectedDisease))){

                    removedCubes = player.getLocation().removeDiseaseCube(DiseaseType.getType(selectedDisease), true);
                }
                else {

                    removedCubes = player.getLocation().removeDiseaseCube(DiseaseType.getType(selectedDisease), false);
                }

                try {

                    Model.getInstance().getDiseaseCubes().putBackDiseaseCubes(selectedDisease, removedCubes);
                }
                catch (TooManyDiseaseCubesException e){

                    System.out.println("YOU'RE THE WORSTESTEST");
                }

                // decrement the number of remaining actions
                Model.getInstance().getTurnTracker().decrementActionPoints(1);

            }
        };
    }

    public ICommand getDoGiveKnowledge(final String cityName) {

        return new ICommand() {
            @Override
            public void execute(FragmentManager fm) {
                //Remove the card from the
                IPlayerCard card = player.removeCityCard(cityName);
                //Put the card in the receiving player's hand
                //and check to see if the player has too many cards
                boolean tooManyCards = false;

                //Decrement action points
                Model.getInstance().getTurnTracker().decrementActionPoints(1);
            }
        };
    }

    ICommand getDoReceiveKnowledge() {
        return null;
    }

    // will probably need an Object param also for additional data
    ICommand getDoSpecialAction(final String actionName) {
        return null;
    }

    public ICommand getIncrease() {
        return new ICommand() {
            @Override
            public void execute(FragmentManager fm) {
                IInfectionTracker tracker = Model.getInstance().getGameState().getInfectionTracker();
                tracker.advanceTrack(1);
                final int newInfectionRate = tracker.getInfectionsPerTurn();
            }
        };
    }

    public ICommand getInfect() {
        return new ICommand() {
            @Override
            public void execute(FragmentManager fm) {
            }
        };
    }

    public ICommand getIntensify() {
        return new ICommand() {
            @Override
            public void execute(FragmentManager fm) {
                Model.getInstance().getGameDecks().intensify();
            }
        };
    }

    // endregion

    /**
     * @return a list of all the special actions the player can perform
     */
    public List<String> getCanDoSpecialActions(){

        ArrayList<String> availableActions = new ArrayList<>();
        for (String action : specialActions){
            if(canPerformSpecialAction(action))
                availableActions.add(action);
        }
        return availableActions;
    }

    /**
     *
     * @param specialAction A string corresponding to a special action in the user's role
     * @return true if this action can be performed, otherwise false
     */
    public boolean canPerformSpecialAction(String specialAction){
        return false;
    }

    /**
     *
     * @param specialAction A string corresponding to a special action in the user's role
     * @return The return of the ICommand execute is as follows:
     */
    public ICommand getPerformSpecialAction(String specialAction){
        return null;
    }


    /*
    TODO: ALSO ADD...
        - outbreaks (3 steps in separate functions?)
        - infection
        - turn setup (that sets the number of remaining actions
    */


    public List<String> getSpecialActions() {
        return specialActions;
    }

    // ========================
    // ==== OPTION METHODS ====
    // ========================



}
