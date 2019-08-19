package com.cartisan.huiduoduo.services;

import com.cartisan.common.exceptions.CartisanException;
import com.cartisan.common.utils.IdWorker;
import com.cartisan.huiduoduo.constants.CouponCodeMessage;
import com.cartisan.huiduoduo.domains.Coupon;
import com.cartisan.huiduoduo.domains.CouponSchema;
import com.cartisan.huiduoduo.dtos.CouponInfo;
import com.cartisan.huiduoduo.dtos.CouponSummaryInfo;
import com.cartisan.huiduoduo.repositories.CouponRepository;
import com.cartisan.huiduoduo.repositories.CouponSchemaRepository;
import com.cartisan.huiduoduo.repositories.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * @author colin
 */
@Service
public class CouponService {
    @Autowired
    private CouponSchemaRepository couponSchemaRepository;

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private MerchantRepository merchantRepository;

    @Autowired
    private IdWorker idWorker;

    public List<CouponSummaryInfo> getCanGetCoupons(Long userId) {
        final List<CouponSchema> schemas = couponSchemaRepository.findAll((Specification<CouponSchema>) (root, query, criteriaBuilder) ->
                criteriaBuilder.and(new Predicate[]{
                        criteriaBuilder.lessThanOrEqualTo(root.get("getStart"), new Date()),
                        criteriaBuilder.greaterThanOrEqualTo(root.get("getEnd"), new Date())
                }));

        final List<Coupon> myCoupons = couponRepository.findByUserId(userId);
        final List<Long> alreadyGetCouponSchemaIds = myCoupons.stream().map(Coupon::getCouponSchemaId).collect(toList());

        return schemas.stream().map(schema -> CouponSummaryInfo.convertFrom(merchantRepository.findById(schema.getMerchantId()).get(),
                schema, alreadyGetCouponSchemaIds.contains(schema.getId()))).collect(toList());
    }

    public List<CouponSummaryInfo> getMyCoupons(Long userId) {
        final List<Coupon> myCoupons = couponRepository.findByUserId(userId);

        return myCoupons.stream().map(coupon -> {
            final CouponSchema schema = couponSchemaRepository.findById(coupon.getCouponSchemaId()).get();

            return CouponSummaryInfo.convertFrom(merchantRepository.findById(schema.getMerchantId()).get(), schema, true);
        }).collect(toList());
    }

    public CouponInfo getMyCoupon(Long userId, Long couponSchemaId) {
        final Optional<Coupon> couponOptional = couponRepository.findByUserIdAndCouponSchemaId(userId, couponSchemaId);

        if (!couponOptional.isPresent()) {
            throw new CartisanException(CouponCodeMessage.USER_NOT_GET_COUPON);
        }

        final CouponSchema schema = couponSchemaRepository.findById(couponOptional.get().getCouponSchemaId()).get();

        return CouponInfo.convertFrom(merchantRepository.findById(schema.getMerchantId()).get(), schema, true);
    }

    public void receive(Long userId, Long couponSchemaId) {
        if (!couponRepository.existsByUserIdAndCouponSchemaId(userId, couponSchemaId)) {
            final Coupon coupon = new Coupon(idWorker.nextId(), userId, couponSchemaRepository.findById(couponSchemaId).get());
            couponRepository.save(coupon);
        }
    }
}
