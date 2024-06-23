package com.hac.facade.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.hac.facade.service.KeycloakService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class KeycloakAspect {

    private final KeycloakService keycloakService;

    @Around("@annotation(IntrospectedToken)")
    public Object IntrospectToken(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Introspecting token");

        var args = joinPoint.getArgs();
        var authHeader = (String) args[0];
        var token = authHeader.split("Bearer ")[1];
        var userIDForToken = keycloakService.getUserIDForToken(token);
        args[1] = userIDForToken;

        if (userIDForToken == null) {
            throw new RuntimeException("FAILED AUTH");
        }
        log.info("Introspected token for user {}", userIDForToken);
        return joinPoint.proceed(args);
    }

    @Around("@annotation(IntrospectedAndAddUsername)")
    public Object IntrospectTokenAndAddUsername(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Introspecting token");

        var args = joinPoint.getArgs();
        var authHeader = (String) args[0];
        var token = authHeader.split("Bearer ")[1];
        var introspectionResponse = keycloakService.introspectToken(token);
        args[1] = introspectionResponse.getPatientID();
        var username = introspectionResponse.getUsername();
        args[2] = username;
        log.info("Introspected token for user {}", username);
        if (introspectionResponse.getUsername() == null || !introspectionResponse.getActive() || introspectionResponse.getPatientID() == null) {
            throw new RuntimeException("FAILED AUTH");
        }
        return joinPoint.proceed(args);
    }

}
