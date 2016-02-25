package com.cs428.pandemic.backEnd.roles.abs;

import java.util.HashMap;

public abstract class AbstractRole {

    private HashMap<String, String> specialProperties = new HashMap<>();

    public String getRoleName(){ return ""; }
    public String getUIElementPath(){ return ""; }

    //======================
    // ==== CAN METHODS ====
    // =====================

    /**
     * CanDo method for the Drive/Ferry movement action.
     * @param cityName Name of the city to see if the player can travel there.
     * @return The return of the IRoleCommand execute is as follows:
     * <ul>
     *     <li> getResult: (LEGAL|ILLEGAL) </li>
     *     <li> getMessage: error message if error </li>
     *     <li> getChainCommand: null </li>
     *     <li> getData: null </li>
     * </ul>
     */
    IRoleCommand getCanDriveFerry(final String cityName){ return null; }

    /**
     * CanDo method for the Direct Flight movement action. Verify that cityName
     * matches one of the player's current cards.
     * @param cityName Name of the city to see if the player can travel there.
     * @return The return of the IRoleCommand execute is as follows:
     * <ul>
     *     <li> getResult: (LEGAL|ILLEGAL) </li>
     *     <li> getMessage: error message if error </li>
     *     <li> getChainCommand: null </li>
     *     <li> getData: null </li>
     * </ul>
     */
    IRoleCommand getCanDirectFlight(final String cityName){ return null; }

    /**
     * CanDo method for the Charter Flight movement action. Verifies that the
     * player has the card for the current city that they're in.
     * @param cityName Name of the city to see if the player can travel there.
     * @return The return of the IRoleCommand execute is as follows:
     * <ul>
     *     <li> getResult: (LEGAL|ILLEGAL) </li>
     *     <li> getMessage: error message if error </li>
     *     <li> getChainCommand: null </li>
     *     <li> getData: null </li>
     * </ul>
     */
    IRoleCommand getCanCharterFlight(final String cityName){ return null; }

    /**
     * CanDo method for the Shuttle Flight movement action. Verifies that the
     * player is currently in a city with a research station and that cutyName
     * has a research station.
     * @param cityName Name of the city to see if the player can travel there.
     * @return The return of the IRoleCommand execute is as follows:
     * <ul>
     *     <li> getResult: (LEGAL|ILLEGAL) </li>
     *     <li> getMessage: error message if error </li>
     *     <li> getChainCommand: null </li>
     *     <li> getData: null </li>
     * </ul>
     */
    IRoleCommand getCanShuttleFlight(final String cityName){ return null; }



    /**
     * Should always return true
     * @return The return of the IRoleCommand execute is as follows:
     * <ul>
     *     <li> getResult: (LEGAL|ILLEGAL) </li>
     *     <li> getMessage: error message if error </li>
     *     <li> getChainCommand: null </li>
     *     <li> getData: null </li>
     * </ul>
     */
    IRoleCommand getCanPass(){ return null; }


    /**
     *
     * @param cityName
     * @return
     */
    IRoleCommand getCanBuildStation(final String cityName){ return null; }
    IRoleCommand getCanDiscoverCure(final String diseaseType){ return null; }
    IRoleCommand getCanTreatDisease(final String diseaseType){ return null; }

    IRoleCommand getCanGiveKnowledge(final String cityName){ return null; }
    IRoleCommand getCanReceiveKnowledge(final String cityName){ return null; }

    // will probably need an Object param also for additional data
    IRoleCommand getCanSpecialAction(final String actionName) { return null; }


    //=====================
    // ==== DO METHODS ====
    // ====================
    IRoleCommand getDoDriveFerry(final String cityName){ return null; }
    IRoleCommand getDoDirectFlight(final String cityName){ return null; }
    IRoleCommand getDoCharterFlight(final String cityName){ return null; }
    IRoleCommand getDoShuttleFlight(final String cityName){ return null; }

    IRoleCommand getDoPass(){ return null; }

    IRoleCommand getDoBuildStation(final String cityName){ return null; }
    IRoleCommand getDoDiscoverCure(final String diseaseType){ return null; }
    IRoleCommand getDoTreatDisease(final String diseaseType){ return null; }

    IRoleCommand getDoGiveKnowledge(final String cityName){ return null; }
    IRoleCommand getDoReceiveKnowledge(final String cityName){ return null; }

    // will probably need an Object param also for additional data
    IRoleCommand getDoSpecialAction(final String actionName) { return null; }

}
