package com.cartisan.huiduoduo.services;

import com.cartisan.common.exceptions.CartisanException;
import com.cartisan.common.utils.IdWorker;
import com.cartisan.huiduoduo.constants.CouponCodeMessage;
import com.cartisan.huiduoduo.domains.Store;
import com.cartisan.huiduoduo.dtos.StoreDto;
import com.cartisan.huiduoduo.params.StoreParam;
import com.cartisan.huiduoduo.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * @author colin
 */
@Service
public class StoreService {
    @Autowired
    private StoreRepository repository;

    @Autowired
    private IdWorker idWorker;

    public List<StoreDto> getStores(Long merchantId) {
        final List<Store> categories = repository.findByMerchantId(merchantId, new Sort(Sort.Direction.ASC, "sort"));

        return categories.stream().map(StoreDto::convertFrom).collect(toList());
    }

    public StoreDto getStore(Long id) {
        return StoreDto.convertFrom(findStoreById(id));
    }

    @Transactional(rollbackOn = Exception.class)
    public StoreDto addStore(Long merchantId, StoreParam storeParam) {
        if (repository.existsByMerchantIdAndName(merchantId, storeParam.getName())) {
            throw new CartisanException(CouponCodeMessage.SAME_STORE_NAME);
        }

        final Store store = new Store(idWorker.nextId(), merchantId, storeParam.getName());
        changeInfo(storeParam, store);

        repository.save(store);

        return StoreDto.convertFrom(store);
    }

    @Transactional(rollbackOn = Exception.class)
    public StoreDto editStore(Long merchantId, Long id, StoreParam storeParam) {
        if (repository.existsByMerchantIdAndNameAndIdNot(merchantId, storeParam.getName(), id)) {
            throw new CartisanException(CouponCodeMessage.SAME_STORE_NAME);
        }

        final Store store = findStoreById(id);
        store.setName(storeParam.getName());

        changeInfo(storeParam, store);

        repository.save(store);

        return StoreDto.convertFrom(store);
    }

    @Transactional(rollbackOn = Exception.class)
    public void removeStore(Long merchantId, Long id) {
        repository.delete(findStoreById(id));
    }

    private Store findStoreById(Long id) {
        final Optional<Store> storeOptional = repository.findById(id);
        if (!storeOptional.isPresent()) {
            throw new CartisanException(CouponCodeMessage.STORE_NOT_EXIST);
        }

        return storeOptional.get();
    }

    private void changeInfo(StoreParam storeParam, Store store) {
        store.setPhone(storeParam.getPhone());
        store.setArea(storeParam.getArea());
        store.setAddress(storeParam.getAddress());
        store.setDescription(storeParam.getDescription());
        store.setSort(storeParam.getSort());
    }
}
