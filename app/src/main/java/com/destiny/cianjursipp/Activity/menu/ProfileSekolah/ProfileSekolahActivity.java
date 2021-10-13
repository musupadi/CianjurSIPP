package com.destiny.cianjursipp.Activity.menu.ProfileSekolah;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.destiny.cianjursipp.API.ApiRequest;
import com.destiny.cianjursipp.API.RetroServer;
import com.destiny.cianjursipp.Activity.LoginActivity;
import com.destiny.cianjursipp.Activity.MainActivity;
import com.destiny.cianjursipp.Method.Destiny;
import com.destiny.cianjursipp.Model.DataModel;
import com.destiny.cianjursipp.Model.Pena.ResponseModel;
import com.destiny.cianjursipp.R;
import com.destiny.cianjursipp.SharedPreferance.DB_Helper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileSekolahActivity extends AppCompatActivity {
    Destiny destiny;
    ImageView Logo;
    TextView NamaSekolah,NamaSekolah2,AlamatSekolah,AkreditasiSekolah,NPSN,NoIzinSekolah,KepalaSekolah,NamaYayasan,KetuaYayasan;
    DB_Helper dbHelper;
    String Username,Password,Nama,Token,Level,Photo;
    RecyclerView recycler,recyclerKabar;
    private List<DataModel> mItems = new ArrayList<>();
    private List<DataModel> mItems2 = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_sekolah);
        destiny = new Destiny();
        Logo = findViewById(R.id.ivLogo);
        NamaSekolah = findViewById(R.id.tvNamaProfileSekolah);
        NamaSekolah2 = findViewById(R.id.tvNamaSekolah);
        AlamatSekolah = findViewById(R.id.tvAlamat);
        AkreditasiSekolah = findViewById(R.id.tvAkreditasi);
        NPSN = findViewById(R.id.tvNPSN);
        NoIzinSekolah = findViewById(R.id.tvNoIzinSekolah);
        KepalaSekolah = findViewById(R.id.tvKepalaSekolah);
        NamaYayasan = findViewById(R.id.tvYayasan);
        KetuaYayasan = findViewById(R.id.tvKetuaYayasan);
        dbHelper = new DB_Helper(this);
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
        GetSekolah();
    }
    private void GetSekolah(){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseModel> Point = api.ProfileSekolah(destiny.AUTH(Token));
        Point.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                try {
                    if (response.body().getStatusCode().equals("000")){
                        NamaSekolah.setText(response.body().getData().get(0).getNama_sekolah());
                        AlamatSekolah.setText(response.body().getData().get(0).getNama_sekolah());
                        NPSN.setText(response.body().getData().get(0).getNpsn());
                        NamaSekolah2.setText(response.body().getData().get(0).getNama_sekolah());
                        AkreditasiSekolah.setText(response.body().getData().get(0).getAkreditasi_sekolah());
                        NoIzinSekolah.setText(response.body().getData().get(0).getNo_izin_sekolah());
                        KepalaSekolah.setText(response.body().getData().get(0).getKepala_sekolah());
                        NamaYayasan.setText(response.body().getData().get(0).getNama_yayasan());
                        KetuaYayasan.setText(response.body().getData().get(0).getKetua_yayasan());
                        Glide.with(ProfileSekolahActivity.this)
                                .load(destiny.BASE_URL()+response.body().getData().get(0).getLogo_sekolah())
                                .into(Logo);
                    }else if (response.body().getStatusCode().equals("001") || response.body().getStatusCode().equals("002")){
                        destiny.AutoLogin(Username,Password,ProfileSekolahActivity.this);
                        Intent intent = new Intent(ProfileSekolahActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(ProfileSekolahActivity.this, "Terjadi Kesalahan ", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Log.i("Error : ",e.toString());
                    Toast.makeText(ProfileSekolahActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                    dbHelper.Logout();
                    Intent intent = new Intent(ProfileSekolahActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(ProfileSekolahActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}