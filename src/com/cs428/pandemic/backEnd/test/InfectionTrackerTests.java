/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEnd.test;

import com.cs428.pandemic.backEnd.gamestate.Implementation.InfectionTracker;
import static org.junit.Assert.assertEquals;

import com.cs428.pandemic.backEnd.gamestate.*;
import java.util.List;
import org.junit.*;

/**
 *
 * @author James
 */
public class InfectionTrackerTests 
{
    private IInfectionTracker tracker;
    private int[] values;
    
    @Before
    public void setup()
    {
        values = new int[]{2,2,2,3,3,4,4};
        tracker = new InfectionTracker(values);
    }
    
    @Test
    public void test()
    {
        int testNum = 1;
        try
        {
            assertEquals("Test Infection Tracker " + testNum++ + ": Turn tracker should not be null",
                    true,tracker!=null);
            assertEquals("Test Infection Tracker " + testNum++ + ": Infections per turn should be",
                    values[0],tracker.getInfectionsPerTurn());
            for(int i = 0; i < values.length; ++i)
            {
                assertEquals("Test Infection Tracker " + testNum++ + ": Infections per turn should be",
                    values[i],tracker.getInfectionsPerTurn());
                tracker.advanceTrack(1);
            }
            tracker.setInfectionsTrack(0);
            assertEquals("Test Infection Tracker " + testNum++ + ": Infections per turn should be",
                    values[0],tracker.getInfectionsPerTurn());
            int advanceRate = 2;
            tracker.advanceTrack(advanceRate);
            assertEquals("Test Infection Tracker " + testNum++ + ": Infections per turn should be",
                    values[advanceRate],tracker.getInfectionsPerTurn());
            tracker.advanceTrack(advanceRate);
            assertEquals("Test Infection Tracker " + testNum++ + ": Infections per turn should be",
                    values[2 * advanceRate],tracker.getInfectionsPerTurn());
            tracker.advanceTrack(advanceRate);
            assertEquals("Test Infection Tracker " + testNum++ + ": Infections per turn should be",
                    values[3 * advanceRate],tracker.getInfectionsPerTurn());
        }
        catch(Exception e)
        {
            assertEquals("Test Infection Tracker " + testNum++ + ": Threw an exception " + e.getMessage(),
                    true,false);
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

        org.junit.runner.JUnitCore.main(testClasses);
    }
}
