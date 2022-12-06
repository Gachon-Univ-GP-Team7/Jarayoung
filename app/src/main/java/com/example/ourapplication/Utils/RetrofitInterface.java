package com.example.ourapplication.Utils;

import com.example.ourapplication.Base.BaseResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitInterface {

    @GET("example/")
    Call<BaseResponse<String>> exampleAPI();

}
