package com.cs428.pandemic.backEnd.roles.abs;

/**
 * IRoleCommand objects perform the main game logic of Pandemic game play.
 * The IRoleCommand documentation describes this further. Because the operations
 * that are performed by IRoleCommand are so varied, it is unrealistic for
 * them to return a primitive type as a result.
 * <br>
 * The ICommandResult is a wrapper for the results of the execute function on
 * IRoleCommand. It allows the execute function to specify the type of result and
 * related messages, new commands, and data.
 *
 */
public interface ICommandResult {

    /**
     * The types of results that the execute function on IRoleCommand
     * can return. Some notes about these types are as follows:
     * <ul>
     *     <li> CanDo methods return LEGAL or ILLEGAL </li>
     *     <li> Do methods return SUCCESS or FAILURE </li>
     *     <li> Results that require executing another IRoleCommand return CHAIN </li>
     * </ul>
     */
    public enum ResultType {
        SUCCESS, FAILURE, LEGAL, ILLEGAL, CHAIN
    }

    /**
     * Get the type of the IRoleCommand result.
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
     * @return An IRoleCommand object that may be executed later.
     */
    IRoleCommand getChainCommand();

    /**
     * In some instances a command may need to return some extra data. This data would be
     * returned as an Object type through getData, then cast to its actual type externally.
     * @return Some information relevant to the IRoleCommand result passed as an Object.
     */
    Object getData();

}
