package com.cartisan.domains;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

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

}
