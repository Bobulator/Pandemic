package com.cs428.pandemic.backEnd.command.roles;

import java.util.ArrayList;

import com.cs428.pandemic.backEnd.command.CaseyBFG;
import com.cs428.pandemic.backEnd.command.CommandBFG;
import com.cs428.pandemic.backEnd.command.ICommand;
import com.cs428.pandemic.backEnd.command.ICommandResult;
import com.cs428.pandemic.backEnd.model.IGameModel;
import com.cs428.pandemic.backEnd.model.deck.CardCollection;
import com.cs428.pandemic.backEnd.model.deck.IPlayerCard;
import com.cs428.pandemic.backEnd.model.gamestate.DiseaseType;
import com.cs428.pandemic.backEnd.model.player.IPlayer;

/**
 * Created by Casey on 3/11/16.
 */
public class Scientist extends CaseyBFG {
	
    public Scientist(IPlayer player){
        super(player);
        specialActions = new ArrayList<String>();
    }

    @Override
    public ICommand getCanDiscoverCure(){

        return new ICommand() {
            @Override
            public ICommandResult execute(IGameModel model) {
            	
                // are you in a city with a research station?
                if(!player.getLocation().hasResearchStation())
                    return canDoFailure("This city has no research station.");

                // do you have 4 cards of the disease to cure?

                CardCollection<IPlayerCard> curCards = player.getHand();
                if (curCards.size() < 4 || (curCards.getColorCount(DiseaseType.RED) < 4 &&
											curCards.getColorCount(DiseaseType.BLUE) < 4 &&
						                	curCards.getColorCount(DiseaseType.BLACK) < 4 &&
						                	curCards.getColorCount(DiseaseType.YELLOW) < 4))
                    return canDoFailure("Player does not have 4 cards of the same color.");

                return canDoSuccess("Cure the crap out of this, John.");
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
