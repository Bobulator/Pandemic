package com.cs428.pandemic.backEnd.command;

import com.cs428.pandemic.backEnd.model.IGameModel;
import com.cs428.pandemic.backEnd.model.deck.*;
import com.cs428.pandemic.backEnd.model.gamestate.DiseaseType;
import com.cs428.pandemic.backEnd.model.gamestate.IInfectionTracker;
import com.cs428.pandemic.backEnd.model.map.ICity;
import com.cs428.pandemic.backEnd.model.player.IPlayer;

import java.util.HashMap;
import java.util.List;

import static com.cs428.pandemic.backEnd.command.ICommandResult.ResultType.*;

public abstract class CaseyBFG {

    private HashMap<String, String> specialProperties = new HashMap<>();
    protected List<String> specialActions;
    public String getRoleName(){ return ""; }
    public String getUIElementPath(){ return ""; }

    protected IPlayer player;

    protected CaseyBFG(IPlayer player){
    	this.player = player;
    }
    
    // =====================
    // ==== CAN METHODS ====
    // =====================

    // TODO: add in a setup function that is called at the beginning of each players turn.
    // This function will, among other things, set the number of actions on the TurnTracker to 4

    /**
     * CanDo method for the Drive/Ferry movement action.
     * @return The return of the ICommand execute is as follows:
     * <ul>
     *     <li> getResult: (LEGAL|ILLEGAL) </li>
     *     <li> getMessage: error message if error </li>
     *     <li> getChainCommand: null </li>
     *     <li> getData: null </li>
     * </ul>
     */
    public ICommand getCanDriveFerry(){

        return new ICommand() {
            @Override
            public ICommandResult execute(IGameModel model) {
            	
            	// Make sure the player's current location has at least one adjacent city
            	if(player.getLocation().getNumberOfAdjacentCities() <= 0){
            		return canDoFailure("There is no ferry to/from the moon.");
            	}
            	
	            // Make sure the player has actions remaining
	            if(!(model.getTurnTracker().getCurrentActionPoints() > 0)){
	            	return canDoFailure("There are no actions remaining.");
	            }
            	
            	return canDoSuccess("You CAN drive the crap out of this ferry, John.");
            }
        };
    }

    /**
     * CanDo method for the Direct Flight movement action. Verify that cityName
     * matches one of the player's current cards.
     * @return The return of the ICommand execute is as follows:
     * <ul>
     *     <li> getResult: (LEGAL|ILLEGAL) </li>
     *     <li> getMessage: error message if error </li>
     *     <li> getChainCommand: null </li>
     *     <li> getData: null </li>
     * </ul>
     */
    public ICommand getCanFlyDirect(){ 
    	
        return new ICommand() {
            @Override
            public ICommandResult execute(IGameModel model) {
            	
            	// Make sure the player has at least 1 city card
            	// TODO Make it so we can get the number of city cards (not just number of cards)
            	if(!(player.getHand().size() > 0)){
            		return canDoFailure("The player does not have this card.");
            	}
            	
	            // Make sure the player has actions remaining
	            if(!(model.getTurnTracker().getCurrentActionPoints() > 0)){
	            	return canDoFailure("There are no actions remaining.");
	            }
	            
            	return canDoSuccess("Take the crap out of this direct flight, John.");
            	
            }
        };
    }

    /**
     * CanDo method for the Charter Flight movement action. Verifies that the
     * player has the card for the current city that they're in.
     * @return The return of the ICommand execute is as follows:
     * <ul>
     *     <li> getResult: (LEGAL|ILLEGAL) </li>
     *     <li> getMessage: error message if error </li>
     *     <li> getChainCommand: null </li>
     *     <li> getData: null </li>
     * </ul>
     */
    public ICommand getCanFlyCharter(){ //Parameter not necessary here; just the Player's location is germane. 
        return new ICommand() {
            @Override
            public ICommandResult execute(IGameModel model) {
            	
            	// Make sure the player has the card that corresponds to the player's location
            	// Replace null with the player's current location when that functionality exists
            	if(!player.getHand().hasCard(null)){
            		return canDoFailure("The player does not have the card corresponding to the player's location.");
            	}
            	
	            // Make sure the player has actions remaining
	            if(!(model.getTurnTracker().getCurrentActionPoints() > 0)){
	            	return canDoFailure("There are no actions remaining.");
	            }
            	
            	return canDoSuccess("You CAN charter the crap ouf of this flight, John.");
            }
        };
    }

    /**
     * CanDo method for the Shuttle Flight movement action. Verifies that the
     * player is currently in a city with a research station and that cityName
     * has a research station.
     * @return The return of the ICommand execute is as follows:
     * <ul>
     *     <li> getResult: (LEGAL|ILLEGAL) </li>
     *     <li> getMessage: error message if error </li>
     *     <li> getChainCommand: null </li>
     *     <li> getData: null </li>
     * </ul>
     */
	public ICommand getCanFlyShuttle(){ 
        return new ICommand() {
            @Override
            public ICommandResult execute(IGameModel model) {
            	
            	// Make sure the player's location has a research station
            	if(!player.getLocation().hasResearchStation()){
            		return canDoFailure("The player's location does not have a research station.");
            	}
            	
            	// Make sure there is at least 1 other research station on the board (besides the one at the player's current location)
            	if(model.getMap().getNumberOfResearchStationLocations() <= 1){
            		return canDoFailure("There are no research stations to travel to, John.");
            	}
            	
	            // Make sure the player has actions remaining
	            if(!(model.getTurnTracker().getCurrentActionPoints() > 0)){
	            	return canDoFailure("There are no actions remaining.");
	            }
            	
            	return canDoSuccess("You CAN shuttle the crap ouf of this flight, John.");
            }
        };
    }



    /**
     * Makes sure the player has actions remaining
     * @return The return of the ICommand execute is as follows:
     * <ul>
     *     <li> getResult: (LEGAL|ILLEGAL) </li>
     *     <li> getMessage: error message if error </li>
     *     <li> getChainCommand: null </li>
     *     <li> getData: null </li>
     * </ul>
     */
    ICommand getCanPass(){ 
        return new ICommand() {
            @Override
            public ICommandResult execute(IGameModel model) {
            	
	            // Make sure the player has actions remaining
	            if(!(model.getTurnTracker().getCurrentActionPoints() > 0)){
	            	return canDoFailure("There are no actions remaining.");
	            }
            	
            	return canDoSuccess("You CAN pass the crap out of this, John.");
            }
        };
    }


    /**
     * Makes sure the player has the card associated with that player's location, the player's location does not currently
     * have a research station, and that the player has remaining actions
     * @return The return of the ICommand execute is as follows:
     * <ul>
     *     <li> getResult: (LEGAL|ILLEGAL) </li>
     *     <li> getMessage: error message if error </li>
     *     <li> getChainCommand: null </li>
     *     <li> getData: null </li>
     * </ul>
     */
    public ICommand getCanBuildStation(){ 
    	
        return new ICommand() {
            @Override
            public ICommandResult execute(IGameModel model) {
	            
	            // Make sure the player has the card associated with the player's location
	            // TODO Make sure you can see if the player has this card with a String
	            // Replace "null" with the player's location when this functionality exists
	            if(!player.getHand().hasCard(null)){
	            	return canDoFailure("Player does not have this card.");
	            }
	            
	            // Check to see if the city already has a research station
	            if(player.getLocation().hasResearchStation()){
	            	return canDoFailure("This city already has a research station.");
	            }
	            
	            // Make sure the player has actions remaining
	            if(!(model.getTurnTracker().getCurrentActionPoints() > 0)){
	            	return canDoFailure("There are no actions remaining.");
	            }
	            
	            // All checks passed!
	            return canDoSuccess("You CAN build the crap out of this research station, John.");
            	
            }
           
        };
    }
    
    /**
     * Checks to see if the player can discover a cure. The player must be at a location with a research station, the player must
     * have at least 5 cards, and at least 5 of the player's cards must be the same color
     * @return The return of the ICommand execute is as follows:
     * <ul>
     *     <li> getResult: (LEGAL|ILLEGAL) </li>
     *     <li> getMessage: error message if error </li>
     *     <li> getChainCommand: null </li>
     *     <li> getData: null </li>
     * </ul>
     */
    public ICommand getCanDiscoverCure(){

        return new ICommand() {
            @Override
            public ICommandResult execute(IGameModel model) {
            	
                // are you in a city with a research station?
                if(!player.getLocation().hasResearchStation())
                    return canDoFailure("This city does not have a research station.");

                // Make sure the player has at least 5 cards, and that the player 
                // has 5 red, 5 blue, 5 black, or 5 yellow cards
                // TODO player.getHand() does not work; we
                CardCollection<IPlayerCard> curCards = player.getHand();
                if (curCards.size() < 5 || (curCards.getColorCount(DiseaseType.RED) < 5 &&
                							curCards.getColorCount(DiseaseType.BLUE) < 5 &&
						                	curCards.getColorCount(DiseaseType.BLACK) < 5 &&
						                	curCards.getColorCount(DiseaseType.YELLOW) < 5))
                    return canDoFailure("The player does not have 5 cards of the same color.");

	            // Make sure the player has actions remaining
	            if(!(model.getTurnTracker().getCurrentActionPoints() > 0)){
	            	return canDoFailure("There are no actions remaining.");
	            }
                
                return canDoSuccess("Cure the crap out of this, John.");
            }
        };
    }

    /**
     * Checks to see if the player's current location has at least 1 disease cube on it and that there are actions remaining.
     * @return The return of the ICommand execute is as follows:
     * <ul>
     *     <li> getResult: (LEGAL|ILLEGAL) </li>
     *     <li> getMessage: error message if error </li>
     *     <li> getChainCommand: null </li>
     *     <li> getData: null </li>
     * </ul>
     */
    public ICommand getCanTreatDisease(){ 
    	return new ICommand(){

			@Override
			public ICommandResult execute(IGameModel model) {
				
				// Make sure the player's current location has at least 1 disease cube on it
				if(!(player.getLocation().hasDiseaseCubes())){
					return canDoFailure("There are no disease cubes on the player's location.");
				}
				
	            // Make sure the player has actions remaining
	            if(!(model.getTurnTracker().getCurrentActionPoints() > 0)){
	            	return canDoFailure("There are no actions remaining.");
	            }
	            
	            return canDoSuccess("You CAN treat the crap out of a disease, John.");
			}
    		
    	};
    }

    /**
     * Check to see if the player's location is being populated by any other players, that the player
     * has at least one card, and that there are actions remaining
     * @return The return of the ICommand execute is as follows:
     * <ul>
     *     <li> getResult: (LEGAL|ILLEGAL) </li>
     *     <li> getMessage: error message if error </li>
     *     <li> getChainCommand: null </li>
     *     <li> getData: null </li>
     * </ul>
     */
    public ICommand getCanGiveKnowledge(final String cityName, final IPlayer recPlayer){
    	
    	return new ICommand(){

			@Override
			public ICommandResult execute(IGameModel model) {
				
				// Make sure at least one other player is at the player's location
				boolean hasOtherPlayer = false;
				for(IPlayer gamePlayer : model.getPlayers()){
					
					// Make sure you aren't looking at yourself
					if(player.getPlayerIndex() != gamePlayer.getPlayerIndex()){
						
						// Check other players' locations against your location
						if(player.getLocation().getName().equals(gamePlayer.getLocation().getName())){
							
							hasOtherPlayer = true;
							break;
						}
					}
				}
				
				if(!hasOtherPlayer){
					return canDoFailure("There are no other players at this location.");
				}
				
				// Make sure the player has at least one city card
				// TODO change .size() to something that actually will work for just city cards
				if(player.getHand().size() <= 0){
					return canDoFailure("The player does not have any city cards.");
				}
				
	            // Make sure the player has actions remaining
	            if(model.getTurnTracker().getCurrentActionPoints() <= 0){
	            	return canDoFailure("There are no actions remaining.");
	            }
	            
				return canDoSuccess("Share the crap out of this knowledge, John.");
			}
    		
    	};
    }
    
    public ICommand getCanReceiveKnowledge(final String cityName){ return null; }

    // will probably need an Object param also for additional data
    ICommand getCanSpecialAction(final String actionName) { return null; }


    // ====================
    // ==== DO METHODS ====
    // ====================
    public ICommand getDoDriveFerry(final String cityName){ 
        return new ICommand() {
            @Override
            public ICommandResult execute(IGameModel model) {
            	
            	// Set the player's location to cityName
            	player.setLocation(model.getMap().getCity(cityName));
            	
            	// Decrement action counter
            	model.getTurnTracker().decrementActionPoints(1);
            	
            	return doSuccess("Drive the crap out of that ferry, John.");
            }
        };
    }
    
    /**
     * Performs the fly action by removing the card associated with the provided cityCardName from the player's 
     * hand and discarding it, moving the player to the provided destination, and decrementing the action counter.
     * @param destination The name of the city the player is moving to.
     * @return the return of the ICommand execute is as follows:
     * <ul>
     *     <li> getResult: (SUCCESS) </li>
     *     <li> getMessage: success message </li>
     *     <li> getChainCommand: null </li>
     *     <li> getData: germane information (if necessary) </li>
     * </ul>
     */
    public ICommand getDoFly(final String cityCardName, final String destination){
        return new ICommand() {
            @Override
            public ICommandResult execute(IGameModel model) {
            	
            	// Remove the card represented by cityCardName from the player's hand
            	IPlayerCard removedCard = player.removeCityCard(cityCardName);
            	
            	// Discard the removed card
            	model.getGameDecks().addCard(removedCard, CardFamilyType.PLAYER, DeckType.DISCARD);
            	
            	// Set the player's location to destination
            	player.setLocation(model.getMap().getCity(destination));
            	
            	// Decrement action counter
            	model.getTurnTracker().decrementActionPoints(1);

            	return doSuccess("Fly the crap out of it, John.");
            }
        };
    }
    
    /**
     * Performs the Direct Flight action by moving the player to the provided destination, removing
     * the card associated with the destination from the player's and discarding it, and decrementing 
     * the action counter.
     * @param destination The name of the city the player is moving to.
     * @return The return of the ICommand execute is as follows:
     * <ul>
     *     <li> getResult: (SUCCESS) </li>
     *     <li> getMessage: success message </li>
     *     <li> getChainCommand: null </li>
     *     <li> getData: germane information (if necessary) </li>
     * </ul>
     */
    public ICommand getDoDirectFlight(final String destination){ 
        return new ICommand() {
            @Override
            public ICommandResult execute(IGameModel model) {
            	
            	// Set the player's location to destination
            	player.setLocation(model.getMap().getCity(destination));
            	
            	// Remove the card associated with destination from the player's hand
            	IPlayerCard removedCard = player.removeCityCard(destination);
            	
            	// Discard the removed card
            	model.getGameDecks().addCard(removedCard, CardFamilyType.PLAYER, DeckType.DISCARD);
            	
            	// Decrement action counter
            	model.getTurnTracker().decrementActionPoints(1);
            	
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
     *     <li> getResult: (SUCCESS) </li>
     *     <li> getMessage: success message </li>
     *     <li> getChainCommand: null </li>
     *     <li> getData: germane information (if necessary) </li>
     * </ul>
     */
    public ICommand getDoCharterFlight(final String destination){ 
    	
        return new ICommand() {
            @Override
            public ICommandResult execute(IGameModel model) {
            	
            	// Set the player's location to cityName
            	player.setLocation(model.getMap().getCity(destination));
            	
            	// Discard the card associated with cityName
            	IPlayerCard removedCard = player.removeCityCard(player.getLocation().getName());
            	
            	// Discard the removed card
            	model.getGameDecks().addCard(removedCard, CardFamilyType.PLAYER, DeckType.DISCARD);
            	
            	// Decrement action counter
            	model.getTurnTracker().decrementActionPoints(1);
            	
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
     *     <li> getResult: (SUCCESS) </li>
     *     <li> getMessage: success message </li>
     *     <li> getChainCommand: null </li>
     *     <li> getData: germane information (if necessary) </li>
     * </ul>
     */
    public ICommand getDoShuttleFlight(final String destination){ 
        return new ICommand() {
            @Override
            public ICommandResult execute(IGameModel model) {
            	
            	// Set the player's location to destination
            	player.setLocation(model.getMap().getCity(destination));
            	
            	// Decrement action counter
            	model.getTurnTracker().decrementActionPoints(1);
            	
            	return doSuccess("Shuttle the crap out of this flight, John.");
            }
        };
    }

    /**
     * Decrements the player's action count by 1
     * @return The return of the ICommand execute is as follows:
     * <ul>
     *     <li> getResult: (SUCCESS) </li>
     *     <li> getMessage: success message </li>
     *     <li> getChainCommand: null </li>
     *     <li> getData: germane information (if necessary) </li>
     * </ul>
     */
    public ICommand getDoPass(){ 
    	
        return new ICommand() {
            @Override
            public ICommandResult execute(IGameModel model) {
            	
            	// Decrement action counter
            	model.getTurnTracker().decrementActionPoints(1);
            	
            	return doSuccess("Pass the crap out of this, John.");
            }
        };
    	
    }

    /**
     * Puts a research station on the player's location. If there are no research stations remaining, the player
     * chooses which city to remove a research station from. Removes the card associated with the player's location
     * from the player's hand and discards that card. Decrements the action counter.
     * <ul>
     *     <li> getResult: (SUCCESS) </li>
     *     <li> getMessage: success message </li>
     *     <li> getChainCommand: null </li>
     *     <li> getData: germane information (if necessary) </li>
     * </ul>
     */
    public ICommand getDoBuildStation(){ 
    	
        return new ICommand() {
            @Override
            public ICommandResult execute(IGameModel model) {
            	// Check to see if there are available research stations
            	// If not, tell the GUI to have the player choose which research station to move
            	// Add getCitiesWithResearchStations() to the GameMap
            	if(model.getMap().getRemainingResearchStationCount() <= 0){
            		List<String> researchStationLocations = model.getMap().getResearchStationLocations();
            		// GUI must have the player choose one of these locations to remove a research station from
            		// Return null if cancel is selected
            		String selectedLocation = "";
            		
            		// Remove the research station from the selected location
            		model.getMap().getCity(selectedLocation).removeResearchStation();
            	}
            	
        		// Place the research station at the player's location 
        		player.getLocation().addResearchStation();
            	
            	// Remove the card from the player's hand
            	IPlayerCard removedCard = player.removeCityCard(player.getLocation().getName());
            	
            	// Discard the card
            	model.getGameDecks().addCard(removedCard, CardFamilyType.PLAYER, DeckType.DISCARD);
            	
            	// Decrement the action points
            	model.getTurnTracker().decrementActionPoints(1);
            	
            	return doSuccess("Build the crap out of this research station, John.");
            }
        };
    }
    ICommand getDoDiscoverCure(final DiseaseType diseaseType, final List<String> cityNames){
        return new ICommand() {
            @Override
            public ICommandResult execute(IGameModel model) {

                for(String city : cityNames) {
                    // remove the cards from the player's hand
                    IPlayerCard removedCard = player.removeCityCard(city);
                    if(removedCard == null)
                        return doFailure("You did not have the city card that you tried to discard.");

                    // add the cards to the discard pile
                    model.getGameDecks().addCard(removedCard, CardFamilyType.PLAYER, DeckType.DISCARD);
                }

                // set the cure marker
                model.getGameState().getDiseaseData().cure(diseaseType);
                
                // check to see if the disease is eradicated
                // if so, set that marker
                try {
					if(model.getDiseaseCubes().getDiseaseCount(diseaseType) == 24){
						model.getGameState().getDiseaseData().eradicate(diseaseType);
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}

                // decrement the number of remaining actions
                model.getTurnTracker().decrementActionPoints(1);

                return doSuccess("Cure discoverd.");
            }
        };
    }
    ICommand getDoTreatDisease(final String diseaseType){ return null; }

    public ICommand getDoGiveKnowledge(final String cityName){
    	
        return new ICommand() {
            @Override
            public ICommandResult execute(IGameModel model) {
            	//Remove the card from the 
            	IPlayerCard card = player.removeCityCard(cityName);
            	//Put the card in the receiving player's hand
            	//and check to see if the player has too many cards
            	boolean tooManyCards = false;
            	
            	//Decrement action points
                model.getTurnTracker().decrementActionPoints(1);

                // TODO How do players discard? And how do we trigger it?
                
                
            	return doSuccess("Shared the crap out of that knowledge, John", tooManyCards);
            }
        };
    }
    ICommand getDoReceiveKnowledge(final String cityName){ return null; }

    // will probably need an Object param also for additional data
    ICommand getDoSpecialAction(final String actionName) { return null; }
    
    public ICommand getIncrease() 
    {
        return new ICommand()
        {
            @Override
            public ICommandResult execute(IGameModel model) 
            {
                IInfectionTracker tracker = model.getGameState().getInfectionTracker();
                tracker.advanceTrack(1);
                final int newInfectionRate = tracker.getInfectionsPerTurn();
                return doSuccess("Infection Rate advanced by 1. Will now draw " + newInfectionRate + " infections per turn", newInfectionRate);
            }
        };
    }

    public ICommand getInfect() 
    {
        return new ICommand()
        {
            @Override
            public ICommandResult execute(IGameModel model) 
            {
                return doSuccess("Succeeded in infecting the stuffz", true);
            }
        };
    }

    public ICommand getIntensify() 
    {
        return new ICommand()
        {
            @Override
            public ICommandResult execute(IGameModel model) 
            {
                model.getGameDecks().intensify();
                return doSuccess("Shuffled Infection Discard into Infection Draw", true);
            }
        };
    }

    public abstract ICommand getCanPerformSpecialAction(String specialAction);
    
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

    protected ICommandResult canDoSuccess(final String message){
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

    protected ICommandResult canDoFailure(final String message){
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

    protected ICommandResult doSuccess(final String message){

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

    protected ICommandResult doFailure(final String message){

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

    protected ICommandResult canDoSuccess(final String message, final Object data){
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

    protected ICommandResult canDoFailure(final String message, final Object data){
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

    protected ICommandResult doSuccess(final String message, final Object data){
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

    protected ICommandResult doFailure(final String message, final Object data){
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

    public List<String> getSpecialActions(){
    	return specialActions;
    }

}
