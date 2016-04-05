package com.cs428.pandemic.backEnd.command.roles;

import android.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;

import com.cs428.pandemic.backEnd.command.CaseyBFG;
import com.cs428.pandemic.backEnd.command.CommandBFG;
import com.cs428.pandemic.backEnd.command.ICommand;
import com.cs428.pandemic.backEnd.command.ICommandResult;
import com.cs428.pandemic.backEnd.model.IGameModel;
import com.cs428.pandemic.backEnd.model.Model;
import com.cs428.pandemic.backEnd.model.deck.CardCollection;
import com.cs428.pandemic.backEnd.model.deck.CardFamilyType;
import com.cs428.pandemic.backEnd.model.deck.DeckType;
import com.cs428.pandemic.backEnd.model.deck.IPlayerCard;
import com.cs428.pandemic.backEnd.model.gamestate.DiseaseType;
import com.cs428.pandemic.backEnd.model.player.IPlayer;
import com.cs428.pandemic.frontEnd.enums.Role;


public class Scientist extends CommandBFG {

    public Scientist(IPlayer player){

        super(player, Role.SCIENTIST);
        specialActions = new ArrayList<String>();
    }

    @Override
    public boolean canDiscoverCure(){

        // are you in a city with a research station?
        if(!player.getLocation().hasResearchStation())
            return false;

        // do you have 4 cards of the disease to cure?

        CardCollection<IPlayerCard> curCards = player.getHand();
        if (curCards.size() < 4 || (curCards.getColorCount(DiseaseType.RED) < 4 &&
                                    curCards.getColorCount(DiseaseType.BLUE) < 4 &&
                                    curCards.getColorCount(DiseaseType.BLACK) < 4 &&
                                    curCards.getColorCount(DiseaseType.YELLOW) < 4))
            return false;

        return true;
    }

    public ICommand getDoDiscoverCure() {
        return new ICommand() {
            @Override
            public void execute(FragmentManager fm) {

                // Show user frame of all disease types.
                // Types for which the player has enough city cards to cure the disease will be enabled

                // TODO Need a way to get the list of
                List<String> cureableDiseases = new ArrayList<String>();

                for (DiseaseType d : DiseaseType.values()){

                    if (player.getHand().getColorCount(d) >= 4){

                        cureableDiseases.add(d.name());
                    }
                }

                /* ****FRAME MAGIC**** */

                // Get this from the user
                String diseaseName = "";

                // Show user all city cards of diseaseType in their hand
                // TODO NEED FUNCTION FOR ALL CITY CARDS IN PLAYER'S HAND

                // Have user choose 4 cards to discard

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

                DiseaseType diseaseType = DiseaseType.valueOf(diseaseName);
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

    @Override
    public List<String> getCanDoSpecialActions(){

        return null;
    }

    public boolean canPerformSpecialAction(String specialAction){

        return false;
    }

	@Override
	public ICommand getPerformSpecialAction(String specialAction) {

		return null;
	}
}
