package com.cartisan.huiduoduo.services;

import com.cartisan.common.utils.IdWorker;
import com.cartisan.huiduoduo.domains.CouponSchema;
import com.cartisan.huiduoduo.dtos.MerchantCoupon;
import com.cartisan.huiduoduo.repositories.CouponSchemaRepository;
import com.cartisan.huiduoduo.repositories.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

/**
 * @author colin
 */
@Service
public class CouponService {
    @Autowired
    private CouponSchemaRepository couponSchemaRepository;

    @Autowired
    private MerchantRepository merchantRepository;

    @Autowired
    private IdWorker idWorker;

//    public PageResult<CouponSchemaDto> searchCoupons(Integer currentPage, Integer pageSize) {
//        PageRequest pageRequest = PageRequest.of(currentPage - 1, pageSize);
//
//        final Page<CouponSchema> searchResult = couponSchemaRepository.findAll(pageRequest);
//
//        return new PageResult<>(searchResult.getTotalElements(), searchResult.getTotalPages(),
//                searchResult.map(CouponSchemaDto::convertFrom).getContent());
//    }


    public List<MerchantCoupon> getCanGetCoupons() {
        final List<CouponSchema> schemas = couponSchemaRepository.findAll((Specification<CouponSchema>) (root, query, criteriaBuilder) ->
                criteriaBuilder.and(new Predicate[]{
                        criteriaBuilder.lessThanOrEqualTo(root.get("getStart"), new Date()),
                        criteriaBuilder.greaterThanOrEqualTo(root.get("getEnd"), new Date())
                }));

        final Map<Long, List<CouponSchema>> schemaGroups = schemas.stream().collect(groupingBy(CouponSchema::getMerchantId));

        return schemaGroups.keySet().stream().map(
                merchantId->MerchantCoupon.convertFrom(merchantRepository.findById(merchantId).get(),
                        schemaGroups.get(merchantId))).collect(toList());
    }


//    public CouponSchemaDto getCouponSchema(Long id) {
//        return CouponSchemaDto.convertFrom(couponSchemaRepository.findById(id).get());
//    }
//
//    public List<CouponSchemaDto> getCouponSchemas() {
//        final List<CouponSchema> categories = couponSchemaRepository.findAll();
//
//        return categories.stream().map(CouponSchemaDto::convertFrom).collect(toList());
//    }
//
//
//    @Transactional(rollbackOn = Exception.class)
//    public void addCouponSchema(CouponSchemaParam couponSchemaParam) {
//        if (couponSchemaRepository.existsByName(couponSchemaParam.getName())) {
//            throw new CartisanException(CouponCodeMessage.SAME_MERCHANT_NAME);
//        }
//
//        final CouponSchema couponSchema = new CouponSchema();
//        couponSchema.setId(idWorker.nextId());
//        couponSchema.setMerchantId(couponSchemaParam.getMerchantId());
//        couponSchema.setName(couponSchemaParam.getName());
//        couponSchema.setTitle(couponSchemaParam.getTitle());
//        couponSchema.setImage(couponSchemaParam.getImage());
//        couponSchema.setIntroduction(couponSchemaParam.getIntroduction());
//        couponSchema.setCommission(couponSchemaParam.getCommission());
//        couponSchema.setGetStart(couponSchemaParam.getGetStart());
//        couponSchema.setGetEnd(couponSchemaParam.getGetEnd());
//        couponSchema.setValidStart(couponSchemaParam.getValidStart());
//        couponSchema.setValidEnd(couponSchemaParam.getValidEnd());
//        couponSchema.setType(couponSchemaParam.getType());
//        couponSchema.setCodeImage(couponSchemaParam.getCodeImage());
//        couponSchema.setGetMethod(couponSchemaParam.getGetMethod());
//
//        couponSchema.setOperateIp("");
//        couponSchema.setOperator("");
//
//
//        couponSchemaRepository.save(couponSchema);
//    }
//
//    @Transactional(rollbackOn = Exception.class)
//    public void editCouponSchema(Long id, CouponSchemaParam couponSchemaParam) {
//        if (couponSchemaRepository.existsByNameAndIdNot(couponSchemaParam.getName(), id)) {
//            throw new CartisanException(CouponCodeMessage.SAME_MERCHANT_NAME);
//        }
//
//        final Optional<CouponSchema> couponSchemaOptional = couponSchemaRepository.findById(id);
//        if (!couponSchemaOptional.isPresent()) {
//            throw new CartisanException(CouponCodeMessage.MERCHANT_NOT_EXIST);
//        }
//
//        final CouponSchema couponSchema = couponSchemaOptional.get();
//        couponSchema.setMerchantId(couponSchemaParam.getMerchantId());
//        couponSchema.setName(couponSchemaParam.getName());
//        couponSchema.setTitle(couponSchemaParam.getTitle());
//        couponSchema.setImage(couponSchemaParam.getImage());
//        couponSchema.setIntroduction(couponSchemaParam.getIntroduction());
//        couponSchema.setCommission(couponSchemaParam.getCommission());
//        couponSchema.setGetStart(couponSchemaParam.getGetStart());
//        couponSchema.setGetEnd(couponSchemaParam.getGetEnd());
//        couponSchema.setValidStart(couponSchemaParam.getValidStart());
//        couponSchema.setValidEnd(couponSchemaParam.getValidEnd());
//        couponSchema.setType(couponSchemaParam.getType());
//        couponSchema.setCodeImage(couponSchemaParam.getCodeImage());
//        couponSchema.setGetMethod(couponSchemaParam.getGetMethod());
//
//        couponSchema.setOperateIp("");
//        couponSchema.setOperator("");
//
//        couponSchemaRepository.save(couponSchema);
//    }
//
//    @Transactional(rollbackOn = Exception.class)
//    public void removeCouponSchema(long id) {
//        final Optional<CouponSchema> couponSchemaOptional = couponSchemaRepository.findById(id);
//        if (!couponSchemaOptional.isPresent()) {
//            throw new CartisanException(CouponCodeMessage.MERCHANT_NOT_EXIST);
//        }
//
//        couponSchemaRepository.deleteById(id);
//    }
}
