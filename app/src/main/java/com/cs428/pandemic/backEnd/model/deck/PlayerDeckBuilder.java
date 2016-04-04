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
public class PlayerDeckBuilder 
{
    private int numEpidemics;
    private List<IPlayerCard> cards;
    private Random rand;
    
    public PlayerDeckBuilder()
    {
        cards = new ArrayList<>();
    }
    
    PlayerDeckBuilder setEpidemics(int num)
    {
        numEpidemics = num;
        return this;
    }
    
    PlayerDeckBuilder addCard(IPlayerCard card)
    {
        cards.add(card);
        return this;
    }
    
    PlayerDeckBuilder setRandomizer(Random r)
    {
        rand = r;
        return this;
    }
    
    PlayerDeckBuilder setRandomizer()
    {
        return setRandomizer(new Random());
    }
    
    PlayerDeckBuilder setEpidemics(ICardFactory fact)
    {
//        Collections.shuffle(cards);
//        int epidCount = numEpidemics;
//        int extraCount = cards.size() % epidCount;
//        int baseGroupSize = cards.size() / epidCount;
//        int curIndex = 0;
//        for(int i = 0; i < epidCount; i++) 
//        {
//            int groupSize = baseGroupSize;
//            if(extraCount > 0) 
//            {
//                groupSize++;
//                extraCount--;
//            }
//            int epicLoc = rand.nextInt(groupSize);
//            cards.add(curIndex + epicLoc, fact.createPlayerEpidemicCard());
//            curIndex += groupSize + 1;
//        }
        return this;
    }
    
    PlayerDeckBuilder setEpidemics2nd(ICardFactory fact)
    {
        /*
        Collections.shuffle(cards);
        int numPiles = numEpidemics;
        List<IPlayerCard>[] piles = new ArrayList<>[numPiles];
        for(int i = 0; i < piles.length; ++i)
        {
            piles[i] = new ArrayList<>();
            piles[i].add(fact.createPlayerEpidemicCard());
        }
        while(cards.size() > 0)
        {
            for(int i = 0; i < piles.length; ++i)
            {
                if(cards.size() > 0)
                {
                    piles[i].add(cards.remove(0));
                }
            }
        }
        for(int i = 0; i < piles.length; ++i)
        {
            Collections.shuffle(piles[i]);
            cards.addAll(piles[i]);
        }
        */
        return this;
    }
    
    CardCollection<IPlayerCard> buildDeck()
    {
        return new CardCollection<>(cards, rand);
    }
    
    public static void main(String[] args)
    {
        PlayerDeckBuilder builder = new PlayerDeckBuilder();
        builder.setRandomizer();
        CardFactory fact = new CardFactory();
        for(int i = 0; i < 50; ++i)
        {
            builder.addCard(fact.createPlayerCityCard("City " + i,DiseaseType.BLACK));
        }
        builder.setEpidemics(17)
               .setEpidemics2nd(fact);
    }
}
