package com.cartisan.huiduoduo.dtos;

import com.cartisan.huiduoduo.domains.Merchant;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author colin
 */
@Data
@AllArgsConstructor
public class MerchantDto {
    private String id;

    private String name;

    private String logo;

    public static MerchantDto convertFrom(Merchant merchant) {
        return new MerchantDto(
                merchant.getId().toString(), merchant.getName(), merchant.getLogo());
    }
}
