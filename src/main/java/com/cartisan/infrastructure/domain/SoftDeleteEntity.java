package com.cartisan.infrastructure.domain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author zhangcolin
 */
@MappedSuperclass
@SQLRestriction("deleted=0")
public class SoftDeleteEntity extends AbstractEntity implements Serializable {
//    @Column(name = "active", columnDefinition = "bit default 1", nullable = false)
//    private boolean isActive = true;

    @Column(name = "deleted", columnDefinition = "bit default 0", nullable = false)
    private boolean isDeleted = false;
}
