package com.cartisan.accountcontext.gateway;

import com.cartisan.accountcontext.domain.DefaultPasswordProvider;

/**
 * @author zhangcolin
 */
public class FixedDefaultPasswordProvider implements DefaultPasswordProvider {
    @Override
    public String generate() {
        return "123456";
    }
}
