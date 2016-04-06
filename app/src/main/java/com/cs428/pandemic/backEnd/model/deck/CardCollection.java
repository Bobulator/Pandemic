/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEnd.model.deck;

import com.cs428.pandemic.backEnd.model.gamestate.DiseaseType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author James
 */
public class CardCollection <T extends ICard>
{
    private List<T> cardList;
    private Random rand;
    
    public CardCollection(List<T> cardList, Random rand)
    {
        this.cardList = cardList;
        this.rand = rand;
    }

    public CardCollection(){
        this.cardList = new ArrayList<>();
        this.rand = new Random();
    }
    
    public void receiveCard(T card)
    {
        cardList.add(card);
    }
    
    public void addTop(T card)
    {
        cardList.add(0, card);
    }
    
    public T removeRandomCard()
    {
        int index = rand.nextInt(cardList.size());
        return cardList.remove(index);
    }
    
    public T removeTopCard()
    {
        return cardList.remove(0);
    }
    
    public T removeBottomCard()
    {
        return cardList.remove(cardList.size() - 1);
    }
    
    public boolean removeCard(T card)
    {
        return cardList.remove(card);
    }
    
    public List<String> getNamesOfCityCards()
    {
        return getNamesOfCityCards("");
    }
    
    public List<String> getNamesOfCityCards(String identifier)
    {
        List<String> names = new ArrayList<>();
        for(ICard card : cardList)
        {
            if(!card.equalsIdentifier(identifier)
                && card instanceof ICityCard)
            {
                names.add(((ICityCard)card).getName());
            }
        }
        return names;
    }
    
    public boolean cardExists(String identifier)
    {
        for(ICard card : cardList)
        {
            if(card.equalsIdentifier(identifier))
            {
                return true;
            }
        }
        return false;
    }
    
    public ICityCard removeCityCard(String cityName)
    {
        for(int i = 0; i < cardList.size(); i++)
        {
            ICard card = cardList.get(i);
            if(card instanceof ICityCard 
                    && ((ICityCard) card).getName().equalsIgnoreCase(cityName)) 
            {
                cardList.remove(i);
                return (ICityCard)card;
            }
        }
        return null;
    }

    public boolean hasCard(T card)
    {
        return cardList.contains(card);
    }
    
    public void shuffle()
    {
        Collections.shuffle(cardList);
    }
    
    public int size()
    {
        return cardList.size();
    }

    public int getColorCount(DiseaseType type)
    {
        int matchCount = 0;
        for(T card : cardList)
        {
            if (card instanceof ICityCard && ((ICityCard) card).getDiseaseType() == type)
            {
                matchCount++;
            }
        }
        return matchCount;
    }

    public List<T> getCardList(){
        return cardList;
    }
}
