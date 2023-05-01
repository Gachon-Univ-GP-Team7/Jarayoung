package com.example.ourapplication.Service;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.ourapplication.Base.BaseResponse;
import com.example.ourapplication.Data.auth.PostApplyReq;
import com.example.ourapplication.Data.auth.PostApplyRes;
import com.example.ourapplication.Data.auth.PostLoginReq;
import com.example.ourapplication.Data.auth.PostLoginRes;
import com.example.ourapplication.Utils.NetworkModule;
import com.example.ourapplication.Utils.RetrofitInterface;

import java.io.IOException;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthService {
    private RetrofitInterface retrofitInterface;
    private PostLoginRes result;
    private PostApplyRes postApplyRes;
    private final NetworkModule networkModule = NetworkModule.getInstance();

    public PostLoginRes callLoginApi(PostLoginReq dto) throws IOException {
        if(networkModule != null) {

            retrofitInterface = NetworkModule.getRetrofitAPI();

            result = Objects.requireNonNull(retrofitInterface.loginAPI(dto).execute().body()).getResult();
        }

        return result;
    }

    public PostApplyRes callApplyApi(PostApplyReq dto) throws IOException {
        if(networkModule != null) {

            retrofitInterface = NetworkModule.getRetrofitAPI();
//            retrofitInterface.applyAPI(dto).enqueue(new Callback<BaseResponse<PostApplyRes>>(){
//                @Override
//                public void onResponse(@NonNull Call<BaseResponse<PostApplyRes>> call, @NonNull Response<BaseResponse<PostApplyRes>> response) {
//                    BaseResponse<PostApplyRes> data = response.body();
//
//                    Log.d("Apply call", Boolean.toString(data.getResult().isApplySuccess()));
//                    postApplyRes = data.getResult();
//                }
//
//                @Override
//                public void onFailure(@NonNull Call<BaseResponse<PostApplyRes>> call, @NonNull Throwable t) {
//                    Log.d("Fail on Apply API", t.getMessage());
//                }
//            });

            postApplyRes = Objects.requireNonNull(retrofitInterface.applyAPI(dto).execute().body()).getResult();
        }
        return postApplyRes;
    }
}
