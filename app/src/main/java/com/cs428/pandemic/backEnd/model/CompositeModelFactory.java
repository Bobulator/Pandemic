/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEnd.model;

import com.cs428.pandemic.backEnd.model.deck.IGameDecksFactory;
import com.cs428.pandemic.backEnd.model.deck.IGameDecksHolder;
import com.cs428.pandemic.backEnd.model.deck.StandardGameDecksFactory;
import com.cs428.pandemic.backEnd.model.disease.IDiseaseCubes;
import com.cs428.pandemic.backEnd.model.gamestate.IDiseaseData;
import com.cs428.pandemic.backEnd.model.gamestate.IGameState;
import com.cs428.pandemic.backEnd.model.gamestate.IGameStateFactory;
import com.cs428.pandemic.backEnd.model.gamestate.IInfectionTracker;
import com.cs428.pandemic.backEnd.model.gamestate.StandardGameStateFactory;
import com.cs428.pandemic.backEnd.model.map.IGameMap;
import com.cs428.pandemic.backEnd.model.player.IPlayer;
import com.cs428.pandemic.backEnd.model.turntracker.ITurnTracker;
import com.cs428.pandemic.backEnd.model.turntracker.ITurnTrackerFactory;
import com.cs428.pandemic.backEnd.model.turntracker.StandardTurnTrackerFactory;
import java.util.List;

/**
 * Is a composite of the various factories that generate model objects.
 * GO TO HERE FIRST FOR ALL YOUR CONSTRUCTION NEEDS
 * @author James
 */
public class CompositeModelFactory implements IGameModelFactory,ITurnTrackerFactory,IGameStateFactory,IGameDecksFactory
{
    private ITurnTrackerFactory turnTrackerFactory;
    private IGameStateFactory gameStateFactory;
    private IGameDecksFactory deckFactory;
    private IGameModelFactory modelFactory;
    
    public CompositeModelFactory(IGameModelFactory modelFactory, 
            ITurnTrackerFactory turnTrackerFactory, 
            IGameStateFactory gameStateFactory,
            IGameDecksFactory decksFactory)
    {
        this.turnTrackerFactory = turnTrackerFactory;
        this.gameStateFactory = gameStateFactory;
        deckFactory = decksFactory;
        this.modelFactory = modelFactory;
    }
    
    public CompositeModelFactory()
    {
        this(new BasicModelFactory(), new StandardTurnTrackerFactory(), new StandardGameStateFactory(), new StandardGameDecksFactory());
    }
    
    @Override
    public IGameModelBuilder createModelBuilder() 
    {
        return modelFactory.createModelBuilder();
    }

    @Override
    public ITurnTracker createTurnTracker(int numberOfPlayers) 
    {
        return turnTrackerFactory.createTurnTracker(numberOfPlayers);
    }

    @Override
    public IGameState createGameState() 
    {
        return gameStateFactory.createGameState();
    }

    @Override
    public IInfectionTracker createInfectionTracker() 
    {
        return gameStateFactory.createInfectionTracker();
    }

    @Override
    public IDiseaseData createDiseaseData() 
    {
        return gameStateFactory.createDiseaseData();
    }

    @Override
    public IGameDecksHolder createGameDeck() 
    {
        return deckFactory.createGameDeck();
    }
}
