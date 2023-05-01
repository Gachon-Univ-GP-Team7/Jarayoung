package com.example.ourapplication.Data.auth;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostApplyRes {

    @SerializedName("applySuccess")
    private boolean applySuccess;

    public boolean isApplySuccess() {
        return applySuccess;
    }
}
