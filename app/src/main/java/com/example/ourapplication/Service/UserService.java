package com.example.ourapplication.Service;

import android.util.Log;

import com.example.ourapplication.Data.user.GetMainViewRes;
import com.example.ourapplication.Utils.NetworkModule;
import com.example.ourapplication.Utils.RetrofitInterface;

import java.io.IOException;
import java.util.Objects;

public class UserService {
    private RetrofitInterface retrofitInterface;

    private final NetworkModule networkModule = NetworkModule.getInstance();

    private GetMainViewRes getMainViewRes;

    public GetMainViewRes callMainViewApi(int userIdx) throws IOException {
        if(networkModule != null){
            retrofitInterface = NetworkModule.getRetrofitAPI();

            getMainViewRes = Objects.requireNonNull(retrofitInterface.getMainView(userIdx).execute().body()).getResult();

            Log.d("Main view", getMainViewRes.toString());
        }

        return getMainViewRes;
    }
}
