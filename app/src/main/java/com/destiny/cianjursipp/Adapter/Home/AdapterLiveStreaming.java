package com.destiny.cianjursipp.Adapter.Home;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.destiny.cianjursipp.Activity.menu.LiveStreaming.DetailLiveStreamingActivity;
import com.destiny.cianjursipp.Activity.menu.MediaPembelajaran.DetailMediaPembelajaranActivity;
import com.destiny.cianjursipp.Method.Destiny;
import com.destiny.cianjursipp.Model.DataModel;
import com.destiny.cianjursipp.R;
import com.destiny.cianjursipp.SharedPreferance.DB_Helper;

import java.util.List;

public class AdapterLiveStreaming extends RecyclerView.Adapter<AdapterLiveStreaming.HolderData> {
    private List<DataModel> mList;
    private Context ctx;

    DB_Helper dbHelper;
    Boolean onClick=false;
    RecyclerView recyclerView;
    Destiny destiny;
    public AdapterLiveStreaming(Context ctx, List<DataModel> mList){
        this.ctx = ctx;
        this.mList = mList;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_pembelajaran_media,viewGroup,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final HolderData holderData, int posistion) {
        destiny = new Destiny();
        final DataModel dm = mList.get(posistion);
        holderData.Judul.setText(dm.getJudul_media());
        holderData.Deskripsi.setText(destiny.SmallDescription(destiny.FilterTextToJava(dm.getIsi_media())));
        try {
            holderData.Tanggal.setText(destiny.MagicDateChange(dm.getCreated_at_media()));
        }catch (Exception e){
            holderData.Tanggal.setText(dm.getCreated_at_media());
        }

        if (!dm.getFile_media().isEmpty()){
            if (destiny.CheckerPDF(dm.getFile_media())){
                holderData.Image.setImageResource(R.drawable.pdf);
                holderData.PDF.setVisibility(View.VISIBLE);
            }else{
                holderData.PDF.setVisibility(View.GONE);
                Glide.with(ctx)
                        .load(destiny.CheckerImageYoutube(dm.getLink_youtube_media(),dm.getFile_media()))
                        .into(holderData.Image);
            }
        }else{
            holderData.PDF.setVisibility(View.GONE);
            Glide.with(ctx)
                    .load(destiny.CheckerImageYoutube(dm.getLink_youtube_media(),dm.getFile_media()))
                    .into(holderData.Image);
        }
        holderData.Lihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(destiny.BASE_URL()+dm.getFile_media()));
                    ctx.startActivity(browserIntent);
                }catch (Exception e){
                    Toast.makeText(ctx, "Perangkat anda Tidak Support untuk membuka File PDF", Toast.LENGTH_SHORT).show();
                }
            }
        });
        holderData.Download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
                    builder.setMessage("Download File ?")
                            .setCancelable(false)
                            .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    destiny.DownloadPDF(destiny.BASE_URL()+dm.getFile_media(),dm.getJudul_media(),ctx);
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
                }catch (Exception e){
                    Toast.makeText(ctx, "Link Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        holderData.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!dm.getFile_media().isEmpty()){
                    if (!destiny.CheckerPDF(dm.getFile_media())){
                        Intent i = new Intent(ctx, DetailLiveStreamingActivity.class);
                        i.putExtra("JUDUL", dm.getJudul_media());
                        i.putExtra("ISI",dm.getIsi_media());
                        i.putExtra("TANGGAL",dm.getCreated_at_media());
                        i.putExtra("GAMBAR",destiny.BASE_URL()+dm.getFile_media());
                        i.putExtra("YOUTUBE",dm.getLink_youtube_media());
                        ctx.startActivity(i);
                    }
                }else{
                    Intent i = new Intent(ctx, DetailLiveStreamingActivity.class);
                    i.putExtra("JUDUL", dm.getJudul_media());
                    i.putExtra("ISI",dm.getIsi_media());
                    i.putExtra("TANGGAL",dm.getCreated_at_media());
                    i.putExtra("GAMBAR",destiny.BASE_URL()+dm.getFile_media());
                    i.putExtra("YOUTUBE",dm.getLink_youtube_media());
                    ctx.startActivity(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        ImageView Image;
        TextView Judul,Deskripsi,Tanggal;
        LinearLayout card,PDF;
        Button Lihat,Download;
        public HolderData(View v){
            super(v);
            Image = v.findViewById(R.id.ivGambar);
            Judul = v.findViewById(R.id.tvJudul);
            Deskripsi = v.findViewById(R.id.tvDeskripsi);
            Tanggal = v.findViewById(R.id.tvTanggal);
            PDF = v.findViewById(R.id.linearPDF);
            Lihat = v.findViewById(R.id.btnLihat);
            Download = v.findViewById(R.id.btnDownload);
            card = v.findViewById(R.id.LayoutCardView);
        }
    }
}