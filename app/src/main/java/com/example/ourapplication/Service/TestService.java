package com.example.ourapplication.Service;

import android.util.Log;

import com.example.ourapplication.Base.BaseResponse;
import com.example.ourapplication.Data.Test.GetTestGraphRes;
import com.example.ourapplication.Data.Test.GetTestListRes;
import com.example.ourapplication.Data.Test.GetTestViewRes;
import com.example.ourapplication.Data.Test.GetVideoDetailRes;
import com.example.ourapplication.Data.Test.GetVoiceDetailRes;
import com.example.ourapplication.Data.Test.PostTestRes;
import com.example.ourapplication.Utils.NetworkModule;
import com.example.ourapplication.Utils.RetrofitInterface;

import java.io.IOException;
import java.util.Objects;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public class TestService {

    private RetrofitInterface retrofitInterface;

    private final NetworkModule networkModule = NetworkModule.getInstance();

    private GetTestListRes getTestListRes;
    private GetTestGraphRes getTestGraphRes;
    private GetVoiceDetailRes getVoiceDetailRes;
    private GetVideoDetailRes getVideoDetailRes;
    private GetTestViewRes getTestViewRes;

    private PostTestRes postTestRes;

    //Test List Api
    public GetTestListRes callTestListApi(int userIdx) throws IOException {
        if(networkModule != null){
            retrofitInterface = NetworkModule.getRetrofitAPI();

            getTestListRes = Objects.requireNonNull(retrofitInterface.getTestListApi(userIdx).execute().body()).getResult();
        }

        return getTestListRes;
    }

    //Test Graph Api
    public GetTestGraphRes callTestGraphApi(int userIdx) throws IOException {
        if(networkModule != null){
            retrofitInterface = NetworkModule.getRetrofitAPI();

            BaseResponse<GetTestGraphRes> test = retrofitInterface.getTestGraphApi(userIdx).execute().body();

            Log.d("Graph Api", test.toString());

            getTestGraphRes = Objects.requireNonNull(test).getResult();
        }

        return getTestGraphRes;
    }

    //Test Detail - Voice
    public GetVoiceDetailRes callVoiceDetailApi(int voiceTestIdx) throws IOException {
        if(networkModule != null){
            retrofitInterface = NetworkModule.getRetrofitAPI();

            getVoiceDetailRes = Objects.requireNonNull(retrofitInterface.getVoiceTestDetailApi(voiceTestIdx).execute().body()).getResult();
        }

        return getVoiceDetailRes;
    }

    //Test Detail - Voice
    public GetVideoDetailRes callVideoDetailApi(int videoTestIdx) throws IOException {
        if(networkModule != null){
            retrofitInterface = NetworkModule.getRetrofitAPI();

            getVideoDetailRes = Objects.requireNonNull(retrofitInterface.getVideoTestDetailApi(videoTestIdx).execute().body()).getResult();
        }

        return getVideoDetailRes;
    }
    //Test View Api
    public GetTestViewRes callTestViewApi(int userIdx, int testMode) throws IOException {
        if(networkModule != null){
            retrofitInterface = NetworkModule.getRetrofitAPI();

            getTestViewRes = Objects.requireNonNull(retrofitInterface.getTestViewApi(userIdx, testMode).execute().body()).getResult();
        }

        return getTestViewRes;
    }

    //Test Api
    public PostTestRes callTestApi(int userIdx, int testMode) throws IOException {
        if(networkModule != null){
            retrofitInterface = NetworkModule.getRetrofitAPI();

            postTestRes = Objects.requireNonNull(retrofitInterface.postTestApi(userIdx, testMode).execute().body()).getResult();
        }

        return postTestRes;
    }
}
