package com.cartisan.huiduoduo.repositories;

import com.cartisan.common.repositories.BaseRepository;
import com.cartisan.huiduoduo.domains.Referrer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author colin
 */
public interface ReferrerRepository extends BaseRepository<Referrer, Long> {
    boolean existsByUserId(Long userId);

    Page<Referrer> findByUserIdIn(List<Long> userIds, Pageable pageable);
}
