package com.cartisan.management.modules.sys.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: Menu</p>
 * <p>Description: </p>
 *
 * @author colin
 */
@Data
//@NoArgsConstructor
public class Menu {
    public Menu(Long menuId, Long parentId, String parentName, String name,
                String icon, String url, String perms, int order, int type) {
        this.menuId = menuId;
        this.parentId = parentId;
        this.parentName = parentName;
        this.name = name;
        this.icon = icon;
        this.url = url;
        this.perms = perms;
        this.order = order;
        this.type = type;
    }

    private Long menuId;
    private Long parentId;
    private String parentName;
    private String name;
    private String icon;
    private String url;
    private String perms;
    private int order;
    private int type;

    private List<Menu> list = new ArrayList<>();
}
