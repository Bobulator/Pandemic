/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEnd.gamestate;

/**
 * Builds the Infection Tracker (IInfection Tracker is complicated to build)
 * @author James
 */
public interface IInfectionTrackerBuilder 
{
    /**
     * adds an infection rate to the tracker
     * @param infectionsPerTurn the infection rate to be added
     * @return this
     */
    IInfectionTrackerBuilder addTrackValue(int infectionsPerTurn);
    
    /**
     * Returns the newly created Tracker
     * @return the new Tracker
     */
    IInfectionTracker getTracker();
}
