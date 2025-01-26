package com.hac.facade.service;

import com.hac.facade.configuration.KeycloakProperties;
import com.hac.facade.dto.TokenExchangeResponse;
import com.hac.facade.dto.TokenIntrospectionResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.representations.idm.authorization.ResourceRepresentation;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class KeycloakService {

    public static final String TOKEN_INTROSPECT = "/realms/data-facade-app/protocol/openid-connect/token/introspect";
    public static final String TOKEN_EXCHANGE = "/realms/data-facade-app/protocol/openid-connect/token";
    public static final String SHARE_RESOURCE = "/realms/data-facade-app/authz/protection/permission/ticket";
    private final KeycloakProperties configuration;

    public TokenIntrospectionResponse introspectToken(String token) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<MultiValueMap<String, String>> multiValueMapHttpEntity = buildIntrospectionRequest(token);
        String uri = "http://" + configuration.getIp() + TOKEN_INTROSPECT;
        log.info("Starting token introspection");
        long start = System.currentTimeMillis();
        TokenIntrospectionResponse tokenIntrospectionResponse = restTemplate.postForObject(uri, multiValueMapHttpEntity, TokenIntrospectionResponse.class);
        log.info("Introspected Token in {}ms", System.currentTimeMillis() - start);
        return tokenIntrospectionResponse;
    }

    public TokenExchangeResponse exchangeToken(String splitToken) {
        RestTemplate restTemplate = new RestTemplate();
        var multiValueMapHttpEntity = buildExchangeRequest(splitToken);
        String url = "http://" + configuration.getIp() + TOKEN_EXCHANGE;
        long start = System.currentTimeMillis();
        var tokenExchangeResponse = restTemplate.postForObject(url, multiValueMapHttpEntity, TokenExchangeResponse.class);
        log.info("Exchanged Token in {}ms", System.currentTimeMillis() - start);
        return tokenExchangeResponse;

    }

    public String shareResource(String token, String resource, String requester) {
        var restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        var read = new HttpEntity<>(KeycloakSharingRequest.builder().requesterName(requester).granted(true).scopeName("read").resource(resource).build(), headers);
        return restTemplate.postForObject("http://" + configuration.getIp() + SHARE_RESOURCE, read, String.class);
    }

    public String createResource(String type, String userID, String username, String resourceName) {
        var authzClient = AuthzClient.create();
        var resourceRepresentation = new ResourceRepresentation();
        resourceRepresentation.addScope("read", "write");
        resourceRepresentation.setOwnerManagedAccess(true);
        resourceRepresentation.setOwner(username);
        resourceRepresentation.setName(resourceName);
        resourceRepresentation.setType(type);
        var representation = authzClient.protection().resource().create(resourceRepresentation);
        return representation.getId();
    }

    public String getUserIDForToken(String token) {
        if (configuration.isDebugMode()) {
            log.info("DEBUG MODE ON SKIPPING KEYCLOAK!!!");
            return "1L";
        }
        TokenIntrospectionResponse introspectedToken = introspectToken(token);
        return introspectedToken.getPatientID().toString();
    }


    private HttpEntity<MultiValueMap<String, String>> buildIntrospectionRequest(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", configuration.getClientID());
        map.add("client_secret", configuration.getClientSecret());
        map.add("token", token);
        return new HttpEntity<>(map, headers);
    }


    private HttpEntity<MultiValueMap<String, String>> buildExchangeRequest(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", configuration.getClientID());
        map.add("client_secret", configuration.getClientSecret());
        map.add("subject_token", token);
        map.add("grant_type", "urn:ietf:params:oauth:grant-type:token-exchange");
        map.add("requested_token_type", "urn:ietf:params:oauth:token-type:refresh_token");
        map.add("subject_token_type", "urn:ietf:params:oauth:token-type:access_token");
        return new HttpEntity<>(map, headers);
    }
}
