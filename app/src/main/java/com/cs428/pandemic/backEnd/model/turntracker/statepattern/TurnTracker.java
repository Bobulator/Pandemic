/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEnd.model.turntracker.statepattern;

import com.cs428.pandemic.backEnd.model.turntracker.ITurnTracker;
import com.cs428.pandemic.backEnd.model.turntracker.TurnStateValue;

/**
 * Turn Tracker that utilizes the State Pattern
 * @author James
 */
public class TurnTracker implements ITurnTracker, ITurnTrackerMachine
{
    int currentPlayer;
    int numberOfPlayers;
    int actionPoints;
    ITurnState currentState;

    public TurnTracker(int numberOfPlayers)
    {
        this.numberOfPlayers = numberOfPlayers;
        currentPlayer = 0;
        actionPoints = 0;
        currentState = new ActionState();
    }
    
    @Override
    public int getCurrentPlayer() 
    {
        return currentPlayer;
    }

    @Override
    public void setCurrentPlayer(int currentPlayer) 
    {
        this.currentPlayer = currentPlayer;
    }

    @Override
    public void advancePlayer() 
    {
        currentPlayer = (currentPlayer + 1) % numberOfPlayers;
    }

    @Override
    public void advanceTurnState() 
    {
        currentState.advanceTurnState(this);
    }

    @Override
    public void setTurnState(TurnStateValue value) 
    {
        currentState.setTurnStateValue(value, this);
    }

    @Override
    public TurnStateValue getTurnState() 
    {
        return currentState.getTurnState();
    }

    @Override
    public void setCurrentActionPoints(int value) 
    {
        actionPoints = value;
    }

    @Override
    public int getCurrentActionPoints() 
    {
        return this.actionPoints;
    }

    @Override
    public boolean decrementActionPoints(int amount) 
    {
        if(getTurnState() == TurnStateValue.ACTION)
        {
            if(actionPoints - amount < 0)
            {
                return false;
            }
            else
            {
                actionPoints -= amount;
                if(this.actionPoints == 0)
                {
                    advanceTurnState();
                }
                return true;
            }
        }
        else
        {
            return false;
        }
    }

    @Override
    public void setOneQuietNight() 
    {
        currentState.setOneQuietNight(this);
    }

    @Override
    public void setTurnState(ITurnState state) 
    {
        currentState = state;
    }
}
