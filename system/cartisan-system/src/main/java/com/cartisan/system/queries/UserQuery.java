package com.cartisan.system.queries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * @author colin
 */
@Service
public class UserQuery {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean existsUserInDepartment(Long departmentId) {
        final String sql = "select exists(select 1 from sys_user_departments where department_id = ?);";

        return jdbcTemplate.queryForObject(sql, Boolean.class, departmentId);
    }
}
