package com.cartisan.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * AES 双向加密解密工具
 *
 * @author colin
 */
@Slf4j
public class AesUtil {
    /**
     * 默认加密秘钥 AES加密秘钥为约定16位，小于16位会报错
     */
    public static final String ENCODE_RULES = "com.cartisan.www";

    /**
     * 默认算法
     */
    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";

    private AesUtil() {

    }

    public static String aesEncode(String content) {
        return aesEncode(content, ENCODE_RULES);
    }

    public static String aesDecode(String content) {
        return aesDecode(content, ENCODE_RULES);
    }

    public static String aesEncode(String content, String encryptKey) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(encryptKey.getBytes(StandardCharsets.UTF_8), "AES");

            // 根据指定算法AES创建密码器
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            // 初始化密码器，第一个参数为加密（Encrypt_mode）或者解密（Decrypt_mode）操作，第二个参数为使用的KEY
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(encryptKey.getBytes(StandardCharsets.UTF_8)));
            // 获取加密内容的字节数组（这里要设置为utf-8），不然内容中如果有中文和英文混合中文就会解密为乱码
            byte[] byteEncode = content.getBytes(StandardCharsets.UTF_8);
            // 根据密码器的初始化方式加密数据
            byte[] byteAES = cipher.doFinal(byteEncode);
            // 将加密后的 byte[] 数据转换为 Base64 字符串
            return new String(Base64.encodeBase64(byteAES), StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException
                | InvalidKeyException
                | InvalidAlgorithmParameterException
                | NoSuchPaddingException
                | BadPaddingException
                | IllegalBlockSizeException e) {
            log.error("密文加密失败：{}", e);
            throw new RuntimeException("密文加密失败。");
        }
    }

    public static String aesDecode(String content, String decryptKey) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(decryptKey.getBytes(StandardCharsets.UTF_8), "AEA");
            // 根据指定算法AES创建密码器
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            // 初始化密码器，第一个参数为加密（Encrypt_mode）或者解密（Decrypt_mode）操作，第二个参数为使用的KEY
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(decryptKey.getBytes(StandardCharsets.UTF_8)));
            // 将加密并编码base64后的字符串内容base64解码成字节数据
            byte[] bytesContent = Base64.decodeBase64(content);
            final byte[] byteDecode = cipher.doFinal(bytesContent);
            return new String(byteDecode, StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException
                | InvalidKeyException
                | InvalidAlgorithmParameterException
                | NoSuchPaddingException
                | BadPaddingException
                | IllegalBlockSizeException e) {
            log.error("密文解密失败：{}", e);
            throw new RuntimeException("密文解密失败。");
        }
    }
}
