/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEnd.model.gamestate;

/**
 *
 * @author James
 */
public class TooManyOutbreaksException extends Exception
{
    public TooManyOutbreaksException(int outbreaks)
    {
        super("You lost. " + outbreaks + " outbreaks happened.");
    }
}
