/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEnd.model.gamestate;

/**
 * Tracks the number of infections per turn
 * @author James
 */
public interface IInfectionTracker 
{
    /**
     * Advances the track by amount
     * @param amount the amount to advance the track by
     */
    void advanceTrack(int amount);
    
    /**
     * Sets the number of times the infections track has been advanced
     * @param amount 
     */
    void setInfectionsTrack(int amount);
    
    /**
     * Returns the number of infections per turn
     * @return the infection rate
     */
    int getInfectionsPerTurn();
}
