/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEnd.model.gamestate;

/**
 * Responsible for creating any and all object types in the Game State Subsystem
 * @author James
 */
public interface IGameStateFactory 
{
    /**
     * Generates a new Game State
     * @return 
     */
    IGameState createGameState();

    /**
     * Generates a new Infection tracker
     * @return 
     */
    IInfectionTracker createInfectionTracker();

    /**
     * Generates a new Disease Data
     * @return 
     */
    IDiseaseData createDiseaseData();
}
