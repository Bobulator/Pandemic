package com.cs428.pandemic.frontEnd.dataTransferObjects;

import com.cs428.pandemic.frontEnd.enums.DiseaseColor;

/**
 * Created by Chad Bacon on 2/16/2016.
 *
 * This class holds all of the necessary card data for the UI. Note that this class is used for
 * both Player cards and Infection cards. The UI determine which card it is based on context since
 * splitting the two into different classes would result in them being virtually identical.
 */
public class UI_Card {

    /**
     * The name of the city associated with the card.
     */
    private String cardName;

    /**
     * The disease color associated with this card's city.
     */
    private DiseaseColor diseaseColor;

    /**
     *
     * @param cardName The name of the city to be associated with this card.
     * @param diseaseColor The disease color associated with this card's city.
     */
    public UI_Card(String cardName, DiseaseColor diseaseColor) {

        this.cardName = cardName;
        this.diseaseColor = diseaseColor;
    }

    /**
     *
     * @return The name of the city associated with the card.
     */
    public String getCardName() {
        return cardName;
    }

    /**
     *
     * @return The disease color associated with this card's city.
     */
    public DiseaseColor getDiseaseColor() {
        return diseaseColor;
    }
}