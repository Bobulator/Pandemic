/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEnd.turntracker.tester;

import static org.junit.Assert.assertEquals;

import com.cs428.pandemic.backEnd.turntracker.*;
import java.util.Random;
import org.junit.*;

/**
 *
 * @author James
 */
public class TurnTrackerTests 
{
    private int numPlayers;
    private int actionPoints;
    private ITurnTracker tracker;
    
    @Before
    public void setup()
    {
        this.numPlayers = 5;
        this.actionPoints = 4;
        this.tracker = new TurnTracker(this.numPlayers);
        this.tracker.setTurnState(TurnStateValue.ACTION);
        this.tracker.setCurrentActionPoints(this.actionPoints);
    }
    
    @Test
    public void testTracker()
    {
        int val = 1;
        for(int i = 0; i < this.numPlayers; ++i)
        {
            assertEquals("Test Tracker " + val++ + ": Should be in the action state ", 
                    TurnStateValue.ACTION, this.tracker.getTurnState());
            assertEquals("Test Tracker " + val++ + ": Player " + i + " should be the current player ", 
                    i, this.tracker.getCurrentPlayer());
            this.tracker.advanceTurnState(); 
            assertEquals("Test Tracker " + val++ + ": Should be in the draw player card state ", 
                    TurnStateValue.DRAW_PLAYER, this.tracker.getTurnState());
            assertEquals("Test Tracker " + val++ + ": Player " + i + " should be the current player ", 
                    i, this.tracker.getCurrentPlayer());
            this.tracker.advanceTurnState();
            assertEquals("Test Tracker " + val++ + ": Should be in the infect state ", 
                    TurnStateValue.DRAW_INFECT, this.tracker.getTurnState());
            assertEquals("Test Tracker " + val++ + ": Player " + i + " should be the current player ", 
                    i, this.tracker.getCurrentPlayer());
            this.tracker.advanceTurnState();
        }
        assertEquals("Test Tracker " + val++ + ": Should be in the action state ", 
                TurnStateValue.ACTION, this.tracker.getTurnState());
        assertEquals("Test Tracker " + val++ + ": Player 0 should be the current player ", 
                0, this.tracker.getCurrentPlayer());
    }
    
    @Test
    public void testOneQuietNight()
    {
        int val = 1;
        Random rand = new Random();
        for(int i = 0; i < this.numPlayers; ++i)
        {
            boolean oneQuietNight = rand.nextBoolean();
            assertEquals("Test One Quiet Night " + val++ + ": Should be in the action state ", 
                    TurnStateValue.ACTION, this.tracker.getTurnState());
            assertEquals("Test One Quiet Night " + val++ + ": Player " + i + " should be the current player ", 
                    i, this.tracker.getCurrentPlayer());
            this.tracker.advanceTurnState(); 
            assertEquals("Test One Quiet Night " + val++ + ": Should be in the draw player card state ", 
                    TurnStateValue.DRAW_PLAYER, this.tracker.getTurnState());
            assertEquals("Test One Quiet Night " + val++ + ": Player " + i + " should be the current player ", 
                    i, this.tracker.getCurrentPlayer());
            if(oneQuietNight)
            {
                this.tracker.setOneQuietNight();
                this.tracker.advanceTurnState();
            }
            else
            {
                this.tracker.advanceTurnState();
                assertEquals("Test One Quiet Night " + val++ + ": Should be in the infect state ", 
                        TurnStateValue.DRAW_INFECT, this.tracker.getTurnState());
                assertEquals("Test One Quiet Night " + val++ + ": Player " + i + " should be the current player ", 
                        i, this.tracker.getCurrentPlayer());
                this.tracker.advanceTurnState();
            }
        }
        assertEquals("Test One Quiet Night " + val++ + ": Should be in the action state ", 
                TurnStateValue.ACTION, this.tracker.getTurnState());
        assertEquals("Test One Quiet Night " + val++ + ": Player 0 should be the current player ", 
                0, this.tracker.getCurrentPlayer());
    }
    
    @Test
    public void testActionPoints()
    {
        int val = 1;
        assertEquals("Test Action Points " + val++ + ": Should be in the action state ", 
                TurnStateValue.ACTION, this.tracker.getTurnState());
        assertEquals("Test Action Points " + val++ + ": Player 0 should be the current player ", 
                0, this.tracker.getCurrentPlayer());
        int i = this.actionPoints;
        assertEquals("Test Action Points " + val++ + ": Action Points should be the same ", 
                i, this.tracker.getCurrentActionPoints());
        boolean valid = this.tracker.decrementActionPoints(2);
        assertEquals("Test Action Points " + val++ + ": decrementing the tracker by 2 should return true ", 
                true, valid);
        assertEquals("Test Action Points " + val++ + ": Action Points should be -2 ", 
                i - 2, this.tracker.getCurrentActionPoints());
        valid = this.tracker.decrementActionPoints(1);
        assertEquals("Test Action Points " + val++ + ": decrementing the tracker by 1 after 2 should return true ", 
                true, valid);
        assertEquals("Test Action Points " + val++ + ": Action Points should be -3 ", 
                i - 3, this.tracker.getCurrentActionPoints());
        valid = this.tracker.decrementActionPoints(i - 3);
        assertEquals("Test Action Points " + val++ + ": decrementing the tracker by 1 after 2 should return true ", 
                true, valid);
        assertEquals("Test Action Points " + val++ + ": Action Points should be 0 ", 
                0, this.tracker.getCurrentActionPoints());
        assertEquals("Test Action Points " + val++ + ": Should be in the draw player card state ", 
                TurnStateValue.DRAW_PLAYER, this.tracker.getTurnState());
        assertEquals("Test Action Points " + val++ + ": Player 0 should be the current player ", 
                0, this.tracker.getCurrentPlayer());
        this.tracker.setCurrentActionPoints(i);
        valid = this.tracker.decrementActionPoints(1);
        assertEquals("Test Action Points " + val++ + ": decrementing Action Points should return false when it's in the incorrect state ", 
                false, valid);
        this.tracker.setTurnState(TurnStateValue.ACTION);
        this.tracker.setCurrentActionPoints(1);
        valid = this.tracker.decrementActionPoints(2);
        assertEquals("Test Action Points " + val++ + ": decrementing Action Points below 0 should return false ", 
                false, valid);
        assertEquals("Test Action Points " + val++ + ": decrementing Action Points below 0 should NOT change the turn state ", 
                TurnStateValue.ACTION, this.tracker.getTurnState());
    }

    public static void main(String[] args)
    {
            String[] testClasses = new String[] 
            {
                    "com.cs428.pandemic.backEnd.turntracker.tester.TurnTrackerTests"
            };

            org.junit.runner.JUnitCore.main(testClasses);
    }
}
