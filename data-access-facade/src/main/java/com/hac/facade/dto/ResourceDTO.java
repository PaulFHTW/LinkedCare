package com.hac.facade.dto;

import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResourceDTO {

    private int resourceTypeDiscriminator;

    @JsonRawValue
    private String resourceValue;
}
