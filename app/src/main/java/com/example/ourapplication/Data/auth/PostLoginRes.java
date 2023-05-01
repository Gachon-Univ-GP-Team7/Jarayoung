package com.example.ourapplication.Data.auth;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class PostLoginRes {
    @SerializedName("loginSuccess")
    private boolean loginSuccess;
    @SerializedName("userIdx")
    private int userIdx;

    public boolean isLoginSuccess() {
        return loginSuccess;
    }

    public int getUserIdx() {
        return userIdx;
    }

    public PostLoginRes(boolean loginSuccess, int userIdx) {
        this.loginSuccess = loginSuccess;
        this.userIdx = userIdx;
    }
}
