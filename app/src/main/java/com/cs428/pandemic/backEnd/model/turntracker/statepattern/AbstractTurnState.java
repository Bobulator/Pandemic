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
public abstract class AbstractTurnState implements ITurnState
{
    private TurnStateValue state;
    private TurnStateFactory fact;
    
    public AbstractTurnState(TurnStateValue state)
    {
        this.state = state;
        fact = new TurnStateFactory();
    }
    
    @Override
    public TurnStateValue getTurnState() 
    {
        return state;
    }
    
    @Override
    public void setTurnStateValue(TurnStateValue val, ITurnTrackerMachine machine) 
    {
        if(this.getTurnState() != val)
        {
            machine.setTurnState(fact.createState(val, false));
        }
    }
    
    protected void setTurnState(TurnStateValue val, boolean oneQuietNight, ITurnTrackerMachine machine)
    {
        machine.setTurnState(fact.createState(val, oneQuietNight));
    }
}
