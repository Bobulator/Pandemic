/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEnd.model.turntracker;

/**
 *
 * @author James
 */
public interface ITurnTrackerFactory 
{
    ITurnTracker createTurnTracker(int numberOfPlayers);
}
