/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEnd.model.turntracker.statepattern;

import com.cs428.pandemic.backEnd.model.turntracker.TurnStateValue;

/**
 *
 * @author James
 */
public class DrawPlayerState extends AbstractTurnState
{
    public DrawPlayerState()
    {
        super(TurnStateValue.DRAW_PLAYER);
    }
    
    @Override
    public void advanceTurnState(ITurnTrackerMachine machine) 
    {
        setTurnState(TurnStateValue.DRAW_INFECT,false,machine);
    }

    @Override
    public void setOneQuietNight(ITurnTrackerMachine machine) 
    {
        setTurnState(TurnStateValue.DRAW_PLAYER,true,machine);
    }
    
}
