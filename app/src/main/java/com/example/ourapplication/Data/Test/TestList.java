package com.example.ourapplication.Data.Test;

public class TestList {
    private int testIdx;
    private String testDate;

    public TestList(int testIdx, String testDate) {
        this.testIdx = testIdx;
        this.testDate = testDate;
    }

    public int getTestIdx() {
        return testIdx;
    }

    public String getTestDate() {
        return testDate;
    }
}
