package com.cartisan.base.service;

import com.cartisan.base.domain.Account;
import com.cartisan.base.dto.AccountDto;
import com.cartisan.base.repository.AccountRepository;
import com.cartisan.common.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Cacheable(value = "cache:base:services:AccountService:findAccount", key = "#account")
    public AccountDto findAccount(Long accountId) {
        return AccountDto.convertFrom(accountRepository.getOne(accountId));
    }

    public void createAccount(AccountDto accountDto) {
        final long accountId = idWorker.nextId();

        final Account account = new Account(accountId, accountDto.getUsername(),
                encoder.encode(accountDto.getPassword()),
                accountDto.getEmail(), accountDto.getMobile());

        accountRepository.save(account);
    }

//    public void changePassword(Long accountId, String orig)
}
