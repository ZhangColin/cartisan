package com.cartisan.dp;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author colin
 */
public enum OnOffStatus {
    /**
     * 禁用
     */
    Disabled(0, "禁用"),
    /**
     * 启用
     */
    Enabled(1, "启用");

    @JsonValue
    private final int value;
    private final String description;

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    OnOffStatus(int value, String description) {
        this.value = value;
        this.description = description;
    }

    @JsonCreator
    public static OnOffStatus getInstance(int value) {
        for (OnOffStatus status : values()) {
            if (status.value == value) {
                return status;
            }
        }
        return null;
    }
}
