package com.destiny.cianjursipp.Activity.Marketplace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.destiny.cianjursipp.API.ApiRequest;
import com.destiny.cianjursipp.API.RetroServer;
import com.destiny.cianjursipp.Activity.LoginActivity;
import com.destiny.cianjursipp.Activity.MainActivity;
import com.destiny.cianjursipp.Adapter.Universal.AdapterProduk;
import com.destiny.cianjursipp.Method.Destiny;
import com.destiny.cianjursipp.Model.Pena.Marketplace.ProdukModel;
import com.destiny.cianjursipp.Model.Pena.Marketplace.ResponseMarketplace;
import com.destiny.cianjursipp.R;
import com.destiny.cianjursipp.SharedPreferance.DB_Helper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MarketplaceSiswaActivity extends AppCompatActivity {
    Destiny destiny;
    RelativeLayout Back;
    DB_Helper dbHelper;
    String Username,Password,Nama,Token,Level,Photo;
    RecyclerView recycler;
    private List<ProdukModel> mItems = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;

    LottieAnimationView Anim;
    LinearLayout LAnim;
    TextView TAnim;

    ImageView GambarSiswa;
    TextView NamaSiswa;
    Button TambahProduk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marketplace_siswa);
        destiny = new Destiny();
        Anim = findViewById(R.id.lottieAnim);
        LAnim = findViewById(R.id.linearAnim);
        TAnim = findViewById(R.id.tvAnim);
        Back = findViewById(R.id.relativeBack);
        recycler = findViewById(R.id.recycler);
        GambarSiswa = findViewById(R.id.ivSiswa);
        NamaSiswa = findViewById(R.id.tvNamaSiswa);
        TambahProduk = findViewById(R.id.btnTambahProduk);
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
        if (Photo.isEmpty()){
            GambarSiswa.setImageResource(R.drawable.childern);
        }else{
            Glide.with(MarketplaceSiswaActivity.this)
                    .load(destiny.BASE_URL()+Photo)
                    .into(GambarSiswa);
        }
        NamaSiswa.setText(Nama);
        TambahProduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MarketplaceSiswaActivity.this,InputProdukActivity.class);
                startActivity(intent);
            }
        });
        Logic();
    }
    private void Logic(){
        mManager = new GridLayoutManager(MarketplaceSiswaActivity.this,1);
        recycler.setLayoutManager(mManager);
        LAnim.setVisibility(View.VISIBLE);
        TAnim.setVisibility(View.GONE);
        Anim.setAnimation("loading.json");
        Anim.playAnimation();
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseMarketplace> MP = api.ProdukSiswa(destiny.AUTH(Token));
        MP.enqueue(new Callback<ResponseMarketplace>() {
            @Override
            public void onResponse(Call<ResponseMarketplace> call, Response<ResponseMarketplace> response) {
                try {
                    if (response.body().getStatusCode().equals("000")){
                        mItems = response.body().getData();
                        if (mItems.size()<1){
                            TAnim.setVisibility(View.VISIBLE);
                            TAnim.setText("Produk Belum Ada");
                            Anim.setAnimation("notfound.json");
                            Anim.playAnimation();
                        }else{
                            LAnim.setVisibility(View.GONE);
                            mAdapter = new AdapterProduk(MarketplaceSiswaActivity.this,mItems);
                            recycler.setAdapter(mAdapter);
                            mAdapter.notifyDataSetChanged();
                        }
                    }else if (response.body().getStatusCode().equals("001") || response.body().getStatusCode().equals("002")){
                        destiny.AutoLogin(Username,Password,MarketplaceSiswaActivity.this);
                        Intent intent = new Intent(MarketplaceSiswaActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(MarketplaceSiswaActivity.this, "Terjadi Kesalahan ", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(MarketplaceSiswaActivity.this, "Terjadi Kesalahan User akan Terlogout", Toast.LENGTH_SHORT).show();
                    dbHelper.Logout();
                    Intent intent = new Intent(MarketplaceSiswaActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseMarketplace> call, Throwable t) {
                Toast.makeText(MarketplaceSiswaActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}