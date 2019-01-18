package com.cartisan.common.domains;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title: AbstractEntity</p>
 * <p>Description: </p>
 *
 * @author colin
 */
@MappedSuperclass
@Data
@ToString
@EqualsAndHashCode
public class AbstractEntity implements Serializable {
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", insertable= false, nullable = false, length = 19, updatable = false,
            columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
    private Date createDateTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", insertable= false, nullable = false, length = 19, updatable = false,
            columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updateDateTime;

    @Column(name = "active", nullable = false)
    private boolean isActive = true;

    @Column(name = "deleted", nullable = false)
    private boolean isDeleted;


//    @Id
//    @GenericGenerator(name="uuid", strategy="uuid")
//    @GeneratedValue(generator="uuid")
//    @Column(name="id")
//    protected String id;
//
//    @Column(name = "creationtime")
//    protected Timestamp creationTimestamp = new Timestamp(System.currentTimeMillis());
//    @Column(name = "lastmodifiedtime")
//    protected Timestamp modificationTimestamp = new Timestamp(System.currentTimeMillis());
//
//    @Column(name = "dr")
//    protected int dr;// 是否删除。0:未删除;1:已删除
//
//    /**
//     * 主键，对应id字段
//     */
//    public String getId() { return id; }
//    public void setId(String id) { this.id = id; }
//
//    /**
//     * 创建日期，对应ts_insert字段
//     */
//    public Timestamp getCreationTimestamp() { return creationTimestamp; }
//    public void setCreationTimestamp(Timestamp creationTimestamp) { this.creationTimestamp = creationTimestamp; }
//
//    /**
//     * 修改日期，对应ts_update字段
//     */
//    public Timestamp getModificationTimestamp() { return modificationTimestamp; }
//    public void setModificationTimestamp(Timestamp modificationTimestamp) { this.modificationTimestamp = modificationTimestamp; }
//
//    /**
//     * 是否删除，对应dr字段
//     * @return
//     */
//    public int getDr() {
//        return dr;
//    }
//    public void setDr(int dr) {
//        this.dr = dr;
//    }
}
