/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEnd.model.turntracker;

import com.cs428.pandemic.backEnd.model.turntracker.standard.TurnTracker;

/**
 * Generates the Simple Turn Tracker that does not use the state pattern
 * @author James
 */
public class StandardTurnTrackerFactory implements ITurnTrackerFactory
{
    @Override
    public ITurnTracker createTurnTracker(int numberOfPlayers) 
    {
        return new TurnTracker(numberOfPlayers);
    }
}
