package com.destiny.cianjursipp.Model.Eskul;

import androidx.annotation.Nullable;

import com.destiny.cianjursipp.Model.DataModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Self {
    //Eskul Self
    @SerializedName("id_ekskul_siswa")
    @Expose
    public String id_ekskul_siswa;

    @SerializedName("id_siswa")
    @Expose
    public String id_siswa;

    @SerializedName("id_ekskul")
    @Expose
    public String id_ekskul;

    @SerializedName("bergabung_tgl")
    @Expose
    public String bergabung_tgl;

    @SerializedName("created_at_ekskul_siswa")
    @Expose
    public String created_at_ekskul_siswa;

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

    @SerializedName("photo")
    @Nullable
    List<DataModel> photo;


}

