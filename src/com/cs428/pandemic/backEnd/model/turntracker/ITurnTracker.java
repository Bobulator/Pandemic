/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEnd.model.turntracker;

/**
 *
 * @author James
 */
public interface ITurnTracker 
{
    /**
     * The index of the current player
     * @return the current player's index
     */
    int getCurrentPlayer();

    /**
     * Sets the index of the current player
     * @param currentPlayer the current player's index
     */
    void setCurrentPlayer(int currentPlayer);
    
    /**
     * Moves the player index from the current player's index to the next player's index
     */
    void advancePlayer();
    
    /**
     * Moves the turn state from the current turn state to the next state. If One Quiet Night has been played and the current state is DRAW_PLAYER, the turn state moves to ACTION. Any move to ACTION also advances the current player
     */
    void advanceTurnState();
    
    /**
     * Sets the value of turn state
     * @param value the current turn state
     */
    void setTurnState(TurnStateValue value);
    
    /**
     * Gets the value of the turn state
     * @return the turn state's value
     */
    TurnStateValue getTurnState();
    
    /**
     * Sets the value of the action points
     * @param value the action points value
     */
    void setCurrentActionPoints(int value);
    
    /**
     * Gets the number of action points
     * @return the number of action points
     */
    int getCurrentActionPoints();
    
    /**
     * Uses up amount of action points. ONLY VALID IN ACTION PHASE. If the current action points falls to zero EXACTLY (NOT BELOW ZERO) it will advance the turn state from ACTION to DRAW_PLAYER.
     * @param amount the number of action points to be decremented
     * @return whether it was valid NOT WHETHER THE TURN STATE WILL BE MOVED
     */
    boolean decrementActionPoints(int amount);
    
    /**
     * Plays one quiet night. This causes the DRAW_INFECT phase to be skipped
     */
    void setOneQuietNight();
}
