package com.destiny.cianjursipp.Activity.menu.Kebudayaan.KamusBahasa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.destiny.cianjursipp.Method.Destiny;
import com.destiny.cianjursipp.Method.Translate_API;
import com.destiny.cianjursipp.R;

public class KamusBahasaActivity extends AppCompatActivity {
    EditText et1,et2;
    Spinner spin1,spin2;
    Destiny destiny = new Destiny();
    int Checker=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kamus_bahasa);
        initUi();
        et1.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                Logic();
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Logic();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Logic();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    private void Logic(){
        if (Checker>=1){
            Translate_API translate = new Translate_API();
            translate.setOnTranslationCompleteListener(new Translate_API.OnTranslationCompleteListener() {
                @Override
                public void onStartTranslation() {
                    // here you can perform initial work before translated the text like displaying progress bar
                }

                @Override
                public void onCompleted(String text) {
                    // "text" variable will give you the translated text
                    et2.setText(text);
                }

                @Override
                public void onError(Exception e) {

                }
            });
            translate.execute(et1.getText().toString(), destiny.CheckLanguageID(spin1.getSelectedItem().toString()), destiny.CheckLanguageID(spin2.getSelectedItem().toString()));
        }else{
            Checker=1;
        }
    }
    private void initUi() {
        et1=findViewById(R.id.etText1);
        et2=findViewById(R.id.etText2);
        spin1=findViewById(R.id.sp1);
        spin2=findViewById(R.id.sp2);
    }
}