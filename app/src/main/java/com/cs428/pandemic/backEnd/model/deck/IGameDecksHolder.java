/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEnd.model.deck;

import java.util.List;

/**
 * Holds and keeps track of all of the 
 * @author James
 */
public interface IGameDecksHolder
{
    /**
     * Prepares the decks for use at the start of the game
     * @param numberEpidemics the number of epidemic cards that will be in the player deck (BASED ON DIFFICULTY LEVEL)
     * @param cities the list of all of the cities on the map
     * @param eventName the list of all of the event card names
     */
    void prepareDecks(int numberEpidemics, List<CityCardParams> cities, List<EventCardParams> eventName);
    
    /**
     * Shuffles the discarded infection cards onto the top of the infection draw pile
     */
    void intensify();
    
    /**
     * Adds a card to a given deck denoted by the CardFamilyType and the DeckType
     * @param card the card to be added to one of the decks (SHOULD HAVE A DOWNCAST FROM ICard TO IInfectionCard OR IPlayerCard)
     * @param family the family of the card {INFECTION, PLAYER}
     * @param deck where the card will be added {DRAW,DISCARDED,ELIMINATED}
     */
    void addCard(ICard card, CardFamilyType family, DeckType deck);
    
    /**
     * Removes the top card from the given deck denoted by the CardFamilyType and the DeckType
     * @param family the family of the card {INFECTION, PLAYER}
     * @param deck where the card will be removed from {DRAW,DISCARDED,ELIMINATED}
     * @return the card to be removed
     */
    ICard removeTopCard(CardFamilyType family, DeckType deck);
    
    /**
     * Removes the bottom card from the given deck denoted by the CardFamilyType and the DeckType
     * @param family the family of the card {INFECTION, PLAYER}
     * @param deck where the card will be removed from {DRAW,DISCARDED,ELIMINATED}
     * @return the card to be removed
     */
    ICard removeBottomCard(CardFamilyType family, DeckType deck);
    
    /**
     * Attempts to remove a given card from the deck denoted by the CardFamilyType and the DeckType
     * @param card the card to be added to one of the decks (SHOULD HAVE A DOWNCAST FROM ICard TO IInfectionCard OR IPlayerCard)
     * @param family the family of the card {INFECTION, PLAYER}
     * @param deck where the card will be removed from {DRAW,DISCARDED,ELIMINATED}
     * @return whether the card was successfully removed
     */
    boolean removeCard(ICard card, CardFamilyType family, DeckType deck);

    /**
     * Checks to see if a given card is in the discard pile of a given card family
     * @param card to be added to one of the decks (SHOULD HAVE A DOWNCAST FROM ICard TO IInfectionCard OR IPlayerCard)
     * @param family the family of the card {INFECTION, PLAYER} (ONLY THE FAMILY IS NEEDED BECAUSE THE DeckType IS OF TYPE: DISCARDED)
     * @return whether the discarded deck contains the card
     */
    boolean isCardDiscarded(ICard card, CardFamilyType family);
    
    /**
     * Retrieves the given player deck based on the deck type
     * @param deck where the card will be put {DRAW,DISCARDED,ELIMINATED}
     * @return the collection containing the card values
     */
    CardCollection<IPlayerCard> getPlayerDeck(DeckType deck);

    /**
     * Retrieves the given infection deck based on the deck type
     * @param deck where the card will be put {DRAW,DISCARDED,ELIMINATED}
     * @return the collection containing the card values
     */
    CardCollection<IInfectionCard> getInfectDeck(DeckType deck);

    /**
     * Returns the number of cards in the given deck
     * @param family the family of the card {INFECTION, PLAYER}
     * @param deck where the card will be put {DRAW,DISCARDED,ELIMINATED}
     * @return the number of cards in the deck
     */
    int getDeckCount(CardFamilyType family, DeckType deck);
}