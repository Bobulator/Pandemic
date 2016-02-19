/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEnd.test;

import static org.junit.Assert.assertEquals;

import com.cs428.pandemic.backEnd.gamestate.*;
import java.util.List;
import java.util.Random;
import org.junit.*;

/**
 *
 * @author James
 */
public class InfectionTrackerDirectorTests 
{
    private IInfectionTrackerDirector director;
    private MockInfectionTrackBuilder builder;
    
    @Before
    public void setup()
    {
        director = new InfectionTrackerDirector();
        builder = new MockInfectionTrackBuilder();
    }
    
    @Test
    public void test()
    {
        int testNum = 1;
        IInfectionTracker tracker = this.director.create(builder);
        assertEquals("Test Infection Track Director " + testNum++ + ": Turn tracker should not be null",true,tracker!=null);
        List<Integer> values = this.builder.getValues();
        int[] arr = new int[] {2,2,2,3,3,4,4};
        if(values.size() == arr.length)
        {
            for(int i = 0; i < values.size(); ++i)
            {
                assertEquals("Test " + testNum++ + ": value[" + i + "] needs to be equal to " + arr[i],
                        arr[i], values.get(i).intValue());
            }
        }
        else
        {
            assertEquals("Test " + testNum++ + ": Builder does not have enough values", 
                    values.size(), arr.length);
        }
    }

    public static void main(String[] args)
    {
        String[] testClasses = new String[] 
        {
                "com.cs428.pandemic.backEnd.test.InfectionTrackerDirectorTests"
        };

        org.junit.runner.JUnitCore.main(testClasses);
    }
}
