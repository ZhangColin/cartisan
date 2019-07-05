package com.cartisan.huiduoduo.repositories;

import com.cartisan.common.repositories.BaseRepository;
import com.cartisan.huiduoduo.domains.Referrer;

/**
 * @author colin
 */
public interface ReferrerRepository extends BaseRepository<Referrer, Long> {
    boolean existsByUserId(Long userId);
}
