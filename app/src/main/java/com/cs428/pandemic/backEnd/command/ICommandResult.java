package com.cs428.pandemic.backEnd.command;

/**
 * ICommand objects perform the main game logic of Pandemic game play.
 * The ICommand documentation describes this further. Because the operations
 * that are performed by ICommand are so varied, it is unrealistic for
 * them to return a primitive type as a result.
 * <br>
 * The ICommandResult is a wrapper for the results of the execute function on
 * ICommand. It allows the execute function to specify the type of result and
 * related messages, new commands, and data.
 *
 */
public interface ICommandResult {

    public enum ResultType {

        SUCCESS, FAILURE, LEGAL, ILLEGAL, CHAIN

    }

    /**
     * The types of results that the execute function on ICommand
     * can return. Some notes about these types are as follows:
     * <ul>
     *     <li> CanDo methods return LEGAL or ILLEGAL </li>
     *     <li> Do methods return SUCCESS or FAILURE </li>
     *     <li> Results that require executing another ICommand return CHAIN </li>
     * </ul>
     */

    /**
     * Get the type of the ICommand result.
     * @return The ResultType value of this ICommandResult.
     */
    ResultType getResult();

    /**
     * Get the message associated with this ICommandResult.
     * @return A string with a message related to the result. Usually an error message.
     */
    String getMessage();

    /**
     * In some instances a command may need to be executed after another is finished.
     * This method returns a command object that can be executed at some later point.
     * @return An ICommand object that may be executed later.
     */
    ICommand getChainCommand();

    /**
     * In some instances a command may need to return some extra data. This data would be
     * returned as an Object type through getData, then cast to its actual type externally.
     * @return Some information relevant to the ICommand result passed as an Object.
     */
    Object getData();

}