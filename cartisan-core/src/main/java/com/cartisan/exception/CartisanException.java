package com.cartisan.exception;

import com.cartisan.constants.CodeMessage;
import lombok.Getter;

/**
 * @author colin
 */
public class CartisanException extends RuntimeException {
    @Getter
    private final CodeMessage codeMessage;

    public CartisanException(CodeMessage codeMessage) {
        super(codeMessage.getMessage());
        this.codeMessage = codeMessage;
    }
}
