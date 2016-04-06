/*
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

public abstract class JamesBFG {

    private HashMap<String, String> specialProperties = new HashMap<>();

    public String getRoleName(){ return ""; }
    public String getUIElementPath(){ return ""; }

    public int playerIndex = -1;

    protected JamesBFG(int playerIndex){
        this.playerIndex = playerIndex;
    }
    
    // =====================
    // ==== CAN METHODS ====
    // =====================

    // TODO: add in a setup function that is called at the beginning of each players turn.
    // This function will, among other things, set the number of actions on the TurnTracker to 4

    */
/**
     * CanDo method for the Drive/Ferry movement action.
     * @param cityName Name of the city to see if the player can travel there.
     * @return The return of the ICommand execute is as follows:
     * <ul>
     *     <li> getResult: (LEGAL|ILLEGAL) </li>
     *     <li> getMessage: error message if error </li>
     *     <li> getChainCommand: null </li>
     *     <li> getData: null </li>
     * </ul>
     *//*

    ICommand getCanDriveFerry(final String cityName){

        return new ICommand() {
            @Override
            public ICommandResult execute(IGameModel model) {

                // check if the city is an immediate neighbor
                ICity curLocation = model.getPlayers().get(playerIndex).getLocation();
                boolean isNeighbor = model.getGameMap().getCity(curLocation.getName()).isAdjacent(cityName);

                if(isNeighbor)
                    return canDoSuccess("Move to " + cityName + " is legal.");
                else
                    return canDoFailure("Move to " + cityName + " is illegal.");

                //TODO: ask about ICity vs city name as a string
            }
        };
    }

    */
/**
     * CanDo method for the Direct Flight movement action. Verify that cityName
     * matches one of the player's current cards.
     * @param cityName Name of the city to see if the player can travel there.
     * @return The return of the ICommand execute is as follows:
     * <ul>
     *     <li> getResult: (LEGAL|ILLEGAL) </li>
     *     <li> getMessage: error message if error </li>
     *     <li> getChainCommand: null </li>
     *     <li> getData: null </li>
     * </ul>
     *//*

    ICommand getCanDirectFlight(final String cityName){ return null; }

    */
/**
     * CanDo method for the Charter Flight movement action. Verifies that the
     * player has the card for the current city that they're in.
     * @param cityName Name of the city to see if the player can travel there.
     * @return The return of the ICommand execute is as follows:
     * <ul>
     *     <li> getResult: (LEGAL|ILLEGAL) </li>
     *     <li> getMessage: error message if error </li>
     *     <li> getChainCommand: null </li>
     *     <li> getData: null </li>
     * </ul>
     *//*

    ICommand getCanCharterFlight(final String cityName){ return null; }

    */
/**
     * CanDo method for the Shuttle Flight movement action. Verifies that the
     * player is currently in a city with a research station and that cutyName
     * has a research station.
     * @param cityName Name of the city to see if the player can travel there.
     * @return The return of the ICommand execute is as follows:
     * <ul>
     *     <li> getResult: (LEGAL|ILLEGAL) </li>
     *     <li> getMessage: error message if error </li>
     *     <li> getChainCommand: null </li>
     *     <li> getData: null </li>
     * </ul>
     *//*

    ICommand getCanShuttleFlight(final String cityName){ return null; }



    */
/**
     * Should always return true
     * @return The return of the ICommand execute is as follows:
     * <ul>
     *     <li> getResult: (LEGAL|ILLEGAL) </li>
     *     <li> getMessage: error message if error </li>
     *     <li> getChainCommand: null </li>
     *     <li> getData: null </li>
     * </ul>
     *//*

    ICommand getCanPass(){ return null; }


    */
/**
     *
     * @param cityName
     * @return
     *//*

    ICommand getCanBuildStation(final String cityName){ return null; }


    public ICommand getCanDiscoverCure(final DiseaseType diseaseType){

        return new ICommand() {
            @Override
            public ICommandResult execute(IGameModel model) {

                IPlayer curPlayer = model.getPlayers().get(playerIndex);
                // are you in a city with a research station?
                if(!curPlayer.getLocation().hasResearchStation())
                    return canDoFailure("This city has no research station.");

                // do you have 5 cards of the disease to cure?

                CardCollection<IPlayerCard> curCards = curPlayer.getHand();
                if (curCards.size() < 5 || curCards.getColorCount(diseaseType) < 5)
                    return canDoFailure("Not enough cards of type " + diseaseType);

                return canDoSuccess("Thou shalt discover cure.");
            }
        };
    }

    ICommand getCanTreatDisease(final String diseaseType){ return null; }

    ICommand getCanGiveKnowledge(final String cityName){ return null; }
    ICommand getCanReceiveKnowledge(final String cityName){ return null; }

    // will probably need an Object param also for additional data
    ICommand getCanSpecialAction(final String actionName) { return null; }


    // ====================
    // ==== DO METHODS ====
    // ====================
    ICommand getDoDriveFerry(final String cityName){ return null; }
    ICommand getDoDirectFlight(final String cityName){ return null; }
    ICommand getDoCharterFlight(final String cityName){ return null; }
    ICommand getDoShuttleFlight(final String cityName){ return null; }

    ICommand getDoPass(){ return null; }

    ICommand getDoBuildStation(final String cityName){ return null; }
    ICommand getDoDiscoverCure(final DiseaseType diseaseType, final List<String> cityNames){
        return new ICommand() {
            @Override
            public ICommandResult execute(IGameModel model) {

                IPlayer curPlayer = getCurPlayer(model);

                for(String city : cityNames) {
                    // remove the cards from the player's hand
                    ICityCard removedCard = curPlayer.getHand().removeCityCard(city);
                    if(removedCard == null)
                        return doFailure("You did not have the city card that you tried to discard.");

                    // add the cards to the discard pile
                    model.getGameDecks().addCard(removedCard, CardFamilyType.PLAYER, DeckType.DISCARD);
                }

                // set the cure marker
                model.getGameState().getDiseaseData().cure(diseaseType);

                // TODO: Finish the next two commented steps AFTER IDiseaseCubes has getters

                // check to see if the disease is eradicated

                    // if so, set that marker

                // decrement the number of remaining actions
                model.getTurnTracker().decrementActionPoints(1);

                return null;
            }
        };
    }
    ICommand getDoTreatDisease(final String diseaseType){ return null; }

    ICommand getDoGiveKnowledge(final String cityName){ return null; }
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

    */
/*
    TODO: ALSO ADD...
        - outbreaks (3 steps in separate functions?)
        - infection
        - turn setup (that sets the number of remaining actions

     *//*

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

    protected IPlayer getCurPlayer(IGameModel model){
        return model.getPlayers().get(playerIndex);
    }

}
*/
