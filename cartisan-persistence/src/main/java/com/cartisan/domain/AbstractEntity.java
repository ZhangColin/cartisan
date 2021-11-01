package com.cartisan.domain;

import lombok.Getter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author colin
 */
@MappedSuperclass
@Getter
public class AbstractEntity implements Serializable {
    @CreationTimestamp
    @Column(name = "created", insertable = false, nullable = false, length = 19, updatable = false,
            columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
    protected LocalDateTime createDateTime;

    @UpdateTimestamp
    @Column(name = "updated", insertable = false, nullable = false, length = 19, updatable = false,
            columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    protected LocalDateTime updateDateTime;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
//        AbstractEntity brand = (AbstractEntity) o;
//        return id != null && Objects.equals(id, brand.id);
//    }x`
//
//    @Override
//    public int hashCode() {
//        return 0;
//    }
}
