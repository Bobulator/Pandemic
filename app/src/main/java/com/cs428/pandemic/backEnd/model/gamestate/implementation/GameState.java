/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEnd.model.gamestate.implementation;

import com.cs428.pandemic.backEnd.model.gamestate.TooManyOutbreaksException;
import com.cs428.pandemic.backEnd.model.gamestate.IDiseaseData;
import com.cs428.pandemic.backEnd.model.gamestate.IGameState;
import com.cs428.pandemic.backEnd.model.gamestate.IInfectionTracker;

/**
 *
 * @author James
 */
public class GameState implements IGameState
{
    private int maxOutBreaks;
    private int numberOfOutbreaks;
    private IInfectionTracker infections;
    private IDiseaseData data;
    
    public GameState(int maximumOutBreaks, int numOutbreaks, IInfectionTracker infections, IDiseaseData data)
    {
        maxOutBreaks = maximumOutBreaks;
        this.infections = infections;
        this.data = data;
        this.numberOfOutbreaks = numOutbreaks;
    }
    
    @Override
    public void outBreak() throws TooManyOutbreaksException
    {
        if(++numberOfOutbreaks >= maxOutBreaks)
        {
            throw new TooManyOutbreaksException(numberOfOutbreaks);
        }
    }

    @Override
    public void setNumberOfOutbreaks(int amount) throws TooManyOutbreaksException
    {
        this.numberOfOutbreaks = amount;
        if(numberOfOutbreaks >= maxOutBreaks)
        {
            throw new TooManyOutbreaksException(numberOfOutbreaks);
        }
    }

    @Override
    public int getNumberOfOutbreaks() 
    {
        return this.numberOfOutbreaks;
    }

    @Override
    public IInfectionTracker getInfectionTracker() 
    {
        return this.infections;
    }

    @Override
    public void setInfectionTracker(IInfectionTracker tracker) 
    {
        this.infections = tracker;
    }

    @Override
    public IDiseaseData getDiseaseData() 
    {
        return this.data;
    }

    @Override
    public void setDiseaseData(IDiseaseData data) 
    {
        this.data = data;
    }
}
