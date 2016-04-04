package com.cs428.pandemic.backEnd.command;

import android.app.FragmentManager;

import com.cs428.pandemic.backEnd.model.IGameModel;

/**
 * ICommand objects execute most of the game logic. They are generated
 * anonymously by the functions in AbstractRole and children of the AbstractRole.
 * <br>
 * ICommand objects are generally executed by the GameManager. They accept
 * the entire GameModel as a parameter and act upon that model to perform
 * they're functions.
 */
public interface ICommand {

    /**
     * Call this method to perform the operation encapsulated by this ICommand object. The
     * intent is that ICommand objects perform all of the in-game operations on the model. This
     * excludes game setup.
     */
    void execute(FragmentManager fm);

}
