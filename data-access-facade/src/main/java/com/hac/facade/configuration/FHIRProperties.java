package com.hac.facade.configuration;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@ConfigurationProperties(prefix = "fhir")
@Data
@Slf4j
public class FHIRProperties {

    private String serverUrl;

    @PostConstruct
    private void printStuff() {
        log.info("FHIR Config: {}", this.toString());
    }

}
