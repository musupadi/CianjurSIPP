package com.destiny.cianjursipp.Model.Alquran.Surah;

import androidx.annotation.Nullable;

import com.destiny.cianjursipp.Model.Alquran.Universal.QuranModel;
import com.google.gson.annotations.SerializedName;

public class Alfatihah {
    @SerializedName("1")
    @Nullable
    QuranModel one;

    @Nullable
    public QuranModel getOne() {
        return one;
    }

    public void setOne(@Nullable QuranModel one) {
        this.one = one;
    }
}
