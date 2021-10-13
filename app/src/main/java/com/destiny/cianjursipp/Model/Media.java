package com.destiny.cianjursipp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Media {
    @SerializedName("id_media_pembelajaran")
    @Expose
    public String id_media_pembelajaran;

    @SerializedName("id_media_tema")
    @Expose
    public String id_media_tema;

    @SerializedName("judul_media")
    @Expose
    public String judul_media;

    @SerializedName("link_youtube_media")
    @Expose
    public String link_youtube_media;

    @SerializedName("cover_media")
    @Expose
    public String cover_media;

    @SerializedName("isi_media")
    @Expose
    public String isi_media;

    @SerializedName("status_media")
    @Expose
    public String status_media;

    @SerializedName("created_at_media")
    @Expose
    public String created_at_media;

    public String getId_media_pembelajaran() {
        return id_media_pembelajaran;
    }

    public void setId_media_pembelajaran(String id_media_pembelajaran) {
        this.id_media_pembelajaran = id_media_pembelajaran;
    }

    public String getId_media_tema() {
        return id_media_tema;
    }

    public void setId_media_tema(String id_media_tema) {
        this.id_media_tema = id_media_tema;
    }

    public String getJudul_media() {
        return judul_media;
    }

    public void setJudul_media(String judul_media) {
        this.judul_media = judul_media;
    }

    public String getLink_youtube_media() {
        return link_youtube_media;
    }

    public void setLink_youtube_media(String link_youtube_media) {
        this.link_youtube_media = link_youtube_media;
    }

    public String getCover_media() {
        return cover_media;
    }

    public void setCover_media(String cover_media) {
        this.cover_media = cover_media;
    }

    public String getIsi_media() {
        return isi_media;
    }

    public void setIsi_media(String isi_media) {
        this.isi_media = isi_media;
    }

    public String getStatus_media() {
        return status_media;
    }

    public void setStatus_media(String status_media) {
        this.status_media = status_media;
    }

    public String getCreated_at_media() {
        return created_at_media;
    }

    public void setCreated_at_media(String created_at_media) {
        this.created_at_media = created_at_media;
    }
}

