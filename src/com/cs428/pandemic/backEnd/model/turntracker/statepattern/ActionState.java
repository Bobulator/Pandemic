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
public class ActionState extends AbstractTurnState
{
    public ActionState()
    {
        super(TurnStateValue.ACTION);
    }

    @Override
    public void advanceTurnState(ITurnTrackerMachine machine) 
    {
        setTurnState(TurnStateValue.DRAW_PLAYER,false,machine);
    }

    @Override
    public void setOneQuietNight(ITurnTrackerMachine machine) 
    {
        setTurnState(TurnStateValue.ACTION,true,machine);

    }
    
}
