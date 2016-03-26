package com.cs428.pandemic.backEnd.command.roles;

import java.util.ArrayList;

import com.cs428.pandemic.backEnd.command.CaseyBFG;
import com.cs428.pandemic.backEnd.command.CommandBFG;
import com.cs428.pandemic.backEnd.command.ICommand;
import com.cs428.pandemic.backEnd.command.ICommandResult;
import com.cs428.pandemic.backEnd.model.IGameModel;
import com.cs428.pandemic.backEnd.model.player.IPlayer;

public class Researcher extends CaseyBFG{
	
	public Researcher(IPlayer player) {
		super(player);
		specialActions = new ArrayList<String>();
	}
	
	@Override
	public ICommand getCanGiveKnowledge(final String cityName, final IPlayer recPlayer){
		
	   	return new ICommand(){

				@Override
				public ICommandResult execute(IGameModel model) {
					// TODO Figure out how to get a card based on the cityName 
					//Make sure the current player has the card
					if(!player.getHand().hasCard(null)){
						return canDoFailure("The player does not have this card.");
					}
					
					String location1, location2;
					//Get the 2 players' locations
					location1 = player.getLocation().getName();
					location2 = recPlayer.getLocation().getName();
					
					//Make sure the player's locations match cityName
					if(!(location1.equals(location2))){
						return canDoFailure("Players' locations are not the same.");
					}
					
					return canDoSuccess("Share the crap out of this knowledge, John.");
				}
	    		
	    	};
	    	
	}

	@Override
	public ICommand getCanPerformSpecialAction(String specialAction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ICommand getPerformSpecialAction(String specialAction) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
