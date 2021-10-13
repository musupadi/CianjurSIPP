package com.destiny.cianjursipp.Activity.menu.SumberPustaka;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.destiny.cianjursipp.API.ApiRequest;
import com.destiny.cianjursipp.API.RetroServer;
import com.destiny.cianjursipp.Activity.LoginActivity;
import com.destiny.cianjursipp.Adapter.Home.AdapterSumberPustaka;
import com.destiny.cianjursipp.Adapter.Home.AdapterSumberPustakaKelas;
import com.destiny.cianjursipp.Adapter.Home.AdapterSumberPustakaLembaga;
import com.destiny.cianjursipp.Method.Destiny;
import com.destiny.cianjursipp.Model.DataModel;
import com.destiny.cianjursipp.Model.Pena.ResponseModel;
import com.destiny.cianjursipp.R;
import com.destiny.cianjursipp.SharedPreferance.DB_Helper;
import com.destiny.cianjursipp.Spinner.SpinnerJurusan;
import com.destiny.cianjursipp.Spinner.SpinnerPustakaKelas;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LembagaSumberPustakaActivity extends AppCompatActivity {
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
    String ID_LEMBAGA;
    TextView tvJurusan,tvKelasKe;
    Spinner Jurusan,KelasKe;
    LinearLayout Cari;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lembaga_sumber_pustaka);
        Jurusan = findViewById(R.id.spJurusan);
        KelasKe = findViewById(R.id.spKelas);
        Cari = findViewById(R.id.linearCari);
        tvKelasKe = findViewById(R.id.tvKelasKe);
        tvJurusan = findViewById(R.id.tvJurusan);
        Intent intent = getIntent();
        ID_LEMBAGA = intent.getExtras().getString("ID_LEMBAGA");
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
        Kelas();
        Jurusan();
        KelasKe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    DataModel clickedItem = (DataModel) adapterView.getItemAtPosition(i);
                    String clickedItems = clickedItem.getKelas_ke();
                    tvKelasKe.setText(clickedItems);
                }catch (Exception e){

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Jurusan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    DataModel clickedItem = (DataModel) adapterView.getItemAtPosition(i);
                    String clickedItems = clickedItem.getId_sumber_pustaka_penjurusan();
                    tvJurusan.setText(clickedItems);
                }catch (Exception e){

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Cari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogicCari();
            }
        });
        Logic2();
        Logic();
    }
    private void LogicCari(){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseModel> Data=api.SumberPustaka(destiny.AUTH(Token),"",ID_LEMBAGA,tvJurusan.getText().toString(),tvKelasKe.getText().toString());
        Data.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                try {
                    if (response.body().getStatusCode().equals("000")){
                        mManager = new GridLayoutManager(LembagaSumberPustakaActivity.this,1);
                        recycler.setLayoutManager(mManager);
                        mItems=response.body().getData();
                        mAdapter = new AdapterSumberPustaka(LembagaSumberPustakaActivity.this,mItems);
                        recycler.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                    }else{
                        Toast.makeText(LembagaSumberPustakaActivity.this, "Terjadi Kesalahan ", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(LembagaSumberPustakaActivity.this, "Terjadi Kesalahan User akan Terlogout", Toast.LENGTH_SHORT).show();
                    dbHelper.Logout();
                    Intent intent = new Intent(LembagaSumberPustakaActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {

            }
        });
    }
    private void Kelas(){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseModel> getProvinsi = api.SumberPustakaKelas(destiny.AUTH(Token));
        getProvinsi.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                try {
                    mItems=response.body().getData();
                    SpinnerPustakaKelas adapter = new SpinnerPustakaKelas(LembagaSumberPustakaActivity.this,mItems);
                    KelasKe.setAdapter(adapter);
                }catch (Exception e){
                    Toast.makeText(LembagaSumberPustakaActivity.this, "Terjadi kesalahan "+e.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(LembagaSumberPustakaActivity.this,"Koneksi Gagal",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void Jurusan(){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseModel> getProvinsi = api.SumberPustakaJurusan(destiny.AUTH(Token));
        getProvinsi.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                try {
                    mItems=response.body().getData();
                    SpinnerJurusan adapter = new SpinnerJurusan(LembagaSumberPustakaActivity.this,mItems);
                    Jurusan.setAdapter(adapter);
                }catch (Exception e){
                    Toast.makeText(LembagaSumberPustakaActivity.this, "Terjadi kesalahan "+e.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(LembagaSumberPustakaActivity.this,"Koneksi Gagal",Toast.LENGTH_SHORT).show();
            }
        });
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
                                Glide.with(LembagaSumberPustakaActivity.this)
                                        .load(destiny.BASE_URL()+response.body().getData().get(0).getPhoto())
                                        .into(image);
                            }
                        }catch (Exception e){

                        }
                    }else{
                        Toast.makeText(LembagaSumberPustakaActivity.this, response.body().getStatusMessage(), Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(LembagaSumberPustakaActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
                Log.i("Login Logic : ",t.toString());
            }
        });
    }
    private void Logic(){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseModel> Data=api.SumberPustaka(destiny.AUTH(Token));
        Data.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                try {
                    if (response.body().getStatusCode().equals("000")){
                        mManager = new GridLayoutManager(LembagaSumberPustakaActivity.this,1);
                        recycler.setLayoutManager(mManager);
                        mItems=response.body().getData();
                        mAdapter = new AdapterSumberPustaka(LembagaSumberPustakaActivity.this,mItems);
                        recycler.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                    }else{
                        Toast.makeText(LembagaSumberPustakaActivity.this, "Terjadi Kesalahan ", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(LembagaSumberPustakaActivity.this, "Terjadi Kesalahan User akan Terlogout", Toast.LENGTH_SHORT).show();
                    dbHelper.Logout();
                    Intent intent = new Intent(LembagaSumberPustakaActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {

            }
        });
    }
}