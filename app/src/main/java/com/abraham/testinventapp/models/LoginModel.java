package com.abraham.testinventapp.models;

import java.util.List;

import lombok.Data;

@Data
public class LoginModel {
    private List<UserLoginData> hasil;
    private boolean response;

    @Data
    public class UserLoginData {
        private int user_id;
        private String username;
        private String user_password;
        private String user_role;
    }
}
