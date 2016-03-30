package com.cs428.pandemic.frontEnd.gamePlay.boardFragment;

import android.content.res.XmlResourceParser;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * This class is responsible for reading and storing data from the relative_city_locations.xml file.
 * Created by Chad on 3/30/2016.
 */
public class CityParser {

    private XmlResourceParser xmlResourceParser;

    public CityParser(XmlResourceParser xmlResourceParser) {
        this.xmlResourceParser = xmlResourceParser;
    }

    public Cities parseXML() {
        Cities cities = null;

        try {
            int eventType = xmlResourceParser.getEventType();

            while (eventType != XmlResourceParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlResourceParser.START_DOCUMENT:
                        cities = new Cities();
                        break;
                    case XmlResourceParser.START_TAG:
                        if (xmlResourceParser.getName().equals("cities")) {
                            break;
                        }

                        String city;
                        float rel_x;
                        float rel_y;

                        city = xmlResourceParser.getName();
                        xmlResourceParser.next();
                        if (xmlResourceParser.getName().equals("relative_x")) {
                            rel_x = Float.parseFloat(xmlResourceParser.nextText());
                        } else throw new IOException("Expected relative_x but got " + xmlResourceParser.getName());
                        xmlResourceParser.next();
                        if (xmlResourceParser.getName().equals("relative_y")) {
                            rel_y = Float.parseFloat(xmlResourceParser.nextText());
                        } else throw new IOException("Expected relative_y but got " + xmlResourceParser.getName());

                        assert cities != null;
                        cities.addCity(city, rel_x, rel_y);
                }
                eventType = xmlResourceParser.next();
            }
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
        return cities;
    }
}
