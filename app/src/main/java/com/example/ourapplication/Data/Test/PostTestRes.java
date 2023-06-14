package com.example.ourapplication.Data.Test;

public class PostTestRes {
    private int testIdx;
    private String testDate;
    private float overallScore;
    private float label;

    public PostTestRes(int testIdx, String testDate, float overallScore, float label) {
        this.testIdx = testIdx;
        this.testDate = testDate;
        this.overallScore = overallScore;
        this.label = label;
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

    public float getLabel() {
        return label;
    }
}
