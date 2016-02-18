/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEnd.turntracker;

/**
 *
 * @author James
 */
public interface ITurnTracker 
{
    int getCurrentPlayer();
    void setCurrentPlayer(int currentPlayer);
    void advancePlayer();
    void advanceTurnState();
    void setTurnState(TurnStateValue value);
    TurnStateValue getTurnState();
    void setCurrentActionPoints(int value);
    int getCurrentActionPoints();
    boolean decrementActionPoints(int amount);
    void setOneQuietNight();
}
