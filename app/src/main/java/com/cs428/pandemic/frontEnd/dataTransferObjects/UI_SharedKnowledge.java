package com.cs428.pandemic.frontEnd.dataTransferObjects;

import java.util.List;

/**
 * Created by Chad Bacon on 2/16/2016.
 *
 * This class contains all the necessary data for the UI to display information when the player
 * chooses to share knowledge as a potential action. Note that there are both shareable cards and
 * receivable cards; this is to account for the Researcher's unique ability.
 */
public class UI_SharedKnowledge {

    /**
     * A list of UI_Cards associated with each card the player is able to share.
     */
    private List<UI_Card> shareableCards;

    /**
     * A list of UI_Cards associated with each card the player is able to receive through the
     * Researcher's special ability.
     */
    private List<UI_Card> receivableCards;

    /**
     *
     * @param shareableCards A list of cards the player is able to share.
     * @param receivableCards A list of cards the player is able to receive.
     */
    public UI_SharedKnowledge(List<UI_Card> shareableCards, List<UI_Card> receivableCards) {
        this.shareableCards = shareableCards;
        this.receivableCards = receivableCards;
    }

    /**
     *
     * @return The list of cards the player is able to share.
     */
    public List<UI_Card> getShareableCards() {
        return shareableCards;
    }

    /**
     *
     * @return The list of cards the player is able to receive.
     */
    public List<UI_Card> getReceivableCards() {
        return receivableCards;
    }
}