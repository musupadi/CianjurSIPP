package com.destiny.cianjursipp.Model.Eskul;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Anggota {
    @SerializedName("nama_kelas")
    @Expose
    public String nama_kelas;

    @SerializedName("nama_siswa")
    @Expose
    @Nullable
    public String nama_siswa;

    public String getNama_kelas() {
        return nama_kelas;
    }

    public void setNama_kelas(String nama_kelas) {
        this.nama_kelas = nama_kelas;
    }

    @Nullable
    public String getNama_siswa() {
        return nama_siswa;
    }

    public void setNama_siswa(@Nullable String nama_siswa) {
        this.nama_siswa = nama_siswa;
    }
}
