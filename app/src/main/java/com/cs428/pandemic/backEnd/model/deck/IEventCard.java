/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEnd.model.deck;

import com.cs428.pandemic.backEnd.command.ICommand;

/**
 * Performs special actions on the model depending on the type of event card that it is
 * @author James
 */
public interface IEventCard extends IPlayerCard
{
    /**
     * Returns the name of the event
     * @return 
     */
    String getName();
    
    /**
     * Returns the description of the event
     * @return 
     */
    String getDescription();
}
