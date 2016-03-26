/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEnd.model;

import com.cs428.pandemic.backEnd.model.deck.IGameDecksHolder;
import com.cs428.pandemic.backEnd.model.disease.IDiseaseCubes;
import com.cs428.pandemic.backEnd.model.gamestate.IGameState;
import com.cs428.pandemic.backEnd.model.map.IGameMap;
import com.cs428.pandemic.backEnd.model.player.IPlayer;
import com.cs428.pandemic.backEnd.model.turntracker.ITurnTracker;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author James
 */
public class CompositeModelBuilder implements IGameModelBuilder 
{
    private ITurnTrackerStorage tracker;
    private IGameStateStorage gameState;
    private IMapStorage gameMap;
    private IGameDecksHolderStorage gameDecks;
    private List<IPlayer> players;
    private IDiseaseCubesStorage diseaseCubes;
    
    public CompositeModelBuilder()
    {
        tracker = new ITurnTrackerStorage()
        {
            @Override
            public ITurnTracker getTurnTracker() 
            {
                return null;
            }
        };
        gameState = new IGameStateStorage()
        {
            @Override
            public IGameState getGameState() 
            {
                return null;
            }
        };
        gameMap = new IMapStorage()
        {
            @Override
            public IGameMap getMap()
            {
                return null;
            }
        };
        gameDecks = new IGameDecksHolderStorage()
        {
            @Override
            public IGameDecksHolder getGameDecks() 
            {
                return null;
            }
        };
        players = new ArrayList<>();
        diseaseCubes = new IDiseaseCubesStorage()
        {
            @Override
            public IDiseaseCubes getDiseaseCubes() 
            {
                return null;
            }
        };
    }
    
    @Override
    public IGameModelBuilder setTracker(ITurnTracker track)
    {
        final ITurnTracker turnTracker = track;
        tracker = new ITurnTrackerStorage()
        {
            @Override
            public ITurnTracker getTurnTracker() 
            {
                return turnTracker;
            }
        };
        return this;
    }
            
    @Override
    public IGameModelBuilder setGameState(IGameState state)
    {
        final IGameState tehBawsState = state;
        gameState = new IGameStateStorage()
        {
            @Override
            public IGameState getGameState() 
            {
                return tehBawsState;
            }
        };
        return this;
    }
    
    @Override
    public IGameModelBuilder setMap(IGameMap map)
    {
        final IGameMap theMap = map;
        gameMap = new IMapStorage()
        {
            @Override
            public IGameMap getMap()
            {
                return theMap;
            }
        };
        return this;
    }
    
    @Override
    public IGameModelBuilder setDeck(IGameDecksHolder decks)
    {
        final IGameDecksHolder theDecks = decks;
        gameDecks = new IGameDecksHolderStorage()
        {
            @Override
            public IGameDecksHolder getGameDecks() 
            {
                return theDecks;
            }
        };
        return this;
    }
    
    @Override
    public IGameModelBuilder setPlayers(List<IPlayer> playerList)
    {
        players = playerList;
        return this;
    }
    
    @Override
    public IGameModelBuilder addPlayers(IPlayer player)
    {
        players.add(player);
        return this;
    }
    
    @Override
    public IGameModelBuilder setDiseaseCubes(IDiseaseCubes cubes)
    {
        final IDiseaseCubes theDiseaseCubes = cubes;
        diseaseCubes = new IDiseaseCubesStorage()
        {
            @Override
            public IDiseaseCubes getDiseaseCubes() 
            {
                return theDiseaseCubes;
            }
        };
        return this;
    }
    
    @Override
    public IGameModel createModel()
    {
        final List<IPlayer> playerList = players;
        IPlayerStorage playerStorage = new IPlayerStorage()
        {
            @Override
            public List<IPlayer> getPlayers() 
            {
                return playerList;
            }
        };
        return new CompositeModel(tracker,gameState,gameMap,gameDecks,playerStorage,diseaseCubes);
    }
}
