package com.example.ourapplication.Utils;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class SharedPreferenceManager implements Serializable {
    private int userIdx;

    public SharedPreferenceManager() {
    }

    public SharedPreferenceManager(int userIdx) {
        this.userIdx = userIdx;
    }

    public int getUserIdx() {
        return userIdx;
    }

    public void setUserIdx(int userIdx) {
        this.userIdx = userIdx;
    }
}
