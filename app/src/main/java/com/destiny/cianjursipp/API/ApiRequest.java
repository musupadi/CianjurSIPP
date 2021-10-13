package com.destiny.cianjursipp.API;

import com.destiny.cianjursipp.Model.Alquran.Surah.Alfatihah;
import com.destiny.cianjursipp.Model.DataAbsen;
import com.destiny.cianjursipp.Model.Destiny.ResponseDestiny;
import com.destiny.cianjursipp.Model.Eskul.ResponseData;
import com.destiny.cianjursipp.Model.Essay;
import com.destiny.cianjursipp.Model.Pena.Marketplace.ResponseMarketplace;
import com.destiny.cianjursipp.Model.Pena.NewResponse;
import com.destiny.cianjursipp.Model.Pena.ResponseModel;
import com.destiny.cianjursipp.Model.ResponseDoa;
import com.destiny.cianjursipp.Model.ResponseProduk;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiRequest {
    @FormUrlEncoded
    @POST("auth/login")
    Call<ResponseModel> login(@Field("username") String username,
                              @Field("password") String password);

    @FormUrlEncoded
    @POST("kelas")
    Call<ResponseModel> GetKelas(@Field("id_sekolah") String id_sekolah);


    @Multipart
    @POST("ubahphotoprofil")
    Call<ResponseModel> ChangeFoto(@Header("Authorization") String authHeader,
                                   @Part MultipartBody.Part photo);


    @FormUrlEncoded
    @POST("ubahpassword")
    Call<ResponseModel> UbahPassword(@Header("Authorization") String authHeader,
                                     @Field("passwordNew") String passwordNew,
                                     @Field("passwordConfirm") String passwordConfirm,
                                     @Field("passwordOld") String passwordOld);

    @FormUrlEncoded
    @POST("supray/puskomdik")
    Call<ResponseDestiny> Checkers(@Header("Authorization") String authHeader,
                                   @Field("supri_key") String supri_key);

    @POST("evadir")
    Call<ResponseModel> Evadir(@Header("Authorization") String authHeader,
                               @Query("id_evadir[]") ArrayList<String> id_evadir,
                               @Query("skor[]") ArrayList<String> skor,
                               @Query("id_kategori[]") ArrayList<String> id_kategori);

    @FormUrlEncoded
    @POST("kelasmuridabsen")
    Call<ResponseModel> KelasMuridAbsen(@Header("Authorization") String authHeader,
                                        @Field("idkelas") String idkelas,
                                        @Field("idmapel") String idmapel,
                                        @Field("piket") String piket,
                                        @Field("idguru") String idguru,
                                        @Field("idsiswa[]") ArrayList<String> idsiswa,
                                        @Field("status[]") ArrayList<String> status,
                                        @Field("alasan[]") ArrayList<String> alasan);

    @FormUrlEncoded
    @POST("tugas")
    Call<ResponseModel> TugasPG(@Header("Authorization") String authHeader,
                                @Field("id_tugas") String id_tugas,
                                @Query("jawaban[]") ArrayList<String> jawab);


    @FormUrlEncoded
    @POST("penulusurankelulusan")
    Call<ResponseModel> PostPenelusuranLulusan(@Header("Authorization") String authHeader,
                                @Field("isi") String isi);

    @FormUrlEncoded
    @POST("ujian")
    Call<ResponseModel> Ujian(@Header("Authorization") String authHeader,
                                @Field("id_ujian") String id_ujian,
                                @Field("id_ujian_jadwal") String id_ujian_jadwal,
                                @Query("jawaban[]") ArrayList<String> jawab);

    @Multipart
    @POST("tugas")
    Call<ResponseModel> Tugas(@Header("Authorization") String authHeader,
                              @Part("id_tugas") RequestBody id_tugas,
                              @Part("text") RequestBody text,
                              @Part MultipartBody.Part photo);

    @Multipart
    @POST("tugas")
    Call<ResponseModel> Tugas(@Header("Authorization") String authHeader,
                              @Part("id_tugas") RequestBody id_tugas,
                              @Part("text") RequestBody text,
                              @Part MultipartBody.Part photo,
                              @Part MultipartBody.Part photo2);

    @Multipart
    @POST("tugas")
    Call<ResponseModel> Tugas(@Header("Authorization") String authHeader,
                              @Part("id_tugas") RequestBody id_tugas,
                              @Part("text") RequestBody text,
                              @Part MultipartBody.Part photo,
                              @Part MultipartBody.Part photo2,
                              @Part MultipartBody.Part photo3);

    @Multipart
    @POST("tugas")
    Call<ResponseModel> Tugas(@Header("Authorization") String authHeader,
                              @Part("id_tugas") RequestBody id_tugas,
                              @Part("text") RequestBody text,
                              @Part MultipartBody.Part photo,
                              @Part MultipartBody.Part photo2,
                              @Part MultipartBody.Part photo3,
                              @Part MultipartBody.Part photo4);

    //GET
    @GET("ekskul?self=true")
    Call<ResponseData> EskulAll(@Header("Authorization") String authHeader);

    @GET("ekskul_anggota")
    Call<ResponseModel> AnggotaEskul(@Header("Authorization") String authHeader,
                                    @Query("id_ekskul") String idEskul);

    @GET("live_streaming")
    Call<ResponseModel> LiveStreaming(@Header("Authorization") String authHeader);

    @GET("ekskul")
    Call<ResponseData> EskulAll(@Header("Authorization") String authHeader,
                                @Query("id_eskul") String idEskul);

    @GET("kelasmuridabsen")
    Call<ResponseData> SiswaKelas(@Header("Authorization") String authHeader,
                                @Query("idkelas") String idkelas);

    @GET("sumber_pustaka")
    Call<ResponseModel> SumberPustaka(@Header("Authorization") String authHeader,
                                      @Query("search") String search,
                                      @Query("lembaga_sekolah") String lembaga_sekolah,
                                      @Query("id_jurusan") String id_jurusan,
                                      @Query("kelas_ke") String kelas_ke);
    @GET("sumber_pustaka")
    Call<ResponseModel> SumberPustaka(@Header("Authorization") String authHeader);

    @GET("sumber_pustaka_kelas")
    Call<ResponseModel> SumberPustakaKelas(@Header("Authorization") String authHeader);

    @GET("sumber_pustaka_lembaga")
    Call<ResponseModel> SumberPustakaLembaga(@Header("Authorization") String authHeader);

    @GET("sumber_pustaka_jurusan")
    Call<ResponseModel> SumberPustakaJurusan(@Header("Authorization") String authHeader);

    @GET("ekskul_self")
    Call<ResponseModel> EskulSelf(@Header("Authorization") String authHeader);

    @GET("quran")
    Call<ResponseDoa> Quran();

    @GET("lowongankerja")
    Call<ResponseModel> Loker(@Header("Authorization") String authHeader);

    @GET("produksiswa")
    Call<ResponseMarketplace> ProdukSiswa(@Header("Authorization") String authHeader);


    @GET("produk")
    Call<ResponseProduk> Produk(@Header("Authorization") String authHeader);

    @GET("tahunajaranppdb")
    Call<ResponseModel> TahunAjaran(@Header("Authorization") String authHeader);

    @GET("kelasabsen")
    Call<ResponseModel> KelasAbsen(@Header("Authorization") String authHeader);



    @GET("evadir")
    Call<ResponseModel> Evadir(@Header("Authorization") String authHeader);

    @GET("mapelkelasabsen")
    Call<ResponseModel> GuruMapelAbsen1(@Header("Authorization") String authHeader,@Query("idkelas") String idkelas);

    @GET("kelasmuridabsen")
    Call<ResponseModel> GuruMapelAbsen(@Header("Authorization") String authHeader,
                                       @Query("idkelas") String idkelas);

    @GET("gurungaji")
    Call<ResponseModel> GuruNgaji(@Header("Authorization") String authHeader,
                                  @Query("byiddaerah") String byiddaerah);

    @GET("kabarsekolah")
    Call<ResponseModel> KabarSekolah(@Header("Authorization") String authHeader);

    @GET("strukturoganisasi")
    Call<ResponseModel> StrukturSekolah(@Header("Authorization") String authHeader);

    @GET("strukturoganisasisekolah")
    Call<ResponseModel> StrukturOrganisasi(@Header("Authorization") String authHeader);

    @GET("prestasi")
    Call<ResponseModel> Prestasi(@Header("Authorization") String authHeader);

    @GET("getmapelkelasjadwal")
    Call<ResponseModel> GetMapel(@Header("Authorization") String authHeader,
                                 @Query("tglKelas") String tglKelas);

    @GET("sponsorsekolah")
    Call<ResponseModel> SponsorSekolah(@Header("Authorization") String authHeader,
                                       @Query("idsponsor") String idsponsor);

    @GET("kamis_nyunda")
    Call<ResponseModel> GetKemisNyunda(@Header("Authorization") String authHeader,
                                       @Query("tipe") String tipe);

    @GET("mediainformasi")
    Call<ResponseModel> GetMediaInformasi(@Header("Authorization") String authHeader,
                                          @Query("tipe_media") String tipe_media);

    @GET("khutbah")
    Call<ResponseModel> GetKhutbah(@Header("Authorization") String authHeader);

    @GET("teman")
    Call<ResponseModel> Teman(@Header("Authorization") String authHeader,
                              @Query("idKelas") String idKelas);

    @GET("raport")
    Call<ResponseModel> Raport(@Header("Authorization") String authHeader);

    @GET("mediapembelajaran")
    Call<ResponseModel> MediaPembelajaran(@Header("Authorization") String authHeader,
                                          @Query("tema_kategori") String tema_kategori);

    @GET("mediapembelajaran")
    Call<ResponseModel> NewMediaPembelajaran(@Header("Authorization") String authHeader,
                                          @Query("idmapel") String idmapel);


    @GET("poinsiswa")
    Call<ResponseModel> PointSiswa(@Header("Authorization") String authHeader);

    @GET("getmapeltugas")
    Call<ResponseModel> MapelTugas(@Header("Authorization") String authHeader);

    @GET("agenda_sekolah")
    Call<ResponseModel> AgendaSekolah(@Header("Authorization") String authHeader);

    @GET("tugas")
    Call<ResponseModel> NewTugas(@Header("Authorization") String authHeader,
                              @Query("idmapel") String id_tugas);

    @GET("ujian")
    Call<ResponseModel> Ujian(@Header("Authorization") String authHeader);

    @GET("ujian")
    Call<ResponseModel> Ujian(@Header("Authorization") String authHeader,
                              @Query("tipe_ujian") String tipe_ujian);

    @GET("tugassoal")
    Call<ResponseModel> Tugas(@Header("Authorization") String authHeader,
                            @Query("id_tugas") String id_tugas);

    @GET("tugassoal")
    Call<Essay> TugasEssay(@Header("Authorization") String authHeader,
                           @Query("id_tugas") String id_tugas);

    @GET("gallery")
    Call<ResponseModel> Gallery(@Header("Authorization") String authHeader);

    @GET("gurusekolah")
    Call<ResponseModel> GuruSekolah(@Header("Authorization") String authHeader);

    @GET("gameshewan")
    Call<ResponseModel> GameHewan(@Header("Authorization") String authHeader);

    @GET("profilsekolah")
    Call<ResponseModel> ProfileSekolah(@Header("Authorization") String authHeader);

    @GET("infodisdik")
    Call<ResponseModel> InfoDisdik(@Header("Authorization") String authHeader);

    @GET("sponsor")
    Call<ResponseModel> Sponsor(@Header("Authorization") String authHeader);

    @GET("sekolahguest")
    Call<ResponseModel> SekolahGuest(@Query("lembaga") String lembaga,
                                     @Query("searchbyname") String searchbyname);

    @GET("banner")
    Call<ResponseModel> Banner();

    @GET("info_publik")
    Call<ResponseModel> InfoPublik();

    @GET("daerah")
    Call<ResponseModel> Daerah(@Header("Authorization") String authHeader);

    @GET("penulusurankelulusan")
    Call<ResponseModel> GetPenelusuranLulusan(@Header("Authorization") String authHeader);

    @Multipart
    @POST("izin")
    Call<ResponseModel> Izins(@Header("Authorization") String authHeader,
                              @Part("namaIzin") RequestBody nama_izin,
                              @Part("deskripsiIzin") RequestBody deskripsi_izin,
                              @Part("tanggalMulai") RequestBody tanggalMulai,
                              @Part("tanggalAkhir") RequestBody tanggalAkhir,
                              @Part MultipartBody.Part fileIzin);

    //Tambah Produk
    @Multipart
    @POST("produksiswa")
    Call<ResponseModel> ProdukSiswa(@Header("Authorization") String authHeader,
                                    @Part("namaproduk") RequestBody namaproduk,
                                    @Part("deskripsiproduk") RequestBody deskripsiproduk,
                                    @Part("hargaproduk") RequestBody hargaproduk,
                                    @Part("nomorteleponpengurus") RequestBody nomorteleponpengurus,
                                    @Part MultipartBody.Part Foto1,
                                    @Part MultipartBody.Part Foto2,
                                    @Part("photodeskripsi[]") RequestBody photodeskripsi2);

    @Multipart
    @POST("produksiswa")
    Call<ResponseModel> ProdukSiswa(@Header("Authorization") String authHeader,
                                     @Part("namaproduk") RequestBody namaproduk,
                                     @Part("deskripsiproduk") RequestBody deskripsiproduk,
                                     @Part("hargaproduk") RequestBody hargaproduk,
                                     @Part("nomorteleponpengurus") RequestBody nomorteleponpengurus,
                                     @Part MultipartBody.Part Foto1,
                                     @Part MultipartBody.Part Foto2,
                                     @Part("photodeskripsi[]") RequestBody photodeskripsi2,
                                     @Part MultipartBody.Part Foto3,
                                     @Part("photodeskripsi[]") RequestBody photodeskripsi3);

    @Multipart
    @POST("produksiswa")
    Call<ResponseModel> ProdukSiswa(@Header("Authorization") String authHeader,
                                     @Part("namaproduk") RequestBody namaproduk,
                                     @Part("deskripsiproduk") RequestBody deskripsiproduk,
                                     @Part("hargaproduk") RequestBody hargaproduk,
                                     @Part("nomorteleponpengurus") RequestBody nomorteleponpengurus,
                                     @Part MultipartBody.Part Foto1,
                                     @Part MultipartBody.Part Foto2,
                                     @Part("photodeskripsi[]") RequestBody photodeskripsi2,
                                     @Part MultipartBody.Part Foto3,
                                     @Part("photodeskripsi[]") RequestBody photodeskripsi3,
                                     @Part MultipartBody.Part Foto4,
                                     @Part("photodeskripsi[]") RequestBody photodeskripsi4);


    @Multipart
    @POST("ppdb")
    Call<ResponseModel> PPDB(@Header("Authorization") String authHeader,
                             //New
                             @Part("agama") RequestBody agama,
                             @Part("rt") RequestBody rt,
                             @Part("rw") RequestBody rw,
                             @Part("kecamatan") RequestBody kecamatan,
                             @Part("tahunlahir_ayah") RequestBody tahunlahir_ayah,
                             @Part("pendidikan_ayah") RequestBody pendidikan_ayah,
                             @Part("pekerjaan_ayah") RequestBody pekerjaan_ayah,
                             @Part("penghasilan_ayah") RequestBody penghasilan_ayah,
                             @Part("nik_ayah") RequestBody nik_ayah,
                             @Part("tahunlahir_ibu") RequestBody tahunlahir_ibu,
                             @Part("pendidikan_ibu") RequestBody pendidikan_ibu,
                             @Part("pekerjaan_ibu") RequestBody pekerjaan_ibu,
                             @Part("penghasilan_ibu") RequestBody penghasilan_ibu,
                             @Part("nik_ibu") RequestBody nik_ibu,
                             @Part("kebutuhan_khusus") RequestBody kebutuhan_khusus,
                             @Part("sekolah_asal") RequestBody sekolah_asal,
                             @Part("anak_keberapa") RequestBody anak_keberapa,
                             @Part("berat_badan") RequestBody berat_badan,
                             @Part("tinggi_badan") RequestBody tinggi_badan,
                             @Part("lingkar_kepala") RequestBody lingkar_kepala,
                             @Part("jumlah_saudara_kandung") RequestBody jumlah_saudara_kandung,
                             @Part("jarak_rumah_sekolah") RequestBody jarak_rumah_sekolah,
                             //New
                             @Part("tahunajaranid") RequestBody tahunajaranid,
                             @Part("alamat") RequestBody alamat,
                             @Part("email") RequestBody email,
                             @Part("nomorujian") RequestBody nomorujian,
                             @Part("nisn") RequestBody nisn,
                             @Part("nik") RequestBody nik,
                             @Part("idsekolah") RequestBody idsekolah,
                             @Part("namalengkapsiswa") RequestBody namalengkapsiswa,
                             @Part("nomortelepon") RequestBody nomortelepon,
                             @Part("jeniskelamin") RequestBody jeniskelamin,
                             @Part("tempatlahir") RequestBody tempatlahir,
                             @Part("tanggallahir") RequestBody tanggallahir,
                             @Part("namaayahkandung") RequestBody namaayahkandung,
                             @Part("namaibukandung") RequestBody namaibukandung,
                             @Part("whatsappwaliortu") RequestBody whatsappwaliortu);

    @Multipart
    @POST("ppdb")
    Call<ResponseModel> PPDB(@Header("Authorization") String authHeader,
                             //New
                             @Part("agama") RequestBody agama,
                             @Part("rt") RequestBody rt,
                             @Part("rw") RequestBody rw,
                             @Part("kecamatan") RequestBody kecamatan,
                             @Part("tahunlahir_ayah") RequestBody tahunlahir_ayah,
                             @Part("pendidikan_ayah") RequestBody pendidikan_ayah,
                             @Part("pekerjaan_ayah") RequestBody pekerjaan_ayah,
                             @Part("penghasilan_ayah") RequestBody penghasilan_ayah,
                             @Part("nik_ayah") RequestBody nik_ayah,
                             @Part("tahunlahir_ibu") RequestBody tahunlahir_ibu,
                             @Part("pendidikan_ibu") RequestBody pendidikan_ibu,
                             @Part("pekerjaan_ibu") RequestBody pekerjaan_ibu,
                             @Part("penghasilan_ibu") RequestBody penghasilan_ibu,
                             @Part("nik_ibu") RequestBody nik_ibu,
                             @Part("kebutuhan_khusus") RequestBody kebutuhan_khusus,
                             @Part("sekolah_asal") RequestBody sekolah_asal,
                             @Part("anak_keberapa") RequestBody anak_keberapa,
                             @Part("berat_badan") RequestBody berat_badan,
                             @Part("tinggi_badan") RequestBody tinggi_badan,
                             @Part("lingkar_kepala") RequestBody lingkar_kepala,
                             @Part("jumlah_saudara_kandung") RequestBody jumlah_saudara_kandung,
                             @Part("jarak_rumah_sekolah") RequestBody jarak_rumah_sekolah,
                             //New
                             @Part("tahunajaranid") RequestBody tahunajaranid,
                             @Part("alamat") RequestBody alamat,
                             @Part("email") RequestBody email,
                             @Part("nomorujian") RequestBody nama_izin,
                             @Part("nisn") RequestBody deskripsi_izin,
                             @Part("nik") RequestBody tanggalMulai,
                             @Part("idsekolah") RequestBody idsekolah,
                             @Part("namalengkapsiswa") RequestBody namalengkapsiswa,
                             @Part("nomortelepon") RequestBody nomortelepon,
                             @Part("jeniskelamin") RequestBody jeniskelamin,
                             @Part("tempatlahir") RequestBody tempatlahir,
                             @Part("tanggallahir") RequestBody tanggallahir,
                             @Part("namaayahkandung") RequestBody namaayahkandung,
                             @Part("namaibukandung") RequestBody namaibukandung,
                             @Part("whatsappwaliortu") RequestBody whatsappwaliortu,
                             @Part MultipartBody.Part Foto1);

    @Multipart
    @POST("ppdb")
    Call<ResponseModel> PPDB(@Header("Authorization") String authHeader,
                             //New
                             @Part("agama") RequestBody agama,
                             @Part("rt") RequestBody rt,
                             @Part("rw") RequestBody rw,
                             @Part("kecamatan") RequestBody kecamatan,
                             @Part("tahunlahir_ayah") RequestBody tahunlahir_ayah,
                             @Part("pendidikan_ayah") RequestBody pendidikan_ayah,
                             @Part("pekerjaan_ayah") RequestBody pekerjaan_ayah,
                             @Part("penghasilan_ayah") RequestBody penghasilan_ayah,
                             @Part("nik_ayah") RequestBody nik_ayah,
                             @Part("tahunlahir_ibu") RequestBody tahunlahir_ibu,
                             @Part("pendidikan_ibu") RequestBody pendidikan_ibu,
                             @Part("pekerjaan_ibu") RequestBody pekerjaan_ibu,
                             @Part("penghasilan_ibu") RequestBody penghasilan_ibu,
                             @Part("nik_ibu") RequestBody nik_ibu,
                             @Part("kebutuhan_khusus") RequestBody kebutuhan_khusus,
                             @Part("sekolah_asal") RequestBody sekolah_asal,
                             @Part("anak_keberapa") RequestBody anak_keberapa,
                             @Part("berat_badan") RequestBody berat_badan,
                             @Part("tinggi_badan") RequestBody tinggi_badan,
                             @Part("lingkar_kepala") RequestBody lingkar_kepala,
                             @Part("jumlah_saudara_kandung") RequestBody jumlah_saudara_kandung,
                             @Part("jarak_rumah_sekolah") RequestBody jarak_rumah_sekolah,
                             //New
                             @Part("tahunajaranid") RequestBody tahunajaranid,
                             @Part("alamat") RequestBody alamat,
                             @Part("email") RequestBody email,
                             @Part("nomorujian") RequestBody nama_izin,
                             @Part("nisn") RequestBody deskripsi_izin,
                             @Part("nik") RequestBody tanggalMulai,
                             @Part("idsekolah") RequestBody idsekolah,
                             @Part("namalengkapsiswa") RequestBody namalengkapsiswa,
                             @Part("nomortelepon") RequestBody nomortelepon,
                             @Part("jeniskelamin") RequestBody jeniskelamin,
                             @Part("tempatlahir") RequestBody tempatlahir,
                             @Part("tanggallahir") RequestBody tanggallahir,
                             @Part("namaayahkandung") RequestBody namaayahkandung,
                             @Part("namaibukandung") RequestBody namaibukandung,
                             @Part("whatsappwaliortu") RequestBody whatsappwaliortu,
                             @Part MultipartBody.Part Foto1,
                             @Part MultipartBody.Part foto2);

    @Multipart
    @POST("ppdb")
    Call<ResponseModel> PPDB(@Header("Authorization") String authHeader,
                             //New
                             @Part("agama") RequestBody agama,
                             @Part("rt") RequestBody rt,
                             @Part("rw") RequestBody rw,
                             @Part("kecamatan") RequestBody kecamatan,
                             @Part("tahunlahir_ayah") RequestBody tahunlahir_ayah,
                             @Part("pendidikan_ayah") RequestBody pendidikan_ayah,
                             @Part("pekerjaan_ayah") RequestBody pekerjaan_ayah,
                             @Part("penghasilan_ayah") RequestBody penghasilan_ayah,
                             @Part("nik_ayah") RequestBody nik_ayah,
                             @Part("tahunlahir_ibu") RequestBody tahunlahir_ibu,
                             @Part("pendidikan_ibu") RequestBody pendidikan_ibu,
                             @Part("pekerjaan_ibu") RequestBody pekerjaan_ibu,
                             @Part("penghasilan_ibu") RequestBody penghasilan_ibu,
                             @Part("nik_ibu") RequestBody nik_ibu,
                             @Part("kebutuhan_khusus") RequestBody kebutuhan_khusus,
                             @Part("sekolah_asal") RequestBody sekolah_asal,
                             @Part("anak_keberapa") RequestBody anak_keberapa,
                             @Part("berat_badan") RequestBody berat_badan,
                             @Part("tinggi_badan") RequestBody tinggi_badan,
                             @Part("lingkar_kepala") RequestBody lingkar_kepala,
                             @Part("jumlah_saudara_kandung") RequestBody jumlah_saudara_kandung,
                             @Part("jarak_rumah_sekolah") RequestBody jarak_rumah_sekolah,
                             //New
                             @Part("tahunajaranid") RequestBody tahunajaranid,
                             @Part("alamat") RequestBody alamat,
                             @Part("email") RequestBody email,
                             @Part("nomorujian") RequestBody nama_izin,
                             @Part("nisn") RequestBody deskripsi_izin,
                             @Part("nik") RequestBody tanggalMulai,
                             @Part("idsekolah") RequestBody idsekolah,
                             @Part("namalengkapsiswa") RequestBody namalengkapsiswa,
                             @Part("nomortelepon") RequestBody nomortelepon,
                             @Part("jeniskelamin") RequestBody jeniskelamin,
                             @Part("tempatlahir") RequestBody tempatlahir,
                             @Part("tanggallahir") RequestBody tanggallahir,
                             @Part("namaayahkandung") RequestBody namaayahkandung,
                             @Part("namaibukandung") RequestBody namaibukandung,
                             @Part("whatsappwaliortu") RequestBody whatsappwaliortu,
                             @Part MultipartBody.Part Foto1,
                             @Part MultipartBody.Part foto2,
                             @Part MultipartBody.Part Foto3);

    @Multipart
    @POST("ppdb")
    Call<ResponseModel> PPDB(@Header("Authorization") String authHeader,
                             //New
                             @Part("agama") RequestBody agama,
                             @Part("rt") RequestBody rt,
                             @Part("rw") RequestBody rw,
                             @Part("kecamatan") RequestBody kecamatan,
                             @Part("tahunlahir_ayah") RequestBody tahunlahir_ayah,
                             @Part("pendidikan_ayah") RequestBody pendidikan_ayah,
                             @Part("pekerjaan_ayah") RequestBody pekerjaan_ayah,
                             @Part("penghasilan_ayah") RequestBody penghasilan_ayah,
                             @Part("nik_ayah") RequestBody nik_ayah,
                             @Part("tahunlahir_ibu") RequestBody tahunlahir_ibu,
                             @Part("pendidikan_ibu") RequestBody pendidikan_ibu,
                             @Part("pekerjaan_ibu") RequestBody pekerjaan_ibu,
                             @Part("penghasilan_ibu") RequestBody penghasilan_ibu,
                             @Part("nik_ibu") RequestBody nik_ibu,
                             @Part("kebutuhan_khusus") RequestBody kebutuhan_khusus,
                             @Part("sekolah_asal") RequestBody sekolah_asal,
                             @Part("anak_keberapa") RequestBody anak_keberapa,
                             @Part("berat_badan") RequestBody berat_badan,
                             @Part("tinggi_badan") RequestBody tinggi_badan,
                             @Part("lingkar_kepala") RequestBody lingkar_kepala,
                             @Part("jumlah_saudara_kandung") RequestBody jumlah_saudara_kandung,
                             @Part("jarak_rumah_sekolah") RequestBody jarak_rumah_sekolah,
                             //New
                             @Part("tahunajaranid") RequestBody tahunajaranid,
                             @Part("alamat") RequestBody alamat,
                             @Part("email") RequestBody email,
                             @Part("nomorujian") RequestBody nama_izin,
                             @Part("nisn") RequestBody deskripsi_izin,
                             @Part("nik") RequestBody tanggalMulai,
                             @Part("idsekolah") RequestBody idsekolah,
                             @Part("namalengkapsiswa") RequestBody namalengkapsiswa,
                             @Part("nomortelepon") RequestBody nomortelepon,
                             @Part("jeniskelamin") RequestBody jeniskelamin,
                             @Part("tempatlahir") RequestBody tempatlahir,
                             @Part("tanggallahir") RequestBody tanggallahir,
                             @Part("namaayahkandung") RequestBody namaayahkandung,
                             @Part("namaibukandung") RequestBody namaibukandung,
                             @Part("whatsappwaliortu") RequestBody whatsappwaliortu,
                             @Part MultipartBody.Part Foto1,
                             @Part MultipartBody.Part foto2,
                             @Part MultipartBody.Part Foto3,
                             @Part MultipartBody.Part Foto4);

    //FAJAR KONTOL
    @FormUrlEncoded
    @POST("info_dinas")
    Call<ResponseModel> InfoDinas(@Field("id_daerah") String id_daerah,
                                  @Field("tipe_info") String tipe_info);

//    @FormUrlEncoded
//    @POST("izin")
//    Call<ResponseModel> Izin(@Field("id_siswa") String id_siswa,
//                                  @Field("nama_izin") String nama_izin,
//                                  @Field("deskripsi_izin") String deskripsi_izin,
//                                  @Field("tgl_mulai") String tgl_mulai,
//                                  @Field("tgl_akhir") String tgl_akhir,
//                                  @Field("file_izin") String file_izin);

    @Multipart
    @POST("izin")
    Call<ResponseModel> Izin(@Part("id_siswa") RequestBody id_siswa,
                             @Part("nama_izin") RequestBody nama_izin,
                             @Part("deskripsi_izin") RequestBody deskripsi_izin,
                             @Part("tgl_mulai") RequestBody tgl_mulai,
                             @Part("tgl_akhir") RequestBody tgl_akhir,
                             @Part MultipartBody.Part photo);



    //Quran API
    @GET("1.json")
    Call<Alfatihah> Alfatihah();
}
