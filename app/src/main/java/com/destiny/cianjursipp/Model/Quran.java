package com.destiny.cianjursipp.Model;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Quran {
    @SerializedName("data")
    @Nullable
    List<Surat> data;

    @Nullable
    public List<Surat> getData() {
        return data;
    }

    public void setData(@Nullable List<Surat> data) {
        this.data = data;
    }
}

