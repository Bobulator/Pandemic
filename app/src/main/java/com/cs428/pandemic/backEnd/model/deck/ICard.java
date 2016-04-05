/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEnd.model.deck;

/**
 * All Card Collection subtypes must implement this interface
 * @author James
 */
public interface ICard 
{
    boolean equalsIdentifier(String identifier);
}
