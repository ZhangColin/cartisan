package com.cartisan.base.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * <p>Title: SystemInfo</p>
 * <p>Description: </p>
 *
 * @author colin
 */
@Data
@AllArgsConstructor
public class SystemInfo {
    private String name;
    private String version;
}
