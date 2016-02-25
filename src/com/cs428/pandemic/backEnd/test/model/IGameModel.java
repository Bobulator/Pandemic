/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEnd.test.model;

import com.cs428.pandemic.backEnd.model.deck.IInfectionDeck;
import com.cs428.pandemic.backEnd.model.deck.IPlayerDeck;
import com.cs428.pandemic.backEnd.model.disease.IDiseaseCubes;
import com.cs428.pandemic.backEnd.model.gamestate.IGameState;
import com.cs428.pandemic.backEnd.model.map.IMap;
import com.cs428.pandemic.backEnd.model.player.IPlayer;
import com.cs428.pandemic.backEnd.model.turntracker.ITurnTracker;
import java.util.List;

/**
 *
 * @author James
 */
public interface IGameModel 
{
    ITurnTracker getTurnTracker();
    IGameState getGameState();
    IMap getMap();
    IPlayerDeck getPlayerDeck();
    IInfectionDeck getInfectionDeck();
    List<IPlayer> getPlayers();
    IDiseaseCubes getDiseaseCubes();
}
