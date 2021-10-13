package com.destiny.cianjursipp.Activity.menu.Ujian;

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
import com.destiny.cianjursipp.Activity.HomeActivity;
import com.destiny.cianjursipp.Activity.menu.Tugas.TugasActivity;
import com.destiny.cianjursipp.Model.Pena.ResponseModel;
import com.github.barteksc.pdfviewer.PDFView;

import com.destiny.cianjursipp.Activity.menu.Tugas.SoalPGActivity;
import com.destiny.cianjursipp.Adapter.Home.AdapterSoalPG;
import com.destiny.cianjursipp.Method.Destiny;
import com.destiny.cianjursipp.Model.DataModel;
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

public class DetailUjianActivity extends AppCompatActivity {
    PDFView photoView;
    String ID,ID_JADWAL,NAMA,JUMLAH,PDF;
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
        setContentView(R.layout.activity_detail_ujian);
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
    }
    private void GETDATA(){
        Intent intent = getIntent();
        ID = intent.getExtras().getString("ID");
        ID_JADWAL = intent.getExtras().getString("ID_JADWAL");
        JUMLAH = intent.getExtras().getString("JUMLAH");
        PDF = intent.getExtras().getString("PDF");
        NAMA = intent.getExtras().getString("NAMA");
        getSupportActionBar().setTitle(NAMA);

        new RetreivePDFStreamsss().execute(destiny.BASE_URL()+PDF);
        dialog = new Dialog(DetailUjianActivity.this);
        dialog.setContentView(R.layout.dialog_jawaban);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.btn_rounded_white);
        recyclerView = dialog.findViewById(R.id.recycler);
        JawabSoal = dialog.findViewById(R.id.btnJawabSoal);
        Tutup = dialog.findViewById(R.id.btnTutupSoal);
        mManager = new GridLayoutManager(DetailUjianActivity.this,1);
        recyclerView.setLayoutManager(mManager);
        mAdapter = new AdapterSoalPG(DetailUjianActivity.this,Integer.parseInt(JUMLAH),JAWABAN);
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(DetailUjianActivity.this);

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
                    AlertDialog.Builder builder = new AlertDialog.Builder(DetailUjianActivity.this);

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
    }
    public void Send(){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseModel> Data=api.Ujian(destiny.AUTH(Token),ID,ID_JADWAL,JAWABAN);
        Data.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                Toast.makeText(DetailUjianActivity.this, response.body().getStatusMessage(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DetailUjianActivity.this, UjianActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(DetailUjianActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
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