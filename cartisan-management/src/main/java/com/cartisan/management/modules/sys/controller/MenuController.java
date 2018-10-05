package com.cartisan.management.modules.sys.controller;

import com.cartisan.management.modules.sys.model.Menu;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

/**
 * <p>Title: MenuController</p>
 * <p>Description: </p>
 *
 * @author colin
 */
@RestController
@RequestMapping("/modules/sys/menu")
public class MenuController {
    @GetMapping("/nav")
    public HashMap<String, Object> nav() {
        final List<Menu> list = list();

        final List<Menu> menus = list.stream()
                .filter(menu -> menu.getParentId() == 0).collect(toList());

        menus.forEach(menu -> menu.setList(list.stream().filter(subMenu -> subMenu.getParentId()
                .equals(menu.getMenuId())).collect(toList())));

        HashMap<String, Object> result = new HashMap<>();
        result.put("menuList", menus);

        return result;
    }

    @GetMapping("/list")
    public List<Menu> list() {
        final ArrayList<Menu> menus = new ArrayList<>();

        final Menu dashboard = new Menu(1L, 0L, "", "控制台", "fa fa-dashboard", "/index.html", "sys:dashboard", 1, 1);
        final Menu system = new Menu(2L, 0L, "", "系统管理", "fa fa-cog", "", "", 2, 0);
        final Menu menu = new Menu(3L, 2L, "系统管理", "菜单管理", "fa fa-th-list", "/modules/sys/menu.html", "sys:menu:list", 1, 1);
        final Menu addMenu = new Menu(4L, 3L, "菜单管理", "新增", "fa fa-plus", "", "sys:menu:add", 1, 2);
        final Menu updateMenu = new Menu(5L, 3L, "菜单管理", "修改", "fa fa-pencil-square-o", "", "sys:menu:update", 2, 2);
        final Menu deleteMenu = new Menu(6L, 3L, "菜单管理", "删除", "fa fa-trash-o", "", "sys:menu:delete", 3, 2);

        final Menu user = new Menu(7L, 2L, "系统管理", "用户管理", "fa fa-user", "/modules/sys/user.html", "sys:user:list", 1, 1);
        final Menu addUser = new Menu(8L, 7L, "用户管理", "新增", "fa fa-plus", "", "sys:user:add", 1, 2);
        final Menu updateUser = new Menu(9L, 7L, "用户管理", "修改", "fa fa-pencil-square-o", "", "sys:user:update", 2, 2);
        final Menu deleteUser = new Menu(10L, 7L, "用户管理", "删除", "fa fa-trash-o", "", "sys:user:delete", 3, 2);


        menus.addAll(asList(dashboard, system, menu, addMenu, updateMenu, deleteMenu, user, addUser, updateUser, deleteUser));

        return menus;

    }
}
