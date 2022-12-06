package com.example.ourapplication.Service;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.ourapplication.Base.BaseResponse;
import com.example.ourapplication.Utils.NetworkModule;
import com.example.ourapplication.Utils.RetrofitInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExampleService {
    private RetrofitInterface retrofitInterface;

    public String callTestApi(){
        NetworkModule networkModule = NetworkModule.getInstance();
        final String[] result = {"dump"};

        if(networkModule != null) {
            retrofitInterface = NetworkModule.getRetrofitAPI();
            retrofitInterface.exampleAPI().enqueue(new Callback<BaseResponse<String>>(){
                @Override
                public void onResponse(@NonNull Call<BaseResponse<String>> call, @NonNull Response<BaseResponse<String>> response) {
                    BaseResponse<String> data = response.body();

                    Log.d("API Test: ", data.getResult());

                    result[0] = data.getResult();
                }

                @Override
                public void onFailure(@NonNull Call<BaseResponse<String>> call, @NonNull Throwable t) {
                    Log.d("Fail on API Test", t.getMessage());
                }
            });
        }

        return result[0];
    }
}
