/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEnd.model.deck;

/**
 *
 * @author James
 */
public interface IPlayerDeck extends IGameDeck
{
    void receiveCard(IPlayerCard card);
    
    IPlayerCard removeRandomCard();
    
    IPlayerCard removeTopCard();
    
    boolean removeCard(IPlayerCard card);
    
    boolean hasCard(IPlayerCard card);
}
