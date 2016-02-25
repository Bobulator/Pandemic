/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEnd.model.gamestate;

/**
 * The State of the Game. Largely a container for other specialist components
 * @author James
 */
public interface IGameState 
{
    /**
     * Advances the outbreak tracker
     */
    void outBreak();
    
    /**
     * Sets the number of outbreaks that have happened
     * @param amount the number of outbreaks
     */
    void setNumberOfOutbreaks(int amount);
    
    /**
     * Gets the number of outbreaks that have happened
     * @return the number of outbreaks
     */
    int getNumberOfOutbreaks();
    
    /**
     * Gets the Infection Tracker
     * @return the Infection Tracker
     */
    IInfectionTracker getInfectionTracker();

    /**
     * Sets the Infection Tracker
     * @param tracker the Infection Tracker
     */
    void setInfectionTracker(IInfectionTracker tracker);
    
    /**
     * Gets the disease data (responsible for knowing if it has been eradicated or cured)
     * @return the disease data object storing those states
     */
    IDiseaseData getDiseaseData();
    
    /**
     * Sets the disease data
     * @param data the data object
     */
    void setDiseaseData(IDiseaseData data);
}
