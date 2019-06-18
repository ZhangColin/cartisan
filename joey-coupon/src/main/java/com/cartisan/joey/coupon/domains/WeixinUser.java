package com.cartisan.joey.coupon.domains;

import com.cartisan.common.domains.AbstractEntity;
import com.cartisan.common.domains.AggregateRoot;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author colin
 */
@Entity
@Table(name = "cpn_weixin_users")
@Getter
@EqualsAndHashCode(callSuper = true)
public class WeixinUser extends AbstractEntity implements AggregateRoot {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "open_id")
    private String openId;

    @Column(name = "union_id")
    private String unionId;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "referrer_id")
    private Long referrerId;

    private WeixinUser() {
    }

    public WeixinUser(Long id, String nickName, String openId, String unionId, String country, String city,
                      Integer gender, String avatarUrl, Long referrerId) {
        this.id = id;
        this.nickName = nickName;
        this.openId = openId;
        this.unionId = unionId;
        this.country = country;
        this.city = city;
        this.gender = gender;
        this.avatarUrl = avatarUrl;
        this.referrerId = referrerId;
    }
}
