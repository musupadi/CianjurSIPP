package com.destiny.cianjursipp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("id_evadir")
    @Expose
    public String id_evadir;

    @SerializedName("skor")
    @Expose
    public String skor;

    @SerializedName("id_kategori")
    @Expose
    public String id_kategori;

    public Data(String id_evadir, String skor, String id_kategori){
        this.id_evadir = id_evadir;
        this.skor = skor;
        this.id_kategori = id_kategori;
    }

    public String getId_evadir() {
        return id_evadir;
    }

    public void setId_evadir(String id_evadir) {
        this.id_evadir = id_evadir;
    }

    public String getSkor() {
        return skor;
    }

    public void setSkor(String skor) {
        this.skor = skor;
    }

    public String getId_kategori() {
        return id_kategori;
    }

    public void setId_kategori(String id_kategori) {
        this.id_kategori = id_kategori;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id_evadir = "+id_evadir+", skor = "+skor+", id_kategori = "+id_kategori+"]";
    }
}

