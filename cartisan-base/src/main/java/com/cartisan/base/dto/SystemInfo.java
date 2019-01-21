package com.cartisan.base.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author colin
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemInfo {
    private String name;
    private String version;
}
