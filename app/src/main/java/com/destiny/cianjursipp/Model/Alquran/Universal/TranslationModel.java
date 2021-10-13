package com.destiny.cianjursipp.Model.Alquran.Universal;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class TranslationModel {
    @SerializedName("id")
    @Nullable
    public IdModel id;

    @Nullable
    public IdModel getId() {
        return id;
    }

    public void setId(@Nullable IdModel id) {
        this.id = id;
    }
}
