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


    @Transactional(rollbackOn = Exception.class)
    public void addStore(StoreParam storeParam) {
        if (repository.existsByMerchantIdAndName(storeParam.getMerchantId(), storeParam.getName())) {
            throw new CartisanException(CouponCodeMessage.SAME_STORE_NAME);
        }

        final Store store = new Store(idWorker.nextId(), storeParam.getMerchantId(), storeParam.getName());
        changeInfo(storeParam, store);

        repository.save(store);
    }

    private void changeInfo(StoreParam storeParam, Store store) {
        store.setPhone(storeParam.getPhone());
        store.setArea(storeParam.getArea());
        store.setAddress(storeParam.getAddress());
        store.setDescription(storeParam.getDescription());
        store.setSort(storeParam.getSort());
    }

    @Transactional(rollbackOn = Exception.class)
    public void editStore(Long id, StoreParam storeParam) {
        if (repository.existsByMerchantIdAndNameAndIdNot(storeParam.getMerchantId(), storeParam.getName(), id)) {
            throw new CartisanException(CouponCodeMessage.SAME_STORE_NAME);
        }

        final Optional<Store> storeOptional = repository.findById(id);
        if (!storeOptional.isPresent()) {
            throw new CartisanException(CouponCodeMessage.STORE_NOT_EXIST);
        }

        final Store store = storeOptional.get();
        store.setName(storeParam.getName());

        changeInfo(storeParam, store);

        repository.save(store);
    }

    @Transactional(rollbackOn = Exception.class)
    public void removeStore(long id) {
        final Optional<Store> storeOptional = repository.findById(id);
        if (!storeOptional.isPresent()) {
            throw new CartisanException(CouponCodeMessage.MERCHANT_NOT_EXIST);
        }

        repository.deleteById(id);
    }
}
