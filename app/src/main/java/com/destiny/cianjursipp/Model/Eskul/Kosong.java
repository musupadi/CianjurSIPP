package com.destiny.cianjursipp.Model.Eskul;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Kosong {
    @SerializedName("")
    @Nullable
    Anggota anggota;

    @Nullable
    public Anggota getAnggota() {
        return anggota;
    }

    public void setAnggota(@Nullable Anggota anggota) {
        this.anggota = anggota;
    }
}
