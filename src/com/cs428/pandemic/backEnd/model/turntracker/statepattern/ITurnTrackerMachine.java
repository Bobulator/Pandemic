/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEnd.model.turntracker.statepattern;

/**
 *
 * @author James
 */
public interface ITurnTrackerMachine 
{
    void setTurnState(ITurnState state);
    void advancePlayer();
}
