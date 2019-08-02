package com.cartisan.huiduoduo.repositories;

import com.cartisan.common.repositories.BaseRepository;
import com.cartisan.huiduoduo.domains.Merchant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author colin
 */
public interface MerchantRepository extends BaseRepository<Merchant, Long> {
    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Long id);

    Page<Merchant> findByNameLike(String name, Pageable pageable);
}
