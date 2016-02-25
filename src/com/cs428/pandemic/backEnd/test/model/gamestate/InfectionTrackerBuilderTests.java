/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEnd.test.model.gamestate;

import asserts.LiteAssertFailedException;
import test.annotations.LiteTest;
import test.LiteDriver;
import com.cs428.pandemic.backEnd.model.gamestate.Implementation.InfectionTrackerBuilder;

import com.cs428.pandemic.backEnd.model.gamestate.*;

import static asserts.LiteAsserts.*;

/**
 *
 * @author James
 */
public class InfectionTrackerBuilderTests 
{
    private IInfectionTrackerBuilder builder;
    private int[] values;
    
    public void setup()
    {
        values = new int[]{2,2,2,3,3,4,4};
        builder = new InfectionTrackerBuilder();
    }
    
    @LiteTest
    public void test() throws LiteAssertFailedException
    {
        setup();
        int testNum = 1;
        try
        {
            for(int i = 0; i < values.length; ++i)
            {
                builder.addTrackValue(values[i]);
            }
            IInfectionTracker tracker = builder.getTracker();
            assertEquals(true,tracker!=null,
                    "Test Infection Builder " + testNum++ + ": Turn tracker should not be null");
            for(int i = 0; i < values.length; ++i)
            {
                tracker.setInfectionsTrack(i);
                assertEquals(values[i],tracker.getInfectionsPerTurn(),
                    "Test Infection Builder " + testNum++ + ": value[" + i + "] passed from builder to tracker should be " + values[i]);
            }
        }
        catch(Exception e)
        {
            assertEquals(true,false,
                    "Test Infection builder " + testNum++ + ": Threw an exception " + e.getMessage());
        }
    }

    public static void main(String[] args)
    {
        String[] testClasses = new String[] 
        {
                "com.cs428.pandemic.backEnd.test.InfectionTrackerBuilderTests"
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
