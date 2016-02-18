/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEnd.gamestate;

/**
 * Holds the data for the disease: whether the disease has been cured or eradicated
 * @author James
 */
public interface IDiseaseData 
{
    /**
     * Tests whether vir has been cured
     * @param vir the disease to be tested
     * @return whether it has been cured
     */
    boolean isCured(DiseaseType vir);
    
    /**
     * Tests whether vir has been eradicated
     * @param vir the disease to be tested
     * @return whether it has been eradicated
     */
    boolean isEradicated(DiseaseType vir);

    /**
     * Sets the isCured flag for the given virus to true
     * @param vir the disease to be cured
     */
    void cure(DiseaseType vir);
    
    /**
     * Sets the isCured flag for the given virus to false
     * @param vir the disease to be decured 
     */
    void decure(DiseaseType vir);
    
    /**
     * Sets the isEradicated flag for the given virus to true
     * @param vir the disease to be eradicated
     */
    void eradicate(DiseaseType vir);

    /**
     * Sets the isEradicated flag for the given virus to false
     * @param vir the disease to be deeradicated
     */
    void deeradicate(DiseaseType vir);
    
    /**
     * Returns if all of the diseases have been cured
     * @return true if all diseases have been cured, false if otherwise
     */
    boolean allCured();
}
