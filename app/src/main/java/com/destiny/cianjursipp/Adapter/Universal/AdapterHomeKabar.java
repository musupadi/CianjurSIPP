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
import com.destiny.cianjursipp.Activity.menu.KabarSekolah.DetailKabarSekolahActivity;
import com.destiny.cianjursipp.Method.Destiny;
import com.destiny.cianjursipp.Model.DataModel;
import com.destiny.cianjursipp.R;
import com.destiny.cianjursipp.SharedPreferance.DB_Helper;

import java.util.List;

public class AdapterHomeKabar extends RecyclerView.Adapter<AdapterHomeKabar.HolderData> {
    private List<DataModel> mList;
    private Context ctx;

    DB_Helper dbHelper;
    Boolean onClick=false;
    RecyclerView recyclerView;
    Destiny destiny;
    public AdapterHomeKabar(Context ctx, List<DataModel> mList){
        this.ctx = ctx;
        this.mList = mList;
    }

    @NonNull
    @Override
    public AdapterHomeKabar.HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_kabar,viewGroup,false);
        AdapterHomeKabar.HolderData holder = new AdapterHomeKabar.HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterHomeKabar.HolderData holderData, int posistion) {
        destiny = new Destiny();
        final DataModel dm = mList.get(posistion);
        holderData.Judul.setText(dm.getJudul_kabar());
        holderData.Deskripsi.setText(destiny.SmallDescription(destiny.FilterTextToJava(dm.getIsi_kabar())));
        holderData.Tanggal.setText(destiny.MagicDateChange(dm.getCreated_at_kabar()));
        Glide.with(ctx)
                .load(destiny.CheckerImageYoutube(dm.getLink_youtube_kabar(),dm.getCover_kabar()))
                .into(holderData.Image);
        holderData.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ctx, DetailKabarSekolahActivity.class);
                i.putExtra("JUDUL", dm.getJudul_kabar());
                i.putExtra("ISI",dm.getIsi_kabar());
                i.putExtra("TANGGAL",dm.getCreated_at_kabar());
                i.putExtra("GAMBAR",destiny.BASE_URL()+dm.getCover_kabar());
                i.putExtra("YOUTUBE",dm.getLink_youtube_kabar());
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
