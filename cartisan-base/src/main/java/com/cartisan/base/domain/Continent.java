package com.cartisan.base.domain;

import com.cartisan.common.domains.AbstractEntity;
import com.cartisan.common.domains.AggregateRoot;
import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * @author colin
 */
@Entity
@Table(name = "bas_continents")
@Where(clause = "active=1 and deleted=0")
@Data
public class Continent extends AbstractEntity implements AggregateRoot {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "code")
    private String code;
    @Column(name = "name")
    private String name;
}
