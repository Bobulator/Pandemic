/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEnd.test;

import com.cs428.pandemic.backEnd.model.gamestate.Implementation.InfectionTrackerBuilder;
import static org.junit.Assert.assertEquals;

import com.cs428.pandemic.backEnd.model.gamestate.*;
import java.util.List;
import java.util.Random;
import org.junit.*;

/**
 *
 * @author James
 */
public class InfectionTrackerBuilderTests 
{
    private IInfectionTrackerBuilder builder;
    private int[] values;
    
    @Before
    public void setup()
    {
        values = new int[]{2,2,2,3,3,4,4};
        builder = new InfectionTrackerBuilder();
    }
    
    @Test
    public void test()
    {
        int testNum = 1;
        try
        {
            for(int i = 0; i < values.length; ++i)
            {
                builder.addTrackValue(values[i]);
            }
            IInfectionTracker tracker = builder.getTracker();
            assertEquals("Test Infection Builder " + testNum++ + ": Turn tracker should not be null",
                    true,tracker!=null);
            for(int i = 0; i < values.length; ++i)
            {
                tracker.setInfectionsTrack(i);
                assertEquals("Test Infection Builder " + testNum++ + ": value[" + i + "] passed from builder to tracker should be " + values[i],
                    values[i],tracker.getInfectionsPerTurn());
            }
        }
        catch(Exception e)
        {
            assertEquals("Test Infection builder " + testNum++ + ": Threw an exception " + e.getMessage(),
                    true,false);
        }
    }

    public static void main(String[] args)
    {
        String[] testClasses = new String[] 
        {
                "com.cs428.pandemic.backEnd.test.InfectionTrackerBuilderTests"
        };

        org.junit.runner.JUnitCore.main(testClasses);
    }
}
