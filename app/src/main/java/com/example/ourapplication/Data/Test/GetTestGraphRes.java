package com.example.ourapplication.Data.Test;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetTestGraphRes {
    @SerializedName("voiceGraphList")
    private List<TestGraph> voiceGraphList;
    @SerializedName("videoGraphList")
    private List<TestGraph> videoGraphList;

    public GetTestGraphRes(List<TestGraph> voiceGraphList, List<TestGraph> videoGraphList) {
        this.voiceGraphList = voiceGraphList;
        this.videoGraphList = videoGraphList;
    }

    public List<TestGraph> getVoiceGraphList() {
        return voiceGraphList;
    }

    public List<TestGraph> getVideoGraphList() {
        return videoGraphList;
    }
}
