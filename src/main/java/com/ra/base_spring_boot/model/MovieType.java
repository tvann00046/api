package com.ra.base_spring_boot.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum MovieType {
    D2("2D"),
    D3("3D");

    private final String value;

    MovieType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static MovieType fromValue(String value) {
        for (MovieType type : values()) {
            if (type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown MovieType: " + value);
    }

    @Override
    public String toString() {
        return value;
    }
}
