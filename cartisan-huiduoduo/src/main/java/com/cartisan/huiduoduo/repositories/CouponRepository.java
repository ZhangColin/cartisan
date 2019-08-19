package com.cartisan.huiduoduo.repositories;

import com.cartisan.common.repositories.BaseRepository;
import com.cartisan.huiduoduo.domains.Coupon;

import java.util.List;
import java.util.Optional;

/**
 * @author colin
 */
public interface CouponRepository extends BaseRepository<Coupon, Long> {
    List<Coupon> findByUserId(Long userId);
    Optional<Coupon> findByUserIdAndCouponSchemaId(Long userId, Long schemaId);

    Boolean existsByUserIdAndCouponSchemaId(Long userId, Long schemaId);
}
