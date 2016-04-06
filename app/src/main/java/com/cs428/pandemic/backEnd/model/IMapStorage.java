/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEnd.model;

import com.cs428.pandemic.backEnd.model.map.IGameMap;

/**
 * Slice of the model that holds the map accessor
 * @author James
 */
public interface IMapStorage 
{
    /**
     * Contains city data and adjacency information
     * @return 
     */
    IGameMap getGameMap();
}
