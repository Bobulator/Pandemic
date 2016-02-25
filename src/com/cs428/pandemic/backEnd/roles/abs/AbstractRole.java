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
    IRoleCommand getCanDirectFlight(final String cityName){ return null; }
    IRoleCommand getCanCharterFlight(final String cityName){ return null; }
    IRoleCommand getCanShuttleFlight(final String cityName){ return null; }

    IRoleCommand getCanPass(){ return null; }

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
