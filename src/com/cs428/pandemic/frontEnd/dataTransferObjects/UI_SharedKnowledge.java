package com.cs428.pandemic.frontEnd.dataTransferObjects;

import java.util.List;

/**
 * Created by Chad Bacon on 2/16/2016.
 * TODO: javadocs
 */
public class UI_SharedKnowledge {
    private List<UI_Card> shareableCards;
    private List<UI_Card> receivableCards;

    public UI_SharedKnowledge(List<UI_Card> shareableCards, List<UI_Card> receivableCards) {
        this.shareableCards = shareableCards;
        this.receivableCards = receivableCards;
    }

    public List<UI_Card> getShareableCards() {
        return shareableCards;
    }

    public List<UI_Card> getReceivableCards() {
        return receivableCards;
    }
}
