package com.destiny.cianjursipp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.destiny.cianjursipp.API.ApiRequest;
import com.destiny.cianjursipp.API.DestinyServer;
import com.destiny.cianjursipp.Activity.ui.AbsenFragment;
import com.destiny.cianjursipp.Activity.ui.AbsenFragmentGuru;
import com.destiny.cianjursipp.Activity.ui.GamesFragment;
import com.destiny.cianjursipp.Activity.ui.HomeFragment;
import com.destiny.cianjursipp.Activity.ui.IzinFragment;
import com.destiny.cianjursipp.Activity.ui.UserFragment;
import com.destiny.cianjursipp.Method.Destiny;
import com.destiny.cianjursipp.Model.Destiny.ResponseDestiny;
import com.destiny.cianjursipp.R;
import com.destiny.cianjursipp.SharedPreferance.DB_Helper;

import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    LinearLayout LHome,LIzin,LAbsen,LGames,LUser;
    TextView THome,TIzin,TAbsen,TGames,TUser;
    ImageView IHome,IIzin,IAbsen,IGames,IUser;
    Fragment fragment;
    private String[] galleryPermissions =
            {Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA};
    DB_Helper dbHelper;
    String Username,Password,Nama,Token,Level,Photo;
    Destiny destiny;
    String PAGING;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //Bottom Nav
        LHome = findViewById(R.id.linearHome);
        LIzin = findViewById(R.id.linearIzin);
        LAbsen = findViewById(R.id.linearAbsen);
        LGames = findViewById(R.id.linearGames);
        LUser = findViewById(R.id.linearUser);
        THome = findViewById(R.id.tvHome);
        TIzin = findViewById(R.id.tvIzin);
        TAbsen = findViewById(R.id.tvAbsen);
        TGames = findViewById(R.id.tvGames);
        TUser = findViewById(R.id.tvUser);
        IHome = findViewById(R.id.ivHome);
        IIzin = findViewById(R.id.ivIzin);
        IAbsen = findViewById(R.id.ivAbsen);
        IGames = findViewById(R.id.ivGames);
        IUser = findViewById(R.id.ivUser);


        //
        dbHelper = new DB_Helper(HomeActivity.this);
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
        try {
            Intent intent = getIntent();
            PAGING = intent.getExtras().getString("PAGING");
            Toast.makeText(this, "Users", Toast.LENGTH_LONG).show();
            User();
        }catch (Exception e){
            Home();
        }
        if (Level.equals("guest")){
            LAbsen.setVisibility(View.GONE);
            LIzin.setVisibility(View.GONE);
            IUser.setImageResource(R.drawable.ic_baseline_exit_to_app_24);
            TUser.setText("Login");
        }
        LHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Home();
            }
        });
        LIzin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Izin();
            }
        });
        LAbsen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Absen();
            }
        });
        LGames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Games();
            }
        });
        LUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User();
            }
        });
        if(EasyPermissions.hasPermissions(HomeActivity.this, galleryPermissions)) {

        }else{
            EasyPermissions.requestPermissions(HomeActivity.this, "Access for storage",
                    101, galleryPermissions);
        }
        ApiRequest api = DestinyServer.getClient().create(ApiRequest.class);
        Call<ResponseDestiny> Check = api.Checkers("Basic YWRtaW46MTIzNA==",
                "destinykitabelajarkey");
        Check.enqueue(new Callback<ResponseDestiny>() {
            @Override
            public void onResponse(Call<ResponseDestiny> call, Response<ResponseDestiny> response) {
                try {
                    if (response.body().getData().equals("1")){

                    }else{
//                        DB_Helper db_helper = new DB_Helper(HomeActivity.this);
////                        db_helper.Logout();
//                        Toast.makeText(HomeActivity.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(HomeActivity.this,LoginActivity.class);
//                        startActivity(intent);
                    }
                }catch (Exception e){
//                    Toast.makeText(HomeActivity.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDestiny> call, Throwable t) {
//                Toast.makeText(HomeActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void Default(){
        IHome.setImageResource(R.drawable.home);
        THome.setTextColor(Color.rgb(0,0,0));
        IIzin.setImageResource(R.drawable.izin);
        TIzin.setTextColor(Color.rgb(0,0,0));
        IAbsen.setImageResource(R.drawable.absen);
        TAbsen.setTextColor(Color.rgb(0,0,0));
        IGames.setImageResource(R.drawable.games);
        TGames.setTextColor(Color.rgb(0,0,0));
        IUser.setImageResource(R.drawable.user);
        TUser.setTextColor(Color.rgb(0,0,0));
    }
    private void Home(){
        Default();
        IHome.setImageResource(R.drawable.home_active);
        THome.setTextColor(Color.rgb(37,166,161));
        fragment = new HomeFragment();
        ChangeFragment(fragment);
    }
    private void Izin(){
        Default();
        IIzin.setImageResource(R.drawable.izin_active);
        TIzin.setTextColor(Color.rgb(37,166,161));
        fragment = new IzinFragment();
        ChangeFragment(fragment);
    }
    private void Absen(){
        Default();
        IAbsen.setImageResource(R.drawable.absen_active);
        TAbsen.setTextColor(Color.rgb(37,166,161));
        if (Level.equals("guru")){
            fragment = new AbsenFragmentGuru();
            ChangeFragment(fragment);
        }else{
            fragment = new AbsenFragment();
            ChangeFragment(fragment);
        }

    }
    private void Games(){
        Default();
        IGames.setImageResource(R.drawable.games_active);
        TGames.setTextColor(Color.rgb(37,166,161));
        fragment = new GamesFragment();
        ChangeFragment(fragment);
    }
    private void User(){
        if (Level.equals("guest")){
            dbHelper.Logout();
            Intent intent = new Intent(HomeActivity.this,LoginActivity.class);
            startActivity(intent);
        }else{
            Default();
            IUser.setImageResource(R.drawable.user_active);
            TUser.setTextColor(Color.rgb(37,166,161));
            fragment = new UserFragment();
            ChangeFragment(fragment);
        }
    }
    private void ChangeFragment(Fragment fragment){
        if(fragment != null){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.Container,fragment);
            ft.commit();
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);

        // Set a title for alert dialog
        builder.setTitle("Pemberitahuan");

        // Ask the final question
        builder.setMessage("Apakah Anda Yakin Ingin Logout ? ");

        // Set the alert dialog yes button click listener
        builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do something when user clicked the Yes button
                // Set the TextView visibility GONE
                DB_Helper db_helper = new DB_Helper(HomeActivity.this);
                db_helper.Logout();
                Toast.makeText(HomeActivity.this, "Anda Berhasil Logout", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
                finishAffinity();
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