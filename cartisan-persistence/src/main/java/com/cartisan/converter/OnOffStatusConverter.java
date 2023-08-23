package com.cartisan.converter;

import com.cartisan.dp.OnOffStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;

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