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
     * <br>
     * Because the operations performed by execute vary widely, the return values may vary
     * widely as well. Therefore, execute returns an IResult object which has methods for
     * identifying different elements of the results.
     * <br>
     * IResult docs give more detail, but to summarize, each IResult object will be built by the
     * execute(GameModel) method by implementing the IResult methods in the following manner:
     * <ul>
     *     <li> getResult() for a canDo command returns LEGAL or ILLEGAL </li>
     *     <li> getResult() for a do command returns SUCCESS or FAILURE </li>
     *     <li> getResult() for a command that should invoke another command returns CHAIN </li>
     *     <li> getMessage() will generally only return error messages, but could be other things</li>
     *     <li> getChainCommand() may return a command object that should be invoked externally</li>
     *     <li> getData() may return data accompanying results of the command </li>
     * </ul>
     * @return The result of the operation expresses as an IResult object.
     */
    ICommandResult execute(FragmentManager fm);

}
