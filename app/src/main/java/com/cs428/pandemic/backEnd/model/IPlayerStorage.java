/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEnd.model;

import com.cs428.pandemic.backEnd.model.player.IPlayer;
import java.util.List;

/**
 * Slice of the model that holds the player accessors
 * @author James
 */
public interface IPlayerStorage 
{
    /**
     * Contains all of the basic information for the players
     * @return 
     */
    List<IPlayer> getPlayers();
}
