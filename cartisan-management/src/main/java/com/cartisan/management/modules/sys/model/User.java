package com.cartisan.management.modules.sys.model;

import lombok.Data;

/**
 * <p>Title: User</p>
 * <p>Description: </p>
 *
 * @author colin
 */
@Data
public class User {
    public Long userId;
    public String username;
    public String password;
    public String salt;
    public int status;

}
