package com.cartisan.system.services;

import com.cartisan.system.domains.OperationLog;
import com.cartisan.system.domains.OperationLogType;
import com.cartisan.system.repositories.OperationLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author colin
 */
@Service
public class OperationLogService {
    @Autowired
    private OperationLogRepository repository;

    @Transactional(rollbackOn = Exception.class)
    public void createOperationLog(OperationLogType type, Long userId, String ip, String url,
                                   String method, String parameters, Boolean isSuccess) {
        repository.save(new OperationLog(type, userId, ip, url, method, parameters, isSuccess));
    }
}
