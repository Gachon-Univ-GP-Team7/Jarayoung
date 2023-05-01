package com.example.ourapplication.Utils;

import com.example.ourapplication.Base.BaseResponse;
import com.example.ourapplication.Data.Test.GetTestGraphRes;
import com.example.ourapplication.Data.Test.GetTestListRes;
import com.example.ourapplication.Data.Test.GetTestViewRes;
import com.example.ourapplication.Data.Test.GetVideoDetailRes;
import com.example.ourapplication.Data.Test.GetVoiceDetailRes;
import com.example.ourapplication.Data.auth.PostApplyReq;
import com.example.ourapplication.Data.auth.PostApplyRes;
import com.example.ourapplication.Data.auth.PostLoginReq;
import com.example.ourapplication.Data.auth.PostLoginRes;
import com.example.ourapplication.Data.user.GetMainViewRes;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitInterface {

    @GET("example/")
    Call<BaseResponse<String>> exampleAPI();

    @POST("auth/login")
    Call<BaseResponse<PostLoginRes>> loginAPI(@Body PostLoginReq postLoginReq);

    @POST("auth/apply")
    Call<BaseResponse<PostApplyRes>> applyAPI(@Body PostApplyReq postApplyReq);

    @GET("user/info/{userIdx}")
    Call<BaseResponse<GetMainViewRes>> getMainView(@Path("userIdx") int userIdx);

    //Test List Api
    @GET("test/testList/{userIdx}")
    Call<BaseResponse<GetTestListRes>> getTestListApi(@Path("userIdx") int userIdx);

    //Test Graph Api
    @GET("test/testGraph/{userIdx}")
    Call<BaseResponse<GetTestGraphRes>> getTestGraphApi(@Path("userIdx") int userIdx);

    //Test Detail - Voice
    @GET("test/voiceTestRecord/{voiceTestIdx}")
    Call<BaseResponse<GetVoiceDetailRes>> getVoiceTestDetailApi(@Path("voiceTestIdx") int voiceTestIdx);

    //Test Detail - Voice
    @GET("test/videoTestRecord/{videoTestIdx}")
    Call<BaseResponse<GetVideoDetailRes>> getVideoTestDetailApi(@Path("videoTestIdx") int videoTestIdx);

    //Test View Api
    @GET("test/testView")
    Call<BaseResponse<GetTestViewRes>> getTestViewApi(@Query("userIdx") int userIdx, @Query("testMode") int testMode);

}
