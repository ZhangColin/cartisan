package com.cartisan.repository;

import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author colin
 */
@Converter
public class StringListConverter implements AttributeConverter<List<String>, String> {
    @Override
    public String convertToDatabaseColumn(List<String> imageItems) {
        if (imageItems == null) {
            return "";
        }
        return String.join(",", imageItems);
    }

    @Override
    public List<String> convertToEntityAttribute(String data) {
        if (!StringUtils.hasLength(data)){
            return new ArrayList<>();
        }
        return Arrays.stream(data.split(",")).collect(toList());
    }
}
