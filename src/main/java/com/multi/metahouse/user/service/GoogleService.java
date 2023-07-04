package com.multi.metahouse.user.service;

import java.util.HashMap;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;

@Service
public class GoogleService {
	
	private final RestTemplate restTemplate = new RestTemplate();

	public HashMap<String, String> socialLogin(String code) {
		HashMap<String, String> userInfo = new HashMap<>();
        //System.out.println("code = " + code);
        String accessToken = getAccessToken(code);
        JsonNode userResourceNode = getUserResource(accessToken);
        System.out.println("accessToken = " + accessToken);
        System.out.println("userResourceNode = " + userResourceNode);
        
        String id = userResourceNode.get("id").asText();
        String email = userResourceNode.get("email").asText();
        String nickname = userResourceNode.get("name").asText();
        
        userInfo.put("id", id);
        userInfo.put("email", email);
        userInfo.put("nickname", nickname);
        
        return userInfo;
    }
	
	private String getAccessToken(String authorizationCode) {
        String clientId = "243843722682-a2c7urdnevcbqksjm4ahf69qcnrfb32c.apps.googleusercontent.com";
        String clientSecret = "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";
        String redirectUri = "http://localhost:8088/metahaus/signgoogle";
        String tokenUri = "https://oauth2.googleapis.com/token";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("code", authorizationCode);
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("redirect_uri", redirectUri);
        params.add("grant_type", "authorization_code");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity entity = new HttpEntity(params, headers);

        ResponseEntity<JsonNode> responseNode = restTemplate.exchange(tokenUri, HttpMethod.POST, entity, JsonNode.class);
        JsonNode accessTokenNode = responseNode.getBody();
        return accessTokenNode.get("access_token").asText();
    }

    private JsonNode getUserResource(String accessToken) {
        String resourceUri = "https://www.googleapis.com/oauth2/v2/userinfo";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        HttpEntity entity = new HttpEntity(headers);
        return restTemplate.exchange(resourceUri, HttpMethod.GET, entity, JsonNode.class).getBody();
    }
}