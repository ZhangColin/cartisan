package com.cartisan.management.modules.sys.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;


/**
 * <p>Title: ShiroTag</p>
 * <p>Description: </p>
 *
 * @author colin
 */
@Component
public class ShiroTag {
    public boolean hasPermission(String permission) {
        Subject subject = SecurityUtils.getSubject();
        return subject != null && subject.isPermitted(permission);
    }
}
