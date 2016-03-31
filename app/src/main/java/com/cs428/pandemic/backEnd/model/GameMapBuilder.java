/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEnd.model;

import com.cs428.pandemic.backEnd.model.gamestate.DiseaseType;
import com.cs428.pandemic.backEnd.model.map.City;
import com.cs428.pandemic.backEnd.model.map.GameMap;
import com.cs428.pandemic.backEnd.model.map.ICity;
import com.cs428.pandemic.backEnd.model.map.IGameMap;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author James
 */
public class GameMapBuilder 
{
    HashMap<String, ICity> board = new HashMap<>();
    HashMap<String, HashMap<String, ICity>> adjacencyMap = new HashMap<>();
    HashMap<String, List<String>> adjacencyList = new HashMap<>();
    
    GameMapBuilder addCity(String name, DiseaseType color, List<String> adjacencies)
    {
        HashMap<String, ICity> initialCityAdjacencies = new HashMap<>();
        adjacencyMap.put(name, initialCityAdjacencies);
        adjacencyList.put(name, adjacencies);
        board.put(name, new City(name, color, initialCityAdjacencies));
        return this;
    }
    
    IGameMap createMap()
    {
        for(String cityName : adjacencyList.keySet())
        {
            for(String adjacentCity : adjacencyList.get(cityName))
            {
                adjacencyMap.get(cityName).put(adjacentCity, board.get(adjacentCity));
            }
        }
        return new GameMap(board);
    }
}
