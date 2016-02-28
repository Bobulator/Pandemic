package com.cs428.pandemic.frontEnd.dataTransferObjects;

/**
 * Created by Chad Bacon on 2/16/2016.
 *
 * This class packages all of the data the UI needs to display the cards a player draws at the end
 * of their turn, including whether or not the player needs to discard any of their cards and is so
 * how many.
 */
public class UI_DrawnCards {

    /**
     * The city name associated with the first drawn card.
     */
    private UI_Card card1;

    /**
     * The city name associated with the second drawn card.
     */
    private UI_Card card2;

    /**
     * The number of cards the player needs to discard as a result of their draw. This should be
     * between 0-2.
     */
    int toDiscard = 0;

    /**
     * If this constructor is used the toDiscard int will default to 0.
     * @param card1 The city name associated with the first drawn card.
     * @param card2 The city name associated with the second drawn card.
     */
    public UI_DrawnCards(UI_Card card1, UI_Card card2) {
        this.card1 = card1;
        this.card2 = card2;
    }

    /**
     *
     * @param card1 The city name associated with the first drawn card.
     * @param card2 The city name associated with the second drawn card.
     * @param toDiscard The number of cards the player needs to discard as a result of this draw.
     */
    public UI_DrawnCards(UI_Card card1, UI_Card card2, int toDiscard) {
        this.card1 = card1;
        this.card2 = card2;
        this.toDiscard = toDiscard;
    }

    /**
     *
     * @return The city name of the first drawn card.
     */
    public UI_Card getCard1() {
        return card1;
    }

    /**
     *
     * @return The city name of the second drawn card.
     */
    public UI_Card getCard2() {
        return card2;
    }

    /**
     *
     * @return The number of cards to be discarded by the player.
     */
    public int getToDiscard() {
        return toDiscard;
    }
}