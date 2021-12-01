package com.destiny.cianjursipp.Activity.Absensi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.destiny.cianjursipp.API.ApiRequest;
import com.destiny.cianjursipp.API.RetroServer;
import com.destiny.cianjursipp.Activity.HomeActivity;
import com.destiny.cianjursipp.Activity.LoginActivity;
import com.destiny.cianjursipp.Activity.MainActivity;
import com.destiny.cianjursipp.Adapter.Universal.AdapterAbsensiSiswa;
import com.destiny.cianjursipp.Adapter.Universal.AdapterGuruMapel;
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

public class AbsensiKelasActivity extends AppCompatActivity {
    Destiny destiny;
    RelativeLayout Back;
    DB_Helper dbHelper;
    String Username,Password,Nama,Token,Level,Photo;
    String ID,NAMA,ID_MAPEL,PIKET,ID_GURU;
    RecyclerView rv;
    Button Kirim;
    Spinner piket;
    private List<DataModel> mItems = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    ArrayList<String> ID_SISWA = new ArrayList<String>();
    ArrayList<String> ALASAN = new ArrayList<String>();
    ArrayList<String> ABSEN = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absensi_kelas);
        destiny = new Destiny();
        rv = findViewById(R.id.recycler);
        piket = findViewById(R.id.spPiket);
        Kirim = findViewById(R.id.btnKirim);
        mManager = new GridLayoutManager(AbsensiKelasActivity.this,1);
        rv.setLayoutManager(mManager);
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
        Intent intent = getIntent();
        ID = intent.getExtras().getString("ID");
        ID_MAPEL = intent.getExtras().getString("ID_MAPEL");
        ID_GURU= intent.getExtras().getString("ID_GURU");
        PIKET = intent.getExtras().getString("PIKET");
        NAMA = intent.getExtras().getString("NAMA");
        getSupportActionBar().setTitle(NAMA);
        Log.d("ID KELAS UCUP zyarga : ",ID);
        Data();
        Kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Send();
            }
        });
    }
    private void Send(){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseModel> Data = api.KelasMuridAbsen(
                destiny.AUTH(Token),
                ID,
                ID_MAPEL,
                PIKET,
                ID_GURU,
                ID_SISWA,
                ABSEN,
                ALASAN);
        Data.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                Toast.makeText(AbsensiKelasActivity.this, response.body().getStatusMessage(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AbsensiKelasActivity.this, HomeActivity.class);
                startActivity(intent);
                finishAffinity();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(AbsensiKelasActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void Data(){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseModel> Data=api.GuruMapelAbsen(destiny.AUTH(Token),ID);
        Data.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                try {
                    if (response.body().getStatusCode().equals("000")){
                        mItems=response.body().getData();
                        mAdapter = new AdapterAbsensiSiswa(AbsensiKelasActivity.this,mItems,ID_SISWA,ALASAN,ABSEN);
                        rv.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                    }else if (response.body().getStatusCode().equals("001") || response.body().getStatusCode().equals("002")){
                        destiny.AutoLogin(Username,Password,AbsensiKelasActivity.this);
                        Intent intent = new Intent(AbsensiKelasActivity.this, MainActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(AbsensiKelasActivity.this, "Terjadi Kesalahan ", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    dbHelper.Logout();
                    Intent intent = new Intent(AbsensiKelasActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(AbsensiKelasActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}