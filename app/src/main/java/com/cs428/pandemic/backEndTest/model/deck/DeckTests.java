/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEndTest.model.deck;

import asserts.LiteAssertFailedException;
import static asserts.LiteAsserts.*;

import com.cs428.pandemic.backEnd.model.deck.*;
import java.util.ArrayList;
import java.util.List;

import com.cs428.pandemic.backEnd.model.gamestate.DiseaseType;
import test.LiteDriver;
import test.annotations.LiteClass;
import test.annotations.LiteTest;

/**
 *
 * @author James
 */
@LiteClass
public class DeckTests
{
    ICardFactory fact;
    IGameDecksFactory gameDecksFactory;
    int numEpidemics;
    int cityCount;
    int eventCount;
    int testPlayerCardTimes;
    boolean verboseCardTests;
    
    public DeckTests()
    {
        StandardGameDecksFactory stdFact = new StandardGameDecksFactory();
        fact = stdFact;
        gameDecksFactory = stdFact;
        numEpidemics = 15;
        cityCount = 50;
        eventCount = 25;
        testPlayerCardTimes = 5000;
        verboseCardTests = true;
    }
    
    @LiteTest
    public void testCard() throws LiteAssertFailedException
    {
        IInfectionCard infCard = fact.createInfectCard("Albuquerque",DiseaseType.BLACK);
        assertEquals(infCard.getName(), "Albuquerque", 
                "Name of card should be Albuquerque");
        assertEquals(infCard.getColor(), DiseaseType.BLACK, 
                "Color of card should be black");
        // TODO: Check the disease type as well
        IPlayerCard playCard = fact.createPlayerCityCard("Salt Lake City", DiseaseType.BLACK);
        assertEquals(playCard.getType(),PlayerCardType.CITY,
                "Type of card should be city");
        assertEquals(playCard instanceof ICityCard, true, 
                "Card should be of type ICityCard");
        ICityCard cityCard = (ICityCard) playCard;
        assertEquals(cityCard.getName(),"Salt Lake City",
                "Name of city card should be Salt Lake City");
        assertEquals(cityCard.getDiseaseType(), DiseaseType.BLACK, 
                "Color of card should be black");
        playCard = fact.createPlayerEpidemicCard();
        assertEquals(playCard.getType(),PlayerCardType.EPIDEMIC,
                "Type of card should be Epidemic");
        assertEquals(playCard instanceof IEpidemicCard, true, 
                "Card should be of type IEpidemicCard");
        playCard = fact.createPlayerEventCard("One Quite Night", "Skip next DRAW INFECT");
        assertEquals(playCard.getType(),PlayerCardType.EVENT,
                "Type of card should be Event");
        assertEquals(playCard instanceof IEventCard, true, 
                "Card should be of type IEventCard");
        IEventCard evtCard = (IEventCard) playCard;
        assertEquals(evtCard.getName(), "One Quite Night", 
                "Event card name should be One Quite Night");
        assertEquals(evtCard.getDescription(), "Skip next DRAW INFECT", 
                "Event card description should be Skip next DRAW INFECT");
    }
    
    private List<CityCardParams> createCityList(String val, DiseaseType type, int times)
    {
        List<CityCardParams> retList = new ArrayList<>();
        for(int i = 0; i < times; ++i)
        {
            retList.add(fact.createCityParams(val, type));
        }
        return retList;
    }
    
    private List<EventCardParams> createEventList(String name, String desc, int times)
    {
        List<EventCardParams> retList = new ArrayList<>();
        for(int i = 0; i < times; ++i)
        {
            retList.add(fact.createEventCardParams(name, desc));
        }
        return retList;
    }
    
    private List<CityCardParams> createCityList()
    {
        return createCityList("Provo",DiseaseType.BLACK,cityCount);
    }
    
    private List<EventCardParams> createEventName()
    {
        return createEventList("NewEvent","EventDescription",eventCount);
    }
    
    private IGameDecksHolder initHolder()
    {
        IGameDecksHolder holder = gameDecksFactory.createGameDeck();
        holder.prepareDecks(numEpidemics, createCityList(), createEventName());
        return holder;
    }
    
    @LiteTest
    public void testPreparedDecksSize() throws LiteAssertFailedException
    {
        IGameDecksHolder holder = initHolder();
        assertEquals(holder.getDeckCount(CardFamilyType.PLAYER, DeckType.DRAW),eventCount + cityCount + numEpidemics,
                "Number of player cards should be events + cities + epidemics");
        assertEquals(holder.getDeckCount(CardFamilyType.PLAYER, DeckType.DISCARD),0,
                "Number of player cards discarded should be 0");
        assertEquals(holder.getDeckCount(CardFamilyType.PLAYER, DeckType.ELIMINATED),0,
                "Number of player cards eliminated should be 0");
        assertEquals(holder.getDeckCount(CardFamilyType.INFECT, DeckType.DRAW),cityCount,
                "Number of infect cards should be equal to the number of cities");
        assertEquals(holder.getDeckCount(CardFamilyType.INFECT, DeckType.DISCARD),0,
                "Number of infect cards discarded should be equal 0");
        assertEquals(holder.getDeckCount(CardFamilyType.INFECT, DeckType.ELIMINATED),0,
                "Number of infect cards eliminated should be equal to the number of cities");
    }

    @LiteTest
    public void testInfectionDrawDeck() throws LiteAssertFailedException
    {
        IGameDecksHolder holder = initHolder();
        CardCollection<IInfectionCard> deck = holder.getInfectDeck(DeckType.DRAW);
        List<CityCardParams> cities = createCityList();
        for(CityCardParams p:cities)
        {
            IInfectionCard card = deck.removeTopCard();
            assertEquals(card.getName(),p.getCityName(),
                    "City names should be equal");
            assertEquals(card.getColor(),p.getColor(),
                    "City names should be equal");
        }
    }
    
    @LiteTest
    public void testPlayerDrawDeck() throws LiteAssertFailedException
    {
        int[] epidemicFrequency = new int[numEpidemics + cityCount + eventCount];
        for(int i = 0; i < epidemicFrequency.length; ++i)
        {
            epidemicFrequency[i] = 0;
        }
        for(int i = 0; i < testPlayerCardTimes; ++i)
        {
            IGameDecksHolder holder = initHolder();
            CardCollection<IPlayerCard> deck = holder.getPlayerDeck(DeckType.DRAW);
            assertEquals(epidemicFrequency.length, deck.size(),
                    "Deck size must be a set size");
            int pileSize = deck.size() / numEpidemics;
            int pileExtra = deck.size() % numEpidemics;
            int minMaxDistribution = deck.size() / (numEpidemics + 1) - 1;
            if(pileExtra > 0)
            {
                ++pileSize;
            }
            int maxMaxDistribution = 2 * pileSize;
            int epidemicsCount = 0;
            int cityCardCount = 0;
            int eventCardCount = 0;
            int maxEpidDist = 0;
            int curEpidDist = 0;
            int index = 0;
            StringBuilder deckDump = new StringBuilder();
            while(deck.size() > 0)
            {
                switch(deck.removeTopCard().getType())
                {
                    case EPIDEMIC:
                        deckDump.append("P");
                        ++epidemicFrequency[index];
                        ++epidemicsCount;
                        curEpidDist = 0;
                        break;
                    case CITY:
                        deckDump.append("C");
                        ++cityCardCount;
                        ++curEpidDist;
                        break;
                    case EVENT:
                    default:
                        deckDump.append("V");
                        ++eventCardCount;
                        ++curEpidDist;
                        break;
                }
                if(maxEpidDist < curEpidDist)
                {
                    maxEpidDist = curEpidDist;
                }
                ++index;
            }
            assertEquals(epidemicsCount,numEpidemics,
                    "Number of epidemics should be equal to the initial values");
            assertEquals(cityCardCount,cityCount,
                    "City count should be equal to initial values");
            assertEquals(eventCardCount,eventCount,
                    "Event count should be equal to initial values");
            if(maxEpidDist - 1 < minMaxDistribution || maxEpidDist >= maxMaxDistribution)
            {
                System.out.println(deckDump.toString());
                System.out.println((i + 1) + ": Max Distribution: " + maxEpidDist);
            }
            assertEquals(maxEpidDist < maxMaxDistribution, true,
                    "Max Epidemic Distance should be less than 2 times the size of each pile. WAS <" + maxEpidDist + "> EXPECTED LESS THAN <" + maxMaxDistribution + ">");
            assertEquals(maxEpidDist > minMaxDistribution, true,
                    "Max Epidemic Distance should be greater than the size of each pile. WAS <" + maxEpidDist + "> EXPECTED GREATER <" + minMaxDistribution + ">");
        }
        if(verboseCardTests)
        {
            for(int f : epidemicFrequency)
            {
                System.out.print(" " + f);
            }
            System.out.println();
            int index = 1;
            for(int f : epidemicFrequency)
            {
                if(f == 0)
                {
                    System.out.print(index + " ");
                }
                ++index;
            }
            System.out.println();
        }
    }
    
    @LiteTest
    public void testIntensify() throws LiteAssertFailedException
    {
        IGameDecksHolder holder = initHolder();
        int testTimes = 15;
        List<ICard> discarded = new ArrayList<>();
        for(int i = 0; i < testTimes; ++i)
        {
            ICard topCard = holder.removeTopCard(CardFamilyType.INFECT, DeckType.DRAW);
            holder.addCard(topCard, CardFamilyType.INFECT, DeckType.DISCARD);
            discarded.add(topCard);
        }
        for(ICard card:discarded)
        {
            assertEquals(holder.isCardDiscarded(card, CardFamilyType.INFECT),true,
                    "Card should be considered discarded");
        }
        holder.intensify();
        for(ICard card:discarded)
        {
            assertEquals(holder.isCardDiscarded(card, CardFamilyType.INFECT),false,
                    "Card should be considered discarded");
        }
    }
    
    public static void main(String[] args)
    {
        LiteDriver driver = new LiteDriver("src","com.cs428.pandemic.backEndTest.model.deck");
        driver.runTests(true,true,true);
    }
}
