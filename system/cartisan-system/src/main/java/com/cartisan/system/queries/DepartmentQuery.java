package com.cartisan.system.queries;

import com.cartisan.system.dtos.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author colin
 */
@Service
public class DepartmentQuery {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<TreeNode> getDepartmentTree() {
        final String sql = "select id, name, parent_id as parentId from sys_departments order by sort asc";

        final List<TreeNode> nodes = jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(TreeNode.class));
        return TreeNode.buildTree(nodes);
    }
}
