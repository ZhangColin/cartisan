package com.cartisan.infrastructure.dp;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * @author zhangcolin
 */

@Getter
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
    @EnumValue
    private final int value;
    private final String description;

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
