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
    private String cardName1;

    /**
     * The city name associated with the second drawn card.
     */
    private String cardName2;

    /**
     * The number of cards the player needs to discard as a result of their draw. This should be
     * between 0-2.
     */
    int toDiscard = 0;

    /**
     * If this constructor is used the toDiscard int will default to 0.
     * @param cardName1 The city name associated with the first drawn card.
     * @param cardName2 The city name associated with the second drawn card.
     */
    public UI_DrawnCards(String cardName1, String cardName2) {
        this.cardName1 = cardName1;
        this.cardName2 = cardName2;
    }

    /**
     *
     * @param cardName1 The city name associated with the first drawn card.
     * @param cardName2 The city name associated with the second drawn card.
     * @param toDiscard The number of cards the player needs to discard as a result of this draw.
     */
    public UI_DrawnCards(String cardName1, String cardName2, int toDiscard) {
        this.cardName1 = cardName1;
        this.cardName2 = cardName2;
        this.toDiscard = toDiscard;
    }

    /**
     *
     * @return The city name of the first drawn card.
     */
    public String getCardName1() {
        return cardName1;
    }

    /**
     *
     * @return The city name of the second drawn card.
     */
    public String getCardName2() {
        return cardName2;
    }

    /**
     *
     * @return The number of cards to be discarded by the player.
     */
    public int getToDiscard() {
        return toDiscard;
    }
}
