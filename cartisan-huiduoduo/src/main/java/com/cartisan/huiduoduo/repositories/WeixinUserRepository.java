package com.cartisan.huiduoduo.repositories;

import com.cartisan.common.repositories.BaseRepository;
import com.cartisan.huiduoduo.domains.WeixinUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author colin
 */
public interface WeixinUserRepository extends BaseRepository<WeixinUser, Long> {
    boolean existsByNickName(String nickName);

    boolean existsByNickNameAndIdNot(String nickName, Long id);

    Page<WeixinUser> findByNickNameLike(String nickName, Pageable pageable);
    List<WeixinUser> findByNickNameLike(String nickName);
}
