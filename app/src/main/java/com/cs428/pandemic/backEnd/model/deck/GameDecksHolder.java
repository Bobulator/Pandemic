/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEnd.model.deck;

import com.cs428.pandemic.backEnd.model.gamestate.DiseaseType;

import java.util.*;

/**
 *
 * @author James
 */
public class GameDecksHolder implements IGameDecksHolder
{
    CardCollection<IPlayerCard> playerDrawPile;
    CardCollection<IPlayerCard> playerDiscardPile;
    CardCollection<IPlayerCard> playerEliminatedPile;
    CardCollection<IInfectionCard> infectDrawPile;
    CardCollection<IInfectionCard> infectDiscardPile;
    CardCollection<IInfectionCard> infectEliminatedPile;

    @Override
    public CardCollection<IPlayerCard> getPlayerDeck(DeckType deck)
    {
        switch(deck)
        {
            case DRAW:
                return this.playerDrawPile;
            case DISCARD:
                return this.playerDiscardPile;
            case ELIMINATED:
            default:
                return this.playerEliminatedPile;
        }
    }

    @Override
    public CardCollection<IInfectionCard> getInfectDeck(DeckType deck)
    {
        switch(deck)
        {
            case DRAW:
                return this.infectDrawPile;
            case DISCARD:
                return this.infectDiscardPile;
            case ELIMINATED:
            default:
                return this.infectEliminatedPile;
        }
    }
    
    public CardCollection getDeck(DeckType deck, CardFamilyType family)
    {
        switch(family)
        {
            case PLAYER:
                return getPlayerDeck(deck);
            case INFECT:
            default:
                return getInfectDeck(deck);
        }
    }
    
    @Override
    public void prepareDecks(int numEpid, List<CityCardParams> cities, List<EventCardParams> events) 
    {
        this.prepareDecks(numEpid, cities, events, new Random());
    }
    
    public void prepareDecks(int numEpid, List<CityCardParams> cities, List<EventCardParams> events, Random rand) 
    {
        PlayerDeckBuilder playerDeckBuilder = new PlayerDeckBuilder();
        playerDeckBuilder.setEpidemics(numEpid)
                         .setRandomizer(rand);
        ICardFactory fact = new CardFactory();
        List<IInfectionCard> infectDeck = new ArrayList<>();
        for(int i = 0; i < cities.size(); ++i)
        {
            CityCardParams city = cities.get(i);
            playerDeckBuilder.addCard(fact.createPlayerCityCard(city));
            infectDeck.add(fact.createInfectCard(city));
        }
        for(int i = 0; i < events.size(); ++i)
        {
            EventCardParams event = events.get(i);
            playerDeckBuilder.addCard(fact.createPlayerEventCard(event));
        }
        playerDeckBuilder.setEpidemics2nd(fact);
        playerDrawPile = playerDeckBuilder.buildDeck();
        playerDiscardPile = new CardCollection<>(new ArrayList<IPlayerCard>(),rand);
        playerEliminatedPile = new CardCollection<>(new ArrayList<IPlayerCard>(),rand);
        
        Collections.shuffle(infectDeck);
        infectDrawPile = new CardCollection<>(infectDeck,rand);
        infectDiscardPile = new CardCollection<>(new ArrayList<IInfectionCard>(),rand);
        infectEliminatedPile = new CardCollection<>(new ArrayList<IInfectionCard>(),rand);
    }

    @Override
    public void intensify() 
    {
        CardCollection<IInfectionCard> discard = getInfectDeck(DeckType.DISCARD);
        CardCollection<IInfectionCard> draw = getInfectDeck(DeckType.DRAW);
        
        discard.shuffle();
        while(discard.size() > 0)
        {
            draw.addTop(discard.removeTopCard());
        }
    }

    @Override
    public void addCard(ICard card, CardFamilyType family, DeckType deck) 
    {
        getDeck(deck,family).receiveCard(card);
    }

    @Override
    public ICard removeTopCard(CardFamilyType family, DeckType deck) 
    {
        return getDeck(deck,family).removeTopCard();
    }

    @Override
    public ICard removeBottomCard(CardFamilyType family, DeckType deck) 
    {
        return getDeck(deck,family).removeBottomCard();
    }

    @Override
    public boolean removeCard(ICard card, CardFamilyType family, DeckType deck) 
    {
        return getDeck(deck,family).removeCard(card);
    }

    @Override
    public boolean isCardDiscarded(ICard card, CardFamilyType family) 
    {
        DeckType deck = DeckType.DISCARD;
        return getDeck(deck, family).hasCard(card);
    }

    @Override
    public int getDeckCount(CardFamilyType family, DeckType deck) 
    {
        return getDeck(deck,family).size();
    }
}
