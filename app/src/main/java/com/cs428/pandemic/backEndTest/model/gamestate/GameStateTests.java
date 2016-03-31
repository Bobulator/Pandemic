/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEndTest.model.gamestate;

import com.cs428.pandemic.backEnd.model.gamestate.implementation.StandardDiseaseData;
import com.cs428.pandemic.backEnd.model.gamestate.implementation.GameState;
import test.LiteDriver;
import asserts.LiteAssertFailedException;
import test.annotations.*;

import com.cs428.pandemic.backEnd.model.gamestate.*;

import static asserts.LiteAsserts.*;

/**
 *
 * @author James
 */
@LiteClass
public class GameStateTests
{
    private IGameState state;
    private int maxOutbreaks;
    public void setup()
    {
        maxOutbreaks = 8;
        IInfectionTracker tracker = new MockInfectionTracker();
        IDiseaseData data = new StandardDiseaseData();
        state = (IGameState)new GameState(maxOutbreaks,0,tracker,data);
    }
    
    @LiteTest
    public void testOutbreaks() throws LiteAssertFailedException
    {
        try
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
            try
            {
                state.setNumberOfOutbreaks(maxOutbreaks);
                assertEquals(true,false,"Exception should be thrown");
            }
            catch(TooManyOutbreaksException e)
            {
                state.setNumberOfOutbreaks(maxOutbreaks - 1);
            }
            try
            {
                state.outBreak();
                assertEquals(true,false,"Exception should be thrown");
            }
            catch(TooManyOutbreaksException e){}
        }
        catch(TooManyOutbreaksException e)
        {
            assertEquals(true,false,
                    "Exception should not be thrown: " + e.getMessage());
        }
    }
    
    public static void main(String[] args)
    {
        LiteDriver driver = new LiteDriver("src","com.cs428.pandemic.backEndTest.model.gamestate");
            driver.runTests(true,true,true);
    }
}
