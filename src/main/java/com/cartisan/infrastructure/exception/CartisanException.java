package com.cartisan.infrastructure.exception;

import com.cartisan.infrastructure.constant.CodeMessage;
import lombok.Getter;

/**
 * @author zhangcolin
 */
@Getter
public class CartisanException extends RuntimeException {
    private final CodeMessage codeMessage;

    public CartisanException(CodeMessage codeMessage) {
        super(codeMessage.getMessage());
        this.codeMessage = codeMessage;
    }
}
