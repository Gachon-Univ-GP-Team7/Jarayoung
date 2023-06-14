package com.example.ourapplication.Data.user;

import lombok.Builder;

public class GetMainViewRes {
    private int userIdx;
    private String userName;
    private String babyName;
    private String babyBirthday;
    private String latestTestDay;
    private float latestTestResult;

    @Builder
    public GetMainViewRes(int userIdx, String userName, String babyName, String babyBirthday, String latestTestDay, float latestTestResult) {
        this.userIdx = userIdx;
        this.userName = userName;
        this.babyName = babyName;
        this.babyBirthday = babyBirthday;
        this.latestTestDay = latestTestDay;
        this.latestTestResult = latestTestResult;
    }

    public int getUserIdx() {
        return userIdx;
    }


    public String getBabyName() {
        return babyName;
    }

    public String getBabyBirthday() {
        return babyBirthday;
    }

    public String getLatestTestDay() {
        return latestTestDay;
    }

    public float getLatestTestResult() {
        return latestTestResult;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public String toString() {
        return "GetMainViewRes{" +
                "userIdx=" + userIdx +
                ", userName='" + userName + '\'' +
                ", babyName='" + babyName + '\'' +
                ", babyBirthday='" + babyBirthday + '\'' +
                ", latestTestDay='" + latestTestDay + '\'' +
                ", latestTestResult=" + latestTestResult +
                '}';
    }
}
