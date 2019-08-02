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
@Table(name = "cpn_referrers")
@Getter
@EqualsAndHashCode(callSuper = true)
public class Referrer extends AbstractEntity implements AggregateRoot {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "profession")
    private String profession;

    @Column(name = "debit_cart")
    private String debitCart;

    @Column(name = "bank")
    private String bank;

    @Column(name = "level")
    private Integer level;

    private Referrer() {
    }

    public Referrer(Long id, Long userId, String name, String phone, String profession, String debitCart, String bank) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.phone = phone;
        this.profession = profession;
        this.debitCart = debitCart;
        this.bank = bank;

        this.level=0;
    }

    public void upgrade() {
        this.level++;
    }

    public void changeInfo(String name, String phone, String profession, String debitCart, String bank) {
        this.name = name;
        this.phone = phone;
        this.profession = profession;
        this.debitCart = debitCart;
        this.bank = bank;
    }
}
