package com.cartisan.system.queries;

import com.cartisan.system.dtos.TreeNode;

import java.util.List;

/**
 * @author colin
 */
public interface DepartmentQueryMapper {
    List<TreeNode> getDepartmentTreeNodes();
}
