package com.cartisan.base.domains;

import com.cartisan.common.domains.AbstractEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>Title: Continent</p>
 * <p>Description: </p>
 *
 * @author colin
 */
@Entity
@Table(name = "bas_continents")
@Data
public class Continent extends AbstractEntity {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "code")
    private String code;
    @Column(name = "name")
    private String name;
}
