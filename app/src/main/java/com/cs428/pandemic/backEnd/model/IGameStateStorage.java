/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEnd.model;

import com.cs428.pandemic.backEnd.model.gamestate.IGameState;

/**
 * Slice of the model that contains the IGameState accessor
 * @author James
 */
public interface IGameStateStorage 
{
    /**
     * Tracks which diseases have been cured or eradicated and the infection rate and the number of epidemics
     * @return 
     */
    IGameState getGameState();
}
