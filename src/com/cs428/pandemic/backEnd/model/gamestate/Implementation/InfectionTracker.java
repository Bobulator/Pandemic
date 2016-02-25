/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEnd.model.gamestate.Implementation;

import com.cs428.pandemic.backEnd.model.gamestate.IInfectionTracker;

/**
 *
 * @author James
 */
public class InfectionTracker implements IInfectionTracker
{
    private int[] infectionTrack;
    private int counter;
    
    public InfectionTracker(int[] infectionTrack)
    {
        this.infectionTrack = infectionTrack;
        this.counter = 0;
    }
    
    @Override
    public void advanceTrack(int amount) 
    {
        this.counter += amount;
    }

    @Override
    public void setInfectionsTrack(int amount) 
    {
        this.counter = amount;
    }

    @Override
    public int getInfectionsPerTurn() 
    {
        if(this.counter < this.infectionTrack.length)
        {
            return this.infectionTrack[this.counter];
        }
        else
        {
            return this.infectionTrack[this.infectionTrack.length - 1];
        }
    }
}
