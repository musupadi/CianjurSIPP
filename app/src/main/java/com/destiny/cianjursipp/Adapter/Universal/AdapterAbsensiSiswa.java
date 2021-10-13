package com.destiny.cianjursipp.Adapter.Universal;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.destiny.cianjursipp.Activity.Absensi.AbsensiKelasActivity;
import com.destiny.cianjursipp.Method.Destiny;
import com.destiny.cianjursipp.Model.DataModel;
import com.destiny.cianjursipp.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterAbsensiSiswa extends RecyclerView.Adapter<AdapterAbsensiSiswa.HolderData>  {
    private List<DataModel> mList;
    private Context ctx;
    Destiny destiny;
    ArrayList<String> ID_SISWA = new ArrayList<String>();
    ArrayList<String> ALASAN = new ArrayList<String>();
    ArrayList<String> ABSEN = new ArrayList<String>();
    public AdapterAbsensiSiswa(Context ctx, List<DataModel> mList,ArrayList<String> ID_SISWA,ArrayList<String> ALASAN,ArrayList<String> ABSEN){
        this.ctx = ctx;
        this.mList = mList;
        this.ID_SISWA = ID_SISWA;
        this.ALASAN = ALASAN;
        this.ABSEN = ABSEN;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_absensi_siswa,viewGroup,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final HolderData holderData, int posistion) {
        destiny = new Destiny();
        final DataModel dm = mList.get(posistion);

        ID_SISWA.add(dm.getId_siswa());
        ALASAN.add("");
        ABSEN.add("a");
        if (ID_SISWA.get(posistion).equals("")){
            DEFAULT(holderData.Alfa,holderData.Izin,holderData.Masuk);
        }
        if (ID_SISWA.size()>mList.size()){
            ID_SISWA.remove(posistion);
        }
        if (ALASAN.size()>mList.size()){
            ALASAN.remove(posistion);
        }
        if (ABSEN.size()>mList.size()){
            ABSEN.remove(posistion);
        }

        holderData.lAlasan.setVisibility(View.GONE);
        holderData.Nama.setText(dm.getNama_siswa());
        holderData.Izin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DEFAULT(holderData.Alfa,holderData.Izin,holderData.Masuk);
                Clicked(holderData.Izin,dm.getId_siswa(),posistion,"i");
                holderData.lAlasan.setVisibility(View.VISIBLE);
                holderData.Alasan.setText("");
            }
        });
        holderData.Masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DEFAULT(holderData.Alfa,holderData.Izin,holderData.Masuk);
                Clicked(holderData.Masuk,dm.getId_siswa(),posistion,"m");
                holderData.lAlasan.setVisibility(View.GONE);
                holderData.Alasan.setText("");
            }
        });
        holderData.Alfa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DEFAULT(holderData.Alfa,holderData.Izin,holderData.Masuk);
                Clicked(holderData.Alfa,dm.getId_siswa(),posistion,"a");
                holderData.lAlasan.setVisibility(View.GONE);
            }
        });
        holderData.Alasan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ALASAN.set(posistion,holderData.Alasan.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HolderData extends RecyclerView.ViewHolder{ ;
        TextView Nama;
        LinearLayout card,lAlasan;
        Button Izin,Masuk,Alfa;
        EditText Alasan;
        public HolderData(View v){
            super(v);
            Nama = v.findViewById(R.id.tvNama);
            card = v.findViewById(R.id.LayoutCardView);
            lAlasan = v.findViewById(R.id.linearAlasan);
            Izin = v.findViewById(R.id.btnIzin);
            Masuk = v.findViewById(R.id.btnMasuk);
            Alfa = v.findViewById(R.id.btnAlfa);
            Alasan = v.findViewById(R.id.etAlasan);
        }
    }
    public void DEFAULT(Button Alpa,Button Izin,Button Masuk){
        Alpa.setBackgroundResource(R.drawable.btn_round_primary);
        Izin.setBackgroundResource(R.drawable.btn_round_primary);
        Masuk.setBackgroundResource(R.drawable.btn_round_primary);
    }
    public void Clicked(Button card,String Jawab,int position,String absen){
        card.setBackgroundResource(R.drawable.btn_round_orange);
        ID_SISWA.set(position,Jawab);
        ABSEN.set(position,absen);
    }
}

