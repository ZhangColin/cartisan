package com.cartisan.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * @author colin
 */
@MappedSuperclass
@Getter
@Setter
public class SoftDeleteEntity extends AbstractEntity implements Serializable {
    @Column(name = "active", columnDefinition = "bit default 1", nullable = false)
    private boolean isActive = true;

    @Column(name = "deleted", columnDefinition = "bit default 0", nullable = false)
    private boolean isDeleted = false;

}
