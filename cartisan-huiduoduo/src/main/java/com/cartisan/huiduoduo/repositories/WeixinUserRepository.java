package com.cartisan.huiduoduo.repositories;

import com.cartisan.common.repositories.BaseRepository;
import com.cartisan.huiduoduo.domains.WeixinUser;

/**
 * @author colin
 */
public interface WeixinUserRepository extends BaseRepository<WeixinUser, Long> {
    boolean existsByNickName(String nickName);

    boolean existsByNickNameAndIdNot(String nickName, Long id);
}
