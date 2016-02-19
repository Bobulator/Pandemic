/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEnd.gamestate;

/**
 *
 * @author James
 */
public class GameState implements IGameState
{
    private int numberOfOutbreaks;
    private IInfectionTracker infections;
    private IDiseaseData data;
    
    public GameState(int numOutbreaks, IInfectionTracker infections, IDiseaseData data)
    {
        this.infections = infections;
        this.data = data;
        this.numberOfOutbreaks = numOutbreaks;
    }
    
    @Override
    public void outBreak() 
    {
        ++this.numberOfOutbreaks;
    }

    @Override
    public void setNumberOfOutbreaks(int amount) 
    {
        this.numberOfOutbreaks = amount;
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
