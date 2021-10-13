package com.destiny.cianjursipp.Activity.menu.JumatNgaji.GuruNgaji;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.destiny.cianjursipp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Locale;

public class DetailGuruNgajiActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    String NAMA,ALAMAT,LAT,LONG,FOTO;
    TextView Alamat,Nama;
    Button btnAlamat;
    ImageView Foto;

    //    i.putExtra("NAMA", dm.getNama_guru_ngaji());
//                i.putExtra("",dm.getAlamat_guru_ngaji());
//                i.putExtra("",dm.getLatitude_guru_ngaji());
//                i.putExtra(", dm.getLongitude_guru_ngaji());
//                i.putExtra("", destiny.BASE_URL()+dm.getFoto_guru_ngaji());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_guru_ngaji);
// Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);





    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Nama = findViewById(R.id.tvNamaGuru);
        Alamat = findViewById(R.id.tvAlamat);
        btnAlamat = findViewById(R.id.btnAlamat);
        Foto = findViewById(R.id.ivFotoGuruNgaji);


        Intent intent = getIntent();
        NAMA = intent.getExtras().getString("NAMA");
        ALAMAT = intent.getExtras().getString("ALAMAT");
        LAT = intent.getExtras().getString("LAT");
        LONG = intent.getExtras().getString("LONG");
        FOTO = intent.getExtras().getString("FOTO");

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(Double.parseDouble(LAT), Double.parseDouble(LONG));
        mMap.addMarker(new MarkerOptions().position(sydney).title(NAMA));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(sydney));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney,16F));

        Nama.setText(NAMA);
        Alamat.setText(ALAMAT);
        Glide.with(this)
                .load(FOTO)
                .into(Foto);

        btnAlamat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = String.format(Locale.ENGLISH, "geo:%f,%f", Double.parseDouble(LAT), Double.parseDouble(LONG));
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent);
            }
        });

    }
}