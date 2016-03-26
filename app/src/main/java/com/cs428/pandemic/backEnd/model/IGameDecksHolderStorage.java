/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEnd.model;

import com.cs428.pandemic.backEnd.model.deck.IGameDecksHolder;

/**
 * Slice of the model that holds Game Decks accessor
 * @author James
 */
public interface IGameDecksHolderStorage 
{
    /**
     * Contains information for the Decks
     * @return 
     */
    IGameDecksHolder getGameDecks();
}
