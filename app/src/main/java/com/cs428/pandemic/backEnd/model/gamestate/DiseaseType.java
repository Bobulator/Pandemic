/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEnd.model.gamestate;

/**
 * The color for a disease
 * @author James
 */
public enum DiseaseType 
{
    YELLOW,
    RED,
    BLUE,
    BLACK;

    @Override
    public String toString() {

        switch(this){

            case YELLOW:
                return "Yellow";
            case RED:
                return "Red";
            case BLUE:
                return "Blue";
            case BLACK:
                return "Black";
            default:
                return "YOU SUCK";
        }
    }

    public static DiseaseType getType(String name){

        switch(name){

            case "Yellow":
                return YELLOW;
            case "Red":
                return RED;
            case "Blue":
                return BLUE;
            case "Black":
                return BLACK;
            default:
                return null;
        }
    }
}
