package com.cartisan.huiduoduo.services;

import com.cartisan.common.exceptions.CartisanException;
import com.cartisan.common.utils.IdWorker;
import com.cartisan.huiduoduo.constants.CouponCodeMessage;
import com.cartisan.huiduoduo.domains.Merchant;
import com.cartisan.huiduoduo.dtos.MerchantDto;
import com.cartisan.huiduoduo.params.MerchantParam;
import com.cartisan.huiduoduo.repositories.MerchantRepository;
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
public class MerchantService {
    @Autowired
    private MerchantRepository repository;

    @Autowired
    private IdWorker idWorker;

    public List<MerchantDto> getMerchants() {
        final List<Merchant> categories = repository.findAll();

        return categories.stream().map(MerchantDto::convertFrom).collect(toList());
    }


    @Transactional(rollbackOn = Exception.class)
    public void addMerchant(MerchantParam merchantParam) {
        if (repository.existsByName(merchantParam.getName())) {
            throw new CartisanException(CouponCodeMessage.SAME_MERCHANT_NAME);
        }

        final Merchant merchant = new Merchant(idWorker.nextId(), merchantParam.getName(),
                merchantParam.getLogo());


        repository.save(merchant);
    }

    @Transactional(rollbackOn = Exception.class)
    public void editMerchant(Long id, MerchantParam merchantParam) {
        if (repository.existsByNameAndIdNot(merchantParam.getName(), id)) {
            throw new CartisanException(CouponCodeMessage.SAME_MERCHANT_NAME);
        }

        final Optional<Merchant> merchantOptional = repository.findById(id);
        if (!merchantOptional.isPresent()) {
            throw new CartisanException(CouponCodeMessage.MERCHANT_NOT_EXIST);
        }

        final Merchant merchant = merchantOptional.get();
        merchant.changeInfo(merchantParam.getName(), merchantParam.getLogo());

        repository.save(merchant);
    }

    @Transactional(rollbackOn = Exception.class)
    public void removeMerchant(long id) {
        final Optional<Merchant> merchantOptional = repository.findById(id);
        if (!merchantOptional.isPresent()) {
            throw new CartisanException(CouponCodeMessage.MERCHANT_NOT_EXIST);
        }

        repository.deleteById(id);
    }
}
