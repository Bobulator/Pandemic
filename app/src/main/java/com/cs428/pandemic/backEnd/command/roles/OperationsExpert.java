package com.cs428.pandemic.backEnd.command.roles;

import java.util.ArrayList;
import java.util.List;

import com.cs428.pandemic.backEnd.command.CaseyBFG;
import com.cs428.pandemic.backEnd.command.ICommand;
import com.cs428.pandemic.backEnd.command.ICommandResult;
import com.cs428.pandemic.backEnd.model.IGameModel;
import com.cs428.pandemic.backEnd.model.deck.CardFamilyType;
import com.cs428.pandemic.backEnd.model.deck.DeckType;
import com.cs428.pandemic.backEnd.model.deck.IPlayerCard;
import com.cs428.pandemic.backEnd.model.player.IPlayer;

public class OperationsExpert extends CaseyBFG{
	
	public OperationsExpert(IPlayer player){
		super(player);
		specialActions = new ArrayList<String>();
		//specialActions.add("Build free research station");
		specialActions.add("Move from research station");
	}
	
	@Override
	public ICommand getCanBuildStation(){ 
	       return new ICommand() {
	            @Override
	            public ICommandResult execute(IGameModel model) {
	            	
	            	// Make sure there is not a research station at the player's location
	            	if(player.getLocation().hasResearchStation()){
	            		return canDoFailure("There is already a research station at this location.");
	            	}
	            	
	            	// Make sure the player has actions remaining
	            	if(model.getTurnTracker().getCurrentActionPoints() <= 0){
	            		return canDoFailure("The player has no more actions remaining.");
	            	}
	            	
	            	return canDoSuccess("You CAN build the crap out of this research station, John.");
	            }
	        }; 
	}
	
	@Override
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
            	
            	// Decrement the action points
            	model.getTurnTracker().decrementActionPoints(1);
            	
            	return doSuccess("Build the crap out of this research station, John.");
            }
        };
    }
	
	/**
	 * Checks to see if the player is at a research station and has at least one city card
	 * @param specialAction the specific special action to be checked
	 * @return The return of the ICommand execute is as follows:
     * <ul>
     *     <li> getResult: (SUCCESS) </li>
     *     <li> getMessage: success message </li>
     *     <li> getChainCommand: null </li>
     *     <li> getData: germane information (if necessary) </li>
     * </ul>
	 */
	@Override
	public ICommand getCanPerformSpecialAction(String specialAction){
		switch(specialAction){
			case "Move from research station":
				return getCanMoveFromResearchStation();
			default:
				return null;
		}
			
	}
	
	private ICommand getCanMoveFromResearchStation(){
		return new ICommand() {
            @Override
            public ICommandResult execute(IGameModel model) {
            	
            	// Make sure the player's location has a research station
            	if(!player.getLocation().hasResearchStation()){
            		return canDoFailure("The player's location does not have a research station.");
            	}
            	
            	// Make sure the player has at least 1 city card
        		if(player.getHand().size() == 0){ // THIS WON'T WORK; see TODO above
        			return canDoFailure("The player doesn't have any cards to discard.");
        		}
            	
            	
            	return canDoSuccess("You CAN shuttle the crap out of this flight, John.");
            }
        };
	}
	
	@Override
	public ICommand getPerformSpecialAction(String specialAction){
		switch(specialAction){
			case "Move from research station":
				return getDoMoveFromResearchStation();
			default:
				return null;
				
		}
	}
	
	private ICommand getDoMoveFromResearchStation(){
       return new ICommand() {
            @Override
            public ICommandResult execute(IGameModel model) {
            	
            	List<String> possibleDestinations = model.getMap().getAllOtherLocations(player.getLocation().getName());
            	// TODO Front enders, use this list to have the player choose the destination
            	
            	// TODO James, we need a way to just get city cards from the player's hand
            	List<String> cityCards = null;
            	// TODO Front enders, use this list to have the player choose a card to discard
            	
            	
            	// Make sure the player did not click cancel
            	
            	// TODO Get the destination from the UI
            	String destination = "";
            	// Set the player's location to destination
            	player.setLocation(model.getMap().getCity(destination));
            	
            	// TODO Get the cityName from the UI
            	String cityName = "";
            	// Remove the card associated with cityName from the player's hand
            	IPlayerCard removedCard = player.removeCityCard(cityName);
            	
            	// Discard the removed card
            	model.getGameDecks().addCard(removedCard, CardFamilyType.PLAYER, DeckType.DISCARD);
            	
            	// Decrement action counter
            	model.getTurnTracker().decrementActionPoints(1);
            	
            	return doSuccess("Shuttle the crap out of this flight, John.");
            }
        };
	}
}
