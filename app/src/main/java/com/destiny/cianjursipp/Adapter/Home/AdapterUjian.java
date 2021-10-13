package com.destiny.cianjursipp.Adapter.Home;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.destiny.cianjursipp.Activity.menu.Ujian.DetailUjianActivity;
import com.destiny.cianjursipp.Method.Destiny;
import com.destiny.cianjursipp.Model.DataModel;
import com.destiny.cianjursipp.Model.SubTema;
import com.destiny.cianjursipp.R;
import com.destiny.cianjursipp.SharedPreferance.DB_Helper;

import java.util.ArrayList;
import java.util.List;

public class AdapterUjian extends RecyclerView.Adapter<AdapterUjian.HolderData> {
    private List<DataModel> mList;
    private Context ctx;

    DB_Helper dbHelper;
    Boolean onClick=true;
    RecyclerView recyclerView;
    Destiny destiny;
    Dialog dialog;
    TextView nilai;
    Button tutup;
    String Username,Password,Nama,Token,Level,Photo;
    public AdapterUjian(Context ctx, List<DataModel> mList){
        this.ctx = ctx;
        this.mList = mList;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_ujian,viewGroup,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final HolderData holderData, int posistion) {
        destiny = new Destiny();
        dbHelper = new DB_Helper(ctx);
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
        dialog = new Dialog(ctx);
        dialog.setContentView(R.layout.dialog_nilai);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.btn_rounded_white_2);
        nilai = dialog.findViewById(R.id.tvNilai);
        tutup = dialog.findViewById(R.id.btnClose);
        final DataModel dm = mList.get(posistion);
        if (Level.equals("siswa")){
            if (!dm.getScore_tugas().equals("0")){
                holderData.card.setAlpha(0.5f);
            }else{
                holderData.card.setAlpha(1f);
            }
        }
        holderData.Nama.setText(dm.getNama_mapel());
        holderData.Tanggal.setText(dm.getNama_hari()+", "+destiny.MagicDateChange(dm.getTgl_ujian())+" - PKL. "+dm.getJam_mulai());
        holderData.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Level.equals("siswa")){
                    if (dm.getScore_tugas().equals("0")){
                        Intent i = new Intent(ctx, DetailUjianActivity.class);
                        i.putExtra("ID", dm.getId_ujian());
                        i.putExtra("ID_JADWAL", dm.getId_ujian_jadwal());
                        i.putExtra("JUMLAH", dm.getJumlah_soal());
                        i.putExtra("PDF", dm.getFile_ujian());
                        i.putExtra("NAMA", dm.getNama_mapel());
                        ctx.startActivity(i);
                    }else{
                        dialog.show();
                        nilai.setText("Nilai Anda Adalah : "+dm.getScore_tugas());
                    }
                }else{
                    Intent i = new Intent(ctx, DetailUjianActivity.class);
                    i.putExtra("ID", dm.getId_ujian());
                    i.putExtra("ID_JADWAL", dm.getId_ujian_jadwal());
                    i.putExtra("JUMLAH", dm.getJumlah_soal());
                    i.putExtra("PDF", dm.getFile_ujian());
                    i.putExtra("NAMA", dm.getNama_mapel());
                    ctx.startActivity(i);
                }
            }
        });
        tutup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.hide();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        TextView Nama,Tanggal;
        LinearLayout card;
        public HolderData(View v){
            super(v);
            Nama = v.findViewById(R.id.tvNama);
            card = v.findViewById(R.id.card_view);
            Tanggal = v.findViewById(R.id.tvTgl);
        }
    }
}
