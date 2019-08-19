package com.cartisan.huiduoduo.controllers;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;

/**
 * @author colin
 */
public class WXCore {
    private static final String WATERMARK = "watermark";
    private static final String APPID = "appid";
    /**
     * 解密数据
     * @return
     * @throws Exception
     */
    public static String decrypt(String appId, String encryptedData, String sessionKey, String iv){
        String result = "";
        try {
            AES aes = new AES();
            byte[] resultByte = aes.decrypt(Base64.decodeBase64(encryptedData), Base64.decodeBase64(sessionKey), Base64.decodeBase64(iv));
            if(null != resultByte && resultByte.length > 0){
                result = new String(WxPKCS7Encoder.decode(resultByte));
                JSONObject jsonObject = JSONObject.parseObject(result);
                String decryptAppid = jsonObject.getJSONObject(WATERMARK).getString(APPID);
                if(!appId.equals(decryptAppid)){
                    result = "";
                }
            }
        } catch (Exception e) {
            result = "";
            e.printStackTrace();
        }
        return result;
    }


    public static void main(String[] args) throws Exception{
        String appId = "wxaa496b8f36ca4d99";
        String encryptedData = "M/o32rsHG6adX6TaL5eyuB1TMLrD1PCPOJfvRwg9ycVoLQwzL5JUct3NzGqELAK4VAwOAy6wFUXw1XP8/bS/mDTW381c8Bb5i11ARbilLElBb3WlO6HeAwYhPVoFMWnJC2TxWOqq3PmUtTqbmrOu7X+XddGB93EGLBQ33n+KY4M5QQcna9yAHXuDQd01By+qqiGl+YNJNC7HuG/FcooC7Gb+mlBhwCWY2BvdSDmONp7Zp8XpW+QaKKzqPj7d7KeJSWhn1U0qRXd6K6I++J6MukeAcJ2yXVzJ0uccfzuTw5x+Wy1160QAXmb6ZYCtFbW+kl5kcVAPkvDoEzrirJSM8D06uhRsN1dG3r7i/cEuB9L0pTtQGmb5UZuPB5GlXg3HBzYpXM3VgOoXaIAANyEVvd3TIDB+keTZxQr93F6sVwoLs0j7UuE1AaCQOJaeWYSc3mxV4R3IKWlRkXIQS8XMMiaJjY5/R7MULyXwMDhWyKY1GfhTbRPryBQL89OJvbR2";
        String sessionKey = "wwfp7urwPE6yHECfh7KKFw==";
        String iv = "5SXRk9WzwoRc9Spep85x4Q==";
        final String decrypt = decrypt(appId, encryptedData, sessionKey, iv);
        System.out.println(decrypt.length());
        System.out.println(decrypt);
    }

}
