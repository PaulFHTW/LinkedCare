package com.hac.facade.service;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KeycloakSharingRequest {

    private String resource;

    private String requesterName;

    private boolean granted;

    private String scopeName;
}
