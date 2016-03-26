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
public interface IGameModelBuilder {

    IGameModelBuilder addPlayers(IPlayer player);

    IGameModel createModel();

    IGameModelBuilder setDeck(IGameDecksHolder decks);

    IGameModelBuilder setDiseaseCubes(IDiseaseCubes cubes);

    IGameModelBuilder setGameState(IGameState state);

    IGameModelBuilder setMap(IGameMap map);

    IGameModelBuilder setPlayers(List<IPlayer> playerList);

    IGameModelBuilder setTracker(ITurnTracker track);
    
}
