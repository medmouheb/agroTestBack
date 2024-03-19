package com.agrotech.api.payload.response;


import java.util.HashSet;
import java.util.List;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String id;
    private String username;
    private String email;
    private List<String> roles;
    private HashSet<String> modules = new HashSet<>();

    private String farmer;

    public String getFarmer() {
        return farmer;
    }

    public void setFarmer(String farmer) {
        this.farmer = farmer;
    }

    public JwtResponse(String accessToken, String id, String username, String farmer,HashSet<String> modules , String email, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.modules = modules;
        this.email = email;
        this.roles = roles;
        this.farmer=farmer;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public HashSet<String> getModules() {
        return modules;
    }

    public void setModules(HashSet<String> modules) {
        this.modules = modules;
    }
}
