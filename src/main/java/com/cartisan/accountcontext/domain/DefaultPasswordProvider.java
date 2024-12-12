package com.cartisan.accountcontext.domain;

/**
 * @author zhangcolin
 */
public interface DefaultPasswordProvider {
    /**
     * 生成默认密码
     * @return 默认密码
     */
    String generate();
}
