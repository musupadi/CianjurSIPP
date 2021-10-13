package com.destiny.cianjursipp.Model.Pena.Marketplace;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FotoProduk {
    @SerializedName("id_produk_foto_siswa")
    @Expose
    @Nullable
    public String id_produk_foto_siswa;

    @SerializedName("id_produk_siswa")
    @Expose
    @Nullable
    public String id_produk_siswa;

    @SerializedName("produk_foto_siswa")
    @Expose
    @Nullable
    public String produk_foto_siswa;

    @SerializedName("deskripsi_foto_produk_siswa")
    @Expose
    @Nullable
    public String deskripsi_foto_produk_siswa;

    @Nullable
    public String getId_produk_foto_siswa() {
        return id_produk_foto_siswa;
    }

    public void setId_produk_foto_siswa(@Nullable String id_produk_foto_siswa) {
        this.id_produk_foto_siswa = id_produk_foto_siswa;
    }

    @Nullable
    public String getId_produk_siswa() {
        return id_produk_siswa;
    }

    public void setId_produk_siswa(@Nullable String id_produk_siswa) {
        this.id_produk_siswa = id_produk_siswa;
    }

    @Nullable
    public String getProduk_foto_siswa() {
        return produk_foto_siswa;
    }

    public void setProduk_foto_siswa(@Nullable String produk_foto_siswa) {
        this.produk_foto_siswa = produk_foto_siswa;
    }

    @Nullable
    public String getDeskripsi_foto_produk_siswa() {
        return deskripsi_foto_produk_siswa;
    }

    public void setDeskripsi_foto_produk_siswa(@Nullable String deskripsi_foto_produk_siswa) {
        this.deskripsi_foto_produk_siswa = deskripsi_foto_produk_siswa;
    }
}
