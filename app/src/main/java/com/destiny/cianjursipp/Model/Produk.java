package com.destiny.cianjursipp.Model;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Produk {
    @SerializedName("id_produk")
    @Expose
    public String id_produk;

    @SerializedName("id_sekolah")
    @Expose
    public String id_sekolah;

    @SerializedName("id_user")
    @Expose
    public String id_user;

    @SerializedName("nama_produk")
    @Expose
    public String nama_produk;

    @SerializedName("deskripsi_produk")
    @Expose
    public String deskripsi_produk;

    @SerializedName("harga_produk")
    @Expose
    public String harga_produk;

    @SerializedName("no_telp_pengurus")
    @Expose
    public String no_telp_pengurus;

    @SerializedName("cover_produk")
    @Expose
    public String cover_produk;

    @SerializedName("status_produk")
    @Expose
    public String status_produk;

    @SerializedName("tgl_upload_produk")
    @Expose
    public String tgl_upload_produk;

    @SerializedName("nama_sekolah")
    @Expose
    public String nama_sekolah;


    @SerializedName("photo")
    @Nullable
    List<Photo> photo;

    public String getId_produk() {
        return id_produk;
    }

    public void setId_produk(String id_produk) {
        this.id_produk = id_produk;
    }

    public String getId_sekolah() {
        return id_sekolah;
    }

    public void setId_sekolah(String id_sekolah) {
        this.id_sekolah = id_sekolah;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getNama_produk() {
        return nama_produk;
    }

    public void setNama_produk(String nama_produk) {
        this.nama_produk = nama_produk;
    }

    public String getDeskripsi_produk() {
        return deskripsi_produk;
    }

    public void setDeskripsi_produk(String deskripsi_produk) {
        this.deskripsi_produk = deskripsi_produk;
    }

    public String getHarga_produk() {
        return harga_produk;
    }

    public void setHarga_produk(String harga_produk) {
        this.harga_produk = harga_produk;
    }

    public String getNo_telp_pengurus() {
        return no_telp_pengurus;
    }

    public void setNo_telp_pengurus(String no_telp_pengurus) {
        this.no_telp_pengurus = no_telp_pengurus;
    }

    public String getCover_produk() {
        return cover_produk;
    }

    public void setCover_produk(String cover_produk) {
        this.cover_produk = cover_produk;
    }

    public String getStatus_produk() {
        return status_produk;
    }

    public void setStatus_produk(String status_produk) {
        this.status_produk = status_produk;
    }

    public String getTgl_upload_produk() {
        return tgl_upload_produk;
    }

    public void setTgl_upload_produk(String tgl_upload_produk) {
        this.tgl_upload_produk = tgl_upload_produk;
    }

    @Nullable
    public List<Photo> getPhoto() {
        return photo;
    }

    public void setPhoto(@Nullable List<Photo> photo) {
        this.photo = photo;
    }

    public String getNama_sekolah() {
        return nama_sekolah;
    }

    public void setNama_sekolah(String nama_sekolah) {
        this.nama_sekolah = nama_sekolah;
    }
}

