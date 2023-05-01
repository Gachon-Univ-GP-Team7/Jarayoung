package com.example.ourapplication.Data.user;

import lombok.Builder;

public class GetMainViewRes {
    private int userIdx;
    private String userName;
    private String email;
    private String babyName;
    private String babyBirthday;

    @Builder
    public GetMainViewRes(int userIdx, String userName, String email, String babyName, String babyBirthday) {
        this.userIdx = userIdx;
        this.userName = userName;
        this.email = email;
        this.babyName = babyName;
        this.babyBirthday = babyBirthday;
    }

    public int getUserIdx() {
        return userIdx;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getBabyName() {
        return babyName;
    }

    public String getBabyBirthday() {
        return babyBirthday;
    }

    @Override
    public String toString() {
        return "GetMainViewRes{" +
                "userIdx=" + userIdx +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", babyName='" + babyName + '\'' +
                ", babyBirthday='" + babyBirthday + '\'' +
                '}';
    }
}
