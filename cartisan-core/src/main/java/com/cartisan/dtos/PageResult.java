package com.cartisan.dtos;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author colin
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> {
    @JsonSerialize(using = TotalJsonSerializer.class)
    private Long total;
    private Integer pages;
    private List<T> rows;
}
