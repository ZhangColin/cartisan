package com.cartisan.huiduoduo.services;

import com.cartisan.common.dtos.PageResult;
import com.cartisan.common.exceptions.CartisanException;
import com.cartisan.common.utils.IdWorker;
import com.cartisan.huiduoduo.constants.CouponCodeMessage;
import com.cartisan.huiduoduo.domains.WeiXinUser;
import com.cartisan.huiduoduo.dtos.WeiXinUserDto;
import com.cartisan.huiduoduo.repositories.WeixinUserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @author colin
 */
@Service
public class WeixinUserService {
    @Autowired
    private WeixinUserRepository repository;

    @Autowired
    private IdWorker idWorker;

    public PageResult<WeiXinUserDto> searchWeiXinUsers(String nickName, Integer currentPage, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(currentPage - 1, pageSize);

        final Page<WeiXinUser> searchResult = StringUtils.isBlank(nickName) ?
                repository.findAll(pageRequest) :
                repository.findByNickNameLike("%"+nickName+"%", pageRequest);

        return new PageResult<>(searchResult.getTotalElements(), searchResult.getTotalPages(),
                searchResult.map(WeiXinUserDto::convertFrom).getContent());
    }


    @Transactional(rollbackOn = Exception.class)
    public WeiXinUserDto login(String openId) {
        if (StringUtils.isBlank(openId)) {
            throw new CartisanException(CouponCodeMessage.OPEN_ID_IS_NULL);
        }
        final Optional<WeiXinUser> weixinUserOptional = repository.findByOpenId(openId);

        if (weixinUserOptional.isPresent()) {
            return WeiXinUserDto.convertFrom(weixinUserOptional.get());
        }

        final WeiXinUser weiXinUser = new WeiXinUser(idWorker.nextId(), openId);

        repository.save(weiXinUser);

        return WeiXinUserDto.convertFrom(weiXinUser);
    }

    @Transactional(rollbackOn = Exception.class)
    public WeiXinUserDto fillWeiXinUserInfo(String openId, String nickName, Integer gender,
                                            String language, String country, String province, String city,
                                            String avatarUrl){
        final Optional<WeiXinUser> weixinUserOptional = repository.findByOpenId(openId);

        if (!weixinUserOptional.isPresent()) {
            throw new CartisanException(CouponCodeMessage.USER_NOT_EXIST);
        }

        final WeiXinUser weiXinUser = weixinUserOptional.get();
        weiXinUser.fillWeiXinUserData(nickName, gender, language, country, province, city, avatarUrl);

        repository.save(weiXinUser);

        return WeiXinUserDto.convertFrom(weiXinUser);
    }
}
