package com.destiny.cianjursipp.Activity.menu.Tugas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.destiny.cianjursipp.API.ApiRequest;
import com.destiny.cianjursipp.API.RetroServer;
import com.destiny.cianjursipp.Activity.LoginActivity;
import com.destiny.cianjursipp.Activity.MainActivity;
import com.destiny.cianjursipp.Adapter.Home.AdapterSoalEssay;
import com.destiny.cianjursipp.Method.Destiny;
import com.destiny.cianjursipp.Model.DataModel;
import com.destiny.cianjursipp.Model.Essay;
import com.destiny.cianjursipp.Model.Pena.ResponseModel;
import com.destiny.cianjursipp.R;
import com.destiny.cianjursipp.SharedPreferance.DB_Helper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SoalEssayActivity extends AppCompatActivity {
    Destiny destiny;
    RelativeLayout Back;
    DB_Helper dbHelper;
    String Username,Password,Nama,Token,Level,Photo;
    Dialog dialog;
    RecyclerView recyclerView;
    Button Jawab;
    String ID,NAMA;

    private List<DataModel> mItems = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    ArrayList<String> JAWABAN = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soal_essay);
        Declaration();
        GETDATA();
    }
    private void Declaration(){
        recyclerView =findViewById(R.id.recycler);
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
        if (!Level.equals("siswa")){
            Jawab.setVisibility(View.GONE);
        }
    }
    private void GETDATA() {
        Intent intent = getIntent();
        ID = intent.getExtras().getString("ID");
        NAMA = intent.getExtras().getString("NAMA");
        getSupportActionBar().setTitle(NAMA);
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<Essay> Data=api.TugasEssay(destiny.AUTH(Token),ID);
        Data.enqueue(new Callback<Essay>() {
            @Override
            public void onResponse(Call<Essay> call, Response<Essay> response) {
                try {
                    if (response.body().getStatusCode().equals("000")){
                        mManager = new GridLayoutManager(SoalEssayActivity.this,1);
                        recyclerView.setLayoutManager(mManager);
                        mItems = response.body().getData().getSoal();
                        mAdapter = new AdapterSoalEssay(SoalEssayActivity.this,mItems,JAWABAN);
                        recyclerView.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                        Jawab.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (JAWABAN.contains("")){
                                    AlertDialog.Builder builder = new AlertDialog.Builder(SoalEssayActivity.this);

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
                                    AlertDialog.Builder builder = new AlertDialog.Builder(SoalEssayActivity.this);

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
                        destiny.AutoLogin(Username,Password,SoalEssayActivity.this);
                        Intent intent = new Intent(SoalEssayActivity.this, MainActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(SoalEssayActivity.this, "Terjadi Kesalahan ", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(SoalEssayActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                    dbHelper.Logout();
                    Intent intent = new Intent(SoalEssayActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Essay> call, Throwable t) {

            }
        });
    }
    public void Send(){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseModel> Data=api.TugasPG(destiny.AUTH(Token),ID,JAWABAN);
        Data.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                Toast.makeText(SoalEssayActivity.this, response.body().getStatusMessage(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SoalEssayActivity.this,NewTugasActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(SoalEssayActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}