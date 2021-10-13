package com.destiny.cianjursipp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Evadir {
    @SerializedName("score")
    @Expose
    public String score;

    @SerializedName("created_at")
    @Expose
    public String created_at;

    @SerializedName("id_evadir")
    @Expose
    public String id_evadir;

    @SerializedName("skor")
    @Expose
    public String skor;

    @SerializedName("id_kategori")
    @Expose
    public String id_kategori;

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
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
}

