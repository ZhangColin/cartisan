package com.cartisan.huiduoduo.services;

import com.cartisan.common.dtos.PageResult;
import com.cartisan.common.exceptions.CartisanException;
import com.cartisan.common.utils.IdWorker;
import com.cartisan.huiduoduo.constants.CouponCodeMessage;
import com.cartisan.huiduoduo.domains.CouponSchema;
import com.cartisan.huiduoduo.domains.StoreGuide;
import com.cartisan.huiduoduo.dtos.CouponSchemaDto;
import com.cartisan.huiduoduo.dtos.StoreGuideDto;
import com.cartisan.huiduoduo.params.CouponSchemaParam;
import com.cartisan.huiduoduo.params.CouponSchemaSearchParam;
import com.cartisan.huiduoduo.repositories.CouponSchemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * @author colin
 */
@Service
public class CouponSchemaService {
    @Autowired
    private CouponSchemaRepository repository;

    @Autowired
    private IdWorker idWorker;

    public PageResult<CouponSchemaDto> searchCouponSchemas(CouponSchemaSearchParam searchParam, Integer currentPage, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(currentPage - 1, pageSize);

        final Page<CouponSchema> searchResult = repository.findAll(searchParam.getSpecifications(), pageRequest);

        return new PageResult<>(searchResult.getTotalElements(), searchResult.getTotalPages(),
                searchResult.map(CouponSchemaDto::convertFrom).getContent());
    }

    public CouponSchemaDto getCouponSchema(Long id) {
        return CouponSchemaDto.convertFrom(repository.findById(id).get());
    }

    public List<CouponSchemaDto> getCouponSchemas() {
        final List<CouponSchema> categories = repository.findAll();

        return categories.stream().map(CouponSchemaDto::convertFrom).collect(toList());
    }


    @Transactional(rollbackOn = Exception.class)
    public void addCouponSchema(CouponSchemaParam couponSchemaParam) {
        if (repository.existsByName(couponSchemaParam.getName())) {
            throw new CartisanException(CouponCodeMessage.SAME_MERCHANT_NAME);
        }

        final CouponSchema couponSchema = new CouponSchema();
        couponSchema.setId(idWorker.nextId());
        couponSchema.setMerchantId(couponSchemaParam.getMerchantId());
        couponSchema.setCategoryId(couponSchemaParam.getCategoryId());
        couponSchema.setName(couponSchemaParam.getName());
        couponSchema.setTitle(couponSchemaParam.getTitle());
        couponSchema.setImage(couponSchemaParam.getImage());
        couponSchema.setIntroduction(couponSchemaParam.getIntroduction());
        couponSchema.setCommission(couponSchemaParam.getCommission());
        couponSchema.setGetStart(couponSchemaParam.getGetStart());
        couponSchema.setGetEnd(couponSchemaParam.getGetEnd());
        couponSchema.setValidStart(couponSchemaParam.getValidStart());
        couponSchema.setValidEnd(couponSchemaParam.getValidEnd());
        couponSchema.setType(couponSchemaParam.getType());
        couponSchema.setCodeImage(couponSchemaParam.getCodeImage());
        couponSchema.setGetMethod(couponSchemaParam.getGetMethod());

        couponSchema.setOperateIp("");
        couponSchema.setOperator("");


        repository.save(couponSchema);
    }

    @Transactional(rollbackOn = Exception.class)
    public void editCouponSchema(Long id, CouponSchemaParam couponSchemaParam) {
        if (repository.existsByNameAndIdNot(couponSchemaParam.getName(), id)) {
            throw new CartisanException(CouponCodeMessage.SAME_MERCHANT_NAME);
        }

        final CouponSchema couponSchema = findByCouponSchemaId(id);
        couponSchema.setMerchantId(couponSchemaParam.getMerchantId());
        couponSchema.setCategoryId(couponSchemaParam.getCategoryId());
        couponSchema.setName(couponSchemaParam.getName());
        couponSchema.setTitle(couponSchemaParam.getTitle());
        couponSchema.setImage(couponSchemaParam.getImage());
        couponSchema.setIntroduction(couponSchemaParam.getIntroduction());
        couponSchema.setCommission(couponSchemaParam.getCommission());
        couponSchema.setGetStart(couponSchemaParam.getGetStart());
        couponSchema.setGetEnd(couponSchemaParam.getGetEnd());
        couponSchema.setValidStart(couponSchemaParam.getValidStart());
        couponSchema.setValidEnd(couponSchemaParam.getValidEnd());
        couponSchema.setType(couponSchemaParam.getType());
        couponSchema.setCodeImage(couponSchemaParam.getCodeImage());
        couponSchema.setGetMethod(couponSchemaParam.getGetMethod());

        couponSchema.setOperateIp("");
        couponSchema.setOperator("");

        repository.save(couponSchema);
    }

    @Transactional(rollbackOn = Exception.class)
    public void removeCouponSchema(long id) {
        final Optional<CouponSchema> couponSchemaOptional = repository.findById(id);
        if (!couponSchemaOptional.isPresent()) {
            throw new CartisanException(CouponCodeMessage.MERCHANT_NOT_EXIST);
        }

        repository.deleteById(id);
    }

    public List<StoreGuideDto> getStoreGuides(Long couponSchemaId) {
        return findByCouponSchemaId(couponSchemaId).getStoreGuides().stream()
                .map(storeGuide->StoreGuideDto.convertFrom(storeGuide, couponSchemaId)).collect(toList());
    }

    public StoreGuideDto saveStoreGuid(Long couponSchemaId, Long storeId, String guide){
        final CouponSchema couponSchema = findByCouponSchemaId(couponSchemaId);

        final Optional<StoreGuide> storeGuideOptional = couponSchema.getStoreGuides().stream()
                .filter(sg -> sg.getStoreId().equals(storeId))
                .findFirst();

        StoreGuide storeGuide;
        if (storeGuideOptional.isPresent()) {
            storeGuide = storeGuideOptional.get();
            storeGuide.setGuide(guide);
        }
        else {
            storeGuide = new StoreGuide(storeId, guide);
            couponSchema.getStoreGuides().add(storeGuide);
        }

        repository.save(couponSchema);

        return StoreGuideDto.convertFrom(storeGuide, couponSchemaId);
    }

    public void removeStoreGuid(Long couponSchemaId, Long storeId){
        final CouponSchema couponSchema = findByCouponSchemaId(couponSchemaId);

        final Optional<StoreGuide> storeGuideOptional = couponSchema.getStoreGuides().stream()
                .filter(sg -> sg.getStoreId().equals(storeId))
                .findFirst();

        storeGuideOptional.ifPresent(storeGuide -> couponSchema.getStoreGuides().remove(storeGuide));

        repository.save(couponSchema);
    }



    private CouponSchema findByCouponSchemaId(Long id) {
        final Optional<CouponSchema> couponSchemaOptional = repository.findById(id);
        if (!couponSchemaOptional.isPresent()) {
            throw new CartisanException(CouponCodeMessage.MERCHANT_NOT_EXIST);
        }

        return couponSchemaOptional.get();
    }
}
