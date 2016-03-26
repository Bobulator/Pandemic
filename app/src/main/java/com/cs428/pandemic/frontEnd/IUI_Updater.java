package com.cs428.pandemic.frontEnd;

/**
 * Created by Chad Bacon on 2/16/2016.
 *
 * This is the interface through which the model will inform the UI of state changes. In most cases
 * this will be done through the updateUI method.
 */
public interface IUI_Updater {

    /**
     * This is the primary method through which the model will notify the UI of state changes. When
     * this method is called the UI will then query the model for map and HUD data. This way the
     * model doesn't need to worry about what data it needs to send to the UI; the UI will
     * specifically request the data it needs.
     */
    void updateUI();

    /**
     * The model will use this method to progress through the steps of the epidemic. This is
     * necessary since during each step of the epidemic there will be a UI element notifying players
     * of what is happening at each step of the epidemic.
     */
    void ResolveEpidemic();
}