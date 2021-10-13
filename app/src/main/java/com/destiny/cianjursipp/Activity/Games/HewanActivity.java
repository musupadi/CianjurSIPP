package com.destiny.cianjursipp.Activity.Games;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.destiny.cianjursipp.R;

import java.util.ArrayList;
import java.util.Locale;

public class HewanActivity extends AppCompatActivity {
    TextView text,nama;
    Button speech;
    ImageView image;
    TextToSpeech textToSpeech;
    private static final int RECOGNIZER_RESULT = 1;

    String NAMA,GAMBAR,INISIAL,ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hewan);
        text = findViewById(R.id.tvJawaban);
        nama = findViewById(R.id.tvNama);
        image = findViewById(R.id.ivImage);
        speech = findViewById(R.id.btnSpeech);

        Intent intent = getIntent();
        NAMA = intent.getExtras().getString("NAMA");
        GAMBAR = intent.getExtras().getString("GAMBAR");
        INISIAL = intent.getExtras().getString("INISIAL");
        ID = intent.getExtras().getString("ID");
        getSupportActionBar().setTitle(NAMA);

        Glide.with(this)
                .load(GAMBAR)
                .into(image);
        nama.setText(NAMA);
        textToSpeech = new TextToSpeech(getApplicationContext(),
                new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS){
                    textToSpeech.setLanguage(Locale.ENGLISH);
                }
            }
        });
        speech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent SpeechIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                SpeechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                SpeechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                SpeechIntent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Sebutkan Nama Hewannya");
                startActivityForResult(SpeechIntent,RECOGNIZER_RESULT);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == RECOGNIZER_RESULT && resultCode ==RESULT_OK){
            ArrayList<String> matches= data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            text.setText(matches.get(0).toString());
            text.setVisibility(View.VISIBLE);
            if (text.getText().toString().equals(NAMA.toLowerCase())){
                text.setTextColor(Color.rgb(73,194,0));
                text.setText("Jawaban Benar\n"+matches.get(0).toString());
            }else{
                text.setTextColor(Color.rgb(255,0,0));
                text.setText("Jawaban Bukan "+matches.get(0).toString()+" Melainkan "+NAMA);
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}