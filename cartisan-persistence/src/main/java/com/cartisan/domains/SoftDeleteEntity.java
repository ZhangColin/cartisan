package com.cartisan.domains;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * @author colin
 */
@MappedSuperclass
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class SoftDeleteEntity extends AbstractEntity implements Serializable {
    @Column(name = "active", nullable = false)
    private boolean isActive = true;

    @Column(name = "deleted", nullable = false)
    private boolean isDeleted;

}
