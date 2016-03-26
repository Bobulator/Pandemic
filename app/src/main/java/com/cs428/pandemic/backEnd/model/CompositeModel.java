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
import java.util.List;

/**
 *
 * @author James
 */
public class CompositeModel implements IGameModel
{
    private ITurnTrackerStorage tracker;
    private IGameStateStorage gameState;
    private IMapStorage gameMap;
    private IGameDecksHolderStorage gameDecks;
    private IPlayerStorage players;
    private IDiseaseCubesStorage diseaseCubes;
    
    public CompositeModel(ITurnTrackerStorage track, 
            IGameStateStorage state, 
            IMapStorage map, 
            IGameDecksHolderStorage decks, 
            IPlayerStorage playerList, 
            IDiseaseCubesStorage diseases)
    {
        tracker = track;
        gameState = state;
        gameMap = map;
        gameDecks = decks;
        players = playerList;
        diseaseCubes = diseases;
    }

    @Override
    public ITurnTracker getTurnTracker() 
    {
        return tracker.getTurnTracker();
    }

    @Override
    public IGameState getGameState() 
    {
        return gameState.getGameState();
    }

    @Override
    public IGameMap getMap()
    {
        return gameMap.getMap();
    }

    @Override
    public IGameDecksHolder getGameDecks() 
    {
        return gameDecks.getGameDecks();
    }

    @Override
    public List<IPlayer> getPlayers() 
    {
        return players.getPlayers();
    }

    @Override
    public IDiseaseCubes getDiseaseCubes() 
    {
        return diseaseCubes.getDiseaseCubes();
    }
}
