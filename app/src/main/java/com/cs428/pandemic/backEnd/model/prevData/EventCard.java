package com.cs428.pandemic.backEnd.model.prevData;

import com.cs428.pandemic.backEnd.model.prevData.EventCardType;
import com.cs428.pandemic.backEnd.model.prevData.PlayerCardType;

/**
 * Created by brandt on 5/13/15.
 */
public class EventCard implements IPlayerCard {

    EventCardType eventType;
    String description;

    public EventCard(EventCardType eventType, String description) {
        this.eventType = eventType;
        this.description = description;
    }

    public EventCardType getEventType() {
        return eventType;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public PlayerCardType getType() {
        return PlayerCardType.EVENT;
    }
}
