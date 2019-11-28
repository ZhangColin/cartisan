package com.cartisan;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author colin
 */
@Data
@AllArgsConstructor
public class CurrentUser {
    private String userName;
    private String ip;
}
