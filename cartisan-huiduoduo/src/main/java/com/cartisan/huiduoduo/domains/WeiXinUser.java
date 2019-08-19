package com.cartisan.huiduoduo.domains;

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
public class WeiXinUser extends AbstractEntity implements AggregateRoot {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "open_id")
    private String openId;

    @Column(name = "union_id")
    private String unionId;

    @Column(name = "language")
    private String language;

    @Column(name = "province")
    private String province;

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

    private WeiXinUser() {
    }

    public WeiXinUser(Long id, String openId) {
        this.id = id;
        this.openId = openId;
    }

    public void fillWeiXinUserData(String nickName, Integer gender, String language,
                                   String country, String province, String city,
                                   String avatarUrl) {
        this.nickName = nickName;
        this.gender = gender;
        this.language = language;
        this.country = country;
        this.province = province;
        this.city = city;
        this.avatarUrl = avatarUrl;
    }
}
