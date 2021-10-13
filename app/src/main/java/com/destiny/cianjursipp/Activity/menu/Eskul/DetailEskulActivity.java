package com.destiny.cianjursipp.Activity.menu.Eskul;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.destiny.cianjursipp.Adapter.Universal.TabPagerAdapter;
import com.destiny.cianjursipp.Method.Destiny;
import com.destiny.cianjursipp.R;
import com.destiny.cianjursipp.SharedPreferance.DB_Helper;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

public class DetailEskulActivity extends AppCompatActivity {
    Destiny destiny;
    RelativeLayout Back;
    DB_Helper dbHelper;
    String Username,Password,Nama,Token,Level,Photo;
    String Eskul;
    //DETAIL Eskul
    String ID_ESKUL,NAMA;
    TextView eskul,deskripsi,pembimbing;
    ImageView gambar;

    private TabLayout Table;
    private AppBarLayout appBar;
    private ViewPager viewPager;
    private FragmentActivity context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_eskul);
        destiny = new Destiny();
        DBHelper();
        Declaration();
        GETDATA();
        ONCLICK();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    private void DBHelper(){
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
        Cursor cursor2 = dbHelper.checkEskul();
        if (cursor2.getCount()>0){
            while (cursor2.moveToNext()){
                Eskul = cursor2.getString(0);
            }
        }
        Table = findViewById(R.id.tableLayout);
        viewPager = findViewById(R.id.viewpager);
        TabPagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager());
        adapter.AddFragment(new DetailEskulFragment(),"Detail");
        adapter.AddFragment(new AnggotaEskulFragment(),"Anggota");
        adapter.AddFragment(new GalleryEskulFragment(),"Gallery");
        viewPager.setAdapter(adapter);
        Table.setupWithViewPager(viewPager);
    }
    private void ONCLICK(){

    }
    private void Declaration(){

    }
    private void GETDATA(){
        Intent intent = getIntent();
        NAMA = intent.getExtras().getString("ESKUL");
        getSupportActionBar().setTitle(NAMA);
    }
}