package com.cartisan.management.modules.sys.gateway;

import com.cartisan.management.modules.sys.model.Menu;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: MenuClient</p>
 * <p>Description: </p>
 *
 * @author colin
 */
@Component
public class MenuClient {
    public List<Menu> getAll() {
        return new ArrayList<>();
    }
}
