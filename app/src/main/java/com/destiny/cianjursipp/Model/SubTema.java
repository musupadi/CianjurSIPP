package com.destiny.cianjursipp.Model;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SubTema {
    @SerializedName("id_media_subtema")
    @Expose
    public String id_media_subtema;

    @SerializedName("id_media_tema")
    @Expose
    public String id_media_tema;

    @SerializedName("nama_subtema")
    @Expose
    public String nama_subtema;

    @SerializedName("media")
    @Nullable
    List<Media> media;

    public String getId_media_subtema() {
        return id_media_subtema;
    }

    public void setId_media_subtema(String id_media_subtema) {
        this.id_media_subtema = id_media_subtema;
    }

    public String getId_media_tema() {
        return id_media_tema;
    }

    public void setId_media_tema(String id_media_tema) {
        this.id_media_tema = id_media_tema;
    }

    public String getNama_subtema() {
        return nama_subtema;
    }

    public void setNama_subtema(String nama_subtema) {
        this.nama_subtema = nama_subtema;
    }

    @Nullable
    public List<Media> getMedia() {
        return media;
    }

    public void setMedia(@Nullable List<Media> media) {
        this.media = media;
    }
}

