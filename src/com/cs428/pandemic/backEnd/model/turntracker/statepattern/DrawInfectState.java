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
public class DrawInfectState extends AbstractTurnState
{
    public DrawInfectState()
    {
        super(TurnStateValue.DRAW_INFECT);
    }
    
    @Override
    public void advanceTurnState(ITurnTrackerMachine machine) 
    {
        setTurnState(TurnStateValue.ACTION,false,machine);
        machine.advancePlayer();
    }

    @Override
    public void setOneQuietNight(ITurnTrackerMachine machine) 
    {
        advanceTurnState(machine);
    }
    
}
