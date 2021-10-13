package com.destiny.cianjursipp.Adapter.Home;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.destiny.cianjursipp.Activity.menu.Tugas.SoalEssayActivity;
import com.destiny.cianjursipp.Activity.menu.Tugas.SoalPGActivity;
import com.destiny.cianjursipp.Activity.menu.Tugas.UploadActivity;
import com.destiny.cianjursipp.Method.Destiny;
import com.destiny.cianjursipp.Model.DataModel;
import com.destiny.cianjursipp.R;
import com.destiny.cianjursipp.SharedPreferance.DB_Helper;

import java.util.List;

public class AdapterTugas extends RecyclerView.Adapter<AdapterTugas.HolderData>  {
    private List<DataModel> mList;
    private Context ctx;
    DB_Helper dbHelper;
    String Username,Password,Nama,Token,Level,Photo;
    Destiny destiny;
    public AdapterTugas(Context ctx, List<DataModel> mList){
        this.ctx = ctx;
        this.mList = mList;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_tugas,viewGroup,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final HolderData holderData, int posistion) {
        destiny = new Destiny();
        final DataModel dm = mList.get(posistion);
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
        holderData.NamaTugas.setText(dm.getIsi_tugas());
        holderData.Mulai.setText(destiny.MagicDateChange(dm.getTgl_mulai()));
        holderData.Selesai.setText(destiny.MagicDateChange(dm.getTgl_selesai()));
        if (dm.getJenis_tugas().equals("pg")){
            holderData.cardEssayPG.setVisibility(View.VISIBLE);
            holderData.cardTugas.setVisibility(View.GONE);
            holderData.ivTugas.setImageResource(R.drawable.pilihan_ganda);
            holderData.Tugas.setText("Kuis");
        }else if(dm.getJenis_tugas().equals("tugas")){
            holderData.cardEssayPG.setVisibility(View.GONE);
            holderData.cardTugas.setVisibility(View.VISIBLE);
            holderData.Periode.setText("Batas Pengumpulan\n"+destiny.MagicDateChange(dm.getTgl_mulai())+" Sampai "+destiny.MagicDateChange(dm.getTgl_selesai()));
            holderData.Nama.setText(dm.getNama_mapel()+" "+dm.getTgl_mulai());
            holderData.Deskripsi.loadData(dm.getIsi_tugas(),"text/html","UTF-8");
            holderData.Tautan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(destiny.BASE_URL()+dm.getFile_tugas()));
                    ctx.startActivity(browserIntent);
                }
            });
            holderData.Download.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Level.equals("siswa")){
                        if (dm.getFillable().equals("true")){
                            AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
                            builder.setMessage("Download File ?")
                                    .setCancelable(false)
                                    .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            destiny.DownloadPDF(destiny.BASE_URL()+dm.getFile_tugas(),dm.getNama_mapel()+" "+dm.getTgl_mulai(),ctx);
                                        }
                                    })
                                    .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    })
                                    //Set your icon here
                                    .setTitle("Perhatian !!!")
                                    .setIcon(R.drawable.ic_baseline_print_24);
                            AlertDialog alert = builder.create();
                            alert.show();
                        }else{
                            Toast.makeText(ctx, R.string.periode_tugas, Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
                        builder.setMessage("Download File ?")
                                .setCancelable(false)
                                .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        destiny.DownloadPDF(destiny.BASE_URL()+dm.getFile_tugas(),dm.getNama_mapel()+" "+dm.getTgl_mulai(),ctx);
                                    }
                                })
                                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                })
                                //Set your icon here
                                .setTitle("Perhatian !!!")
                                .setIcon(R.drawable.ic_baseline_print_24);
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                }
            });
            if (Level.equals("siswa")){
                if (!dm.getTerjawab().equals("0")){
                    holderData.Upload.setAlpha(0.3f);
                }
            }
            holderData.Upload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Level.equals("siswa")){
                        if (dm.getFillable().equals("true")){
                            if (!dm.getTerjawab().equals("0")){
                                Toast.makeText(ctx, "Data Sudah Terjawab", Toast.LENGTH_SHORT).show();
                            }else{
                                Intent i = new Intent(ctx, UploadActivity.class);
                                i.putExtra("ID", dm.getId_tugas());
                                i.putExtra("NAMA",destiny.FilterTextToJava(dm.getIsi_tugas()));
                                ctx.startActivity(i);
                            }
                        }else{
                            Toast.makeText(ctx, R.string.periode_tugas, Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Intent i = new Intent(ctx, UploadActivity.class);
                        i.putExtra("ID", dm.getId_tugas());
                        i.putExtra("NAMA",destiny.FilterTextToJava(dm.getIsi_tugas()));
                        ctx.startActivity(i);
                    }
                }
            });
            if (Level.equals("siswa")){
                if (!dm.getTerjawab().equals("1")){
                    holderData.Nilai2.setText("Belum Dinilai");
                }else{
                    holderData.Nilai2.setText("Nilai : "+dm.getScore_tugas());
                }
            }else{
                holderData.Nilai2.setVisibility(View.GONE);
            }
        }else{
            holderData.cardEssayPG.setVisibility(View.VISIBLE);
            holderData.cardTugas.setVisibility(View.GONE);
            holderData.ivTugas.setImageResource(R.drawable.essay);
            holderData.Tugas.setText("Pertanyaan");
        }
        try {
            if (Level.equals("siswa")){
                if (dm.getTerjawab().equals("0")){
                    holderData.Nilai.setVisibility(View.GONE);
                }else{
                    if (!dm.getScore_tugas().equals("")){
                        holderData.Nilai.setVisibility(View.VISIBLE);
                        holderData.Nilai.setText("Nilai : "+dm.getScore_tugas());
                    }
                }
            }else{
                holderData.Nilai.setVisibility(View.GONE);
            }
        }catch(Exception e){
            holderData.Nilai.setVisibility(View.GONE);
        }
        holderData.cardEssayPG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Level.equals("siswa")){
                    if (dm.getFillable().equals("true")){
                        try {
                            if (dm.getScore_tugas().equals("0") && dm.getTerjawab().equals("0")){
                                if (dm.getJenis_tugas().equals("pg")){
                                    Intent i = new Intent(ctx, SoalPGActivity.class);
                                    i.putExtra("ID", dm.getId_tugas());
                                    i.putExtra("NAMA",dm.getIsi_tugas());
                                    ctx.startActivity(i);
                                }else if (dm.getJenis_tugas().equals("tugas")){

                                }else{
                                    Intent i = new Intent(ctx, SoalEssayActivity.class);
                                    i.putExtra("ID", dm.getId_tugas());
                                    i.putExtra("NAMA",dm.getIsi_tugas());
                                    ctx.startActivity(i);
                                }
                            }else{
                                Toast.makeText(ctx, "Nilai Sudah Dikirimkan ke Guru", Toast.LENGTH_SHORT).show();
                            }
                        }catch(Exception e){
                            if (dm.getJenis_tugas().equals("pg")){
                                Intent i = new Intent(ctx, SoalPGActivity.class);
                                i.putExtra("ID", dm.getId_tugas());
                                i.putExtra("NAMA",dm.getIsi_tugas());
                                ctx.startActivity(i);
                            }else if (dm.getJenis_tugas().equals("tugas")){

                            }else{
                                Intent i = new Intent(ctx, SoalEssayActivity.class);
                                i.putExtra("ID", dm.getId_tugas());
                                i.putExtra("NAMA",dm.getIsi_tugas());
                                ctx.startActivity(i);
                            }
                        }
                    }else{
                        Toast.makeText(ctx, R.string.periode_tugas, Toast.LENGTH_SHORT).show();
                    }
                }else{
                    try {
                        if (dm.getScore_tugas().equals("0") && dm.getTerjawab().equals("0")){
                            if (dm.getJenis_tugas().equals("pg")){
                                Intent i = new Intent(ctx, SoalPGActivity.class);
                                i.putExtra("ID", dm.getId_tugas());
                                i.putExtra("NAMA",dm.getIsi_tugas());
                                ctx.startActivity(i);
                            }else if (dm.getJenis_tugas().equals("tugas")){

                            }else{
                                Intent i = new Intent(ctx, SoalEssayActivity.class);
                                i.putExtra("ID", dm.getId_tugas());
                                i.putExtra("NAMA",dm.getIsi_tugas());
                                ctx.startActivity(i);
                            }
                        }else{
                            Toast.makeText(ctx, "Nilai Sudah Dikirimkan ke Guru", Toast.LENGTH_SHORT).show();
                        }
                    }catch(Exception e){
                        if (dm.getJenis_tugas().equals("pg")){
                            Intent i = new Intent(ctx, SoalPGActivity.class);
                            i.putExtra("ID", dm.getId_tugas());
                            i.putExtra("NAMA",dm.getIsi_tugas());
                            ctx.startActivity(i);
                        }else if (dm.getJenis_tugas().equals("tugas")){

                        }else{
                            Intent i = new Intent(ctx, SoalEssayActivity.class);
                            i.putExtra("ID", dm.getId_tugas());
                            i.putExtra("NAMA",dm.getIsi_tugas());
                            ctx.startActivity(i);
                        }
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HolderData extends RecyclerView.ViewHolder{ ;
        TextView NamaTugas,Tugas,Mulai,Selesai,Periode,Nama;
        WebView Deskripsi;
        ImageView ivTugas;
        Button Nilai,Nilai2,Tautan,Download,Upload;
        CardView cardTugas,cardEssayPG;
        public HolderData(View v){
            super(v);
            NamaTugas = v.findViewById(R.id.tvNamaTugas);
            Tugas = v.findViewById(R.id.tvTugas);
            Nilai = v.findViewById(R.id.btnNilai);
            Nilai2 = v.findViewById(R.id.btnNilai2);
            Mulai = v.findViewById(R.id.tvTglMulai);
            Selesai = v.findViewById(R.id.tvTanggalSelesai);
            ivTugas = v.findViewById(R.id.ivTugas);
            Periode = v.findViewById(R.id.tvPeriode);
            Deskripsi = v.findViewById(R.id.tvDeskripsi);
            Nama = v.findViewById(R.id.tvNama);
            Tautan = v.findViewById(R.id.btnTautan);
            Download = v.findViewById(R.id.btnDownload);
            Upload = v.findViewById(R.id.btnUpload);
            cardTugas = v.findViewById(R.id.cardTugas);
            cardEssayPG = v.findViewById(R.id.cardTugasEssayKuis);
        }
    }
}


