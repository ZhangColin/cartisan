package com.cartisan.huiduoduo.services;

import com.cartisan.common.dtos.PageResult;
import com.cartisan.common.exceptions.CartisanException;
import com.cartisan.common.utils.IdWorker;
import com.cartisan.huiduoduo.constants.CouponCodeMessage;
import com.cartisan.huiduoduo.domains.Referrer;
import com.cartisan.huiduoduo.domains.WeiXinUser;
import com.cartisan.huiduoduo.dtos.ReferrerDto;
import com.cartisan.huiduoduo.dtos.ReferrerInfo;
import com.cartisan.huiduoduo.params.GeneralizeApplyParam;
import com.cartisan.huiduoduo.repositories.CouponRepository;
import com.cartisan.huiduoduo.repositories.ReferrerRepository;
import com.cartisan.huiduoduo.repositories.WeixinUserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * @author colin
 */
@Service
public class ReferrerService {
    @Autowired
    private ReferrerRepository repository;

    @Autowired
    private WeixinUserRepository weixinUserRepository;

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private IdWorker idWorker;

    public PageResult<ReferrerDto> searchReferrers(String nickName, Integer currentPage, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(currentPage - 1, pageSize);

        final Page<Referrer> searchResult = StringUtils.isBlank(nickName) ?
                repository.findAll(pageRequest) :
                repository.findByUserIdIn(weixinUserRepository.findByNickNameLike("%"+nickName+"%").stream().map(WeiXinUser::getId).collect(toList()), pageRequest);

        return new PageResult<>(searchResult.getTotalElements(), searchResult.getTotalPages(),
                searchResult.map(ReferrerDto::convertFrom).getContent());
    }


    @Transactional(rollbackOn = Exception.class)
    public void generalizeApply(GeneralizeApplyParam generalizeApplyParam) {
        if (repository.existsByUserId(Long.parseLong(generalizeApplyParam.getUserId()))) {
            throw new CartisanException(CouponCodeMessage.SAME_MERCHANT_NAME);
        }

        final Referrer referrer = new Referrer(idWorker.nextId(), Long.parseLong(generalizeApplyParam.getUserId()), generalizeApplyParam.getName(),
                generalizeApplyParam.getPhone(), generalizeApplyParam.getProfession(),
                generalizeApplyParam.getDebitCart(), generalizeApplyParam.getBank());

        repository.save(referrer);
    }

    @Transactional(rollbackOn = Exception.class)
    public void generalizeAudit(Long referrerId) {
        final Optional<Referrer> referrerOptional = repository.findById(referrerId);
        if (!referrerOptional.isPresent()) {
            throw new CartisanException(CouponCodeMessage.MERCHANT_NOT_EXIST);
        }

        final Referrer referrer = referrerOptional.get();
        referrer.audit();

        repository.save(referrer);

        final WeiXinUser weiXinUser = weixinUserRepository.findById(referrer.getUserId()).get();
        weiXinUser.setReferrer(referrerId);

        weixinUserRepository.save(weiXinUser);
    }

    @Transactional(rollbackOn = Exception.class)
    public void editReferrer(Long id, GeneralizeApplyParam referrerParam) {
        final Optional<Referrer> referrerOptional = repository.findById(id);
        if (!referrerOptional.isPresent()) {
            throw new CartisanException(CouponCodeMessage.MERCHANT_NOT_EXIST);
        }

        final Referrer referrer = referrerOptional.get();
        referrer.changeInfo(referrerParam.getName(), referrerParam.getPhone(), referrerParam.getProfession(),
                referrerParam.getDebitCart(), referrerParam.getBank());

        repository.save(referrer);
    }

    @Transactional(rollbackOn = Exception.class)
    public void removeReferrer(long id) {
        final Optional<Referrer> referrerOptional = repository.findById(id);
        if (!referrerOptional.isPresent()) {
            throw new CartisanException(CouponCodeMessage.MERCHANT_NOT_EXIST);
        }

        repository.deleteById(id);
    }

    public Boolean isReferrer(Long userId) {
        return repository.existsByUserId(userId);
    }

    public ReferrerInfo getReferrerInfoByUserId(Long userId) {
        final Optional<Referrer> referrerOptional = repository.findByUserId(userId);
        if (!referrerOptional.isPresent()) {
            throw new CartisanException(CouponCodeMessage.MERCHANT_NOT_EXIST);
        }

        final Referrer referrer = referrerOptional.get();

        final ReferrerInfo referrerInfo = new ReferrerInfo();

        referrerInfo.setReferrerId(referrer.getId().toString());
        referrerInfo.setName(referrer.getName());

        referrerInfo.setCoupons(couponRepository.countByUserId(referrer.getUserId()));
        referrerInfo.setUsers(weixinUserRepository.countByReferrerId(referrer.getId()));

        return referrerInfo;
    }
}
