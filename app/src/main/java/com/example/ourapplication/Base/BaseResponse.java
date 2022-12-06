package com.example.ourapplication.Base;

import com.google.gson.annotations.SerializedName;

public class BaseResponse<T> {
    @SerializedName(value = "isSuccess") boolean isSuccess;
    @SerializedName(value = "returnCode") int code;
    @SerializedName(value = "returnMsg") String message;
    @SerializedName(value = "result") T result;

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public int getCode(){
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getResult(){
        return result;
    }
}
