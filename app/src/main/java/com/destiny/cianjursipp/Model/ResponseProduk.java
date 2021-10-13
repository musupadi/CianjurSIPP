package com.destiny.cianjursipp.Model;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseProduk {
    @SerializedName("statusCode")
    @Expose
    @Nullable
    public String statusCode;

    @SerializedName("statusMessage")
    @Expose
    @Nullable
    public String statusMessage;

    @SerializedName("data")
    @Nullable
    List<Produk> data;

    @Nullable
    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(@Nullable String statusCode) {
        this.statusCode = statusCode;
    }

    @Nullable
    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(@Nullable String statusMessage) {
        this.statusMessage = statusMessage;
    }

    @Nullable
    public List<Produk> getData() {
        return data;
    }

    public void setData(@Nullable List<Produk> data) {
        this.data = data;
    }
}

