/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEnd.test.model.gamestate;

import com.cs428.pandemic.backEnd.model.gamestate.IInfectionTracker;
import com.cs428.pandemic.backEnd.model.gamestate.IInfectionTrackerBuilder;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author James
 */
public class MockInfectionTrackBuilder implements IInfectionTrackerBuilder
{
    private List<Integer> values;
    
    public MockInfectionTrackBuilder()
    {
        values = new ArrayList<Integer>();
    }
    
    @Override
    public IInfectionTrackerBuilder addTrackValue(int infectionsPerTurn) 
    {
        values.add(infectionsPerTurn);
        return this;
    }

    @Override
    public IInfectionTracker getTracker() 
    {
        return new MockInfectionTracker();
    }
    
    public List<Integer> getValues()
    {
        return values;
    }
}
