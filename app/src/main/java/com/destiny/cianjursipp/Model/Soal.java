package com.destiny.cianjursipp.Model;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Soal {
    @SerializedName("soal")
    @Nullable
    List<DataModel> soal;

    @Nullable
    public List<DataModel> getSoal() {
        return soal;
    }

    public void setSoal(@Nullable List<DataModel> soal) {
        this.soal = soal;
    }
}
