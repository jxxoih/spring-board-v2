package com.learning.board_0326.vos;

public class UserVo {

    private final String email;
    private final String accessToken;

    public UserVo(String email, String accessToken) {
        this.email = email;
        this.accessToken = accessToken;
    }

    public String getEmail() {
        return email;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
