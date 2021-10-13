package com.destiny.cianjursipp.Model;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataModel {
    @SerializedName("accessToken")
    @Expose
    public String accessToken;

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("lembaga")
    @Expose
    public String lembaga;

    @SerializedName("telepon")
    @Expose
    public String telepon;

    @SerializedName("usernameUser")
    @Expose
    public String usernameUser;

    @SerializedName("as")
    @Expose
    public String as;

    @SerializedName("photo")
    @Expose
    public String photo;

    @SerializedName("foto")
    @Expose
    public String foto;

    @SerializedName("id_environment")
    @Expose
    public String id_environment;

    @SerializedName("kelas")
    @Expose
    public String kelas;

    @SerializedName("nisn")
    @Expose
    public String nisn;

    @SerializedName("namakelas")
    @Expose
    public String namakelas;


    @SerializedName("file_struktur_org_foto")
    @Expose
    public String file_struktur_org_foto;

    //Sumber Pustaka
    @SerializedName("id_sumber_pustaka")
    @Expose
    public String id_sumber_pustaka;

    @SerializedName("id_daerah")
    @Expose
    public String id_daerah;

    @SerializedName("id_dinas_pendidikan")
    @Expose
    public String id_dinas_pendidikan;

    @SerializedName("id_sumber_pustaka_penjurusan")
    @Expose
    public String id_sumber_pustaka_penjurusan;

    @SerializedName("nama_penjurusan")
    @Expose
    public String nama_penjurusan;

    @SerializedName("lembaga_sekolah")
    @Expose
    public String lembaga_sekolah;

    @SerializedName("judul_sumber_pustaka")
    @Expose
    public String judul_sumber_pustaka;

    @SerializedName("link_youtube_sumber_pustaka")
    @Expose
    public String link_youtube_sumber_pustaka;

    @SerializedName("file_sumber_pustaka")
    @Expose
    public String file_sumber_pustaka;

    @SerializedName("isi_sumber_pustaka")
    @Expose
    public String isi_sumber_pustaka;

    @SerializedName("status_sumber_pustaka")
    @Expose
    public String status_sumber_pustaka;

    @SerializedName("created_at_sumber_pustaka")
    @Expose
    public String created_at_sumber_pustaka;


    //Game
    @SerializedName("id_games_hewan")
    @Expose
    public String id_games_hewan;

    @SerializedName("inisial")
    @Expose
    public String inisial;

    @SerializedName("nama_hewan")
    @Expose
    public String nama_hewan;

    @SerializedName("link_file_hewan")
    @Expose
    public String link_file_hewan;

    //Ujian
    @SerializedName("id_ujian")
    @Expose
    public String id_ujian;

    @SerializedName("id_sekolah")
    @Expose
    public String id_sekolah;

    @SerializedName("id_tahun_ajaran")
    @Expose
    public String id_tahun_ajaran;

    @SerializedName("tipe_ujian")
    @Expose
    public String tipe_ujian;

    @SerializedName("id_penjurusan")
    @Expose
    public String id_penjurusan;

    @SerializedName("kelas_ke")
    @Expose
    public String kelas_ke;

    @SerializedName("tgl_ujian")
    @Expose
    public String tgl_ujian;

    @SerializedName("nama_hari")
    @Expose
    public String nama_hari;

    @SerializedName("tgl_tambah_ujian")
    @Expose
    public String tgl_tambah_ujian;

    @SerializedName("id_ujian_jadwal")
    @Expose
    public String id_ujian_jadwal;

    @SerializedName("file_ujian")
    @Expose
    public String file_ujian;

    @SerializedName("status_ujian")
    @Expose
    public String status_ujian;

    @SerializedName("score_tugas")
    @Expose
    public String score_tugas;

    @SerializedName("jumlah_soal")
    @Expose
    public String jumlah_soal;

    @SerializedName("mulai_ujian")
    @Expose
    public String mulai_ujian;






    //Penelusuran Lulusan
    //OTW

    //Guru Ngaji
    @SerializedName("id_guru_ngaji")
    @Expose
    public String id_guru_ngaji;


    @SerializedName("nama_guru_ngaji")
    @Expose
    public String nama_guru_ngaji;

    @SerializedName("alamat_guru_ngaji")
    @Expose
    public String alamat_guru_ngaji;

    @SerializedName("latitude_guru_ngaji")
    @Expose
    public String latitude_guru_ngaji;

    @SerializedName("longitude_guru_ngaji")
    @Expose
    public String longitude_guru_ngaji;

    @SerializedName("foto_guru_ngaji")
    @Expose
    public String foto_guru_ngaji;

    @SerializedName("created_at_guru_ngaji")
    @Expose
    public String created_at_guru_ngaji;

    @SerializedName("nama_daerah")
    @Expose
    public String nama_daerah;
    //Tahun Ajaran

    @SerializedName("urutan_tahun")
    @Expose
    public String urutan_tahun;

    @SerializedName("tahun_ajaran")
    @Expose
    public String tahun_ajaran;

    @SerializedName("status_tahun_ajaran")
    @Expose
    public String status_tahun_ajaran;

    //Kabar Sekolah
    @SerializedName("id_kabar_sekolah")
    @Expose
    public String id_kabar_sekolah;

    @SerializedName("judul_kabar")
    @Expose
    public String judul_kabar;

    @SerializedName("cover_kabar")
    @Expose
    public String cover_kabar;

    @SerializedName("isi_kabar")
    @Expose
    public String isi_kabar;

    @SerializedName("status_kabar")
    @Expose
    public String status_kabar;

    @SerializedName("created_at_kabar")
    @Expose
    public String created_at_kabar;

    @SerializedName("link_youtube_kabar")
    @Expose
    public String link_youtube_kabar;


    //Struktur Organisasi
    @SerializedName("id_struktur_org")
    @Expose
    public String id_struktur_org;

    @SerializedName("nama_guru")
    @Expose
    public String nama_guru;

    @SerializedName("jabatan")
    @Expose
    public String jabatan;

    @SerializedName("file_foto_struktur")
    @Expose
    public String file_foto_struktur;

    @SerializedName("sort_num")
    @Expose
    public String sort_num;

    @SerializedName("created_at")
    @Expose
    public String created_at;

    //Prestasi
    @SerializedName("id_prestasi")
    @Expose
    public String id_prestasi;

    @SerializedName("judul_prestasi")
    @Expose
    public String judul_prestasi;

    @SerializedName("deskripsi_prestasi")
    @Expose
    public String deskripsi_prestasi;

    @SerializedName("tgl_prestasi")
    @Expose
    public String tgl_prestasi;

    @SerializedName("foto_prestasi")
    @Expose
    public String foto_prestasi;

    @SerializedName("created_at_prestasi")
    @Expose
    public String created_at_prestasi;

    //Jadwal Mata Pelajaran
    @SerializedName("id_mapel_kelas_jadwal")
    @Expose
    public String id_mapel_kelas_jadwal;

    @SerializedName("id_mapel_kelas")
    @Expose
    public String id_mapel_kelas;

    @SerializedName("id_mapel")
    @Expose
    public String id_mapel;

    @SerializedName("id_guru")
    @Expose
    public String id_guru;

    @SerializedName("jam_mulai")
    @Expose
    public String jam_mulai;

    @SerializedName("jam_selesai")
    @Expose
    public String jam_selesai;

    @SerializedName("id_kelas")
    @Expose
    public String id_kelas;

    @SerializedName("tgl_kelas")
    @Expose
    public String tgl_kelas;

    @SerializedName("tgl_tambah_mapel_kelas")
    @Expose
    public String tgl_tambah_mapel_kelas;

    @SerializedName("nama_mapel")
    @Expose
    public String nama_mapel;

    @SerializedName("kode_mapel")
    @Expose
    public String kode_mapel;

    @SerializedName("tgl_tambah_mapel")
    @Expose
    public String tgl_tambah_mapel;

    @SerializedName("created_at_mapel")
    @Expose
    public String created_at_mapel;

    //E-Raport
    @SerializedName("id_raport_siswa")
    @Expose
    public String id_raport_siswa;

    @SerializedName("id_siswa")
    @Expose
    public String id_siswa;

    @SerializedName("nama_raport")
    @Expose
    public String nama_raport;

    @SerializedName("link_file_raport")
    @Expose
    public String link_file_raport;

    @SerializedName("tgl_raport_upload")
    @Expose
    public String tgl_raport_upload;

    //Poin
    @SerializedName("poin")
    @Expose
    public String poin;

    //Poin
    @SerializedName("nama_kelas")
    @Expose
    public String nama_kelas;

    @SerializedName("nama_siswa")
    @Expose
    public String nama_siswa;

    //Agenda
    @SerializedName("id_agenda_sekolah")
    @Expose
    public String id_agenda_sekolah;

    @SerializedName("judul_agenda")
    @Expose
    public String judul_agenda;

    @SerializedName("cover_agenda")
    @Expose
    public String cover_agenda;

    @SerializedName("isi_agenda")
    @Expose
    public String isi_agenda;

    @SerializedName("status_agenda")
    @Expose
    public String status_agenda;

    @SerializedName("created_at_agenda")
    @Expose
    public String created_at_agenda;

    //Tugas
    @SerializedName("id_tugas")
    @Expose
    public String id_tugas;

    @SerializedName("jenis_tugas")
    @Expose
    public String jenis_tugas;

    @SerializedName("isi_tugas")
    @Expose
    public String isi_tugas;

    @SerializedName("file_tugas_pg")
    @Expose
    public String file_tugas_pg;

    @SerializedName("file_tugas")
    @Expose
    public String file_tugas;


    @SerializedName("tgl_mulai")
    @Expose
    public String tgl_mulai;

    @SerializedName("tgl_selesai")
    @Expose
    public String tgl_selesai;

    @SerializedName("status_tugas")
    @Expose
    public String status_tugas;


    @SerializedName("tugas_created_at")
    @Expose
    public String tugas_created_at;

    @SerializedName("soal")
    @Expose
    public String soal;

    @SerializedName("jumlahsoal")
    @Expose
    public String jumlahsoal;

    @SerializedName("id_tugas_essay_soal")
    @Expose
    public String id_tugas_essay_soal;


    @SerializedName("no_soal")
    @Expose
    public String no_soal;

    @SerializedName("isi_soal")
    @Expose
    public String isi_soal;

    @SerializedName("terjawab")
    @Expose
    public String terjawab;

    @SerializedName("idtugas")
    @Expose
    public String idtugas;

    @SerializedName("fillable")
    @Expose
    public String fillable;

    //Gallery
    @SerializedName("id_gallery")
    @Expose
    public String id_gallery;

    @SerializedName("link_file_foto")
    @Expose
    public String link_file_foto;

    @SerializedName("deskripsi_foto")
    @Expose
    public String deskripsi_foto;

    //Guru
    @SerializedName("foto_guru")
    @Expose
    public String foto_guru;

    @SerializedName("nip_guru")
    @Expose
    public String nip_guru;

    //Profile
    //Profile Sekolah
    @SerializedName("nama_sekolah")
    @Expose
    public String nama_sekolah;

    @SerializedName("alamat_sekolah")
    @Expose
    public String alamat_sekolah;

    @SerializedName("akreditasi_sekolah")
    @Expose
    public String akreditasi_sekolah;

    @SerializedName("npsn")
    @Expose
    public String npsn;

    @SerializedName("no_izin_sekolah")
    @Expose
    public String no_izin_sekolah;

    @SerializedName("kepala_sekolah")
    @Expose
    public String kepala_sekolah;

    @SerializedName("nama_yayasan")
    @Expose
    public String nama_yayasan;

    @SerializedName("ketua_yayasan")
    @Expose
    public String ketua_yayasan;

    @SerializedName("logo_sekolah")
    @Expose
    public String logo_sekolah;

    //Media Pembelajaran
    @SerializedName("id_media_tema")
    @Expose
    public String id_media_tema;

    @SerializedName("nama_tema")
    @Expose
    public String nama_tema;

    @SerializedName("subtema")
    @Nullable
    List<SubTema> subtema;

    //Dinas Pendidikan
    @SerializedName("id_info_dinas")
    @Expose
    public String id_info_dinas;

    @SerializedName("tipe_info")
    @Expose
    public String tipe_info;

    @SerializedName("nama_info_dinas")
    @Expose
    public String nama_info_dinas;

    @SerializedName("deskripsi_info_dinas")
    @Expose
    public String deskripsi_info_dinas;

    @SerializedName("file_info_dinas")
    @Expose
    public String file_info_dinas;

    @SerializedName("tgl_upload_info")
    @Expose
    public String tgl_upload_info;

    //Sponsor
    @SerializedName("id_sponsor")
    @Expose
    public String id_sponsor;

    @SerializedName("judul_sponsor")
    @Expose
    public String judul_sponsor;

    @SerializedName("file_image_sponsor")
    @Expose
    public String file_image_sponsor;

    @SerializedName("status_sponsor")
    @Expose
    public String status_sponsor;

    @SerializedName("alamat_web_sponsor")
    @Expose
    public String alamat_web_sponsor;

    @SerializedName("level_sponsor")
    @Expose
    public String level_sponsor;

    //INFO Publik
    @SerializedName("id_info_publik")
    @Expose
    public String id_info_publik;

    @SerializedName("judul_info_publik")
    @Expose
    public String judul_info_publik;

    @SerializedName("link_youtube_info_publik")
    @Expose
    public String link_youtube_info_publik;

    @SerializedName("cover_info_publik")
    @Expose
    public String cover_info_publik;

    @SerializedName("isi_info_publik")
    @Expose
    public String isi_info_publik;

    @SerializedName("status_info_publik")
    @Expose
    public String status_info_publik;

    @SerializedName("created_at_info_publik")
    @Expose
    public String created_at_info_publik;

    //Kemis Nyunda
    @SerializedName("id_kemis_nyunda")
    @Expose
    public String id_kemis_nyunda;

    @SerializedName("tipe_kemis_nyunda")
    @Expose
    public String tipe_kemis_nyunda;

    @SerializedName("judul_kemis_nyunda")
    @Expose
    public String judul_kemis_nyunda;

    @SerializedName("link_youtube_nyunda")
    @Expose
    public String link_youtube_nyunda;

    @SerializedName("cover_kemis_nyunda")
    @Expose
    public String cover_kemis_nyunda;

    @SerializedName("deskripsi_kemis_nyunda")
    @Expose
    public String deskripsi_kemis_nyunda;

    @SerializedName("status_kemis_nyunda")
    @Expose
    public String status_kemis_nyunda;

    @SerializedName("created_at_kemis_nyunda")
    @Expose
    public String created_at_kemis_nyunda;

    //Khutbah
    @SerializedName("id_khutbah")
    @Expose
    public String id_khutbah;

    @SerializedName("judul_khutbah")
    @Expose
    public String judul_khutbah;

    @SerializedName("link_youtube_khutbah")
    @Expose
    public String link_youtube_khutbah;

    @SerializedName("cover_khutbah")
    @Expose
    public String cover_khutbah;

    @SerializedName("deskripsi_khutbah")
    @Expose
    public String deskripsi_khutbah;

    @SerializedName("status_khutbah")
    @Expose
    public String status_khutbah;

    @SerializedName("created_at_khutbah")
    @Expose
    public String created_at_khutbah;

    //Media Informasi
    @SerializedName("id_media_informasi")
    @Expose
    public String id_media_informasi;

    @SerializedName("tipe_media_informasi")
    @Expose
    public String tipe_media_informasi;

    @SerializedName("judul_media_informasi")
    @Expose
    public String judul_media_informasi;

    @SerializedName("judul_media")
    @Expose
    public String judul_media;


    @SerializedName("isi_media")
    @Expose
    public String isi_media;


    @SerializedName("link_youtube_media")
    @Expose
    public String link_youtube_media;

    @SerializedName("file_media")
    @Expose
    public String file_media;

    @SerializedName("link_youtube_media_informasi")
    @Expose
    public String link_youtube_media_informasi;

    @SerializedName("cover_media_informasi")
    @Expose
    public String cover_media_informasi;

    @SerializedName("deskripsi_media_informasi")
    @Expose
    public String deskripsi_media_informasi;

    @SerializedName("status_media_informasi")
    @Expose
    public String status_media_informasi;

    @SerializedName("created_at_media_informasi")
    @Expose
    public String created_at_media_informasi;

    @SerializedName("created_at_media")
    @Expose
    public String created_at_media;

    //Banner
    @SerializedName("id_banner")
    @Expose
    public String id_banner;

    @SerializedName("nama_banner")
    @Expose
    public String nama_banner;

    @SerializedName("file_foto_banner")
    @Expose
    public String file_foto_banner;

    @SerializedName("created_at_banner")
    @Expose
    public String created_at_banner;

    //Get Kelas
    @SerializedName("status_kelas")
    @Expose
    public String status_kelas;

    //Sponsor Sekolah
    @SerializedName("id_sekolah_sponsor")
    @Expose
    public String id_sekolah_sponsor;

    //Evadir
    @SerializedName("id_evadir")
    @Expose
    public String id_evadir;

    @SerializedName("nama_evadir")
    @Expose
    public String nama_evadir;

    @SerializedName("kategori_evadir")
    @Expose
    public String kategori_evadir;

    @SerializedName("id_kategori")
    @Expose
    public String id_kategori;

    @SerializedName("answer")
    @Expose
    public List<Evadir> answer;
    //Tugas

    //Loker
    @SerializedName("id_loker")
    @Expose
    public String id_loker;

    @SerializedName("nama_loker")
    @Expose
    public String nama_loker;

    @SerializedName("nama_perusahaan")
    @Expose
    public String nama_perusahaan;

    @SerializedName("alamat_loker")
    @Expose
    public String alamat_loker;

    @SerializedName("link_loker")
    @Expose
    public String link_loker;

    @SerializedName("deskripsi_loker")
    @Expose
    public String deskripsi_loker;

    @SerializedName("status_loker")
    @Expose
    public String status_loker;

    @SerializedName("cover_loker")
    @Expose
    public String cover_loker;

    @SerializedName("created_at_loker")
    @Expose
    public String created_at_loker;




    //GETTER SETTER
    public String getId_evadir() {
        return id_evadir;
    }

    public void setId_evadir(String id_evadir) {
        this.id_evadir = id_evadir;
    }

    public String getNama_evadir() {
        return nama_evadir;
    }

    public void setNama_evadir(String nama_evadir) {
        this.nama_evadir = nama_evadir;
    }

    public String getKategori_evadir() {
        return kategori_evadir;
    }

    public void setKategori_evadir(String kategori_evadir) {
        this.kategori_evadir = kategori_evadir;
    }

    public String getId_kategori() {
        return id_kategori;
    }

    public void setId_kategori(String id_kategori) {
        this.id_kategori = id_kategori;
    }

    public List<Evadir> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Evadir> answer) {
        this.answer = answer;
    }

    public String getId_sekolah_sponsor() {
        return id_sekolah_sponsor;
    }

    public void setId_sekolah_sponsor(String id_sekolah_sponsor) {
        this.id_sekolah_sponsor = id_sekolah_sponsor;
    }

    public String getAlamat_web_sponsor() {
        return alamat_web_sponsor;
    }

    public void setAlamat_web_sponsor(String alamat_web_sponsor) {
        this.alamat_web_sponsor = alamat_web_sponsor;
    }

    public String getLevel_sponsor() {
        return level_sponsor;
    }

    public void setLevel_sponsor(String level_sponsor) {
        this.level_sponsor = level_sponsor;
    }

    public String getId_penjurusan() {
        return id_penjurusan;
    }

    public void setId_penjurusan(String id_penjurusan) {
        this.id_penjurusan = id_penjurusan;
    }

    public String getId_tahun_ajaran() {
        return id_tahun_ajaran;
    }

    public void setId_tahun_ajaran(String id_tahun_ajaran) {
        this.id_tahun_ajaran = id_tahun_ajaran;
    }

    public String getKelas_ke() {
        return kelas_ke;
    }

    public void setKelas_ke(String kelas_ke) {
        this.kelas_ke = kelas_ke;
    }

    public String getStatus_kelas() {
        return status_kelas;
    }

    public void setStatus_kelas(String status_kelas) {
        this.status_kelas = status_kelas;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getUsernameUser() {
        return usernameUser;
    }

    public void setUsernameUser(String usernameUser) {
        this.usernameUser = usernameUser;
    }

    public String getAs() {
        return as;
    }

    public void setAs(String as) {
        this.as = as;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getId_kabar_sekolah() {
        return id_kabar_sekolah;
    }

    public void setId_kabar_sekolah(String id_kabar_sekolah) {
        this.id_kabar_sekolah = id_kabar_sekolah;
    }

    public String getId_sekolah() {
        return id_sekolah;
    }

    public void setId_sekolah(String id_sekolah) {
        this.id_sekolah = id_sekolah;
    }

    public String getCover_kabar() {
        return cover_kabar;
    }

    public void setCover_kabar(String cover_kabar) {
        this.cover_kabar = cover_kabar;
    }

    public String getIsi_kabar() {
        return isi_kabar;
    }

    public void setIsi_kabar(String isi_kabar) {
        this.isi_kabar = isi_kabar;
    }

    public String getStatus_kabar() {
        return status_kabar;
    }

    public void setStatus_kabar(String status_kabar) {
        this.status_kabar = status_kabar;
    }

    public String getCreated_at_kabar() {
        return created_at_kabar;
    }

    public void setCreated_at_kabar(String created_at_kabar) {
        this.created_at_kabar = created_at_kabar;
    }

    public String getId_struktur_org() {
        return id_struktur_org;
    }

    public void setId_struktur_org(String id_struktur_org) {
        this.id_struktur_org = id_struktur_org;
    }

    public String getNama_guru() {
        return nama_guru;
    }

    public void setNama_guru(String nama_guru) {
        this.nama_guru = nama_guru;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getFile_foto_struktur() {
        return file_foto_struktur;
    }

    public void setFile_foto_struktur(String file_foto_struktur) {
        this.file_foto_struktur = file_foto_struktur;
    }

    public String getSort_num() {
        return sort_num;
    }

    public void setSort_num(String sort_num) {
        this.sort_num = sort_num;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getId_prestasi() {
        return id_prestasi;
    }

    public void setId_prestasi(String id_prestasi) {
        this.id_prestasi = id_prestasi;
    }

    public String getJudul_prestasi() {
        return judul_prestasi;
    }

    public void setJudul_prestasi(String judul_prestasi) {
        this.judul_prestasi = judul_prestasi;
    }

    public String getDeskripsi_prestasi() {
        return deskripsi_prestasi;
    }

    public void setDeskripsi_prestasi(String deskripsi_prestasi) {
        this.deskripsi_prestasi = deskripsi_prestasi;
    }

    public String getTgl_prestasi() {
        return tgl_prestasi;
    }

    public void setTgl_prestasi(String tgl_prestasi) {
        this.tgl_prestasi = tgl_prestasi;
    }

    public String getFoto_prestasi() {
        return foto_prestasi;
    }

    public void setFoto_prestasi(String foto_prestasi) {
        this.foto_prestasi = foto_prestasi;
    }

    public String getCreated_at_prestasi() {
        return created_at_prestasi;
    }

    public void setCreated_at_prestasi(String created_at_prestasi) {
        this.created_at_prestasi = created_at_prestasi;
    }

    public String getJudul_kabar() {
        return judul_kabar;
    }

    public void setJudul_kabar(String judul_kabar) {
        this.judul_kabar = judul_kabar;
    }

    public String getId_mapel_kelas_jadwal() {
        return id_mapel_kelas_jadwal;
    }

    public void setId_mapel_kelas_jadwal(String id_mapel_kelas_jadwal) {
        this.id_mapel_kelas_jadwal = id_mapel_kelas_jadwal;
    }

    public String getId_mapel_kelas() {
        return id_mapel_kelas;
    }

    public void setId_mapel_kelas(String id_mapel_kelas) {
        this.id_mapel_kelas = id_mapel_kelas;
    }

    public String getId_mapel() {
        return id_mapel;
    }

    public void setId_mapel(String id_mapel) {
        this.id_mapel = id_mapel;
    }

    public String getId_guru() {
        return id_guru;
    }

    public void setId_guru(String id_guru) {
        this.id_guru = id_guru;
    }

    public String getJam_mulai() {
        return jam_mulai;
    }

    public void setJam_mulai(String jam_mulai) {
        this.jam_mulai = jam_mulai;
    }

    public String getJam_selesai() {
        return jam_selesai;
    }

    public void setJam_selesai(String jam_selesai) {
        this.jam_selesai = jam_selesai;
    }

    public String getId_kelas() {
        return id_kelas;
    }

    public void setId_kelas(String id_kelas) {
        this.id_kelas = id_kelas;
    }

    public String getTgl_kelas() {
        return tgl_kelas;
    }

    public void setTgl_kelas(String tgl_kelas) {
        this.tgl_kelas = tgl_kelas;
    }

    public String getNama_hari() {
        return nama_hari;
    }

    public void setNama_hari(String nama_hari) {
        this.nama_hari = nama_hari;
    }

    public String getTgl_tambah_mapel_kelas() {
        return tgl_tambah_mapel_kelas;
    }

    public void setTgl_tambah_mapel_kelas(String tgl_tambah_mapel_kelas) {
        this.tgl_tambah_mapel_kelas = tgl_tambah_mapel_kelas;
    }

    public String getNama_mapel() {
        return nama_mapel;
    }

    public void setNama_mapel(String nama_mapel) {
        this.nama_mapel = nama_mapel;
    }

    public String getKode_mapel() {
        return kode_mapel;
    }

    public void setKode_mapel(String kode_mapel) {
        this.kode_mapel = kode_mapel;
    }

    public String getTgl_tambah_mapel() {
        return tgl_tambah_mapel;
    }

    public void setTgl_tambah_mapel(String tgl_tambah_mapel) {
        this.tgl_tambah_mapel = tgl_tambah_mapel;
    }

    public String getCreated_at_mapel() {
        return created_at_mapel;
    }

    public void setCreated_at_mapel(String created_at_mapel) {
        this.created_at_mapel = created_at_mapel;
    }

    public String getId_raport_siswa() {
        return id_raport_siswa;
    }

    public void setId_raport_siswa(String id_raport_siswa) {
        this.id_raport_siswa = id_raport_siswa;
    }

    public String getId_siswa() {
        return id_siswa;
    }

    public void setId_siswa(String id_siswa) {
        this.id_siswa = id_siswa;
    }

    public String getNama_raport() {
        return nama_raport;
    }

    public void setNama_raport(String nama_raport) {
        this.nama_raport = nama_raport;
    }

    public String getLink_file_raport() {
        return link_file_raport;
    }

    public void setLink_file_raport(String link_file_raport) {
        this.link_file_raport = link_file_raport;
    }

    public String getTgl_raport_upload() {
        return tgl_raport_upload;
    }

    public void setTgl_raport_upload(String tgl_raport_upload) {
        this.tgl_raport_upload = tgl_raport_upload;
    }

    public String getPoin() {
        return poin;
    }

    public void setPoin(String poin) {
        this.poin = poin;
    }

    public String getNama_kelas() {
        return nama_kelas;
    }

    public void setNama_kelas(String nama_kelas) {
        this.nama_kelas = nama_kelas;
    }

    public String getNama_siswa() {
        return nama_siswa;
    }

    public void setNama_siswa(String nama_siswa) {
        this.nama_siswa = nama_siswa;
    }

    public String getId_agenda_sekolah() {
        return id_agenda_sekolah;
    }

    public void setId_agenda_sekolah(String id_agenda_sekolah) {
        this.id_agenda_sekolah = id_agenda_sekolah;
    }

    public String getJudul_agenda() {
        return judul_agenda;
    }

    public void setJudul_agenda(String judul_agenda) {
        this.judul_agenda = judul_agenda;
    }

    public String getCover_agenda() {
        return cover_agenda;
    }

    public void setCover_agenda(String cover_agenda) {
        this.cover_agenda = cover_agenda;
    }

    public String getIsi_agenda() {
        return isi_agenda;
    }

    public void setIsi_agenda(String isi_agenda) {
        this.isi_agenda = isi_agenda;
    }

    public String getStatus_agenda() {
        return status_agenda;
    }

    public void setStatus_agenda(String status_agenda) {
        this.status_agenda = status_agenda;
    }

    public String getCreated_at_agenda() {
        return created_at_agenda;
    }

    public void setCreated_at_agenda(String created_at_agenda) {
        this.created_at_agenda = created_at_agenda;
    }

    public String getId_tugas() {
        return id_tugas;
    }

    public void setId_tugas(String id_tugas) {
        this.id_tugas = id_tugas;
    }

    public String getIsi_tugas() {
        return isi_tugas;
    }

    public void setIsi_tugas(String isi_tugas) {
        this.isi_tugas = isi_tugas;
    }

    public String getTgl_mulai() {
        return tgl_mulai;
    }

    public void setTgl_mulai(String tgl_mulai) {
        this.tgl_mulai = tgl_mulai;
    }

    public String getTgl_selesai() {
        return tgl_selesai;
    }

    public void setTgl_selesai(String tgl_selesai) {
        this.tgl_selesai = tgl_selesai;
    }

    public String getTugas_created_at() {
        return tugas_created_at;
    }

    public void setTugas_created_at(String tugas_created_at) {
        this.tugas_created_at = tugas_created_at;
    }

    public String getId_gallery() {
        return id_gallery;
    }

    public void setId_gallery(String id_gallery) {
        this.id_gallery = id_gallery;
    }

    public String getLink_file_foto() {
        return link_file_foto;
    }

    public void setLink_file_foto(String link_file_foto) {
        this.link_file_foto = link_file_foto;
    }

    public String getFoto_guru() {
        return foto_guru;
    }

    public void setFoto_guru(String foto_guru) {
        this.foto_guru = foto_guru;
    }

    public String getNip_guru() {
        return nip_guru;
    }

    public void setNip_guru(String nip_guru) {
        this.nip_guru = nip_guru;
    }

    public String getNama_sekolah() {
        return nama_sekolah;
    }

    public void setNama_sekolah(String nama_sekolah) {
        this.nama_sekolah = nama_sekolah;
    }

    public String getAlamat_sekolah() {
        return alamat_sekolah;
    }

    public void setAlamat_sekolah(String alamat_sekolah) {
        this.alamat_sekolah = alamat_sekolah;
    }

    public String getAkreditasi_sekolah() {
        return akreditasi_sekolah;
    }

    public void setAkreditasi_sekolah(String akreditasi_sekolah) {
        this.akreditasi_sekolah = akreditasi_sekolah;
    }

    public String getNpsn() {
        return npsn;
    }

    public void setNpsn(String npsn) {
        this.npsn = npsn;
    }

    public String getNo_izin_sekolah() {
        return no_izin_sekolah;
    }

    public void setNo_izin_sekolah(String no_izin_sekolah) {
        this.no_izin_sekolah = no_izin_sekolah;
    }

    public String getKepala_sekolah() {
        return kepala_sekolah;
    }

    public void setKepala_sekolah(String kepala_sekolah) {
        this.kepala_sekolah = kepala_sekolah;
    }

    public String getNama_yayasan() {
        return nama_yayasan;
    }

    public void setNama_yayasan(String nama_yayasan) {
        this.nama_yayasan = nama_yayasan;
    }

    public String getKetua_yayasan() {
        return ketua_yayasan;
    }

    public void setKetua_yayasan(String ketua_yayasan) {
        this.ketua_yayasan = ketua_yayasan;
    }

    public String getLogo_sekolah() {
        return logo_sekolah;
    }

    public void setLogo_sekolah(String logo_sekolah) {
        this.logo_sekolah = logo_sekolah;
    }

    public String getLink_youtube_kabar() {
        return link_youtube_kabar;
    }

    public void setLink_youtube_kabar(String link_youtube_kabar) {
        this.link_youtube_kabar = link_youtube_kabar;
    }

    public String getId_media_tema() {
        return id_media_tema;
    }

    public void setId_media_tema(String id_media_tema) {
        this.id_media_tema = id_media_tema;
    }

    public String getNama_tema() {
        return nama_tema;
    }

    public void setNama_tema(String nama_tema) {
        this.nama_tema = nama_tema;
    }

    public void setSubtema(@Nullable List<SubTema> subtema) {
        this.subtema = subtema;
    }

    @Nullable
    public List<SubTema> getSubTema() {
        return subtema;
    }

    @Nullable
    public List<SubTema> getSubtema() {
        return subtema;
    }

    public String getId_info_dinas() {
        return id_info_dinas;
    }

    public void setId_info_dinas(String id_info_dinas) {
        this.id_info_dinas = id_info_dinas;
    }

    public String getId_daerah() {
        return id_daerah;
    }

    public void setId_daerah(String id_daerah) {
        this.id_daerah = id_daerah;
    }

    public String getTipe_info() {
        return tipe_info;
    }

    public void setTipe_info(String tipe_info) {
        this.tipe_info = tipe_info;
    }

    public String getNama_info_dinas() {
        return nama_info_dinas;
    }

    public void setNama_info_dinas(String nama_info_dinas) {
        this.nama_info_dinas = nama_info_dinas;
    }

    public String getDeskripsi_info_dinas() {
        return deskripsi_info_dinas;
    }

    public void setDeskripsi_info_dinas(String deskripsi_info_dinas) {
        this.deskripsi_info_dinas = deskripsi_info_dinas;
    }

    public String getFile_info_dinas() {
        return file_info_dinas;
    }

    public void setFile_info_dinas(String file_info_dinas) {
        this.file_info_dinas = file_info_dinas;
    }

    public String getTgl_upload_info() {
        return tgl_upload_info;
    }

    public void setTgl_upload_info(String tgl_upload_info) {
        this.tgl_upload_info = tgl_upload_info;
    }

    public String getId_environment() {
        return id_environment;
    }

    public void setId_environment(String id_environment) {
        this.id_environment = id_environment;
    }

    public String getId_sponsor() {
        return id_sponsor;
    }

    public void setId_sponsor(String id_sponsor) {
        this.id_sponsor = id_sponsor;
    }

    public String getJudul_sponsor() {
        return judul_sponsor;
    }

    public void setJudul_sponsor(String judul_sponsor) {
        this.judul_sponsor = judul_sponsor;
    }

    public String getFile_image_sponsor() {
        return file_image_sponsor;
    }

    public void setFile_image_sponsor(String file_image_sponsor) {
        this.file_image_sponsor = file_image_sponsor;
    }

    public String getStatus_sponsor() {
        return status_sponsor;
    }

    public void setStatus_sponsor(String status_sponsor) {
        this.status_sponsor = status_sponsor;
    }

    public String getId_info_publik() {
        return id_info_publik;
    }

    public void setId_info_publik(String id_info_publik) {
        this.id_info_publik = id_info_publik;
    }

    public String getJudul_info_publik() {
        return judul_info_publik;
    }

    public void setJudul_info_publik(String judul_info_publik) {
        this.judul_info_publik = judul_info_publik;
    }

    public String getLink_youtube_info_publik() {
        return link_youtube_info_publik;
    }

    public void setLink_youtube_info_publik(String link_youtube_info_publik) {
        this.link_youtube_info_publik = link_youtube_info_publik;
    }

    public String getCover_info_publik() {
        return cover_info_publik;
    }

    public void setCover_info_publik(String cover_info_publik) {
        this.cover_info_publik = cover_info_publik;
    }

    public String getIsi_info_publik() {
        return isi_info_publik;
    }

    public void setIsi_info_publik(String isi_info_publik) {
        this.isi_info_publik = isi_info_publik;
    }

    public String getStatus_info_publik() {
        return status_info_publik;
    }

    public void setStatus_info_publik(String status_info_publik) {
        this.status_info_publik = status_info_publik;
    }

    public String getCreated_at_info_publik() {
        return created_at_info_publik;
    }

    public void setCreated_at_info_publik(String created_at_info_publik) {
        this.created_at_info_publik = created_at_info_publik;
    }

    public String getId_kemis_nyunda() {
        return id_kemis_nyunda;
    }

    public void setId_kemis_nyunda(String id_kemis_nyunda) {
        this.id_kemis_nyunda = id_kemis_nyunda;
    }

    public String getTipe_kemis_nyunda() {
        return tipe_kemis_nyunda;
    }

    public void setTipe_kemis_nyunda(String tipe_kemis_nyunda) {
        this.tipe_kemis_nyunda = tipe_kemis_nyunda;
    }

    public String getJudul_kemis_nyunda() {
        return judul_kemis_nyunda;
    }

    public void setJudul_kemis_nyunda(String judul_kemis_nyunda) {
        this.judul_kemis_nyunda = judul_kemis_nyunda;
    }

    public String getLink_youtube_nyunda() {
        return link_youtube_nyunda;
    }

    public void setLink_youtube_nyunda(String link_youtube_nyunda) {
        this.link_youtube_nyunda = link_youtube_nyunda;
    }

    public String getCover_kemis_nyunda() {
        return cover_kemis_nyunda;
    }

    public void setCover_kemis_nyunda(String cover_kemis_nyunda) {
        this.cover_kemis_nyunda = cover_kemis_nyunda;
    }

    public String getDeskripsi_kemis_nyunda() {
        return deskripsi_kemis_nyunda;
    }

    public void setDeskripsi_kemis_nyunda(String deskripsi_kemis_nyunda) {
        this.deskripsi_kemis_nyunda = deskripsi_kemis_nyunda;
    }

    public String getStatus_kemis_nyunda() {
        return status_kemis_nyunda;
    }

    public void setStatus_kemis_nyunda(String status_kemis_nyunda) {
        this.status_kemis_nyunda = status_kemis_nyunda;
    }

    public String getCreated_at_kemis_nyunda() {
        return created_at_kemis_nyunda;
    }

    public void setCreated_at_kemis_nyunda(String created_at_kemis_nyunda) {
        this.created_at_kemis_nyunda = created_at_kemis_nyunda;
    }

    public String getId_khutbah() {
        return id_khutbah;
    }

    public void setId_khutbah(String id_khutbah) {
        this.id_khutbah = id_khutbah;
    }

    public String getJudul_khutbah() {
        return judul_khutbah;
    }

    public void setJudul_khutbah(String judul_khutbah) {
        this.judul_khutbah = judul_khutbah;
    }

    public String getLink_youtube_khutbah() {
        return link_youtube_khutbah;
    }

    public void setLink_youtube_khutbah(String link_youtube_khutbah) {
        this.link_youtube_khutbah = link_youtube_khutbah;
    }

    public String getCover_khutbah() {
        return cover_khutbah;
    }

    public void setCover_khutbah(String cover_khutbah) {
        this.cover_khutbah = cover_khutbah;
    }

    public String getDeskripsi_khutbah() {
        return deskripsi_khutbah;
    }

    public void setDeskripsi_khutbah(String deskripsi_khutbah) {
        this.deskripsi_khutbah = deskripsi_khutbah;
    }

    public String getStatus_khutbah() {
        return status_khutbah;
    }

    public void setStatus_khutbah(String status_khutbah) {
        this.status_khutbah = status_khutbah;
    }

    public String getCreated_at_khutbah() {
        return created_at_khutbah;
    }

    public void setCreated_at_khutbah(String created_at_khutbah) {
        this.created_at_khutbah = created_at_khutbah;
    }

    public String getId_media_informasi() {
        return id_media_informasi;
    }

    public void setId_media_informasi(String id_media_informasi) {
        this.id_media_informasi = id_media_informasi;
    }

    public String getTipe_media_informasi() {
        return tipe_media_informasi;
    }

    public void setTipe_media_informasi(String tipe_media_informasi) {
        this.tipe_media_informasi = tipe_media_informasi;
    }

    public String getJudul_media_informasi() {
        return judul_media_informasi;
    }

    public void setJudul_media_informasi(String judul_media_informasi) {
        this.judul_media_informasi = judul_media_informasi;
    }

    public String getLink_youtube_media_informasi() {
        return link_youtube_media_informasi;
    }

    public void setLink_youtube_media_informasi(String link_youtube_media_informasi) {
        this.link_youtube_media_informasi = link_youtube_media_informasi;
    }

    public String getCover_media_informasi() {
        return cover_media_informasi;
    }

    public void setCover_media_informasi(String cover_media_informasi) {
        this.cover_media_informasi = cover_media_informasi;
    }

    public String getDeskripsi_media_informasi() {
        return deskripsi_media_informasi;
    }

    public void setDeskripsi_media_informasi(String deskripsi_media_informasi) {
        this.deskripsi_media_informasi = deskripsi_media_informasi;
    }

    public String getStatus_media_informasi() {
        return status_media_informasi;
    }

    public void setStatus_media_informasi(String status_media_informasi) {
        this.status_media_informasi = status_media_informasi;
    }

    public String getCreated_at_media_informasi() {
        return created_at_media_informasi;
    }

    public void setCreated_at_media_informasi(String created_at_media_informasi) {
        this.created_at_media_informasi = created_at_media_informasi;
    }

    public String getId_banner() {
        return id_banner;
    }

    public void setId_banner(String id_banner) {
        this.id_banner = id_banner;
    }

    public String getNama_banner() {
        return nama_banner;
    }

    public void setNama_banner(String nama_banner) {
        this.nama_banner = nama_banner;
    }

    public String getFile_foto_banner() {
        return file_foto_banner;
    }

    public void setFile_foto_banner(String file_foto_banner) {
        this.file_foto_banner = file_foto_banner;
    }

    public String getCreated_at_banner() {
        return created_at_banner;
    }

    public void setCreated_at_banner(String created_at_banner) {
        this.created_at_banner = created_at_banner;
    }

    public String getLembaga() {
        return lembaga;
    }

    public void setLembaga(String lembaga) {
        this.lembaga = lembaga;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getJenis_tugas() {
        return jenis_tugas;
    }

    public void setJenis_tugas(String jenis_tugas) {
        this.jenis_tugas = jenis_tugas;
    }

    public String getFile_tugas_pg() {
        return file_tugas_pg;
    }

    public void setFile_tugas_pg(String file_tugas_pg) {
        this.file_tugas_pg = file_tugas_pg;
    }

    public String getStatus_tugas() {
        return status_tugas;
    }

    public void setStatus_tugas(String status_tugas) {
        this.status_tugas = status_tugas;
    }

    public String getScore_tugas() {
        return score_tugas;
    }

    public void setScore_tugas(String score_tugas) {
        this.score_tugas = score_tugas;
    }

    public String getSoal() {
        return soal;
    }

    public void setSoal(String soal) {
        this.soal = soal;
    }

    public String getJumlahsoal() {
        return jumlahsoal;
    }

    public void setJumlahsoal(String jumlahsoal) {
        this.jumlahsoal = jumlahsoal;
    }

    public String getId_tugas_essay_soal() {
        return id_tugas_essay_soal;
    }

    public void setId_tugas_essay_soal(String id_tugas_essay_soal) {
        this.id_tugas_essay_soal = id_tugas_essay_soal;
    }

    public String getNo_soal() {
        return no_soal;
    }

    public void setNo_soal(String no_soal) {
        this.no_soal = no_soal;
    }

    public String getIsi_soal() {
        return isi_soal;
    }

    public void setIsi_soal(String isi_soal) {
        this.isi_soal = isi_soal;
    }

    public String getUrutan_tahun() {
        return urutan_tahun;
    }

    public void setUrutan_tahun(String urutan_tahun) {
        this.urutan_tahun = urutan_tahun;
    }

    public String getTahun_ajaran() {
        return tahun_ajaran;
    }

    public void setTahun_ajaran(String tahun_ajaran) {
        this.tahun_ajaran = tahun_ajaran;
    }

    public String getStatus_tahun_ajaran() {
        return status_tahun_ajaran;
    }

    public void setStatus_tahun_ajaran(String status_tahun_ajaran) {
        this.status_tahun_ajaran = status_tahun_ajaran;
    }

    public String getNama_daerah() {
        return nama_daerah;
    }

    public void setNama_daerah(String nama_daerah) {
        this.nama_daerah = nama_daerah;
    }

    public String getId_guru_ngaji() {
        return id_guru_ngaji;
    }

    public void setId_guru_ngaji(String id_guru_ngaji) {
        this.id_guru_ngaji = id_guru_ngaji;
    }

    public String getNama_guru_ngaji() {
        return nama_guru_ngaji;
    }

    public void setNama_guru_ngaji(String nama_guru_ngaji) {
        this.nama_guru_ngaji = nama_guru_ngaji;
    }

    public String getAlamat_guru_ngaji() {
        return alamat_guru_ngaji;
    }

    public void setAlamat_guru_ngaji(String alamat_guru_ngaji) {
        this.alamat_guru_ngaji = alamat_guru_ngaji;
    }

    public String getLatitude_guru_ngaji() {
        return latitude_guru_ngaji;
    }

    public void setLatitude_guru_ngaji(String latitude_guru_ngaji) {
        this.latitude_guru_ngaji = latitude_guru_ngaji;
    }

    public String getLongitude_guru_ngaji() {
        return longitude_guru_ngaji;
    }

    public void setLongitude_guru_ngaji(String longitude_guru_ngaji) {
        this.longitude_guru_ngaji = longitude_guru_ngaji;
    }

    public String getFoto_guru_ngaji() {
        return foto_guru_ngaji;
    }

    public void setFoto_guru_ngaji(String foto_guru_ngaji) {
        this.foto_guru_ngaji = foto_guru_ngaji;
    }

    public String getCreated_at_guru_ngaji() {
        return created_at_guru_ngaji;
    }

    public void setCreated_at_guru_ngaji(String created_at_guru_ngaji) {
        this.created_at_guru_ngaji = created_at_guru_ngaji;
    }

    public String getTerjawab() {
        return terjawab;
    }

    public void setTerjawab(String terjawab) {
        this.terjawab = terjawab;
    }

    public String getFile_tugas() {
        return file_tugas;
    }

    public void setFile_tugas(String file_tugas) {
        this.file_tugas = file_tugas;
    }

    public String getId_loker() {
        return id_loker;
    }

    public void setId_loker(String id_loker) {
        this.id_loker = id_loker;
    }

    public String getNama_loker() {
        return nama_loker;
    }

    public void setNama_loker(String nama_loker) {
        this.nama_loker = nama_loker;
    }

    public String getNama_perusahaan() {
        return nama_perusahaan;
    }

    public void setNama_perusahaan(String nama_perusahaan) {
        this.nama_perusahaan = nama_perusahaan;
    }

    public String getAlamat_loker() {
        return alamat_loker;
    }

    public void setAlamat_loker(String alamat_loker) {
        this.alamat_loker = alamat_loker;
    }

    public String getLink_loker() {
        return link_loker;
    }

    public void setLink_loker(String link_loker) {
        this.link_loker = link_loker;
    }

    public String getDeskripsi_loker() {
        return deskripsi_loker;
    }

    public void setDeskripsi_loker(String deskripsi_loker) {
        this.deskripsi_loker = deskripsi_loker;
    }

    public String getStatus_loker() {
        return status_loker;
    }

    public void setStatus_loker(String status_loker) {
        this.status_loker = status_loker;
    }

    public String getCreated_at_loker() {
        return created_at_loker;
    }

    public void setCreated_at_loker(String created_at_loker) {
        this.created_at_loker = created_at_loker;
    }

    public String getId_ujian() {
        return id_ujian;
    }

    public void setId_ujian(String id_ujian) {
        this.id_ujian = id_ujian;
    }

    public String getTipe_ujian() {
        return tipe_ujian;
    }

    public void setTipe_ujian(String tipe_ujian) {
        this.tipe_ujian = tipe_ujian;
    }

    public String getTgl_ujian() {
        return tgl_ujian;
    }

    public void setTgl_ujian(String tgl_ujian) {
        this.tgl_ujian = tgl_ujian;
    }

    public String getTgl_tambah_ujian() {
        return tgl_tambah_ujian;
    }

    public void setTgl_tambah_ujian(String tgl_tambah_ujian) {
        this.tgl_tambah_ujian = tgl_tambah_ujian;
    }

    public String getId_ujian_jadwal() {
        return id_ujian_jadwal;
    }

    public void setId_ujian_jadwal(String id_ujian_jadwal) {
        this.id_ujian_jadwal = id_ujian_jadwal;
    }

    public String getFile_ujian() {
        return file_ujian;
    }

    public void setFile_ujian(String file_ujian) {
        this.file_ujian = file_ujian;
    }

    public String getStatus_ujian() {
        return status_ujian;
    }

    public void setStatus_ujian(String status_ujian) {
        this.status_ujian = status_ujian;
    }

    public String getJumlah_soal() {
        return jumlah_soal;
    }

    public void setJumlah_soal(String jumlah_soal) {
        this.jumlah_soal = jumlah_soal;
    }

    public String getMulai_ujian() {
        return mulai_ujian;
    }

    public void setMulai_ujian(String mulai_ujian) {
        this.mulai_ujian = mulai_ujian;
    }


    public String getJudul_media() {
        return judul_media;
    }

    public void setJudul_media(String judul_media) {
        this.judul_media = judul_media;
    }

    public String getIsi_media() {
        return isi_media;
    }

    public void setIsi_media(String isi_media) {
        this.isi_media = isi_media;
    }

    public String getLink_youtube_media() {
        return link_youtube_media;
    }

    public void setLink_youtube_media(String link_youtube_media) {
        this.link_youtube_media = link_youtube_media;
    }

    public String getFile_media() {
        return file_media;
    }

    public void setFile_media(String file_media) {
        this.file_media = file_media;
    }

    public String getCreated_at_media() {
        return created_at_media;
    }

    public void setCreated_at_media(String created_at_media) {
        this.created_at_media = created_at_media;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getId_games_hewan() {
        return id_games_hewan;
    }

    public void setId_games_hewan(String id_games_hewan) {
        this.id_games_hewan = id_games_hewan;
    }

    public String getInisial() {
        return inisial;
    }

    public void setInisial(String inisial) {
        this.inisial = inisial;
    }

    public String getNama_hewan() {
        return nama_hewan;
    }

    public void setNama_hewan(String nama_hewan) {
        this.nama_hewan = nama_hewan;
    }

    public String getLink_file_hewan() {
        return link_file_hewan;
    }

    public void setLink_file_hewan(String link_file_hewan) {
        this.link_file_hewan = link_file_hewan;
    }

    public String getDeskripsi_foto() {
        return deskripsi_foto;
    }

    public void setDeskripsi_foto(String deskripsi_foto) {
        this.deskripsi_foto = deskripsi_foto;
    }

    public String getCover_loker() {
        return cover_loker;
    }

    public void setCover_loker(String cover_loker) {
        this.cover_loker = cover_loker;
    }

    public String getNamakelas() {
        return namakelas;
    }

    public void setNamakelas(String namakelas) {
        this.namakelas = namakelas;
    }

    public String getNisn() {
        return nisn;
    }

    public void setNisn(String nisn) {
        this.nisn = nisn;
    }

    public String getIdtugas() {
        return idtugas;
    }

    public void setIdtugas(String idtugas) {
        this.idtugas = idtugas;
    }

    public String getFillable() {
        return fillable;
    }

    public void setFillable(String fillable) {
        this.fillable = fillable;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getFile_struktur_org_foto() {
        return file_struktur_org_foto;
    }

    public void setFile_struktur_org_foto(String file_struktur_org_foto) {
        this.file_struktur_org_foto = file_struktur_org_foto;
    }

    public String getId_sumber_pustaka() {
        return id_sumber_pustaka;
    }

    public void setId_sumber_pustaka(String id_sumber_pustaka) {
        this.id_sumber_pustaka = id_sumber_pustaka;
    }

    public String getId_dinas_pendidikan() {
        return id_dinas_pendidikan;
    }

    public void setId_dinas_pendidikan(String id_dinas_pendidikan) {
        this.id_dinas_pendidikan = id_dinas_pendidikan;
    }

    public String getLembaga_sekolah() {
        return lembaga_sekolah;
    }

    public void setLembaga_sekolah(String lembaga_sekolah) {
        this.lembaga_sekolah = lembaga_sekolah;
    }

    public String getJudul_sumber_pustaka() {
        return judul_sumber_pustaka;
    }

    public void setJudul_sumber_pustaka(String judul_sumber_pustaka) {
        this.judul_sumber_pustaka = judul_sumber_pustaka;
    }


    public String getFile_sumber_pustaka() {
        return file_sumber_pustaka;
    }

    public void setFile_sumber_pustaka(String file_sumber_pustaka) {
        this.file_sumber_pustaka = file_sumber_pustaka;
    }

    public String getIsi_sumber_pustaka() {
        return isi_sumber_pustaka;
    }

    public void setIsi_sumber_pustaka(String isi_sumber_pustaka) {
        this.isi_sumber_pustaka = isi_sumber_pustaka;
    }

    public String getStatus_sumber_pustaka() {
        return status_sumber_pustaka;
    }

    public void setStatus_sumber_pustaka(String status_sumber_pustaka) {
        this.status_sumber_pustaka = status_sumber_pustaka;
    }

    public String getCreated_at_sumber_pustaka() {
        return created_at_sumber_pustaka;
    }

    public void setCreated_at_sumber_pustaka(String created_at_sumber_pustaka) {
        this.created_at_sumber_pustaka = created_at_sumber_pustaka;
    }

    public String getLink_youtube_sumber_pustaka() {
        return link_youtube_sumber_pustaka;
    }

    public void setLink_youtube_sumber_pustaka(String link_youtube_sumber_pustaka) {
        this.link_youtube_sumber_pustaka = link_youtube_sumber_pustaka;
    }

    public String getId_sumber_pustaka_penjurusan() {
        return id_sumber_pustaka_penjurusan;
    }

    public void setId_sumber_pustaka_penjurusan(String id_sumber_pustaka_penjurusan) {
        this.id_sumber_pustaka_penjurusan = id_sumber_pustaka_penjurusan;
    }

    public String getNama_penjurusan() {
        return nama_penjurusan;
    }

    public void setNama_penjurusan(String nama_penjurusan) {
        this.nama_penjurusan = nama_penjurusan;
    }
}
