/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEnd.test;

import com.cs428.pandemic.backEnd.model.gamestate.IInfectionTracker;

/**
 *
 * @author James
 */
public class MockInfectionTracker implements IInfectionTracker
{
    @Override
    public void advanceTrack(int amount) 
    {
    }

    @Override
    public void setInfectionsTrack(int amount) 
    {
    }

    @Override
    public int getInfectionsPerTurn() 
    {
        return 0;
    }
}
