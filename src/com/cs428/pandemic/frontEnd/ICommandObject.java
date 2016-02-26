package com.cs428.pandemic.frontEnd;

import android.app.DialogFragment;

/**
 * Created by Chad Bacon on 2/16/2016.
 *
 * This interface will be used by any role-exclusive action objects generated by the model. When
 * passed to the UI the UI will simply pass it a popup dialog which the command object will
 * use to query the user for necessary data. The command object will then execute the command by
 * making calls to the model.
 */
public interface ICommandObject {
    
    /**
     * Execute the command associated with this command object. The command object will be given
     * a DialogFragment to which it can draw and query any necessary data to the the user.
     * @param dialogFragment The DialogFragment with which to draw data and make queries of the user.
     */
    void doCommand(DialogFragment dialogFragment);
}