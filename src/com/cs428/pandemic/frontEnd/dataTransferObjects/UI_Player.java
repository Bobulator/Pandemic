package com.cs428.pandemic.frontEnd.dataTransferObjects;

import com.cs428.pandemic.frontEnd.enums.Role;

/**
 * Created by Chad Bacon on 2/16/2016.
 *
 * This class stores all of the information needed for the UI to draw player data. Note that
 * player's are differentiated exclusively by their integer ID; their names need not be unique.
 */
public class UI_Player {

    /**
     * The unique integer ID of the player.
     */
    private int playerID;

    /**
     * The name the player chose for themselves at the start of the game. This does not need to be
     * unique.
     */
    private String playerName;

    /**
     * The role assigned to the player.
     */
    private Role playerRole;

    /**
     *
     * @param playerID The unique integer ID of the player.
     * @param playerName The name of the player.
     * @param playerRole The player's assigned role.
     */
    public UI_Player(int playerID, String playerName, Role playerRole) {
        this.playerID = playerID;
        this.playerName = playerName;
        this.playerRole = playerRole;
    }

    /**
     *
     * @return The player's unique ID.
     */
    public int getPlayerID() {
        return playerID;
    }

    /**
     *
     * @return The player's name.
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     *
     * @return The player's role.
     */
    public Role getPlayerRole() { return playerRole; }
}
