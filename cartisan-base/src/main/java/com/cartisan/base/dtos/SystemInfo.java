package com.cartisan.base.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>Title: SystemInfo</p>
 * <p>Description: </p>
 *
 * @author colin
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemInfo {
    private String name;
    private String version;
}
