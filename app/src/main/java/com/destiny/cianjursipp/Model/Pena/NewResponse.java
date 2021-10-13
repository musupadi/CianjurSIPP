package com.destiny.cianjursipp.Model.Pena;

import androidx.annotation.Nullable;

import com.destiny.cianjursipp.Model.DataModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewResponse {
    @SerializedName("status")
    @Expose
    @Nullable
    public String status;

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
    public DataModel data;


    @Nullable
    public String getStatus() {
        return status;
    }

    public void setStatus(@Nullable String status) {
        this.status = status;
    }

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
    public DataModel getData() {
        return data;
    }

    public void setData(@Nullable DataModel data) {
        this.data = data;
    }
}

