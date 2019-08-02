package com.cartisan.huiduoduo.repositories;

import com.cartisan.common.repositories.BaseRepository;
import com.cartisan.huiduoduo.domains.CouponSchema;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author colin
 */
public interface CouponSchemaRepository extends BaseRepository<CouponSchema, Long> {
    Page<CouponSchema> findByNameLike(String name, Pageable pageable);

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Long id);
}
