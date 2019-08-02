package com.cartisan.huiduoduo.services;

import com.cartisan.common.dtos.PageResult;
import com.cartisan.common.exceptions.CartisanException;
import com.cartisan.common.utils.IdWorker;
import com.cartisan.huiduoduo.constants.CouponCodeMessage;
import com.cartisan.huiduoduo.domains.Merchant;
import com.cartisan.huiduoduo.dtos.MerchantDto;
import com.cartisan.huiduoduo.params.MerchantParam;
import com.cartisan.huiduoduo.repositories.MerchantRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    public PageResult<MerchantDto> searchMerchants(String name, Integer currentPage, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(currentPage - 1, pageSize);

        final Page<Merchant> searchResult = StringUtils.isBlank(name) ?
                repository.findAll(pageRequest) :
                repository.findByNameLike("%"+name+"%", pageRequest);

        return new PageResult<>(searchResult.getTotalElements(), searchResult.getTotalPages(),
                searchResult.map(MerchantDto::convertFrom).getContent());
    }

    public List<MerchantDto> getMerchants() {
        final List<Merchant> merchants = repository.findAll();

        return merchants.stream().map(MerchantDto::convertFrom).collect(toList());
    }

    public MerchantDto getMerchant(Long merchantId) {
        return MerchantDto.convertFrom(findMerchantById(merchantId));
    }


    @Transactional(rollbackOn = Exception.class)
    public MerchantDto addMerchant(MerchantParam merchantParam) {
        if (repository.existsByName(merchantParam.getName())) {
            throw new CartisanException(CouponCodeMessage.SAME_MERCHANT_NAME);
        }

        final Merchant merchant = new Merchant(idWorker.nextId(), merchantParam.getName(),
                merchantParam.getLogo());


        repository.save(merchant);

        return MerchantDto.convertFrom(merchant);
    }

    @Transactional(rollbackOn = Exception.class)
    public MerchantDto editMerchant(Long id, MerchantParam merchantParam) {
        if (repository.existsByNameAndIdNot(merchantParam.getName(), id)) {
            throw new CartisanException(CouponCodeMessage.SAME_MERCHANT_NAME);
        }

        final Merchant merchant = findMerchantById(id);
        merchant.changeInfo(merchantParam.getName(), merchantParam.getLogo());

        repository.save(merchant);

        return MerchantDto.convertFrom(merchant);
    }

    @Transactional(rollbackOn = Exception.class)
    public void removeMerchant(long id) {
        repository.delete(findMerchantById(id));
    }

    private Merchant findMerchantById(Long merchantId) {
        final Optional<Merchant> merchantOptional = repository.findById(merchantId);
        if (!merchantOptional.isPresent()) {
            throw new CartisanException(CouponCodeMessage.MERCHANT_NOT_EXIST);
        }
        return merchantOptional.get();
    }
}
