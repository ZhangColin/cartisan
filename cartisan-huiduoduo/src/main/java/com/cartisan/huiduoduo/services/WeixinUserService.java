package com.cartisan.huiduoduo.services;

import com.cartisan.common.exceptions.CartisanException;
import com.cartisan.common.utils.IdWorker;
import com.cartisan.huiduoduo.constants.CouponCodeMessage;
import com.cartisan.huiduoduo.domains.WeixinUser;
import com.cartisan.huiduoduo.dtos.WeixinUserDto;
import com.cartisan.huiduoduo.params.WeixinUserParam;
import com.cartisan.huiduoduo.repositories.WeixinUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * @author colin
 */
@Service
public class WeixinUserService {
    @Autowired
    private WeixinUserRepository repository;

    @Autowired
    private IdWorker idWorker;

    public List<WeixinUserDto> getWeixinUsers() {
        final List<WeixinUser> categories = repository.findAll();

        return categories.stream().map(WeixinUserDto::convertFrom).collect(toList());
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
