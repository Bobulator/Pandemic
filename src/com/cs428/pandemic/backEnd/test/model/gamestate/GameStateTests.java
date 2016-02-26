/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEnd.test.model.gamestate;

import test.LiteDriver;
import asserts.LiteAssertFailedException;
import test.annotations.*;
import com.cs428.pandemic.backEnd.model.gamestate.Implementation.StandardDiseaseData;
import com.cs428.pandemic.backEnd.model.gamestate.Implementation.GameState;

import com.cs428.pandemic.backEnd.model.gamestate.*;

import static asserts.LiteAsserts.*;

/**
 *
 * @author James
 */
public class GameStateTests
{
    private IGameState state;
    
    public void setup()
    {
        IInfectionTracker tracker = new MockInfectionTracker();
        IDiseaseData data = new StandardDiseaseData();
        state = new GameState(0,tracker,data);
    }
    
    @LiteTest
    public void testOutbreaks() throws LiteAssertFailedException
    {
        setup();
        int testNum = 1;
        int start = 2;
        state.setNumberOfOutbreaks(start);
        assertEquals(start, state.getNumberOfOutbreaks(),
                "Test Outbreaks " + testNum++ + ": current outbreak counter should be set to " + start);
        state.outBreak();
        assertEquals(start + 1, state.getNumberOfOutbreaks(),
                "Test Outbreaks " + testNum++ + ": outbreaks should be " + (start + 1));
    }
    
    public static void main(String[] args)
    {
        LiteDriver driver = new LiteDriver("src","com.cs428.pandemic.backEnd.test.model.gamestate");
            driver.runTests(true,true,true);
    }
}
