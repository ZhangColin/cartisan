package com.cartisan.system.repositories;

import com.cartisan.common.repositories.BaseRepository;
import com.cartisan.system.domains.Permission;

/**
 * @author colin
 */
public interface PermissionRepository extends BaseRepository<Permission, Long> {
    boolean existsByParentIdAndName(Long parentId, String name);
    boolean existsByParentIdAndNameAndIdNot(Long parentId, String name, Long departmentId);

    boolean existsByParentId(Long parentId);
}
