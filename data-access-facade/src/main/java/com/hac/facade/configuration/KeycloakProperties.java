package com.hac.facade.configuration;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Configuration
@ConfigurationProperties(prefix = "keycloak")
@Getter
@Setter
@ToString
@Slf4j
public class KeycloakProperties {

    private String clientID;

    private String clientSecret;

    private String ip;

    private boolean debugMode = false;

    @PostConstruct
    private void printStuff() {
        log.info("Keycloak Config: {}", this.toString());
    }

}