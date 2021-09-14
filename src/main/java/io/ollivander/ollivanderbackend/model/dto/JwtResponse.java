package io.ollivander.ollivanderbackend.model.dto;

import java.util.List;

public class JwtResponse {
    private Integer id;
    private String token;
    private String type = "Bearer";
    private String username;
    private List<String> roles;

    public JwtResponse(Integer id, String accessToken, String username, List<String> roles) {
        this.id = id;
        this.token = accessToken;
        this.username = username;
        this.roles = roles;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }
}
