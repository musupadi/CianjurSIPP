package com.destiny.cianjursipp.Activity.menu.LowonganKerja;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.destiny.cianjursipp.API.ApiRequest;
import com.destiny.cianjursipp.API.RetroServer;
import com.destiny.cianjursipp.Activity.LoginActivity;
import com.destiny.cianjursipp.Activity.MainActivity;
import com.destiny.cianjursipp.Activity.menu.Guru.GuruActivity;
import com.destiny.cianjursipp.Adapter.Home.AdapterGuru;
import com.destiny.cianjursipp.Adapter.Home.AdapterLoker;
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

public class LowonganKerjaActivity extends AppCompatActivity {
    Destiny destiny;
    RelativeLayout Back;
    DB_Helper dbHelper;
    String Username,Password,Nama,Token,Level,Photo;
    RecyclerView recycler;
    private List<DataModel> mItems = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;

    LottieAnimationView Anim;
    LinearLayout LAnim;
    TextView TAnim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lowongan_kerja);
        destiny = new Destiny();
        Anim = findViewById(R.id.lottieAnim);
        LAnim = findViewById(R.id.linearAnim);
        TAnim = findViewById(R.id.tvAnim);
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
        Logic();
    }
    private void Logic(){
        mManager = new GridLayoutManager(LowonganKerjaActivity.this,1);
        recycler.setLayoutManager(mManager);
        LAnim.setVisibility(View.VISIBLE);
        TAnim.setVisibility(View.GONE);
        Anim.setAnimation("loading.json");
        Anim.playAnimation();
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseModel> Temans = api.Loker(destiny.AUTH(Token));
        Temans.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                try {
                    if (response.body().getStatusCode().equals("000")){
                        mItems = response.body().getData();
                        if (mItems.size()<1){
                            TAnim.setVisibility(View.VISIBLE);
                            TAnim.setText("Lowongan Kerja Belum Ada");
                            Anim.setAnimation("notfound.json");
                            Anim.playAnimation();
                        }else{
                            LAnim.setVisibility(View.GONE);
                            mAdapter = new AdapterLoker(LowonganKerjaActivity.this,mItems);
                            recycler.setAdapter(mAdapter);
                            mAdapter.notifyDataSetChanged();
                        }
                    }else if (response.body().getStatusCode().equals("001") || response.body().getStatusCode().equals("002")){
                        destiny.AutoLogin(Username,Password,LowonganKerjaActivity.this);
                        Intent intent = new Intent(LowonganKerjaActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(LowonganKerjaActivity.this, "Terjadi Kesalahan ", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(LowonganKerjaActivity.this, "Terjadi Kesalahan User akan Terlogout", Toast.LENGTH_SHORT).show();
                    dbHelper.Logout();
                    Intent intent = new Intent(LowonganKerjaActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(LowonganKerjaActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}