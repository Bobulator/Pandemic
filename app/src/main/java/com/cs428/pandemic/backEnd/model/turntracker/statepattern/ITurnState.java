/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEnd.model.turntracker.statepattern;

import com.cs428.pandemic.backEnd.model.turntracker.TurnStateValue;

/**
 *
 * @author James
 */
public interface ITurnState 
{
    TurnStateValue getTurnState();
    void setTurnStateValue(TurnStateValue val, ITurnTrackerMachine machine);
    void advanceTurnState(ITurnTrackerMachine machine);
    void setOneQuietNight(ITurnTrackerMachine machine);
}
