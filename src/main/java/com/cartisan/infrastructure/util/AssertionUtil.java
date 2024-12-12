package com.cartisan.infrastructure.util;

import com.cartisan.infrastructure.constant.CodeMessage;
import com.cartisan.infrastructure.exception.CartisanException;

import java.util.Optional;

/**
 * @author zhangcolin
 */
public class AssertionUtil {
    public static <T> T requirePresent(Optional<T> dictOptional) {
        return dictOptional
                .orElseThrow(() -> new CartisanException(CodeMessage.ENTITY_NOT_FOUND));
    }

    public static <T> T requirePresent(Optional<T> dictOptional, String errorMessage) {
        return dictOptional
                .orElseThrow(() -> new CartisanException(CodeMessage.FAIL.fillArgs(errorMessage)));
    }

    public static <T> T requirePresent(Optional<T> dictOptional, CodeMessage codeMessage) {
        return dictOptional
                .orElseThrow(() -> new CartisanException(codeMessage));
    }
}
