package com.cartisan.domains;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author colin
 */
@MappedSuperclass
@Data
@ToString
@EqualsAndHashCode
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
