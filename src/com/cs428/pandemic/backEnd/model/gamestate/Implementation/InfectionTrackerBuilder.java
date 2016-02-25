/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEnd.model.gamestate.Implementation;

import com.cs428.pandemic.backEnd.model.gamestate.IInfectionTracker;
import com.cs428.pandemic.backEnd.model.gamestate.IInfectionTrackerBuilder;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author James
 */
public class InfectionTrackerBuilder implements IInfectionTrackerBuilder
{
    private List<Integer> values;
    
    public InfectionTrackerBuilder()
    {
        this.values = new ArrayList<Integer>();
    }
    
    @Override
    public IInfectionTrackerBuilder addTrackValue(int infectionsPerTurn) 
    {
        this.values.add(infectionsPerTurn);
        return this;
    }

    @Override
    public IInfectionTracker getTracker() 
    {
        int[] valueArray = new int[values.size()];
        for(int i = 0; i < values.size(); ++i)
        {
            valueArray[i] = values.get(i);
        }
        return new InfectionTracker(valueArray);
    }
}
