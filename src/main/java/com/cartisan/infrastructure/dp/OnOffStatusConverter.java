package com.cartisan.infrastructure.dp;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;

/**
 * @author zhangcolin
 */
@Convert
public class OnOffStatusConverter implements AttributeConverter<OnOffStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(OnOffStatus status) {
        return status.getValue();
    }

    @Override
    public OnOffStatus convertToEntityAttribute(Integer dbData) {
        return OnOffStatus.getInstance(dbData);
    }
}
