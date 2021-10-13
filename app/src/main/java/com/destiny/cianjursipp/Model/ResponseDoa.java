package com.destiny.cianjursipp.Model;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseDoa {
    @SerializedName("status")
    @Expose
    @Nullable
    public String status;

    @SerializedName("data")
    @Nullable
    Quran data;

    @Nullable
    public String getStatus() {
        return status;
    }

    public void setStatus(@Nullable String status) {
        this.status = status;
    }

    @Nullable
    public Quran getData() {
        return data;
    }

    public void setData(@Nullable Quran data) {
        this.data = data;
    }
}

