/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEnd.model.gamestate;

import com.cs428.pandemic.backEnd.model.gamestate.implementationtemp.*;

/**
 *
 * @author James
 */
public class StandardGameStateFactory implements IGameStateFactory
{
    private IInfectionTrackerDirector director;
    
    public StandardGameStateFactory()
    {
        director = new InfectionTrackerDirector();
    }

    @Override
    public IGameState createGameState() 
    {
        return new GameState(0,createInfectionTracker(),createDiseaseData());
    }

    @Override
    public IInfectionTracker createInfectionTracker() 
    {
        IInfectionTrackerBuilder builder = new InfectionTrackerBuilder();
        return director.create(builder);
    }

    @Override
    public IDiseaseData createDiseaseData() 
    {
        return new MappedDiseaseData();
    }
}
