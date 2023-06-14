package com.example.ourapplication.Utils;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkModule {
    private static final String BASE_URL = "https://jarayoung.shop/api/"; // 서버 주소
//    private static final String BASE_URL = "http://10.0.2.2:8000/api/"; // 서버 주소
    private static NetworkModule instance = null;
    private static RetrofitInterface retrofitAPI;

    private NetworkModule() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(client).build();

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
