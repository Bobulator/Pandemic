package com.cs428.pandemic.frontEnd.dataTransferObjects;

/**
 * Created by Chad Bacon on 2/16/2016.
 * TODO: javadocs
 */
public class UI_DrawnCards {
    private String cardName1;
    private String cardName2;
    int toDiscard = 0;

    public UI_DrawnCards(String cardName1, String cardName2) {
        this.cardName1 = cardName1;
        this.cardName2 = cardName2;
    }

    public UI_DrawnCards(String cardName1, String cardName2, int toDiscard) {
        this.cardName1 = cardName1;
        this.cardName2 = cardName2;
        this.toDiscard = toDiscard;
    }

    public String getCardName1() {
        return cardName1;
    }

    public String getCardName2() {
        return cardName2;
    }

    public int getToDiscard() {
        return toDiscard;
    }
}
