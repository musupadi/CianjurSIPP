package com.destiny.cianjursipp.Model.Alquran.Universal;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class IdModel {
    @SerializedName("name")
    @Nullable
    public String name;

    @SerializedName("text")
    @Nullable
    TextModel text;

    @Nullable
    public String getName() {
        return name;
    }

    public void setName(@Nullable String name) {
        this.name = name;
    }

    @Nullable
    public TextModel getText() {
        return text;
    }

    public void setText(@Nullable TextModel text) {
        this.text = text;
    }
}

