/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEnd.model;

import com.cs428.pandemic.backEnd.model.disease.IDiseaseCubes;

/**
 * Slice of the model that has the Disease Cube accessors
 * @author James
 */
public interface IDiseaseCubesStorage 
{
    /**
     * Knows how many diseases are still in the bank
     * @return 
     */
    IDiseaseCubes getDiseaseCubes();
}
