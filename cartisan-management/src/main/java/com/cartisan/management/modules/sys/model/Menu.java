package com.cartisan.management.modules.sys.model;

import lombok.Data;

/**
 * <p>Title: Menu</p>
 * <p>Description: </p>
 *
 * @author colin
 */
@Data
public class Menu {
    private Long menuId;
    private Long parentId;
    private String parentName;
    private String name;
    private String url;
    private String perms;
    private int order;
    private int type;
}
