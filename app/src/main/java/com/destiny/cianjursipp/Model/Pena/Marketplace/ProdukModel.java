package com.destiny.cianjursipp.Model.Pena.Marketplace;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProdukModel {
    @SerializedName("id_produk_siswa")
    @Expose
    @Nullable
    public String id_produk_siswa;

    @SerializedName("id_sekolah")
    @Expose
    @Nullable
    public String id_sekolah;

    @SerializedName("id_user")
    @Expose
    @Nullable
    public String id_user;

    @SerializedName("id_siswa")
    @Expose
    @Nullable
    public String id_siswa;

    @SerializedName("nama_produk_siswa")
    @Expose
    @Nullable
    public String nama_produk_siswa;

    @SerializedName("deskripsi_produk_siswa")
    @Expose
    @Nullable
    public String deskripsi_produk_siswa;

    @SerializedName("harga_produk_siswa")
    @Expose
    @Nullable
    public String harga_produk_siswa;

    @SerializedName("no_telp_pengurus")
    @Expose
    @Nullable
    public String no_telp_pengurus;

    @SerializedName("cover_produk_siswa")
    @Expose
    @Nullable
    public String cover_produk_siswa;

    @SerializedName("status_produk_siswa")
    @Expose
    @Nullable
    public String status_produk_siswa;

    @SerializedName("tgl_upload_produk_siswa")
    @Expose
    @Nullable
    public String tgl_upload_produk_siswa;

    @SerializedName("nama_sekolah")
    @Expose
    @Nullable
    public String nama_sekolah;

    @SerializedName("nama_pengurus")
    @Expose
    @Nullable
    public String nama_pengurus;

    @SerializedName("photo")
    @Nullable
    List<FotoProduk> photo;

    @Nullable
    public String getId_produk_siswa() {
        return id_produk_siswa;
    }

    public void setId_produk_siswa(@Nullable String id_produk_siswa) {
        this.id_produk_siswa = id_produk_siswa;
    }

    @Nullable
    public String getId_sekolah() {
        return id_sekolah;
    }

    public void setId_sekolah(@Nullable String id_sekolah) {
        this.id_sekolah = id_sekolah;
    }

    @Nullable
    public String getId_user() {
        return id_user;
    }

    public void setId_user(@Nullable String id_user) {
        this.id_user = id_user;
    }

    @Nullable
    public String getId_siswa() {
        return id_siswa;
    }

    public void setId_siswa(@Nullable String id_siswa) {
        this.id_siswa = id_siswa;
    }

    @Nullable
    public String getNama_produk_siswa() {
        return nama_produk_siswa;
    }

    public void setNama_produk_siswa(@Nullable String nama_produk_siswa) {
        this.nama_produk_siswa = nama_produk_siswa;
    }

    @Nullable
    public String getDeskripsi_produk_siswa() {
        return deskripsi_produk_siswa;
    }

    public void setDeskripsi_produk_siswa(@Nullable String deskripsi_produk_siswa) {
        this.deskripsi_produk_siswa = deskripsi_produk_siswa;
    }

    @Nullable
    public String getHarga_produk_siswa() {
        return harga_produk_siswa;
    }

    public void setHarga_produk_siswa(@Nullable String harga_produk_siswa) {
        this.harga_produk_siswa = harga_produk_siswa;
    }

    @Nullable
    public String getNo_telp_pengurus() {
        return no_telp_pengurus;
    }

    public void setNo_telp_pengurus(@Nullable String no_telp_pengurus) {
        this.no_telp_pengurus = no_telp_pengurus;
    }

    @Nullable
    public String getCover_produk_siswa() {
        return cover_produk_siswa;
    }

    public void setCover_produk_siswa(@Nullable String cover_produk_siswa) {
        this.cover_produk_siswa = cover_produk_siswa;
    }

    @Nullable
    public String getStatus_produk_siswa() {
        return status_produk_siswa;
    }

    public void setStatus_produk_siswa(@Nullable String status_produk_siswa) {
        this.status_produk_siswa = status_produk_siswa;
    }

    @Nullable
    public String getTgl_upload_produk_siswa() {
        return tgl_upload_produk_siswa;
    }

    public void setTgl_upload_produk_siswa(@Nullable String tgl_upload_produk_siswa) {
        this.tgl_upload_produk_siswa = tgl_upload_produk_siswa;
    }

    @Nullable
    public String getNama_sekolah() {
        return nama_sekolah;
    }

    public void setNama_sekolah(@Nullable String nama_sekolah) {
        this.nama_sekolah = nama_sekolah;
    }

    @Nullable
    public List<FotoProduk> getPhoto() {
        return photo;
    }

    public void setPhoto(@Nullable List<FotoProduk> photo) {
        this.photo = photo;
    }

    @Nullable
    public String getNama_pengurus() {
        return nama_pengurus;
    }

    public void setNama_pengurus(@Nullable String nama_pengurus) {
        this.nama_pengurus = nama_pengurus;
    }
}
