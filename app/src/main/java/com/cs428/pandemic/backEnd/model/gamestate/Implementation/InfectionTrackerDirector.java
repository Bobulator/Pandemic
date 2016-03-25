/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEnd.model.gamestate.Implementation;

import com.cs428.pandemic.backEnd.model.gamestate.IInfectionTracker;
import com.cs428.pandemic.backEnd.model.gamestate.IInfectionTrackerBuilder;
import com.cs428.pandemic.backEnd.model.gamestate.IInfectionTrackerDirector;

/**
 *
 * @author James
 */
public class InfectionTrackerDirector implements IInfectionTrackerDirector
{
    @Override
    public IInfectionTracker create(IInfectionTrackerBuilder builder) 
    {
        builder.addTrackValue(2)
                .addTrackValue(2)
                .addTrackValue(2)
                .addTrackValue(3)
                .addTrackValue(3)
                .addTrackValue(4)
                .addTrackValue(4);
        return builder.getTracker();
    }
}
