package com.destiny.cianjursipp.Model;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class Surat {
    @SerializedName("nama")
    @Nullable
    public String nama;

    @SerializedName("link")
    @Nullable
    public String link;

    @SerializedName("no")
    @Nullable
    public String no;

    @Nullable
    public String getNama() {
        return nama;
    }

    public void setNama(@Nullable String nama) {
        this.nama = nama;
    }

    @Nullable
    public String getLink() {
        return link;
    }

    public void setLink(@Nullable String link) {
        this.link = link;
    }

    @Nullable
    public String getNo() {
        return no;
    }

    public void setNo(@Nullable String no) {
        this.no = no;
    }
}

