package com.cartisan.base.domain;

import com.cartisan.common.domains.AbstractEntity;
import com.cartisan.common.domains.AggregateRoot;
import lombok.Getter;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author colin
 */
@Entity
@Table(name = "bas_accounts")
@Where(clause = "active=1 and deleted=0")
@Getter
public class Account extends AbstractEntity implements AggregateRoot {
    public Account(Long id, String username, String password, String email, String mobile) {
        this.id = id;
        Username = username;
        this.password = password;
        this.email = email;
        this.mobile = mobile;
    }

    private Account() {
    }

    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "username")
    private String Username;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "mobile")
    private String mobile;

}
