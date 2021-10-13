package com.destiny.cianjursipp.Activity.menu.Kebudayaan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.destiny.cianjursipp.Activity.menu.Kebudayaan.CeritaSunda.CeritaSundaActivity;
import com.destiny.cianjursipp.Activity.menu.Kebudayaan.KamusBahasa.KamusBahasaActivity;
import com.destiny.cianjursipp.Activity.menu.Kebudayaan.SejarahSunda.SejarahSundaActivity;
import com.destiny.cianjursipp.R;

public class KebudayaanActivity extends AppCompatActivity {
    CardView Kamus,Cerita,Sejarah;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kebudayaan);
        Kamus = findViewById(R.id.cardKamusSunda);
        Cerita = findViewById(R.id.cardCeritaSunda);
        Sejarah = findViewById(R.id.cardSejarahSunda);
        Cerita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KebudayaanActivity.this, CeritaSundaActivity.class);
                startActivity(intent);
            }
        });
        Sejarah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KebudayaanActivity.this, SejarahSundaActivity.class);
                startActivity(intent);
            }
        });
        Kamus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(KebudayaanActivity.this, KamusBahasaActivity.class);
//                startActivity(intent);
            }
        });
    }
}