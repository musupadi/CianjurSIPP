package com.destiny.cianjursipp.Activity.menu.MediaPembelajaran;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.destiny.cianjursipp.API.ApiRequest;
import com.destiny.cianjursipp.API.RetroServer;
import com.destiny.cianjursipp.Activity.LoginActivity;
import com.destiny.cianjursipp.Adapter.Home.AdapterListMediaPembelajaran;
import com.destiny.cianjursipp.Method.Destiny;
import com.destiny.cianjursipp.Model.Media;
import com.destiny.cianjursipp.Model.Pena.ResponseModel;
import com.destiny.cianjursipp.R;
import com.destiny.cianjursipp.SharedPreferance.DB_Helper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListMediaPembelajaranActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    String tema,subtema,nama;
    private List<Media> mItems = new ArrayList<>();
    Destiny destiny;
    DB_Helper dbHelper;
    String Username,Password,Nama,Token,Level,Photo;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_media_pembelajaran);
        destiny = new Destiny();
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
        recyclerView = findViewById(R.id.recycler);
        GetData();
        GetMediaPembelajaran();
    }
    private void GetData(){
        Intent intent = getIntent();
        nama = intent.getExtras().getString("NAMA");
        tema = intent.getExtras().getString("TEMA");
        subtema = intent.getExtras().getString("SUBTEMA");
        getSupportActionBar().setTitle(nama);
    }

    private void GetMediaPembelajaran(){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseModel> Data=api.MediaPembelajaran(destiny.AUTH(Token),"");
        Data.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                try {
                    if (response.body().getStatusCode().equals("000")){
                        mManager = new GridLayoutManager(ListMediaPembelajaranActivity.this,1);
                        recyclerView.setLayoutManager(mManager);
                        mItems = response.body().getData().get(Integer.parseInt(tema)).getSubTema().get(Integer.parseInt(subtema)).getMedia();
                        mAdapter = new AdapterListMediaPembelajaran(ListMediaPembelajaranActivity.this,mItems);
                        recyclerView.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                    }else if (response.body().getStatusCode().equals("001") || response.body().getStatusCode().equals("002")){
                        destiny.AutoLogin(Username,Password,ListMediaPembelajaranActivity.this);
                        Intent intent = new Intent(ListMediaPembelajaranActivity.this,MediaPembelajaranActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(ListMediaPembelajaranActivity.this, "Terjadi Kesalahan ", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(ListMediaPembelajaranActivity.this, "Terjadi Kesalahan User akan Terlogout", Toast.LENGTH_SHORT).show();
                    dbHelper.Logout();
                    Intent intent = new Intent(ListMediaPembelajaranActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(ListMediaPembelajaranActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}