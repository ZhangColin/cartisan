package com.cartisan.huiduoduo.repositories;

import com.cartisan.common.repositories.BaseRepository;
import com.cartisan.huiduoduo.domains.Merchant;

/**
 * @author colin
 */
public interface MerchantRepository extends BaseRepository<Merchant, Long> {
    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Long id);
}
