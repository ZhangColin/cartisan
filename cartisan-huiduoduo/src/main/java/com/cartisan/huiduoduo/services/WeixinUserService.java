package com.cartisan.huiduoduo.services;

import com.cartisan.common.dtos.PageResult;
import com.cartisan.common.exceptions.CartisanException;
import com.cartisan.common.utils.IdWorker;
import com.cartisan.huiduoduo.constants.CouponCodeMessage;
import com.cartisan.huiduoduo.domains.WeixinUser;
import com.cartisan.huiduoduo.dtos.WeixinUserDto;
import com.cartisan.huiduoduo.params.WeixinUserParam;
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

    public PageResult<WeixinUserDto> searchWeixinUsers(String nickName, Integer currentPage, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(currentPage - 1, pageSize);

        final Page<WeixinUser> searchResult = StringUtils.isBlank(nickName) ?
                repository.findAll(pageRequest) :
                repository.findByNickNameLike("%"+nickName+"%", pageRequest);

        return new PageResult<>(searchResult.getTotalElements(), searchResult.getTotalPages(),
                searchResult.map(WeixinUserDto::convertFrom).getContent());
    }


    @Transactional(rollbackOn = Exception.class)
    public void addWeixinUser(WeixinUserParam weixinUserParam) {
        if (repository.existsByNickName(weixinUserParam.getNickName())) {
            throw new CartisanException(CouponCodeMessage.SAME_MERCHANT_NAME);
        }

        final WeixinUser weixinUser = new WeixinUser(idWorker.nextId(),
                weixinUserParam.getNickName(), weixinUserParam.getOpenId(), weixinUserParam.getUnionId(),
                weixinUserParam.getCountry(), weixinUserParam.getCity(), weixinUserParam.getGender(),
                weixinUserParam.getAvatarUrl(), weixinUserParam.getReferrerId());


        repository.save(weixinUser);
    }

    @Transactional(rollbackOn = Exception.class)
    public void editWeixinUser(Long id, WeixinUserParam weixinUserParam) {
        if (repository.existsByNickNameAndIdNot(weixinUserParam.getNickName(), id)) {
            throw new CartisanException(CouponCodeMessage.SAME_MERCHANT_NAME);
        }

        final Optional<WeixinUser> weixinUserOptional = repository.findById(id);
        if (!weixinUserOptional.isPresent()) {
            throw new CartisanException(CouponCodeMessage.MERCHANT_NOT_EXIST);
        }

        final WeixinUser weixinUser = weixinUserOptional.get();
        weixinUser.changeInfo(weixinUserParam.getNickName(), weixinUserParam.getOpenId(), weixinUserParam.getUnionId(),
                weixinUserParam.getCountry(), weixinUserParam.getCity(), weixinUserParam.getGender(),
                weixinUserParam.getAvatarUrl(), weixinUserParam.getReferrerId());

        repository.save(weixinUser);
    }

    @Transactional(rollbackOn = Exception.class)
    public void removeWeixinUser(long id) {
        final Optional<WeixinUser> weixinUserOptional = repository.findById(id);
        if (!weixinUserOptional.isPresent()) {
            throw new CartisanException(CouponCodeMessage.MERCHANT_NOT_EXIST);
        }

        repository.deleteById(id);
    }
}
