package com.destiny.cianjursipp.Activity.menu.Evadir;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.destiny.cianjursipp.API.ApiRequest;
import com.destiny.cianjursipp.API.RetroServer;
import com.destiny.cianjursipp.Activity.HomeActivity;
import com.destiny.cianjursipp.Activity.LoginActivity;
import com.destiny.cianjursipp.Adapter.Home.AdapterEvadir;
import com.destiny.cianjursipp.Method.Destiny;
import com.destiny.cianjursipp.Model.Data;
import com.destiny.cianjursipp.Model.DataEvadir;
import com.destiny.cianjursipp.Model.DataModel;
import com.destiny.cianjursipp.Model.Pena.ResponseModel;
import com.destiny.cianjursipp.R;
import com.destiny.cianjursipp.SharedPreferance.DB_Helper;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EvadirActivity extends AppCompatActivity {
    Destiny destiny;
    RelativeLayout Back;
    DB_Helper dbHelper;
    String Username,Password,Nama,Token,Level,Photo;
    RecyclerView recycler;
    private List<DataModel> mItems = new ArrayList<>();
    private List<Data> Evadir = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    Button Nilai;
    int sizeEvadir=0;
    private DataEvadir dataEvadir = new DataEvadir();
    JSONArray jsonArray = new JSONArray();


    ArrayList<String> ID_EVADIR = new ArrayList<String>();
    ArrayList<String> SKOR = new ArrayList<String>();
    ArrayList<String> ID_KATEGORI = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evadir);
        destiny = new Destiny();
        Nilai = findViewById(R.id.btnNilai);
        Back = findViewById(R.id.relativeBack);
        recycler = findViewById(R.id.recycler);
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
        Data();
        Nilai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Evadir.size()>ID_EVADIR.size()){
                    Toast.makeText(EvadirActivity.this, "Ada Beberapa Evadir yang belum diisi", Toast.LENGTH_SHORT).show();
                }else{
                    try {
                        Logic();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
    private void Logic(){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseModel> Logic = api.Evadir(destiny.AUTH(Token),ID_EVADIR,SKOR,ID_KATEGORI);
        Logic.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                try {
                    if (response.body().getStatusCode().equals("000")){
                        Toast.makeText(EvadirActivity.this, "Data Evadir Sudah dikirimkan", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(EvadirActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finishAffinity();
                    }else if (response.body().getStatusCode().equals("001") || response.body().getStatusCode().equals("002")){
                        destiny.AutoLogin(Username,Password,EvadirActivity.this);
                        Data();
                    }else{
                        Toast.makeText(EvadirActivity.this, "Terjadi Kesalahan ", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(EvadirActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                    dbHelper.Logout();
                    Intent intent = new Intent(EvadirActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(EvadirActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void Data(){
        mManager = new LinearLayoutManager(EvadirActivity.this, LinearLayoutManager.VERTICAL,false);
        recycler.setLayoutManager(mManager);
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseModel> KabarBerita = api.Evadir(destiny.AUTH(Token));
        final LayoutAnimationController layoutAnimationController = AnimationUtils.loadLayoutAnimation(EvadirActivity.this,R.anim.layout_animation2);

        KabarBerita.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                try {
                    if (response.body().getStatusCode().equals("000")){
                        mItems=response.body().getData();
                        sizeEvadir=response.body().getData().size();
                        mAdapter = new AdapterEvadir(EvadirActivity.this,mItems,Evadir,jsonArray,ID_EVADIR,ID_KATEGORI,SKOR);
                        recycler.setAdapter(mAdapter);
                        recycler.setLayoutAnimation(layoutAnimationController);
                        recycler.scheduleLayoutAnimation();
                        mAdapter.notifyDataSetChanged();
                    }else if (response.body().getStatusCode().equals("001") || response.body().getStatusCode().equals("002")){
                        destiny.AutoLogin(Username,Password,EvadirActivity.this);
                        Data();
                    }else{
                        Toast.makeText(EvadirActivity.this, "Terjadi Kesalahan ", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(EvadirActivity.this, "Terjadi Kesalahan User akan Terlogout", Toast.LENGTH_SHORT).show();
                    dbHelper.Logout();
                    Intent intent = new Intent(EvadirActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(EvadirActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}