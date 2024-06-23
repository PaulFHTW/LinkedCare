package com.hac.facade.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class HapiLoggingAspect {

    @Around("@annotation(LogHapiCall)")
    public Object logHapiCall(ProceedingJoinPoint joinPoint) throws Throwable {
        var args = joinPoint.getArgs();
        log.info("Getting Resource from Hapi for id {}", args[0]);
        var start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        var duration = System.currentTimeMillis() - start;
        log.info("Got Resource from HAPI in {} ms", duration);
        return proceed;
    }

}
