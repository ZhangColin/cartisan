package com.cartisan.huiduoduo.repositories;

import com.cartisan.common.repositories.BaseRepository;
import com.cartisan.huiduoduo.domains.WeiXinUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * @author colin
 */
public interface WeixinUserRepository extends BaseRepository<WeiXinUser, Long> {
    Optional<WeiXinUser> findByOpenId(String openId);

    Page<WeiXinUser> findByNickNameLike(String nickName, Pageable pageable);
    List<WeiXinUser> findByNickNameLike(String nickName);
}
