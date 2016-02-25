/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEnd.model.turntracker.statepattern;

import com.cs428.pandemic.backEnd.model.turntracker.TurnStateValue;

/**
 *
 * @author James
 */
public class TurnStateFactory 
{
    ITurnState createState(TurnStateValue val, boolean oneQuietNight)
    {
        switch(val)
        {
            case ACTION:
                if(oneQuietNight)
                {
                    return new OneQuietNightActionState();
                }
                else
                {
                    return new ActionState();
                }
            case DRAW_PLAYER:
                if(oneQuietNight)
                {
                    return new OneQuietNightDrawPlayerState();
                }
                else
                {
                    return new DrawPlayerState();
                }
            case DRAW_INFECT:
                return new DrawInfectState();
            default:
                return null;
        }
    }
}
