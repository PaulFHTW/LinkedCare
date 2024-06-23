package com.hac.facade.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hac.facade.aspects.IntrospectedAndAddUsername;
import com.hac.facade.aspects.IntrospectedToken;
import com.hac.facade.dto.ResourceDTO;
import com.hac.facade.dto.ResourceSharingRequestDTO;
import com.hac.facade.mapper.ResourceTypeToDescrMapper;
import com.hac.facade.persistence.FHIRContextProvider;
import com.hac.facade.persistence.FHIRDataProvider;
import com.hac.facade.service.KeycloakService;
import com.hac.facade.service.KeycloakUmaService;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Slf4j
public class UMAResourceController {

    private final KeycloakUmaService keycloakUmaService;
    private final KeycloakService keycloakService;
    private final FHIRContextProvider fhirContextProvider;
    private final FHIRDataProvider fhirDataProvider;

    @GetMapping("/v1/{id}")
    public String getToken1(@RequestHeader(name = "Authorization") String token, @PathVariable String id) {
        var splitToken = token.split("Bearer")[1];
        return keycloakUmaService.getAccessToken(splitToken, id);
    }

    @GetMapping("/patientInfo/ownedResources")
    @IntrospectedAndAddUsername
    public ResponseEntity<List<String>> getResources(@RequestHeader(name = "Authorization") String token, @Parameter(hidden = true) Long userIDForToken, String username, @RequestParam(required = false) String type) {
        if (userIDForToken == null || username == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(List.of("invalid token"));
        }
        return ResponseEntity.ok(keycloakUmaService.resourcesForUserAndType(username, type));
    }

    @GetMapping("/patientInfo/ownedResources/{id}")
    @IntrospectedAndAddUsername
    public ResponseEntity<ResourceDTO> getOwnedResourceString(@RequestHeader(name = "Authorization") String token, @Parameter(hidden = true) Long userIDForToken, String username, @PathVariable String id) {
        if (userIDForToken == null || username == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
        var strings = keycloakUmaService.resourcesForUserAndType(username, null);
        if (!strings.contains(id)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
        var typeForResource = keycloakUmaService.getTypeForResource(id);

        var resourceForID = fhirDataProvider.getResourceStringForID(id, typeForResource);
        var resourceDTO = ResourceDTO.builder().resourceTypeDiscriminator(ResourceTypeToDescrMapper.mapType(typeForResource)).resourceValue(resourceForID).build();
        return ResponseEntity.ok(resourceDTO);
    }

    @GetMapping("v2/patientInfo/ownedResources/{id}")
    @IntrospectedAndAddUsername
    public ResponseEntity<String> getOwnedResource(@RequestHeader(name = "Authorization") String token, @Parameter(hidden = true) Long userIDForToken, String username, @PathVariable String id) {
        if (userIDForToken == null || username == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
        var strings = keycloakUmaService.resourcesForUserAndType(username, null);
        if (!strings.contains(id)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
        var typeForResource = keycloakUmaService.getTypeForResource(id);

        var resourceForID = fhirDataProvider.getResourceForID(id, typeForResource);
        var resString = fhirContextProvider.getJsonParser().encodeResourceToString(resourceForID);
        return ResponseEntity.ok(resString);
    }

    @GetMapping("/patientInfo/sharedResources")
    @IntrospectedToken
    public ResponseEntity<List<String>> getSharedResources(@RequestHeader(name = "Authorization") String token, @Parameter(hidden = true) Long userIDForToken, @Parameter(hidden = true) String username) {
        if (userIDForToken == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(List.of("invalid token"));
        }
        return ResponseEntity.ok(keycloakUmaService.getSharedResourcesForToken(token));
    }

    @GetMapping("/patientInfo/sharedResources/{id}")
    @IntrospectedToken
    public ResponseEntity<ResourceDTO> getSharedResourceStringForID(@RequestHeader(name = "Authorization") String token, @Parameter(hidden = true) Long userIDForToken, @Parameter(hidden = true) String username, @PathVariable String id) {
        if (userIDForToken == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
        var splitToken = token.split("Bearer ")[1];
        if(keycloakUmaService.checkAccess(splitToken, id)) {
            var typeForResource = keycloakUmaService.getTypeForResource(id);
            var resourceForID = fhirDataProvider.getResourceStringForID(id, typeForResource);
            var resourceDTO = ResourceDTO.builder().resourceTypeDiscriminator(ResourceTypeToDescrMapper.mapType(typeForResource)).resourceValue(resourceForID).build();
            return ResponseEntity.ok().body(resourceDTO);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
    }

    @GetMapping("v2/patientInfo/sharedResources/{id}")
    @IntrospectedToken
    public ResponseEntity<String> getSharedResourceForID(@RequestHeader(name = "Authorization") String token, @Parameter(hidden = true) Long userIDForToken, @Parameter(hidden = true) String username, @PathVariable String id) {
        if (userIDForToken == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
        var splitToken = token.split("Bearer ")[1];
        if(keycloakUmaService.checkAccess(splitToken, id)) {
            var typeForResource = keycloakUmaService.getTypeForResource(id);
            var resourceForID = fhirDataProvider.getResourceForID(id, typeForResource);
            var resourceString = fhirContextProvider.getJsonParser().encodeResourceToString(resourceForID);
            return ResponseEntity.ok().body(resourceString);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
    }

    @PostMapping("/share")
    @IntrospectedToken
    public ResponseEntity<String> shareResource(@RequestHeader(name = "Authorization") String token, @Parameter(hidden = true) Long userIDForToken, @RequestBody ResourceSharingRequestDTO resourceSharingRequestDTO) {
        var splitToken = token.split("Bearer ")[1];
        var tokenExchangeResponse = keycloakService.exchangeToken(splitToken);
        var accessToken = tokenExchangeResponse.getAccessToken();
        if (accessToken != null && !accessToken.isBlank()) {
            log.info("New Token after exchange: ");
            return ResponseEntity.ok(keycloakService.shareResource(accessToken, resourceSharingRequestDTO.getResourceId(), resourceSharingRequestDTO.getUsername()));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Could not Exchange Token");
    }



}
