/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cs428.pandemic.backEnd.model;

/**
 *
 * @author James
 */
public interface IGameModelFactory 
{
    IGameModelBuilder createModelBuilder();
}
