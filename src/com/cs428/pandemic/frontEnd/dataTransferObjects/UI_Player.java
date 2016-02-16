package com.cs428.pandemic.frontEnd.dataTransferObjects;

import com.cs428.pandemic.frontEnd.enums.Role;

/**
 * Created by Chad Bacon on 2/16/2016.
 * TODO: javadocs
 */
public class UI_Player {
    private int playerID;
    private String playerName;
    private Role playerRole = null;

    public UI_Player(int playerID, String playerName) {
        this.playerID = playerID;
        this.playerName = playerName;
    }

    public UI_Player(int playerID, String playerName, Role playerRole) {
        this.playerID = playerID;
        this.playerName = playerName;
        this.playerRole = playerRole;
    }

    public int getPlayerID() {
        return playerID;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Role getPlayerRole() {
        return playerRole;
    }

    public void setPlayerRole(Role role) {
        this.playerRole = role;
    }
}
