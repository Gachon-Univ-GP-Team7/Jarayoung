package com.example.ourapplication.Data.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostLoginReq {
    private String email;
    private String password;

    public PostLoginReq(String strEmail, String strPwd) {
        this.email = strEmail;
        this.password = strPwd;
    }
}
