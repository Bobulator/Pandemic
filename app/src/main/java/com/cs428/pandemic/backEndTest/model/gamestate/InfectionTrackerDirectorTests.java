/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEndTest.model.gamestate;

import asserts.LiteAssertFailedException;
import com.cs428.pandemic.backEnd.model.gamestate.implementationtemp.InfectionTrackerDirector;
import static asserts.LiteAsserts.*;

import com.cs428.pandemic.backEnd.model.gamestate.*;
import java.util.List;

import test.LiteDriver;
import test.annotations.LiteClass;
import test.annotations.LiteTest;

/**
 *
 * @author James
 */
@LiteClass
public class InfectionTrackerDirectorTests 
{
    private IInfectionTrackerDirector director;
    private MockInfectionTrackBuilder builder;
    
    public void setup()
    {
        director = new InfectionTrackerDirector();
        builder = new MockInfectionTrackBuilder();
    }
    
    @LiteTest
    public void test() throws LiteAssertFailedException
    {
        setup();
        int testNum = 1;
        IInfectionTracker tracker = this.director.create(builder);
        assertEquals(true,tracker!=null,
                "Test Infection Track Director " + testNum++ + ": Turn tracker should not be null");
        List<Integer> values = this.builder.getValues();
        int[] arr = new int[] {2,2,2,3,3,4,4};
        if(values.size() == arr.length)
        {
            for(int i = 0; i < values.size(); ++i)
            {
                assertEquals(arr[i], values.get(i).intValue(),
                        "Test " + testNum++ + ": value[" + i + "] needs to be equal to " + arr[i]);
            }
        }
        else
        {
            assertEquals(values.size(), arr.length,
                    "Test " + testNum++ + ": Builder does not have enough values");
        }
    }

    public static void main(String[] args)
    {
        String[] testClasses = new String[] 
        {
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
