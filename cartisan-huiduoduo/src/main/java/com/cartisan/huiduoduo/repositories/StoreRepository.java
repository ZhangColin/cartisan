package com.cartisan.huiduoduo.repositories;

import com.cartisan.common.repositories.BaseRepository;
import com.cartisan.huiduoduo.domains.Store;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * @author colin
 */
public interface StoreRepository extends BaseRepository<Store, Long> {
    List<Store> findByMerchantId(Long merchantId, Sort sort);

    boolean existsByMerchantIdAndName(Long merchantId, String name);

    boolean existsByMerchantIdAndNameAndIdNot(Long merchantId, String name, Long id);
}
