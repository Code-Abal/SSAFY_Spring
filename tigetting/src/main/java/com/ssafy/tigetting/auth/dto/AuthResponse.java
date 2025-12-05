package com.ssafy.tigetting.auth.dto;

public class AuthResponse {
    private String accessToken;
    private String userType;  // USER 또는 ADMIN

    public AuthResponse() { }
    public AuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }
    public AuthResponse(String accessToken, String userType) {
        this.accessToken = accessToken;
        this.userType = userType;
    }

    public String getAccessToken() { return accessToken; }
    public void setAccessToken(String accessToken) { this.accessToken = accessToken; }
    public String getUserType() { return userType; }
    public void setUserType(String userType) { this.userType = userType; }
}
