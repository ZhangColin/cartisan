package com.cartisan.utils;

import com.cartisan.exceptions.CartisanException;
import com.cartisan.constants.CodeMessage;

import java.util.Optional;

/**
 * @author colin
 */
public class AssertionUtil {
    public static <T> T requirePresent(Optional<T> dictOptional) {
        return dictOptional
                .orElseThrow(() -> new CartisanException(CodeMessage.ENTITY_NOT_FOUND));
    }
}
