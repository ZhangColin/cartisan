package com.cartisan.joey.coupon.repositories;

import com.cartisan.common.repositories.BaseRepository;
import com.cartisan.joey.coupon.domains.Category;

/**
 * @author colin
 */
public interface CategoryRepository extends BaseRepository<Category, Long> {
    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Long id);
}
