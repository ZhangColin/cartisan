package com.cartisan.aops.resubmit;

import com.cartisan.constants.CodeMessage;
import com.cartisan.exceptions.CartisanException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author colin
 */
@Aspect
@Component
@Slf4j
public class ResubmitAspect {
    @Autowired
    private ObjectMapper objectMapper;

    private final Object lock = new Object();

    @Around("@annotation(com.cartisan.aops.resubmit.Resubmit)")
    public Object handleResubmit(ProceedingJoinPoint joinPoint) throws Throwable {
        final Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();

        // 获取注解信息
        final Resubmit annotation = method.getAnnotation(Resubmit.class);
        final int delaySeconds = annotation.delaySeconds();

        final Object[] pointArgs = joinPoint.getArgs();

        StringBuffer sb = new StringBuffer();
        for (Object pointArg : pointArgs) {
            sb.append(objectMapper.writeValueAsString(pointArg));
        }

        final String key = ResubmitLock.handleKey(sb.toString());

        boolean lockStatus = false;
        // 执行锁
        try {
            lockStatus = ResubmitLock.getInstance().lock(key, this.lock);

            if (lockStatus) {
                return joinPoint.proceed();
            }
            else{
                throw new CartisanException(CodeMessage.FAIL.fillArgs("不要重复提交"));
            }
        }
        finally {
            ResubmitLock.getInstance().unlock(lockStatus, key, delaySeconds);
        }
    }
}
