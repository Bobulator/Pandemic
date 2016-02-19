/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEnd.gamestate;

/**
 * Generates an infection tracker from a builder. Directs the builder in the creation of the Infection Tracker
 * @author James
 */
public interface IInfectionTrackerDirector 
{
    /**
     * Generates the InfectionTracker from a builder
     * @param builder the builder in charge of managing the object data
     * @return the completed InfectionTracker
     */
    IInfectionTracker create(IInfectionTrackerBuilder builder);
}
