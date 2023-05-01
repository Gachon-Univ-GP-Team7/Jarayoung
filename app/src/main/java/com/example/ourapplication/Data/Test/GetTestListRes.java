package com.example.ourapplication.Data.Test;

import java.util.List;

public class GetTestListRes {
    private List<TestList> voiceTestList;
    private List<TestList> videoTestList;

    public GetTestListRes(List<TestList> voiceTestList, List<TestList> videoTestList) {
        this.voiceTestList = voiceTestList;
        this.videoTestList = videoTestList;
    }

    public List<TestList> getVoiceTestList() {
        return voiceTestList;
    }

    public List<TestList> getVideoTestList() {
        return videoTestList;
    }
}
