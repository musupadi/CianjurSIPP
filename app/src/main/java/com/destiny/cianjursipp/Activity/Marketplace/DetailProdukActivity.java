package com.destiny.cianjursipp.Activity.Marketplace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.destiny.cianjursipp.Method.Destiny;
import com.destiny.cianjursipp.R;
import com.destiny.cianjursipp.SharedPreferance.DB_Helper;

public class DetailProdukActivity extends AppCompatActivity {
    Destiny destiny;
    DB_Helper dbHelper;
    String Username,Password,Nama,Token,Level,Photo;

    //DETAIL KABAR
    String NAMA,SEKOLAH,HARGA,GAMBAR,DESKRIPSI,PENGURUS,TELPON;
    TextView nama,harga,pengurus,telpon;
    ImageView gambar;
    WebView Web;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_produk);
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
        Declaration();
        GETDATA();
    }
    private void Declaration(){
        Web = findViewById(R.id.web);
        nama = findViewById(R.id.tvNama);
        harga = findViewById(R.id.tvHarga);
        gambar = findViewById(R.id.ivGambar);
        pengurus = findViewById(R.id.tvPengurus);
        telpon = findViewById(R.id.tvNoTelpon);
    }
    private void GETDATA(){
        Intent intent = getIntent();
        NAMA = intent.getExtras().getString("NAMA");
        SEKOLAH = intent.getExtras().getString("SEKOLAH");
        HARGA = intent.getExtras().getString("HARGA");
        GAMBAR = intent.getExtras().getString("GAMBAR");
        PENGURUS = intent.getExtras().getString("PENGURUS");
        DESKRIPSI = intent.getExtras().getString("DESKRIPSI");
        TELPON = intent.getExtras().getString("TELPON");
        getSupportActionBar().setTitle(SEKOLAH);

        telpon.setText("No Telpon : "+TELPON);
        Web.loadData(DESKRIPSI,"text/html","UTF-8");
        pengurus.setText("Pengurus : "+PENGURUS);
        nama.setText(NAMA);
        harga.setText("Harga : "+HARGA);

        Glide.with(this)
                .load(GAMBAR)
                .into(gambar);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}