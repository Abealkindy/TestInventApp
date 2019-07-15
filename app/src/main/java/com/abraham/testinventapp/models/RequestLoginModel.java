package com.abraham.testinventapp.models;

import lombok.Data;

@Data
public class RequestLoginModel {
    private String Username, Password, Token, OsType, Role;

    public RequestLoginModel(String username, String password, String token, String osType, String role) {
        Username = username;
        Password = password;
        Token = token;
        OsType = osType;
        Role = role;
    }
}
