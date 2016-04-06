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
    private ITurnTracker tracker;
    private IGameState gameState;
    private IGameMap gameMap;
    private IGameDecksHolder gameDecks;
    private List<IPlayer> players;
    private IDiseaseCubes diseaseCubes;

    public CompositeModel(ITurnTracker track,
                          IGameState state,
                          IGameMap map,
                          IGameDecksHolder decks,
                          List<IPlayer> playerList,
                          IDiseaseCubes diseases)
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
        return tracker;
    }

    @Override
    public IGameState getGameState()
    {
        return gameState;
    }

    @Override
    public IGameMap getGameMap()
    {
        return gameMap;
    }

    @Override
    public IGameDecksHolder getGameDecks()
    {
        return gameDecks;
    }

    @Override
    public List<IPlayer> getPlayers()
    {
        return players;
    }

    @Override
    public IDiseaseCubes getDiseaseCubes()
    {
        return diseaseCubes;
    }
}
