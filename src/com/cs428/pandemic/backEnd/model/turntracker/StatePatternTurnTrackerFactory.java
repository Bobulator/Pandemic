/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEnd.model.turntracker;

import com.cs428.pandemic.backEnd.model.turntracker.statepattern.TurnTracker;

/**
 *
 * @author James
 */
public class StatePatternTurnTrackerFactory implements ITurnTrackerFactory
{
    @Override
    public ITurnTracker createTurnTracker(int numberOfPlayers) 
    {
        return new TurnTracker(numberOfPlayers);
    }
}
