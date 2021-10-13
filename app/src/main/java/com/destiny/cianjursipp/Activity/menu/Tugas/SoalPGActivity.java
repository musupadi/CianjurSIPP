package com.destiny.cianjursipp.Activity.menu.Tugas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.destiny.cianjursipp.API.ApiRequest;
import com.destiny.cianjursipp.API.RetroServer;
import com.destiny.cianjursipp.Activity.LoginActivity;
import com.destiny.cianjursipp.Activity.MainActivity;
import com.destiny.cianjursipp.Activity.menu.MediaPembelajaran.MediaPembelajaranActivity;
import com.destiny.cianjursipp.Adapter.Home.AdapterSoalPG;
import com.destiny.cianjursipp.Method.Destiny;
import com.destiny.cianjursipp.Model.DataModel;
import com.destiny.cianjursipp.Model.Pena.NewResponse;
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

public class SoalPGActivity extends AppCompatActivity {
    PDFView photoView;
    String ID,NAMA;
    Button Jawab;
    Destiny destiny;
    RelativeLayout Back;
    DB_Helper dbHelper;
    String Username,Password,Nama,Token,Level,Photo;
    Dialog dialog;
    RecyclerView recyclerView;
    Button JawabSoal,Tutup;

    private List<DataModel> mItems = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    ArrayList<String> JAWABAN = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soal_p_g);
        Declaration();
        GETDATA();
    }
    private void Declaration(){
        photoView =findViewById(R.id.ivPDF);
        Jawab = findViewById(R.id.btnJawab);
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
        dbHelper.DeleteTugas();
    }
    private void GETDATA(){
        Intent intent = getIntent();
        ID = intent.getExtras().getString("ID");
        NAMA = intent.getExtras().getString("NAMA");
        getSupportActionBar().setTitle(NAMA);
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseModel> Data=api.Tugas(destiny.AUTH(Token),ID);
        Data.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                try {
                    if (response.body().getStatusCode().equals("000")){
                        new RetreivePDFStreamsss().execute(destiny.BASE_URL()+response.body().getData().get(0).getSoal());
                        dialog = new Dialog(SoalPGActivity.this);
                        dialog.setContentView(R.layout.dialog_jawaban);
                        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        dialog.getWindow().setBackgroundDrawableResource(R.drawable.btn_rounded_white);
                        recyclerView = dialog.findViewById(R.id.recycler);
                        JawabSoal = dialog.findViewById(R.id.btnJawabSoal);
                        JawabSoal = dialog.findViewById(R.id.btnJawabSoal);
                        Tutup = dialog.findViewById(R.id.btnTutupSoal);
                        mManager = new GridLayoutManager(SoalPGActivity.this,1);
                        recyclerView.setItemViewCacheSize(100);
                        recyclerView.setDrawingCacheEnabled(true);
                        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
                        recyclerView.setLayoutManager(mManager);
                        mAdapter = new AdapterSoalPG(SoalPGActivity.this,Integer.parseInt(response.body().getData().get(0).jumlahsoal),JAWABAN);
                        recyclerView.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                        if (!Level.equals("siswa")){
                            Jawab.setVisibility(View.GONE);
                        }
                        Jawab.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.show();
                            }
                        });
                        Tutup.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.hide();
                            }
                        });
                        JawabSoal.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (JAWABAN.contains("")){
                                    AlertDialog.Builder builder = new AlertDialog.Builder(SoalPGActivity.this);

                                    // Set a title for alert dialog
                                    builder.setTitle("Pemberitahuan");

                                    // Ask the final question
                                    builder.setMessage("Ada Beberapa Soal Yang belum disisi yakin ingin Mengirimkan ke guru ?\nSalah Satu Nomor Yang Belum Diisi Adalah "+String.valueOf(JAWABAN.indexOf("")+1));

                                    // Set the alert dialog yes button click listener
                                    builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            // Do something when user clicked the Yes button
                                            // Set the TextView visibility GONE
                                            Send();
                                        }
                                    });

                                    // Set the alert dialog no button click listener
                                    builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            // Do something when No button clicked
                                        }
                                    });

                                    AlertDialog dialog = builder.create();
                                    // Display the alert dialog on interface
                                    dialog.show();
                                }else{
                                    AlertDialog.Builder builder = new AlertDialog.Builder(SoalPGActivity.this);

                                    // Set a title for alert dialog
                                    builder.setTitle("Pemberitahuan");

                                    // Ask the final question
                                    builder.setMessage("Semua soal sudah terisi tapi sebaiknya di check dulu apakah sudah yakin semua benar ?");

                                    // Set the alert dialog yes button click listener
                                    builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            // Do something when user clicked the Yes button
                                            // Set the TextView visibility GONE
                                            Send();
                                        }
                                    });

                                    // Set the alert dialog no button click listener
                                    builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            // Do something when No button clicked
                                        }
                                    });

                                    AlertDialog dialog = builder.create();
                                    // Display the alert dialog on interface
                                    dialog.show();
                                }

                            }
                        });
                    }else if (response.body().getStatusCode().equals("001") || response.body().getStatusCode().equals("002")){
                        destiny.AutoLogin(Username,Password,SoalPGActivity.this);
                        Intent intent = new Intent(SoalPGActivity.this, MediaPembelajaranActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(SoalPGActivity.this, "Terjadi Kesalahan ", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(SoalPGActivity.this, "Terjadi Kesalahan User akan Terlogout", Toast.LENGTH_SHORT).show();
                    dbHelper.Logout();
                    Intent intent = new Intent(SoalPGActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(SoalPGActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void Send(){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseModel> Data=api.TugasPG(destiny.AUTH(Token),ID,JAWABAN);
        Data.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                Toast.makeText(SoalPGActivity.this, response.body().getStatusMessage(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SoalPGActivity.this,NewTugasActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(SoalPGActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    class RetreivePDFStreamsss extends AsyncTask<String,Void, InputStream> {
        InputStream inputStream;
        @Override
        protected InputStream doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
                if (urlConnection.getResponseCode() == 200){
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }
            }catch (IOException e){
                return null;
            }
            return inputStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            photoView.fromStream(inputStream).load();
        }
    }
}