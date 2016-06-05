package com.cs428.pandemic.backEnd.model.board.pieces;

import com.cs428.pandemic.backEnd.model.board.BoardLocation;

/**
 * Created by Chad Bacon on 6/5/2016.
 *
 * This abstract class represents placable pieces on the game board. This includes anything that
 * gets placed on the board, such as disease cubes, player pawns, and research stations. This does
 * not include game data that gets displayed on the board, such as the epidemic counter and the
 * infection rate, nor does it include any cards.
 */
public abstract class Piece {

    /**
     * The location of this piece relative to the board.
     */
    private BoardLocation location;

    /**
     * Returns the location of this piece.
     *
     * @return The BoardLocation of this piece.
     */
    public BoardLocation getLocation() {
        return location;
    }

    /**
     * Sets the BoardLocation of this piece.
     *
     * @param location The new BoardLocation of this piece.
     */
    public void setLocation(BoardLocation location) {
        this.location = location;
    }
}
