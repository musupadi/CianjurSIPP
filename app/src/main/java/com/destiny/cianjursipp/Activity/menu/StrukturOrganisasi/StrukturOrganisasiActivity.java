package com.destiny.cianjursipp.Activity.menu.StrukturOrganisasi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.destiny.cianjursipp.API.ApiRequest;
import com.destiny.cianjursipp.API.RetroServer;
import com.destiny.cianjursipp.Activity.LoginActivity;
import com.destiny.cianjursipp.Activity.menu.MediaPembelajaran.ListMediaPembelajaranActivity;
import com.destiny.cianjursipp.Activity.menu.Ujian.DetailUjianActivity;
import com.destiny.cianjursipp.Adapter.Universal.AdapterStrukturOrganisasi;
import com.destiny.cianjursipp.Method.Destiny;
import com.destiny.cianjursipp.Model.DataModel;
import com.destiny.cianjursipp.Model.Pena.ResponseModel;
import com.destiny.cianjursipp.R;
import com.destiny.cianjursipp.SharedPreferance.DB_Helper;
import com.github.barteksc.pdfviewer.PDFView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StrukturOrganisasiActivity extends AppCompatActivity {
    Destiny destiny;
    DB_Helper dbHelper;
    String Username,Password,Nama,Token,Level,Photo;
    RecyclerView recycler;
    private List<DataModel> mItems = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    ImageView photoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_struktur_organisasi);
        destiny = new Destiny();
        recycler = findViewById(R.id.recyclerView);
        photoView =findViewById(R.id.ivPDF);
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
        Logic();
        Struktur();
//        Back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                destiny.Back(StrukturOrganisasiActivity.this);
//            }
//        });
    }
    private void Struktur(){

        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseModel> KabarBerita = api.StrukturOrganisasi(destiny.AUTH(Token));
        KabarBerita.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.body().getStatusCode().equals("000")){
                    try {
                        Glide.with(StrukturOrganisasiActivity.this)
                                .load(destiny.BASE_URL()+response.body().getData().get(0).getFile_struktur_org_foto())
                                .into(photoView);
                    }catch (Exception e){
                        Log.d("Error ",e.toString());
                    }
                }else{
                    Toast.makeText(StrukturOrganisasiActivity.this, "Terjadi Kesalahan ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(StrukturOrganisasiActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void Logic(){
        mManager = new GridLayoutManager(StrukturOrganisasiActivity.this,1);
        recycler.setLayoutManager(mManager);
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseModel> KabarBerita = api.StrukturSekolah(destiny.AUTH(Token));
        KabarBerita.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                try {
                    if (response.body().getStatusCode().equals("000")){
                        mItems=response.body().getData();
                        mAdapter = new AdapterStrukturOrganisasi(StrukturOrganisasiActivity.this,mItems);
                        recycler.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                    }else if (response.body().getStatusCode().equals("001") || response.body().getStatusCode().equals("002")){
                        destiny.AutoLogin(Username,Password, StrukturOrganisasiActivity.this);
                        Logic();
                    }else{
                        Toast.makeText(StrukturOrganisasiActivity.this, "Terjadi Kesalahan ", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(StrukturOrganisasiActivity.this, "Terjadi Kesalahan User akan Terlogout", Toast.LENGTH_SHORT).show();
                    dbHelper.Logout();
                    Intent intent = new Intent(StrukturOrganisasiActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(StrukturOrganisasiActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
//    class RetreivePDFStreamsss extends AsyncTask<String,Void, InputStream> {
//        InputStream inputStream;
//        @Override
//        protected InputStream doInBackground(String... strings) {
//            try {
//                URL url = new URL(strings[0]);
//                HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
//                if (urlConnection.getResponseCode() == 200){
//                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
//                }
//            }catch (IOException e){
//                return null;
//            }
//            return inputStream;
//        }
//
//        @Override
//        protected void onPostExecute(InputStream inputStream) {
//            photoView.fromStream(inputStream).load();
//        }
//    }
}