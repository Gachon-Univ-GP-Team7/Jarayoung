package com.example.ourapplication.Data.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class PostApplyReq {
    private String userName;
    private String infantName;
    private String infantBirthday;
    private String password;
    private String email;

    @Builder
    public PostApplyReq(String userName, String infantName, String infantBirthday, String password, String email) {
        this.userName = userName;
        this.infantName = infantName;
        this.infantBirthday = infantBirthday;
        this.password = password;
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public String getInfantName() {
        return infantName;
    }

    public String getInfantBirthday() {
        return infantBirthday;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
