package com.destiny.cianjursipp.Activity.ui;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.destiny.cianjursipp.API.ApiRequest;
import com.destiny.cianjursipp.API.FajarKontol;
import com.destiny.cianjursipp.API.RetroServer;
import com.destiny.cianjursipp.Activity.Berita.KabarBeritaActivity;
import com.destiny.cianjursipp.Activity.LoginActivity;
import com.destiny.cianjursipp.Activity.MainActivity;
import com.destiny.cianjursipp.Activity.Marketplace.MarketplaceActivity;
import com.destiny.cianjursipp.Activity.Sponsor.SponsorActivity;
import com.destiny.cianjursipp.Activity.menu.PPDB.FormulirPPDBActivity;
import com.destiny.cianjursipp.Activity.menu.Ujian.Ujian2Activity;
import com.destiny.cianjursipp.Activity.menu.Ujian.UjianActivity;
import com.destiny.cianjursipp.Adapter.Home.AdapterBanner;
import com.destiny.cianjursipp.Adapter.Home.AdapterKabarBerita;
import com.destiny.cianjursipp.Adapter.Home.AdapterSponsor;
import com.destiny.cianjursipp.Adapter.Universal.AdapterProduk;
import com.destiny.cianjursipp.Method.Destiny;
import com.destiny.cianjursipp.Model.DataModel;
import com.destiny.cianjursipp.Model.Pena.Marketplace.ProdukModel;
import com.destiny.cianjursipp.Model.Pena.Marketplace.ResponseMarketplace;
import com.destiny.cianjursipp.Model.Pena.ResponseModel;
import com.destiny.cianjursipp.Model.Produk;
import com.destiny.cianjursipp.Model.ResponseProduk;
import com.destiny.cianjursipp.R;
import com.destiny.cianjursipp.SharedPreferance.DB_Helper;
import com.destiny.cianjursipp.Splash.SliderAdapter;
import com.destiny.cianjursipp.Splash.SliderAdapter2;
import com.destiny.cianjursipp.Splash.SplashActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {
    Switch SwitchMasuk;
    ImageView Logo;
    TextView CheckMasuk,Poin,SekolahBesar,Sekolah;
    LinearLayout ProfilSekolah,AgendaSekolah,Prestasi,PPDB,StrukturSekolah,JadwalPelajaran,Eskul,MediaPembelajaran,Tugas,LowonganKerja,LihatSemua;
    LinearLayout DProfilSekolah,DAgendaSekolah,DPrestasi,DPPDB,DStrukturSekolah,DJadwalPelajaran,DEvadir,DMediaPembelajaran,DTugas,DGuru,DBiayaAkademik,DPembayaran,DROB,DERaport,DGallery,DLowonganKerja,DEskul,DUjian,DTryout;
    LinearLayout KemisNyunda,JumatNgaji,MediaInformasi;
    Dialog dialog2;
    TextView Jawaban;
    EditText etJawaban;
    Button SubmitJawaban,TutupJawaban;
    //Dialog
    Dialog dialog;
    Button Kembali;
    Destiny destiny;
    TextView nama,namaSiswa;
    DB_Helper dbHelper;
    String Username,Password,Nama,Token,Level,Photo;
    RecyclerView recyclerKabar,recylerSponsor,recyclerGallery,recyclerProduk;
    LottieAnimationView AHeader,ABerita,ASponsor,AProduk;
    LinearLayout LAHeader,LABerita,LASponsor,LAProduk;
    TextView TAHeader,TABerita,TASponsor,TAProduk;
    LinearLayout infoDinas;
    private List<DataModel> mItems = new ArrayList<>();
    private List<DataModel> mItems2 = new ArrayList<>();
    private List<DataModel> mItems3 = new ArrayList<>();
    private List<ProdukModel> Marketplace = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    LinearLayout LihatSemuaKabarBerita,LihatSemuaSponsor,LihatSemuaGallery,LihatSemuaProduk;
    String ID;

    ImageView ArrowForward,ArrowBack;
    //Slider
    private ViewPager mSlideViewPager2;
    private LinearLayout mDotLayout2;
    private SliderAdapter2 sliderAdapter2;


    private ViewPager mSlideViewPager;
    private LinearLayout mDotLayout;
    private AdapterBanner adapterBanner;
    private Button btnBack, btnNext, btnPlay, btnPause, btnStop;
    private TextView[] mDots;
    private int CurrentPage;

    int SizeBanner = 0;
    boolean forward;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        destiny = new Destiny();
        recyclerKabar = view.findViewById(R.id.recyclerKabarBerita);
        recylerSponsor = view.findViewById(R.id.recyclerSponsor);
        recyclerGallery = view.findViewById(R.id.recyclerGallery);
        recyclerProduk = view.findViewById(R.id.recyclerKabarProduk);
        nama = view.findViewById(R.id.tvNama);
        namaSiswa = view.findViewById(R.id.tvNamaSiswa);
        SwitchMasuk = view.findViewById(R.id.switchMasuk);
        CheckMasuk = view.findViewById(R.id.tvCheckMasuk);
        ProfilSekolah = view.findViewById(R.id.linearProfilSekolah);
        AgendaSekolah = view.findViewById(R.id.linearAgendaSekolah);
        Prestasi = view.findViewById(R.id.linearPrestasi);
        PPDB = view.findViewById(R.id.linearPPDB);
        StrukturSekolah = view.findViewById(R.id.linearStrukturOrganisasi);
        JadwalPelajaran = view.findViewById(R.id.linearJadwalPelajaran);
        Eskul = view.findViewById(R.id.linearEskul);
        MediaPembelajaran = view.findViewById(R.id.linearMediaPembelajaran);
        Tugas = view.findViewById(R.id.linearTugas);
        LihatSemua = view.findViewById(R.id.linearLihatSemua);
        LihatSemuaKabarBerita = view.findViewById(R.id.linearLihatSemuaKabarBerita);
        LihatSemuaSponsor = view.findViewById(R.id.linearLihatSemuaSponsor);
        LihatSemuaGallery = view.findViewById(R.id.linearLihatGallery);
        LihatSemuaProduk = view.findViewById(R.id.linearLihatSemuaProduk);
        Poin = view.findViewById(R.id.tvPoint);
        Logo = view.findViewById(R.id.ivLogoSekolah);
        SekolahBesar = view.findViewById(R.id.tvSekolahBesar);
        Sekolah = view.findViewById(R.id.tvNamaSekolah);
        infoDinas = view.findViewById(R.id.linearInfoDinasPendidikan);
        KemisNyunda = view.findViewById(R.id.linearKebudayaan);
        JumatNgaji = view.findViewById(R.id.linearJumatNgaji);
        MediaInformasi = view.findViewById(R.id.linearMediaInformasi);
        ArrowForward = view.findViewById(R.id.ArrowForward);
        ArrowBack = view.findViewById(R.id.ArrowBack);
//        LowonganKerja = view.findViewById(R.id.linearLoker);

        //Slider
        mSlideViewPager = view.findViewById(R.id.SlideViewPager);
        mDotLayout = view.findViewById(R.id.dotSlayout);
        mSlideViewPager2 = view.findViewById(R.id.SlideViewPager2);
        mDotLayout2 = view.findViewById(R.id.dotSlayout2);

        //ANIM
        AHeader = view.findViewById(R.id.lottieHeader);
        LAHeader = view.findViewById(R.id.linearAHeader);
        TAHeader = view.findViewById(R.id.tvAHeader);
        ABerita = view.findViewById(R.id.lottieBerita);
        LABerita = view.findViewById(R.id.linearABerita);
        TABerita = view.findViewById(R.id.tvABerita);
        ASponsor = view.findViewById(R.id.lottieSponsor);
        LASponsor = view.findViewById(R.id.linearASponsor);
        TASponsor = view.findViewById(R.id.tvASponsor);
        AProduk = view.findViewById(R.id.lottieProduk);
        LAProduk = view.findViewById(R.id.linearAProduk);
        TAProduk = view.findViewById(R.id.tvAProduk);


        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_menu_all);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.btn_rounded_white);

        Kembali = dialog.findViewById(R.id.btnKembali);
        dbHelper = new DB_Helper(getActivity());
        Cursor cursor = dbHelper.checkUser();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                Username = cursor.getString(0);
                Password = cursor.getString(1);
                Nama = cursor.getString(2);
                Token = cursor.getString(3);
                Level = cursor.getString(4);
                Photo = cursor.getString(5);
            }
        }
        nama.setText("Halo, "+Nama);
        namaSiswa.setText(Nama);
        DIALOG();
        if (SwitchMasuk.isChecked()){
            CheckMasuk.setText("Masuk");
        }else{
            CheckMasuk.setText("Tidak\nMasuk");
        }
        SwitchMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SwitchMasuk.setChecked(true);
                if (SwitchMasuk.isChecked()){
                    CheckMasuk.setText("Masuk");
                }else{
                    CheckMasuk.setText("Tidak\nMasuk");
                }
            }
        });
        if (Level.equals("guest")){
            JadwalPelajaran.setAlpha(0.2f);
            DJadwalPelajaran.setAlpha(0.2f);
            Eskul.setAlpha(0.2f);
            DEvadir.setAlpha(0.2f);
            Tugas.setAlpha(0.2f);
            DTugas.setAlpha(0.2f);
            DERaport.setAlpha(0.2f);
            DPembayaran.setAlpha(0.2f);
        }else if(Level.equals("guru")){
            Eskul.setAlpha(0.2f);
        }
        ONCLICK();
//        ONCLICKDIALOG();
        Gallery();
        KabarBerita();
        Sponsor();
        //Removed
        GetPoint();
        //Removed
        GetSekolah();
        Produk();
        Header(0);
        AutoSlide();


    }
    private void Produk(){
        mManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
        recyclerProduk.setLayoutManager(mManager);
        LAProduk.setVisibility(View.VISIBLE);
        TAProduk.setVisibility(View.GONE);
        AProduk.setAnimation("loading.json");
        AProduk.playAnimation();
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseMarketplace> Market = api.ProdukSiswa(destiny.AUTH(Token));
        Market.enqueue(new Callback<ResponseMarketplace>() {
            @Override
            public void onResponse(Call<ResponseMarketplace> call, Response<ResponseMarketplace> response) {
                try {
                    if (response.body().getStatusCode().equals("000")){
                        Marketplace=response.body().getData();
                        if (Marketplace.size()<1){
                            TAProduk.setVisibility(View.VISIBLE);
                            TAProduk.setText("Berita Belum Ada");
                            AProduk.setAnimation("notfound.json");
                            AProduk.playAnimation();
                        }else{
                            LAProduk.setVisibility(View.GONE);
                            mAdapter = new AdapterProduk(getActivity(),Marketplace);
                            recyclerProduk.setAdapter(mAdapter);
                            mAdapter.notifyDataSetChanged();
                        }
                    }else if (response.body().getStatusCode().equals("001") || response.body().getStatusCode().equals("002")){
                        destiny.AutoLogin(Username,Password,getActivity());
                        KabarBerita();
                    }else{
                        Toast.makeText(getActivity(), "Terjadi Kesalahan ", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseMarketplace> call, Throwable t) {
                TAProduk.setVisibility(View.VISIBLE);
                TAProduk.setText("Kesalahan pada Jaringan");
                AProduk.setAnimation("notfound.json");
                AProduk.playAnimation();
            }
        });
    }
    public void addDotsIndicator2(int posistion) {
        mDots = new TextView[4];
        mDotLayout.removeAllViews();
        for (int i = 0; i < mDots.length; i++) {
            mDots[i] = new TextView(getActivity());
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorPrimary));

            mDotLayout.addView(mDots[i]);
        }
        if (mDots.length > 0) {
            mDots[posistion].setTextColor(getResources().getColor(R.color.colorGreen));
        }
    }

    ViewPager.OnPageChangeListener viewList2 = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            addDotsIndicator2(i);
            CurrentPage = i;

            if (CurrentPage == 0) {
                ArrowForward.setEnabled(true);
                ArrowBack.setEnabled(false);
                ArrowBack.setVisibility(View.INVISIBLE);
                ArrowForward.setVisibility(View.VISIBLE);
            } else if (i == mDots.length - 1) {
                ArrowForward.setEnabled(true);
                ArrowBack.setEnabled(true);
                ArrowBack.setVisibility(View.VISIBLE);
                ArrowForward.setVisibility(View.INVISIBLE);
            } else {
                ArrowForward.setEnabled(true);
                ArrowBack.setEnabled(true);
                ArrowBack.setVisibility(View.VISIBLE);
                ArrowForward.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };


    private void Gallery(){
        mManager = new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false);
        recyclerGallery.setLayoutManager(mManager);
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseModel> sponsor = api.Gallery(destiny.AUTH(Token));
        sponsor.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                try {
                    if (response.body().getStatusCode().equals("000")){
//                        mItems=response.body().getData();
//                        mAdapter = new AdapterGalleryHome(getActivity(),mItems);
//                        recyclerGallery.setAdapter(mAdapter);
//                        mAdapter.notifyDataSetChanged();
                    }else if (response.body().getStatusCode().equals("001") || response.body().getStatusCode().equals("002")){
                        destiny.AutoLogin(Username,Password,getActivity());
                        Header(0);
                    }else{
                        Toast.makeText(getActivity(), "Terjadi Kesalahan ", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(getActivity(), "Terjadi Kesalahan User akan Terlogout", Toast.LENGTH_SHORT).show();
//                    dbHelper.Logout();
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
//                Toast.makeText(getActivity(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void GetSekolah(){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseModel> Point = api.ProfileSekolah(destiny.AUTH(Token));
        Point.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                try {
                    if (response.body().getStatusCode().equals("000")){
                        ID=response.body().getData().get(0).getId_sekolah();
                        Sekolah.setText(response.body().getData().get(0).getNama_sekolah());
                        if (Level.equals("guru")){
                            SekolahBesar.setText("Guru");
                        }else{
                            SekolahBesar.setText(response.body().getData().get(0).getNama_kelas());
                        }

                        destiny.deleteCache(getActivity());

                        sliderAdapter2 = new SliderAdapter2(getActivity(),ID);

                        mSlideViewPager2.setAdapter(sliderAdapter2);
                        addDotsIndicator2(0);

                        mSlideViewPager2.addOnPageChangeListener(viewList2);
                        ArrowForward.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mSlideViewPager2.setCurrentItem(CurrentPage + 1);
                            }
                        });
                        ArrowBack.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mSlideViewPager2.setCurrentItem(CurrentPage - 1);
                            }
                        });



                        Glide.with(getActivity())
                                .load(destiny.BASE_URL()+response.body().getData().get(0).getLogo_sekolah())
                                .into(Logo);
                    }else if (response.body().getStatusCode().equals("001") || response.body().getStatusCode().equals("002")){
                        destiny.AutoLogin(Username,Password,getActivity());
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    }else{
                        Toast.makeText(getActivity(), "Terjadi Kesalahan ", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    try {
//                        Toast.makeText(getActivity(), "Terjadi Kesalahan User akan Terlogout", Toast.LENGTH_SHORT).show();
////                        dbHelper.Logout();
//                        Intent intent = new Intent(getActivity(), LoginActivity.class);
//                        startActivity(intent);
//                        getActivity().finish();
                    }catch (Exception ex){
                        Toast.makeText(getActivity(), "????", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
//                Toast.makeText(getActivity(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void GetPoint(){

    }
    private void Sponsor(){
        mManager = new GridLayoutManager(getActivity(),4);
        recylerSponsor.setLayoutManager(mManager);
        LASponsor.setVisibility(View.VISIBLE);
        TASponsor.setVisibility(View.GONE);
        ASponsor.setAnimation("loading.json");
        ASponsor.playAnimation();
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseModel> sponsor = api.Sponsor(destiny.AUTH(Token));
        sponsor.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                try {
                    if (response.body().getStatusCode().equals("000")){
                        mItems=response.body().getData();
                        if (mItems.size()<1){
                            TASponsor.setVisibility(View.VISIBLE);
                            TASponsor.setText("Sponsor Belum Ada");
                            ASponsor.setAnimation("notfound.json");
                            ASponsor.playAnimation();
                        }else{
                            LASponsor.setVisibility(View.GONE);
                            mAdapter = new AdapterSponsor(getActivity(),mItems);
                            recylerSponsor.setAdapter(mAdapter);
                            mAdapter.notifyDataSetChanged();
                        }
                    }else if (response.body().getStatusCode().equals("001") || response.body().getStatusCode().equals("002")){
                        destiny.AutoLogin(Username,Password,getActivity());
                        Header(0);
                    }else{
                        Toast.makeText(getActivity(), "Terjadi Kesalahan ", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(getActivity(), "Terjadi Kesalahan User akan Terlogout", Toast.LENGTH_SHORT).show();
//                    dbHelper.Logout();
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
//                Toast.makeText(getActivity(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Pager Start
    private void Header(final int position){
        mManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
//        recycler.setLayoutManager(mManager);
        LAHeader.setVisibility(View.VISIBLE);
        TAHeader.setVisibility(View.GONE);
        AHeader.setAnimation("loading.json");
        AHeader.playAnimation();
        ApiRequest api = FajarKontol.getClient().create(ApiRequest.class);
        Call<ResponseModel> KabarBerita = api.InfoPublik();
        KabarBerita.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                try {
                    mItems=response.body().getData();
                    if (mItems.size()<1){
                        TAHeader.setVisibility(View.VISIBLE);
                        TAHeader.setText("Banner Belum Ada");
                        AHeader.setAnimation("notfound.json");
                        AHeader.playAnimation();
                    }else{
                        LAHeader.setVisibility(View.GONE);
                        adapterBanner = new AdapterBanner(getActivity(),mItems);
                        mSlideViewPager.setAdapter(adapterBanner);
                        SizeBanner = mItems.size();
                        addDotsIndicator(position);
                        mSlideViewPager.addOnPageChangeListener(viewList);
                    }
                }catch (Exception e){
                    Log.d("FAJAR KONTOOOOOL : ",e.toString());
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
//                Toast.makeText(getActivity(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addDotsIndicator(int posistion) {
        try {
            mDots = new TextView[SizeBanner];
            mDotLayout.removeAllViews();
            for (int i = 0; i < mDots.length; i++) {
                mDots[i] = new TextView(getActivity());
                mDots[i].setText(Html.fromHtml("&#8226;"));
                mDots[i].setTextSize(35);
                mDots[i].setTextColor(getResources().getColor(R.color.colorPrimary));

                mDotLayout.addView(mDots[i]);
            }
            if (mDots.length > 0) {
                mDots[posistion].setTextColor(getResources().getColor(R.color.colorGreen));
            }
        }catch (Exception e){

        }
    }
    ViewPager.OnPageChangeListener viewList = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            addDotsIndicator(i);
            CurrentPage = i;

            if (CurrentPage == 0) {

            } else if (i == mDots.length - 1) {

            } else {

            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {
            AutoSlide();
        }
    };
    private void AutoSlide(){
        if(CurrentPage == 0) {
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    forward=true;
                    mSlideViewPager.setCurrentItem(CurrentPage + 1);
                }
            }, 5000);

        }else if(CurrentPage == mDots.length - 1) {
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    forward=false;
                    mSlideViewPager.setCurrentItem(CurrentPage - 1);
                }
            }, 5000);
        }else{
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    if (forward){
                        mSlideViewPager.setCurrentItem(CurrentPage + 1);
                    }else{
                        mSlideViewPager.setCurrentItem(CurrentPage - 1);
                    }
                }
            }, 5000);
        }
    }
    //PagerEnd

    private void KabarBerita(){
        mManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
        recyclerKabar.setLayoutManager(mManager);
        LABerita.setVisibility(View.VISIBLE);
        TABerita.setVisibility(View.GONE);
        ABerita.setAnimation("loading.json");
        ABerita.playAnimation();
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseModel> KabarBerita = api.KabarSekolah(destiny.AUTH(Token));
        KabarBerita.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                try {
                    if (response.body().getStatusCode().equals("000")){
                        mItems=response.body().getData();
                        if (mItems.size()<1){
                            TABerita.setVisibility(View.VISIBLE);
                            TABerita.setText("Berita Belum Ada");
                            ABerita.setAnimation("notfound.json");
                            ABerita.playAnimation();
                        }else{
                            LABerita.setVisibility(View.GONE);
                            mAdapter = new AdapterKabarBerita(getActivity(),mItems);
                            recyclerKabar.setAdapter(mAdapter);
                            mAdapter.notifyDataSetChanged();
                        }
                    }else if (response.body().getStatusCode().equals("001") || response.body().getStatusCode().equals("002")){
                        destiny.AutoLogin(Username,Password,getActivity());
                        KabarBerita();
                    }else{
                        Toast.makeText(getActivity(), "Terjadi Kesalahan ", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(getActivity(), "Terjadi Kesalahan User akan Terlogout", Toast.LENGTH_SHORT).show();
//                    dbHelper.Logout();
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
//                Toast.makeText(getActivity(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void DIALOG(){
        DProfilSekolah = dialog.findViewById(R.id.linearProfilSekolah);
        DAgendaSekolah = dialog.findViewById(R.id.linearAgendaSekolah);
        DPrestasi = dialog.findViewById(R.id.linearPrestasi);
        DPPDB = dialog.findViewById(R.id.linearPPDB);
        DStrukturSekolah = dialog.findViewById(R.id.linearStrukturOrganisasi);
        DJadwalPelajaran = dialog.findViewById(R.id.linearJadwalPelajaran);
        DEvadir = dialog.findViewById(R.id.linearEvadir);
        DMediaPembelajaran = dialog.findViewById(R.id.linearMediaPembelajaran);
        DTugas = dialog.findViewById(R.id.linearTugas);
        DGuru = dialog.findViewById(R.id.linearGuru);
        DBiayaAkademik = dialog.findViewById(R.id.linearBiayaAkademik);
        DPembayaran = dialog.findViewById(R.id.linearPembayaran);
        DERaport = dialog.findViewById(R.id.linearEraport);
        DGallery = dialog.findViewById(R.id.linearGallery);
        DLowonganKerja = dialog.findViewById(R.id.linearLoker);
        DEskul = dialog.findViewById(R.id.linearEskul);
        DUjian = dialog.findViewById(R.id.linearUjian);
        DTryout = dialog.findViewById(R.id.linearTryout);
    }
    private void ONCLICKDIALOG(){
        DProfilSekolah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destiny.ChangeActivity(getActivity(),"Profile Sekolah",Level);
            }
        });
        DAgendaSekolah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destiny.ChangeActivity(getActivity(),"Agenda Sekolah",Level);
            }
        });
        DPrestasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destiny.ChangeActivity(getActivity(),"Prestasi",Level);
            }
        });
        DPPDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), FormulirPPDBActivity.class);
                i.putExtra("ID", ID);
                startActivity(i);
            }
        });
        DStrukturSekolah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destiny.ChangeActivity(getActivity(),"Struktur Sekolah",Level);
            }
        });
        DJadwalPelajaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destiny.ChangeActivity(getActivity(),"Jadwal Pelajaran",Level);
            }
        });
        DEvadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destiny.ChangeActivity(getActivity(),"Evadir",Level);
            }
        });
        DMediaPembelajaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destiny.ChangeActivity(getActivity(),"Media Pembelajaran",Level);
            }
        });
        DTugas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destiny.ChangeActivity(getActivity(),"Tugas",Level);
            }
        });
        DGuru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destiny.ChangeActivity(getActivity(),"Tugas",Level);
            }
        });
        DGuru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destiny.ChangeActivity(getActivity(),"Guru",Level);
            }
        });
        DBiayaAkademik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destiny.ChangeActivity(getActivity(),"Biaya Akademik",Level);
            }
        });
        DPembayaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destiny.ChangeActivity(getActivity(),"Pembayaran",Level);
            }
        });
        DERaport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                destiny.ChangeActivity(getActivity(),"E-Raport",Level);
            }
        });
        DGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                destiny.ChangeActivity(getActivity(),"Gallery",Level);
            }
        });
        DLowonganKerja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destiny.ChangeActivity(getActivity(),"Loker",Level);
            }
        });
        DEskul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destiny.ChangeActivity(getActivity(),"Eskul",Level);
            }
        });
        DUjian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destiny.ChangeActivity(getActivity(),"Ujian",Level);
            }
        });
        DTryout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Ujian2Activity.class);
                i.putExtra("NAMA", "Try Out");
                i.putExtra("ID", "try_out");
                startActivity(i);
            }
        });
    }
    private void ONCLICK(){
        ProfilSekolah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destiny.ChangeActivity(getActivity(),"Profile Sekolah",Level);
            }
        });
        AgendaSekolah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destiny.ChangeActivity(getActivity(),"Agenda Sekolah",Level);
            }
        });
        Prestasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destiny.ChangeActivity(getActivity(),"Prestasi",Level);
            }
        });
        PPDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), FormulirPPDBActivity.class);
                i.putExtra("ID", ID);
                startActivity(i);
            }
        });
        StrukturSekolah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destiny.ChangeActivity(getActivity(),"Struktur Sekolah",Level);
            }
        });
        JadwalPelajaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destiny.ChangeActivity(getActivity(),"Jadwal Pelajaran",Level);
            }
        });
        Eskul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destiny.ChangeActivity(getActivity(),"Eskul",Level);
            }
        });
        MediaPembelajaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destiny.ChangeActivity(getActivity(),"Media Pembelajaran",Level);
            }
        });
        Tugas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destiny.ChangeActivity(getActivity(),"Tugas",Level);
            }
        });
        infoDinas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                destiny.ChangeActivity(getActivity(),"Dinas Pendidikan",Level);
            }
        });
        KemisNyunda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destiny.ChangeActivity(getActivity(),"Kebudayaan",Level);
            }
        });
        JumatNgaji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destiny.ChangeActivity(getActivity(),"Jumat Ngaji",Level);
            }
        });
        MediaInformasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destiny.ChangeActivity(getActivity(),"Media Informasi",Level);
            }
        });
        LihatSemua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });
        Kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.hide();
            }
        });
        LihatSemuaKabarBerita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), KabarBeritaActivity.class);
                startActivity(intent);
            }
        });
        LihatSemuaProduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MarketplaceActivity.class);
                startActivity(intent);
            }
        });
        LihatSemuaSponsor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SponsorActivity.class);
                startActivity(intent);
            }
        });
        LihatSemuaGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), GalleryActivity.class);
//                startActivity(intent);
            }
        });
    }
}