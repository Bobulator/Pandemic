/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEnd.model.deck;

/**
 * The type of card that goes in the player deck
 * @author James
 */
public interface IPlayerCard extends ICard
{
    /**
     * what subtype is the card {EVENT, CITY, EPIDEMIC}
     * @return 
     */
    PlayerCardType getType();
}
