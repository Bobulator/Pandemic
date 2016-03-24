package com.cs428.pandemic.frontEnd.enums;

/**
 * Created by Chad Bacon on 2/16/2016.
 * Contains all of the difficulties that players can choose to start a game with.
 */
public enum Difficulty {
    NORMAL("NORMAL"),
    HARD("HARD"),
    INSANITY("INSANITY");

    private String value;

    Difficulty(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }

    public static Difficulty stringToEnum(String value) {
        for (Difficulty v : values())
            if (v.getValue().equalsIgnoreCase(value)) return v;
        throw new IllegalArgumentException("No matching difficulty for given value");
    }
}

