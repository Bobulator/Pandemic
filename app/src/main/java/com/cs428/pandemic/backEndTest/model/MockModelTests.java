/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEndTest.model;

import asserts.LiteAssertFailedException;
import static asserts.LiteAsserts.*;

import com.cs428.pandemic.backEnd.model.CompositeModelBuilder;
import com.cs428.pandemic.backEnd.model.IGameModel;
import com.cs428.pandemic.backEnd.model.IGameModelBuilder;
import com.cs428.pandemic.backEnd.model.turntracker.ITurnTracker;
import com.cs428.pandemic.backEnd.model.turntracker.TurnStateValue;
import test.LiteDriver;
import test.annotations.LiteClass;
import test.annotations.LiteTest;

/**
 * Tests the Mock Model
 * @author James
 */
@LiteClass
public class MockModelTests 
{
    IGameModelBuilder builder;
    
    public MockModelTests()
    {
        builder = new CompositeModelBuilder();
    }
    
    @LiteTest
    public void testTurnTrackerMocking() throws LiteAssertFailedException
    {
        assertEquals(true,true,"");
        ITurnTracker tracker = new ITurnTracker()
        {
            @Override
            public int getCurrentPlayer() 
            {
                return 12341;
            }

            @Override
            public void setCurrentPlayer(int currentPlayer) {}

            @Override
            public void advancePlayer() {}

            @Override
            public void advanceTurnState() {}

            @Override
            public void setTurnState(TurnStateValue value) {}

            @Override
            public TurnStateValue getTurnState() 
            {
                return TurnStateValue.ACTION;
            }

            @Override
            public void setCurrentActionPoints(int value) {}

            @Override
            public int getCurrentActionPoints() 
            {
                return 0;
            }

            @Override
            public boolean decrementActionPoints(int amount) 
            {
                return false;
            }

            @Override
            public void setOneQuietNight() {}
        };
        builder.setTracker(tracker);
        IGameModel model = builder.createModel();
        assertEquals(model.getTurnTracker(), tracker,"Trackers should be equal");
    }
}
