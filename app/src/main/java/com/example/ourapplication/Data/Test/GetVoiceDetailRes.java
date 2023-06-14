package com.example.ourapplication.Data.Test;

public class GetVoiceDetailRes {
    private int testIdx;
    private String testDate;
    private float overallScore;
    private float videoLabel;

    public GetVoiceDetailRes(int testIdx, String testDate, float overallScore, float videoLabel) {
        this.testIdx = testIdx;
        this.testDate = testDate;
        this.overallScore = overallScore;
        this.videoLabel = videoLabel;
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

    public float getVideoLabel() {
        return videoLabel;
    }
}
