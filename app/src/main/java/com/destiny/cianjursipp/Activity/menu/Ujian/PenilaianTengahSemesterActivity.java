package com.destiny.cianjursipp.Activity.menu.Ujian;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.destiny.cianjursipp.R;

public class PenilaianTengahSemesterActivity extends AppCompatActivity {
    LinearLayout Ganjil,Genap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penilaian_tengah_semester);

        Ganjil = findViewById(R.id.linearGanjil);
        Genap = findViewById(R.id.linearGenap);

        Genap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PenilaianTengahSemesterActivity.this, Ujian2Activity.class);
                i.putExtra("NAMA", "PTS Genap");
                i.putExtra("ID", "pts_genap");
                startActivity(i);
            }
        });
        Ganjil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PenilaianTengahSemesterActivity.this, Ujian2Activity.class);
                i.putExtra("NAMA", "PTS Ganjil");
                i.putExtra("ID", "pts_ganjil");
                startActivity(i);
            }
        });
    }
}