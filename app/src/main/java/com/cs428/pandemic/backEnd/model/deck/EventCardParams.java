/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEnd.model.deck;

/**
 *
 * @author James
 */
public class EventCardParams 
{
    private String name;
    private String description;
    
    public EventCardParams(String eventName, String eventDescription)
    {
        name = eventName;
        description = eventDescription;
    }

    public String getEventName() 
    {
        return name;
    }

    public String getEventDescription() 
    {
        return description;
    }
}
