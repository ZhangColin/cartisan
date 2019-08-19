package com.cartisan.huiduoduo.params;

import lombok.Data;

/**
 * @author colin
 */
@Data
public class WeiXinEncryptedDataParam {
    private String encryptedData;
    private String iv;
    private String signature;
    private String sessionKey;
}
