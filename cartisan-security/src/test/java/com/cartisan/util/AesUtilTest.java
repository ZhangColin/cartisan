package com.cartisan.util;

import org.junit.Test;

public class AesUtilTest {
    @Test
    public void encode() {
        System.out.println(AesUtil.aesEncode("123456"));
    }

    @Test
    public void decode(){
        System.out.println(AesUtil.aesDecode("8tK2r6Rrfsg9IKTpYqtMug=="));
    }
}
