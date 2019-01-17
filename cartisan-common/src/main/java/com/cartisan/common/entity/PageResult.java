package com.cartisan.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <p>Title: PageResult</p>
 * <p>Description: </p>
 *
 * @author colin
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> {
    private Long total;
    private Integer pages;
    private List<T> rows;
}
