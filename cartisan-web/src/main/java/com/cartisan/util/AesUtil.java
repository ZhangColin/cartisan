package com.cartisan.util;

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

import static java.lang.System.arraycopy;

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
    public static final String KEY = "com.cartisan.www";
    public static final String IV = "com.cartisan.www";

    /**
     * 默认算法
     */
    private static final String ALGORITHM = "AES/CBC/NoPadding";

    private AesUtil() {

    }

    public static String aesEncode(String content) {
        return aesEncode(content, KEY);
    }

    public static String aesDecode(String content) {
        return aesDecode(content, KEY);
    }

    public static String aesEncode(String content, String encryptKey) {
        try {
            // 根据指定算法AES创建密码器
            final Cipher cipher = Cipher.getInstance(ALGORITHM);

            final SecretKeySpec keySpec = new SecretKeySpec(encryptKey.getBytes(StandardCharsets.UTF_8), "AES");
            final IvParameterSpec ivSpec = new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8));

            // 初始化密码器，第一个参数为加密（Encrypt_mode）或者解密（Decrypt_mode）操作，第二个参数为使用的KEY
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

            // 获取加密内容的字节数组（这里要设置为utf-8），不然内容中如果有中文和英文混合中文就会解密为乱码
            int blockSize=cipher.getBlockSize();
            byte[] dataBytes = content.getBytes(StandardCharsets.UTF_8);
            int plainTextLength = dataBytes.length;
            if (plainTextLength % blockSize != 0) {
                plainTextLength = plainTextLength + (blockSize - (plainTextLength % blockSize));
            }

            byte[] encodeBytes = new byte[plainTextLength];
            arraycopy(dataBytes, 0, encodeBytes, 0, dataBytes.length);

            // 根据密码器的初始化方式加密数据
            byte[] encrypted = cipher.doFinal(encodeBytes);

            // 将加密后的 byte[] 数据转换为 Base64 字符串
            return new String(Base64.encodeBase64(encrypted), StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException
                | InvalidKeyException
                | InvalidAlgorithmParameterException
                | NoSuchPaddingException
                | BadPaddingException
                | IllegalBlockSizeException e) {
            log.error("密文加密失败：{}", e.getMessage());
            throw new RuntimeException("密文加密失败。");
        }
    }

    public static String aesDecode(String content, String decryptKey) {
        try {
            // 根据指定算法AES创建密码器
            final Cipher cipher = Cipher.getInstance(ALGORITHM);

            final SecretKeySpec keySpec = new SecretKeySpec(decryptKey.getBytes(StandardCharsets.UTF_8), "AES");
            final IvParameterSpec ivSpec = new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8));

            // 初始化密码器，第一个参数为加密（Encrypt_mode）或者解密（Decrypt_mode）操作，第二个参数为使用的KEY
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

            // 将加密并编码base64后的字符串内容base64解码成字节数据
            byte[] encrypted = Base64.decodeBase64(content);

            final byte[] originalBytes = cipher.doFinal(encrypted);
            return new String(originalBytes, StandardCharsets.UTF_8).trim();
        } catch (NoSuchAlgorithmException
                | InvalidKeyException
                | InvalidAlgorithmParameterException
                | NoSuchPaddingException
                | BadPaddingException
                | IllegalBlockSizeException e) {
            log.error("密文解密失败：{}", e.getMessage());
            throw new RuntimeException("密文解密失败。");
        }
    }
}
