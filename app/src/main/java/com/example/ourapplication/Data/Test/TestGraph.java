package com.example.ourapplication.Data.Test;

public class TestGraph {
    private int testIdx;
    private String testDate;
    private float overallScore;

    public TestGraph(int testIdx, String testDate, float overallScore) {
        this.testIdx = testIdx;
        this.testDate = testDate;
        this.overallScore = overallScore;
    }

    public int getTestIdx() {
        return testIdx;
    }

    public String getTestDate() {
        return testDate;
    }

    public float getOverallScore() {
        return overallScore;
    }
}
