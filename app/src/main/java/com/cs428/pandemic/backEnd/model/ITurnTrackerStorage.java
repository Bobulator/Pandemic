/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEnd.model;

import com.cs428.pandemic.backEnd.model.turntracker.ITurnTracker;

/**
 * Slice of the model that holds the interface for the turn tracker
 * @author James
 */
public interface ITurnTrackerStorage 
{
    /**
     * Keeps track of turn state information
     * @return 
     */
    ITurnTracker getTurnTracker();
}
