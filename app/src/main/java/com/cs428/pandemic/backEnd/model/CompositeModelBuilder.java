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
    private ITurnTracker tracker;
    private IGameState gameState;
    private IGameMap gameMap;
    private IGameDecksHolder gameDecks;
    private List<IPlayer> players;
    private IDiseaseCubes diseaseCubes;

    public CompositeModelBuilder()
    {
        tracker = null;
        gameState = null;
        gameMap = null;
        gameDecks = null;
        players = new ArrayList<>();
        diseaseCubes = null;
    }

    @Override
    public IGameModelBuilder setTracker(ITurnTracker track)
    {
        tracker = track;
        return this;
    }

    @Override
    public IGameModelBuilder setGameState(IGameState state)
    {
        gameState = state;
        return this;
    }

    @Override
    public IGameModelBuilder setMap(IGameMap map)
    {
        gameMap = map;
        return this;
    }

    @Override
    public IGameModelBuilder setDeck(IGameDecksHolder decks)
    {
        gameDecks = decks;
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
        diseaseCubes = cubes;
        return this;
    }

    @Override
    public IGameModel createModel()
    {
        final List<IPlayer> playerList = players;
        return new CompositeModel(tracker,gameState,gameMap,gameDecks,playerList,diseaseCubes);
    }
}
