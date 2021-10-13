package com.destiny.cianjursipp.Activity.menu.SumberPustaka;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.destiny.cianjursipp.API.ApiRequest;
import com.destiny.cianjursipp.API.RetroServer;
import com.destiny.cianjursipp.Activity.LoginActivity;
import com.destiny.cianjursipp.Adapter.Home.AdapterMapel;
import com.destiny.cianjursipp.Adapter.Home.AdapterSumberPustakaLembaga;
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

public class NewSumberPustaka1Activity extends AppCompatActivity {
    Destiny destiny;
    RelativeLayout Back;
    DB_Helper dbHelper;
    String Username,Password,Nama,Token,Level,Photo;
    RecyclerView recycler;
    TextView nama,kelas,nis;
    ImageView image;
    private List<DataModel> mItems = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    TextView NIK;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_sumber_pustaka1);
        image = findViewById(R.id.ivImage);
        nama = findViewById(R.id.tvNama);
        kelas = findViewById(R.id.tvKelas);
        nis = findViewById(R.id.tvNIS);
        destiny = new Destiny();
        Back = findViewById(R.id.relativeBack);
        recycler = findViewById(R.id.recycler);
        NIK = findViewById(R.id.tvNIK);
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
        Logic2();
        Logic();
    }
    private void Logic2(){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        final Call<ResponseModel> login =api.login(Username,Password);
        login.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                try {
                    if (response.body().getStatusCode().equals("000")){
                        try {
                            nama.setText(response.body().getData().get(0).getName());
                            if (Level.equals("guru")){
                                kelas.setText("Guru");
                                NIK.setText("NIK");
                                nis.setText(Username);
                            }else{
                                nis.setText(response.body().getData().get(0).getNisn());
                                kelas.setText(response.body().getData().get(0).getNamakelas());
                            }
                            if (response.body().getData().get(0).getPhoto().equals("") || response.body().getData().get(0).getPhoto().isEmpty()){
                                image.setImageResource(R.drawable.childern);
                            }else{
                                Glide.with(NewSumberPustaka1Activity.this)
                                        .load(destiny.BASE_URL()+response.body().getData().get(0).getPhoto())
                                        .into(image);
                            }
                        }catch (Exception e){

                        }
                    }else{
                        Toast.makeText(NewSumberPustaka1Activity.this, response.body().getStatusMessage(), Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(NewSumberPustaka1Activity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
                Log.i("Login Logic : ",t.toString());
            }
        });
    }
    private void Logic(){
        mManager = new GridLayoutManager(NewSumberPustaka1Activity.this,1);
        recycler.setLayoutManager(mManager);
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseModel> Temans = api.SumberPustakaLembaga(destiny.AUTH(Token));
        Temans.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                try {
                    if (response.body().getStatusCode().equals("000")){
                        mItems=response.body().getData();
                        mAdapter = new AdapterSumberPustakaLembaga(NewSumberPustaka1Activity.this,mItems,"TUGAS");
                        recycler.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                    }else{
                        Toast.makeText(NewSumberPustaka1Activity.this, "Terjadi Kesalahan ", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(NewSumberPustaka1Activity.this, "Terjadi Kesalahan User akan Terlogout", Toast.LENGTH_SHORT).show();
                    dbHelper.Logout();
                    Intent intent = new Intent(NewSumberPustaka1Activity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(NewSumberPustaka1Activity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}