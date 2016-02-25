/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEnd.model.turntracker;

/**
 * Responsible for generating a turn tracker. (could be used as a composite with other model factories)
 * @author James
 */
public interface ITurnTrackerFactory 
{
    /**
     * Creates a new Turn Tracker
     * @param numberOfPlayers the number of players in the game
     * @return 
     */
    ITurnTracker createTurnTracker(int numberOfPlayers);
}
