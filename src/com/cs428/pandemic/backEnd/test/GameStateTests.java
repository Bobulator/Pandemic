/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEnd.test;

import com.cs428.pandemic.backEnd.gamestate.Implementation.StandardDiseaseData;
import com.cs428.pandemic.backEnd.gamestate.Implementation.GameState;
import static org.junit.Assert.assertEquals;

import com.cs428.pandemic.backEnd.gamestate.*;
import java.util.List;
import java.util.Random;
import org.junit.*;

/**
 *
 * @author James
 */
public class GameStateTests
{
    private IGameState state;
    
    @Before
    public void setup()
    {
        IInfectionTracker tracker = new MockInfectionTracker();
        IDiseaseData data = new StandardDiseaseData();
        state = new GameState(0,tracker,data);
    }
    
    @Test
    public void testOutbreaks()
    {
        int testNum = 1;
        int start = 2;
        state.setNumberOfOutbreaks(start);
        assertEquals("Test Outbreaks " + testNum++ + ": current outbreak counter should be set to " + start, 
                start, state.getNumberOfOutbreaks());
        state.outBreak();
        assertEquals("Test Outbreaks " + testNum++ + ": outbreaks should be " + (start + 1),
                start + 1, state.getNumberOfOutbreaks());
    }
    
    public static void main(String[] args)
    {
        String[] testClasses = new String[] 
        {
            "com.cs428.pandemic.backEnd.test.InfectionTrackerTests",
            "com.cs428.pandemic.backEnd.test.InfectionTrackerBuilderTests",
            "com.cs428.pandemic.backEnd.test.InfectionTrackerDirectorTests",
            "com.cs428.pandemic.backEnd.test.DiseaseDataTests",
            "com.cs428.pandemic.backEnd.test.GameStateTests"
        };

        org.junit.runner.JUnitCore.main(testClasses);
    }
}
