package com.hac.facade.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.authorization.client.resource.ProtectionResource;
import org.keycloak.representations.idm.authorization.*;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class KeycloakUmaService {

    public String getAccessToken(String token, String resourceID) {
        var authzClient = AuthzClient.create();
        var authorizationRequest = new AuthorizationRequest();
        authorizationRequest.addPermission(resourceID);
        var authorize = authzClient.authorization(token).authorize(authorizationRequest);
        log.info("AuthResponse: {}", authorize);
        return authorize.getToken();
    }

    public boolean checkAccess(String token, String resourceID) {
        var authzClient = AuthzClient.create();
        var authorizationRequest = new AuthorizationRequest();
        authorizationRequest.addPermission(resourceID);
        AuthorizationResponse authorize;
        try {
            authorize = authzClient.authorization(token).authorize(authorizationRequest);
        } catch (Exception e) {
            log.error("Exception when authorizing request", e);
            return false;
        }
        log.info("AuthResponse: {}", authorize);
        return true;
    }

    public String getTypeForResource(String resource) {
        var authzClient = AuthzClient.create();
        var resourceRepresentation = authzClient.protection().resource().findById(resource);
        return resourceRepresentation.getType();

    }

    public String checkReadAccess(String token, String resourceID) {
        var authzClient = AuthzClient.create();
        var authorizationRequest = new AuthorizationRequest();
        authorizationRequest.addPermission(resourceID, "read");
        var authorize = authzClient.authorization(token).authorize(authorizationRequest);
        log.info("AuthResponse: {}", authorize);
        return authorize.getToken();
    }

    public String checkWriteAccess(String token, String resourceID) {
        var authzClient = AuthzClient.create();
        var authorizationRequest = new AuthorizationRequest();
        authorizationRequest.addPermission(resourceID, "write");
        var authorize = authzClient.authorization(token).authorize(authorizationRequest);
        log.info("AuthResponse: {}", authorize);
        return authorize.getToken();
    }

    public List<String> resourcesForUserAndType(String username, String type) {
        var authzClient = AuthzClient.create();
        log.info("Fetching resources for {} of type {}", username, type);
        if (type == null || type.isBlank()) {
            type = "";
        }
        var ids = authzClient.protection().resource().find(null, null, null, username, type, null, false, null, null);
        return Arrays.asList(ids);
    }

    public List<String> getSharedResourcesForToken(String token) {
        var authzClient = AuthzClient.create();
        var splitToken = token.split("Bearer ")[1];
        var authorize = authzClient.authorization(splitToken).authorize();
        var rpt = authorize.getToken();
        var requestingPartyToken = authzClient.protection().introspectRequestingPartyToken(rpt);
        if (requestingPartyToken.getActive()) {
            return requestingPartyToken.getPermissions().stream()
                    .filter(permission -> permission.getScopes().contains("read"))
                    .map(Permission::getResourceId).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }


    public boolean shareResourceWithUser(String token, String username, String resourceID) {
        var authzClient = AuthzClient.create();
        var permissionResponse = authzClient.protection(token).permission().create(new PermissionRequest(resourceID, "read", "write"));
        var authorizationRequest = new AuthorizationRequest();
        authorizationRequest.setTicket(permissionResponse.getTicket());
        authorizationRequest.setClaimToken(token);
        try {
            authzClient.authorization().authorize(authorizationRequest);
        } catch (Exception e) {
            log.error("Error sharing resource", e);
            return false;
        }
        return true;
    }
    public boolean shareResource(String token, String username, String resourceID) {
        var authzClient = AuthzClient.create();
        var resourcePermissionRepresentation = new ResourcePermissionRepresentation();
        resourcePermissionRepresentation.addResource(resourceID);
        ProtectionResource protectionResource = authzClient.protection(token);
        protectionResource.permission().create(new PermissionRequest(resourceID, "read"));
        return true;
    }
}
