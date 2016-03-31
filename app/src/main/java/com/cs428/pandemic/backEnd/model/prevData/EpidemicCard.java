package com.cs428.pandemic.backEnd.model.prevData;

import com.cs428.pandemic.backEnd.model.prevData.PlayerCardType;

/**
 * Created by brandt on 6/23/15.
 */
public class EpidemicCard implements IPlayerCard {

    @Override
    public PlayerCardType getType() {
        return PlayerCardType.EPIDEMIC;
    }
}
