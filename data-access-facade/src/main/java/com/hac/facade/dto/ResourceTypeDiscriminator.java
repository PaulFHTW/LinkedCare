package com.hac.facade.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ResourceTypeDiscriminator {

    @JsonProperty("6")
    APPOINTMENT,

    @JsonProperty("8")
    CONDITION
}
