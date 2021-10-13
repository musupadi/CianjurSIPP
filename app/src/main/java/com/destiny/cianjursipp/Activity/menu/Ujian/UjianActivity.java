package com.destiny.cianjursipp.Activity.menu.Ujian;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.destiny.cianjursipp.R;

public class UjianActivity extends AppCompatActivity {
    LinearLayout Harian,PTS,PAS,PAT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ujian);
//        Harian = findViewById(R.id.linearHarian);
        PTS = findViewById(R.id.linearPTS);
        PAS = findViewById(R.id.linearPAS);
        PAT = findViewById(R.id.linearPAT);

//        Harian.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(UjianActivity.this, Ujian2Activity.class);
//                i.putExtra("NAMA", "Penliaian Harian");
//                i.putExtra("ID", "Penliaian Harian");
//                startActivity(i);
//            }
//        });

        PTS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UjianActivity.this, PenilaianTengahSemesterActivity.class);
                startActivity(i);
            }
        });

        PAS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UjianActivity.this, Ujian2Activity.class);
                i.putExtra("NAMA", "Penilaian Akhir Semester");
                i.putExtra("ID", "pas");
                startActivity(i);
            }
        });

        PAT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UjianActivity.this, Ujian2Activity.class);
                i.putExtra("NAMA", "Penilaian Akhir Tahun");
                i.putExtra("ID", "pat");
                startActivity(i);
            }
        });
    }
}