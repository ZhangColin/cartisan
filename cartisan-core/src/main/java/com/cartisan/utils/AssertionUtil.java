package com.cartisan.utils;

import com.cartisan.constants.CommonCodeMessage;
import com.cartisan.exceptions.CartisanException;

import java.util.Optional;

/**
 * @author colin
 */
public class AssertionUtil {
    public static <T> T requirePresent(Optional<T> dictOptional) {
        return dictOptional
                .orElseThrow(() -> new CartisanException(CommonCodeMessage.SERVER_ERROR));
    }
}
