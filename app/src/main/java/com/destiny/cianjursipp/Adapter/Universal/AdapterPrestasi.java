package com.destiny.cianjursipp.Adapter.Universal;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.destiny.cianjursipp.Activity.menu.MediaPembelajaran.DetailMediaPembelajaranActivity;
import com.destiny.cianjursipp.Activity.menu.Prestasi.DetailPrestasiActivity;
import com.destiny.cianjursipp.Method.Destiny;
import com.destiny.cianjursipp.Model.DataModel;
import com.destiny.cianjursipp.R;
import com.destiny.cianjursipp.SharedPreferance.DB_Helper;

import java.util.List;

public class AdapterPrestasi extends RecyclerView.Adapter<AdapterPrestasi.HolderData> {
    private List<DataModel> mList;
    private Context ctx;

    Destiny destiny;
    public AdapterPrestasi(Context ctx, List<DataModel> mList){
        this.ctx = ctx;
        this.mList = mList;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_prestasi,viewGroup,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final HolderData holderData, int posistion) {
        destiny = new Destiny();
        final DataModel dm = mList.get(posistion);
        holderData.Judul.setText(dm.getJudul_prestasi());
        holderData.Deskripsi.setText(destiny.SmallDescription(dm.getDeskripsi_prestasi()));
        holderData.Tanggal.setText(dm.getTgl_prestasi());
        Glide.with(ctx)
                .load(destiny.BASE_URL()+dm.getFoto_prestasi())
                .into(holderData.Image);
        holderData.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ctx, DetailPrestasiActivity.class);
                i.putExtra("JUDUL", dm.getJudul_prestasi());
                i.putExtra("ISI",dm.getDeskripsi_prestasi());
                i.putExtra("TANGGAL",dm.getCreated_at_prestasi());
                i.putExtra("GAMBAR",destiny.BASE_URL()+dm.getFoto_prestasi());
                i.putExtra("YOUTUBE","");
                ctx.startActivity(i);
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
        LinearLayout card;
        public HolderData(View v){
            super(v);
            Image = v.findViewById(R.id.ivGambar);
            Judul = v.findViewById(R.id.tvJudul);
            Deskripsi = v.findViewById(R.id.tvDeskripsi);
            Tanggal = v.findViewById(R.id.tvTanggal);
            card = v.findViewById(R.id.LayoutCardView);
        }
    }
}

