/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEnd.gamestate;

/**
 * Implementation without a map
 * @author James
 */
public class StandardDiseaseData implements IDiseaseData
{
    boolean yellowCured;
    boolean redCured;
    boolean blueCured;
    boolean blackCured;
    
    boolean yellowEradicated;
    boolean redEradicated;
    boolean blueEradicated;
    boolean blackEradicated;
    
    public StandardDiseaseData()
    {
        this.yellowCured = false;
        this.redCured = false;
        this.blueCured = false;
        this.blackCured = false;
    
        this.yellowEradicated = false;
        this.redEradicated = false;
        this.blueEradicated = false;
        this.blackEradicated = false;
    }
    
    @Override
    public boolean isCured(DiseaseType vir) 
    {
        switch(vir)
        {
            case YELLOW:
                return yellowCured;
            case RED:
                return redCured;
            case BLUE:
                return blueCured;
            case BLACK:
                return blackCured;
            default:
                return false;
        }
    }

    @Override
    public boolean isEradicated(DiseaseType vir) 
    {
        switch(vir)
        {
            case YELLOW:
                return yellowEradicated;
            case RED:
                return redEradicated;
            case BLUE:
                return blueEradicated;
            case BLACK:
                return blackEradicated;
            default:
                return false;
        }
    }

    @Override
    public void cure(DiseaseType vir) 
    {
        switch(vir)
        {
            case YELLOW:
                yellowCured = true;
                break;
            case RED:
                redCured = true;
                break;
            case BLUE:
                blueCured = true;
                break;
            case BLACK:
                blackCured = true;
                break;
            default:
                break;
        }
    }

    @Override
    public void decure(DiseaseType vir) 
    {
        switch(vir)
        {
            case YELLOW:
                yellowCured = false;
                break;
            case RED:
                redCured = false;
                break;
            case BLUE:
                blueCured = false;
                break;
            case BLACK:
                blackCured = false;
                break;
            default:
                break;
        }
    }

    @Override
    public void eradicate(DiseaseType vir) 
    {
        switch(vir)
        {
            case YELLOW:
                yellowEradicated = true;
                break;
            case RED:
                redEradicated = true;
                break;
            case BLUE:
                blueEradicated = true;
                break;
            case BLACK:
                blackEradicated = true;
                break;
            default:
                break;
        }
    }

    @Override
    public void deeradicate(DiseaseType vir)
    {
        switch(vir)
        {
            case YELLOW:
                yellowEradicated = false;
                break;
            case RED:
                redEradicated = false;
                break;
            case BLUE:
                blueEradicated = false;
                break;
            case BLACK:
                blackEradicated = false;
                break;
            default:
                break;
        }
    }

    @Override
    public boolean allCured() 
    {
        return yellowCured && redCured && blueCured && blackCured;
    }
}
