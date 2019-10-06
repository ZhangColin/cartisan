package com.cartisan.base.services;

import com.cartisan.base.domains.Account;
import com.cartisan.base.dtos.AccountDto;
import com.cartisan.base.repositories.AccountRepository;
import com.cartisan.common.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author colin
 */
@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private IdWorker idWorker;

//    @Autowired
//    private BCryptPasswordEncoder encoder;

    @Cacheable(value = "cache:base:services:AccountService:findAccount", key = "#account")
    public AccountDto findAccount(Long accountId) {
        return AccountDto.convertFrom(accountRepository.findById(accountId).get());
    }

    public void createAccount(AccountDto accountDto) {
        final long accountId = idWorker.nextId();

        final Account account = new Account(accountId, accountDto.getUsername(),
//                encoder.encode(accountDto.getPassword()),
                accountDto.getPassword(),
                accountDto.getEmail(), accountDto.getMobile());

        accountRepository.save(account);
    }

//    public void changePassword(Long accountId, String orig)
}
