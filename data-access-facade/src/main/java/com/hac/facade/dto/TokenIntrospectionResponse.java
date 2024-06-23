package com.hac.facade.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenIntrospectionResponse implements Serializable {

    @JsonProperty("patient-id")
    private Long patientID;

    private Long exp;

    private Boolean active;

    @JsonProperty("username")
    private String username;

    @JsonProperty("preferred_username")
    private String preferredUsername;

}
