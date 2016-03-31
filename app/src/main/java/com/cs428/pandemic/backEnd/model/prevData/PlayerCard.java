package com.cs428.pandemic.backEnd.model.prevData;

import com.cs428.pandemic.backEnd.model.prevData.PlayerCardType;

/**
 * Created by brandt on 5/13/15.
 */
public abstract class PlayerCard {

    PlayerCardType cardType;

    public PlayerCardType getCardType() {
        return cardType;
    }
    public void setCardType(PlayerCardType cardType) {
        this.cardType = cardType;
    }
}
