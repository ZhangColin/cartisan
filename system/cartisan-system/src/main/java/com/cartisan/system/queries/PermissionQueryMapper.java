package com.cartisan.system.queries;

import com.cartisan.system.dtos.TreeNode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author colin
 */
public interface PermissionQueryMapper {
    List<TreeNode> getPermissionTree();

    List<String> getPermissionCodesByRoleCodes(@Param("roleCodes") List<String> roleCodes);
}
