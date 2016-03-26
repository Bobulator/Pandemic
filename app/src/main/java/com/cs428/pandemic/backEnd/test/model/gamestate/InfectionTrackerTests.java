/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEnd.test.model.gamestate;

import com.cs428.pandemic.backEnd.model.gamestate.IInfectionTracker;
import com.cs428.pandemic.backEnd.model.gamestate.Implementation.InfectionTracker;

import asserts.LiteAssertFailedException;
import test.LiteDriver;
import test.annotations.LiteTest;

import static asserts.LiteAsserts.assertEquals;

/**
 *
 * @author James
 */
public class InfectionTrackerTests 
{
    private IInfectionTracker tracker;
    private int[] values;
    
    public void setup()
    {
        values = new int[]{2,2,2,3,3,4,4};
        tracker = new InfectionTracker(values);
    }
    
    @LiteTest
    public void test() throws LiteAssertFailedException
    {
        setup();
        int testNum = 1;
        try
        {
            assertEquals(true,tracker!=null,
                    "Test Infection Tracker " + testNum++ + ": Turn tracker should not be null");
            assertEquals(values[0],tracker.getInfectionsPerTurn(),
                    "Test Infection Tracker " + testNum++ + ": Infections per turn should be");
            for(int i = 0; i < values.length; ++i)
            {
                assertEquals(values[i],tracker.getInfectionsPerTurn(),
                    "Test Infection Tracker " + testNum++ + ": Infections per turn should be");
                tracker.advanceTrack(1);
            }
            tracker.setInfectionsTrack(0);
            assertEquals(values[0],tracker.getInfectionsPerTurn(),
                    "Test Infection Tracker " + testNum++ + ": Infections per turn should be");
            int advanceRate = 2;
            tracker.advanceTrack(advanceRate);
            assertEquals(values[advanceRate],tracker.getInfectionsPerTurn(),
                    "Test Infection Tracker " + testNum++ + ": Infections per turn should be");
            tracker.advanceTrack(advanceRate);
            assertEquals(values[2 * advanceRate],tracker.getInfectionsPerTurn(),
                    "Test Infection Tracker " + testNum++ + ": Infections per turn should be");
            tracker.advanceTrack(advanceRate);
            assertEquals(values[3 * advanceRate],tracker.getInfectionsPerTurn(),
                    "Test Infection Tracker " + testNum++ + ": Infections per turn should be");
        }
        catch(Exception e)
        {
            assertEquals(true,false,
                    "Test Infection Tracker " + testNum++ + ": Threw an exception " + e.getMessage());
        }
    }

    public static void main(String[] args)
    {
        String[] testClasses = new String[] 
        {
                "com.cs428.pandemic.backEnd.test.InfectionTrackerTests",
                "com.cs428.pandemic.backEnd.test.InfectionTrackerBuilderTests",
                "com.cs428.pandemic.backEnd.test.InfectionTrackerDirectorTests"
        };

        LiteDriver driver = new LiteDriver("src");
        for(String str:testClasses)
        {
            driver.queueTests(str);
        }
        driver.executeQueuedTests();
        driver.prettyPrint(true);
    }
}
