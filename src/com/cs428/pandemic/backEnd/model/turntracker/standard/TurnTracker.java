/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEnd.model.turntracker.standard;

import com.cs428.pandemic.backEnd.model.turntracker.ITurnTracker;
import com.cs428.pandemic.backEnd.model.turntracker.TurnStateValue;

/**
 *
 * @author James
 */
public class TurnTracker implements ITurnTracker
{
    private boolean oneQuietNight;
    private int currentPlayer;
    private int numberOfPlayers;
    private TurnStateValue state;
    private int actionPoints;
    
    public TurnTracker(int numberOfPlayers)
    {
        this.oneQuietNight = false;
        this.currentPlayer = 0;
        this.numberOfPlayers = numberOfPlayers;
        this.state = null;
        this.actionPoints = 0;
    }
    
    @Override
    public int getCurrentPlayer() 
    {
        return this.currentPlayer;
    }

    @Override
    public void setCurrentPlayer(int currentPlayer) 
    {
        this.currentPlayer = currentPlayer;
    }

    @Override
    public void advancePlayer() 
    {
        this.oneQuietNight = false;
        this.currentPlayer = (this.currentPlayer + 1) % this.numberOfPlayers;
    }

    @Override
    public void advanceTurnState() 
    {
        switch(this.state)
        {
            case ACTION:
                this.setTurnState(TurnStateValue.DRAW_PLAYER);
                break;
            case DRAW_PLAYER:
                if(this.oneQuietNight)
                {
                    this.advancePlayer();
                    this.setTurnState(TurnStateValue.ACTION);
                }
                else
                {
                    this.setTurnState(TurnStateValue.DRAW_INFECT);
                }
                break;
            case DRAW_INFECT:
                this.advancePlayer();
                this.setTurnState(TurnStateValue.ACTION);
                break;
            default:
                break;
        }
    }

    @Override
    public void setTurnState(TurnStateValue value) 
    {
        this.state = value;
    }

    @Override
    public TurnStateValue getTurnState() 
    {
        return this.state;
    }

    @Override
    public void setCurrentActionPoints(int value) 
    {
        this.actionPoints = value;
    }

    @Override
    public int getCurrentActionPoints() 
    {
        return this.actionPoints;
    }

    /**
     * Uses up AMOUNT of action points. Advances turn state if it is reduced to 0
     * @param amount
     * @return whether there was an exception in the execution of the method
     */
    @Override
    public boolean decrementActionPoints(int amount) 
    {
        if(this.state == TurnStateValue.ACTION)
        {
            this.actionPoints -= amount;
            if(this.actionPoints < 0)
            {
                return false;
            }
            else
            {
                if(this.actionPoints == 0)
                {
                    this.advanceTurnState();
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
        this.oneQuietNight = true;
    }
}
