package com.destiny.cianjursipp.Model.Eskul;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Eskul {
    @SerializedName("id_ekskul")
    @Expose
    public String id_ekskul;

    @SerializedName("id_sekolah")
    @Expose
    public String id_sekolah;

    @SerializedName("nama_ekskul")
    @Expose
    public String nama_ekskul;

    @SerializedName("cover_ekskul")
    @Expose
    public String cover_ekskul;

    @SerializedName("pembimbing_ekskul")
    @Expose
    public String pembimbing_ekskul;

    @SerializedName("deskripsi_ekskul")
    @Expose
    public String deskripsi_ekskul;

    @SerializedName("created_at_ekskul")
    @Expose
    public String created_at_ekskul;

    @SerializedName("link_foto")
    @Expose
    public ArrayList<String> link_foto;

    @SerializedName("anggota")
    @Nullable
    List<Anggota> anggota;



    public String getId_ekskul() {
        return id_ekskul;
    }

    public void setId_ekskul(String id_ekskul) {
        this.id_ekskul = id_ekskul;
    }

    public String getId_sekolah() {
        return id_sekolah;
    }

    public void setId_sekolah(String id_sekolah) {
        this.id_sekolah = id_sekolah;
    }

    public String getNama_ekskul() {
        return nama_ekskul;
    }

    public void setNama_ekskul(String nama_ekskul) {
        this.nama_ekskul = nama_ekskul;
    }

    public String getCover_ekskul() {
        return cover_ekskul;
    }

    public void setCover_ekskul(String cover_ekskul) {
        this.cover_ekskul = cover_ekskul;
    }

    public String getPembimbing_ekskul() {
        return pembimbing_ekskul;
    }

    public void setPembimbing_ekskul(String pembimbing_ekskul) {
        this.pembimbing_ekskul = pembimbing_ekskul;
    }

    public String getDeskripsi_ekskul() {
        return deskripsi_ekskul;
    }

    public void setDeskripsi_ekskul(String deskripsi_ekskul) {
        this.deskripsi_ekskul = deskripsi_ekskul;
    }

    public String getCreated_at_ekskul() {
        return created_at_ekskul;
    }

    public void setCreated_at_ekskul(String created_at_ekskul) {
        this.created_at_ekskul = created_at_ekskul;
    }

    public ArrayList<String> getLink_foto() {
        return link_foto;
    }

    public void setLink_foto(ArrayList<String> link_foto) {
        this.link_foto = link_foto;
    }

    @Nullable
    public List<Anggota> getAnggota() {
        return anggota;
    }

    public void setAnggota(@Nullable List<Anggota> anggota) {
        this.anggota = anggota;
    }
}

