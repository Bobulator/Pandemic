/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEnd.model.gamestate.Implementation;

/**
 * Holds the data for a given disease: whether it has been cured or whether it has been eradicated
 * @author James
 */
public class DiseaseStatusInfo 
{
    private boolean cured;
    private boolean eradicated;
    
    public boolean isCured()
    {
        return cured;
    }
    
    public void cure()
    {
        cured = true;
    }
    
    public void decure()
    {
        cured = false;
    }
    
    public boolean isEradicated()
    {
        return eradicated;
    }
    
    public void eradicate()
    {
        eradicated = true;
    }
    
    public void deeradicate()
    {
        eradicated = false;
    }
}
