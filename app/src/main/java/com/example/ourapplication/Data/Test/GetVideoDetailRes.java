package com.example.ourapplication.Data.Test;

public class GetVideoDetailRes {
    private int testIdx;
    private String testDate;
    private float overallScore;
    private float voiceLabel;

    public GetVideoDetailRes(int testIdx, String testDate, float overallScore, float voiceLabel) {
        this.testIdx = testIdx;
        this.testDate = testDate;
        this.overallScore = overallScore;
        this.voiceLabel = voiceLabel;
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

    public float getVoiceLabel() {
        return voiceLabel;
    }
}
