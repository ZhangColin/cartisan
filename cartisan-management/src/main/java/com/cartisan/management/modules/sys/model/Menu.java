package com.cartisan.management.modules.sys.model;

import com.sun.tracing.dtrace.ArgsAttributes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>Title: Menu</p>
 * <p>Description: </p>
 *
 * @author colin
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu {
    private Long menuId;
    private Long parentId;
    private String parentName;
    private String name;
    private String icon;
    private String url;
    private String perms;
    private int order;
    private int type;
}
