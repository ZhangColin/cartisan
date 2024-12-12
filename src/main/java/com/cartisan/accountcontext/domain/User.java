package com.cartisan.accountcontext.domain;

import com.cartisan.infrastructure.domain.AggregateRoot;
import com.cartisan.infrastructure.domain.SoftDeleteEntity;
import com.cartisan.infrastructure.dp.OnOffStatus;
import com.cartisan.infrastructure.dp.OnOffStatusConverter;
import jakarta.persistence.*;
import lombok.*;

/**
 * @author zhangcolin
 */
@Entity
@Table(name = "acc_users")
@Getter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends SoftDeleteEntity implements AggregateRoot {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "nickname")
    private String nickname;
    @Column(name = "password")
    private String password;
    @Column(name = "avatar")
    @Setter
    private String avatar;
    @Column(name = "motto")
    @Setter
    private String motto;
    @Column(name = "status")
    @Convert(converter = OnOffStatusConverter.class)
    private OnOffStatus status;

    public User(Long id, String username, String nickname, String password) {
        this.id = id;

        this.username = username;
        this.nickname = nickname;
        this.password = password;

        this.status = OnOffStatus.Enabled;
    }

    public void changePhone(String phone) {
        this.phone = phone;
    }

    public void changeEmail(String email) {
        this.email = email;
    }
}
