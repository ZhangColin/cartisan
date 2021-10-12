package com.cartisan.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author colin
 */
@Aspect
@Component
@Slf4j
public class ControllerProcessorAspect {
    @Pointcut("execution(public * com..*Controller.*(..))")
    public void cut() {

    }

    @Before("cut()")
    public void before(JoinPoint joinPoint) {
        log.debug("Controller:[{}], Method:[{}], Args:[{}] ",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                Arrays.toString(joinPoint.getArgs()));
    }

//    @AfterReturning(returning = "obj", pointcut = "cut()")
//    public void afterReturning(Object obj) {
//        log.info("@@@ API Response - {} @@@", JSON.toJSONString(obj));
//    }

    @Around("cut()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object obj = proceedingJoinPoint.proceed();
        log.debug("处理耗时：[{} 毫秒]", System.currentTimeMillis()-startTime);
        return obj;
    }
}
