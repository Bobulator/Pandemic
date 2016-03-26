package com.cs428.pandemic.backEndTest.model.turntracker;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import asserts.LiteAssertFailedException;
import static asserts.LiteAsserts.*;

import com.cs428.pandemic.backEnd.model.turntracker.*;
import com.cs428.pandemic.backEnd.model.turntracker.ITurnTracker;
import com.cs428.pandemic.backEnd.model.turntracker.TurnStateValue;

import java.util.Random;
import test.LiteDriver;
import test.annotations.LiteClass;
import test.annotations.LiteTest;

/**
 *
 * @author James
 */
@LiteClass
public class TurnTrackerTests 
{
    private int numPlayers;
    private int actionPoints;
    private ITurnTracker tracker;
    
    public void setup()
    {
        ITurnTrackerFactory fact = new StatePatternTurnTrackerFactory();
        this.numPlayers = 5;
        this.actionPoints = 4;
        this.tracker = fact.createTurnTracker(this.numPlayers);
        this.tracker.setTurnState(TurnStateValue.ACTION);
        this.tracker.setCurrentActionPoints(this.actionPoints);
    }
    
    @LiteTest
    public void testTracker() throws LiteAssertFailedException
    {
        setup();
        int val = 1;
        for(int i = 0; i < this.numPlayers; ++i)
        {
            assertEquals(TurnStateValue.ACTION, this.tracker.getTurnState(),
                    "Test Tracker " + val++ + ": Should be in the action state ");
            assertEquals(i, this.tracker.getCurrentPlayer(), 
                    "Test Tracker " + val++ + ": Player " + i + " should be the current player ");
            this.tracker.advanceTurnState(); 
            assertEquals(TurnStateValue.DRAW_PLAYER, this.tracker.getTurnState(), 
                    "Test Tracker " + val++ + ": Should be in the draw player card state ");
            assertEquals(i, this.tracker.getCurrentPlayer(), 
                    "Test Tracker " + val++ + ": Player " + i + " should be the current player ");
            this.tracker.advanceTurnState();
            assertEquals(TurnStateValue.DRAW_INFECT, this.tracker.getTurnState(),
                    "Test Tracker " + val++ + ": Should be in the infect state ");
            assertEquals(i, this.tracker.getCurrentPlayer(), 
                    "Test Tracker " + val++ + ": Player " + i + " should be the current player ");
            this.tracker.advanceTurnState();
        }
        assertEquals(TurnStateValue.ACTION, this.tracker.getTurnState(), 
                "Test Tracker " + val++ + ": Should be in the action state ");
        assertEquals(0, this.tracker.getCurrentPlayer(),
                "Test Tracker " + val++ + ": Player 0 should be the current player ");
    }
    
    @LiteTest
    public void testOneQuietNight() throws LiteAssertFailedException
    {
        setup();
        int val = 1;
        Random rand = new Random();
        for(int i = 0; i < this.numPlayers; ++i)
        {
            boolean oneQuietNight = rand.nextBoolean();
            assertEquals(TurnStateValue.ACTION, this.tracker.getTurnState(), 
                    "Test One Quiet Night " + val++ + ": Should be in the action state ");
            assertEquals(i, this.tracker.getCurrentPlayer(), 
                    "Test One Quiet Night " + val++ + ": Player " + i + " should be the current player ");
            this.tracker.advanceTurnState(); 
            assertEquals(TurnStateValue.DRAW_PLAYER, this.tracker.getTurnState(), 
                    "Test One Quiet Night " + val++ + ": Should be in the draw player card state ");
            assertEquals(i, this.tracker.getCurrentPlayer(), 
                    "Test One Quiet Night " + val++ + ": Player " + i + " should be the current player ");
            if(oneQuietNight)
            {
                this.tracker.setOneQuietNight();
                this.tracker.advanceTurnState();
            }
            else
            {
                this.tracker.advanceTurnState();
                assertEquals(TurnStateValue.DRAW_INFECT, this.tracker.getTurnState(), 
                        "Test One Quiet Night " + val++ + ": Should be in the infect state ");
                assertEquals(i, this.tracker.getCurrentPlayer(), 
                        "Test One Quiet Night " + val++ + ": Player " + i + " should be the current player ");
                this.tracker.advanceTurnState();
            }
        }
        assertEquals(TurnStateValue.ACTION, this.tracker.getTurnState(), 
                "Test One Quiet Night " + val++ + ": Should be in the action state ");
        assertEquals(0, this.tracker.getCurrentPlayer(),
                "Test One Quiet Night " + val++ + ": Player 0 should be the current player ");
    }
    
    @LiteTest
    public void testActionPoints() throws LiteAssertFailedException
    {
        setup();
        int val = 1;
        assertEquals(TurnStateValue.ACTION, this.tracker.getTurnState(), 
                "Test Action Points " + val++ + ": Should be in the action state ");
        assertEquals(0, this.tracker.getCurrentPlayer(), 
                "Test Action Points " + val++ + ": Player 0 should be the current player ");
        int i = this.actionPoints;
        assertEquals(i, this.tracker.getCurrentActionPoints(), 
                "Test Action Points " + val++ + ": Action Points should be the same ");
        boolean valid = this.tracker.decrementActionPoints(2);
        assertEquals(true, valid, 
                "Test Action Points " + val++ + ": decrementing the tracker by 2 should return true ");
        assertEquals(i - 2, this.tracker.getCurrentActionPoints(), 
                "Test Action Points " + val++ + ": Action Points should be -2 ");
        valid = this.tracker.decrementActionPoints(1);
        assertEquals(true, valid, 
                "Test Action Points " + val++ + ": decrementing the tracker by 1 after 2 should return true ");
        assertEquals(i - 3, this.tracker.getCurrentActionPoints(), 
                "Test Action Points " + val++ + ": Action Points should be -3 ");
        valid = this.tracker.decrementActionPoints(i - 3);
        assertEquals(true, valid, 
                "Test Action Points " + val++ + ": decrementing the tracker by 1 after 2 should return true ");
        assertEquals(0, this.tracker.getCurrentActionPoints(), 
                "Test Action Points " + val++ + ": Action Points should be 0 ");
        assertEquals(TurnStateValue.DRAW_PLAYER, this.tracker.getTurnState(), 
                "Test Action Points " + val++ + ": Should be in the draw player card state ");
        assertEquals(0, this.tracker.getCurrentPlayer(), 
                "Test Action Points " + val++ + ": Player 0 should be the current player ");
        this.tracker.setCurrentActionPoints(i);
        valid = this.tracker.decrementActionPoints(1);
        assertEquals(false, valid, 
                "Test Action Points " + val++ + ": decrementing Action Points should return false when it's in the incorrect state ");
        this.tracker.setTurnState(TurnStateValue.ACTION);
        this.tracker.setCurrentActionPoints(1);
        valid = this.tracker.decrementActionPoints(2);
        assertEquals(false, valid, 
                "Test Action Points " + val++ + ": decrementing Action Points below 0 should return false ");
        assertEquals(TurnStateValue.ACTION, this.tracker.getTurnState(), 
                "Test Action Points " + val++ + ": decrementing Action Points below 0 should NOT change the turn state ");
    }

    public static void main(String[] args)
    {
            LiteDriver driver = new LiteDriver("src","com.cs428.pandemic.backEndTest.model.turntracker");
            driver.runTests(true,true,true);
    }
}
