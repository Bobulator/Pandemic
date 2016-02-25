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
public class OneQuietNightActionState extends AbstractTurnState
{
    public OneQuietNightActionState()
    {
        super(TurnStateValue.ACTION);
    }

    @Override
    public void advanceTurnState(ITurnTrackerMachine machine) 
    {
        setTurnState(TurnStateValue.DRAW_PLAYER,true,machine);
    }

    @Override
    public void setOneQuietNight(ITurnTrackerMachine machine) 
    {
    }
    
}
