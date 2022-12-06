package com.example.ourapplication.Utils;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkModule {
    private static final String BASE_URL = "https://jarayoung.shop/api/"; // 서버 주소
    private static NetworkModule instance = null;
    private static RetrofitInterface retrofitAPI;

    private NetworkModule() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        retrofitAPI = retrofit.create(RetrofitInterface.class);
    }

    public static NetworkModule getInstance() {
        if (instance == null) {
            instance = new NetworkModule();
        }

        return instance;
    }

    public static RetrofitInterface getRetrofitAPI() {
        return retrofitAPI;
    }
}
