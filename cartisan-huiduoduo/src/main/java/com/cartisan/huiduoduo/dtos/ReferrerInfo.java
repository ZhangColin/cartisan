package com.cartisan.huiduoduo.dtos;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author colin
 */
@Data
public class ReferrerInfo {
    private String referrerId;
    private String name;
    private BigDecimal totalBonus;
    private BigDecimal monthBonus;
    private Integer users;
    private Integer coupons;
}
