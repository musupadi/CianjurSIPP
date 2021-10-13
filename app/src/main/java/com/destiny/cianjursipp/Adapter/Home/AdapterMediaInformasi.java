package com.destiny.cianjursipp.Adapter.Home;

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
import com.destiny.cianjursipp.Activity.menu.MediaInformasi.EducationNews.DetailEducationNewsActivity;
import com.destiny.cianjursipp.Activity.menu.MediaInformasi.EducationPodcast.DetailEducationPodcastActivity;
import com.destiny.cianjursipp.Activity.menu.MediaInformasi.EducationTalkshow.DetailEducationTalkshowActivity;
import com.destiny.cianjursipp.Activity.menu.MediaInformasi.Giveaway.DetailGiveawayActivity;
import com.destiny.cianjursipp.Method.Destiny;
import com.destiny.cianjursipp.Model.DataModel;
import com.destiny.cianjursipp.R;
import com.destiny.cianjursipp.SharedPreferance.DB_Helper;

import java.util.List;

public class AdapterMediaInformasi extends RecyclerView.Adapter<AdapterMediaInformasi.HolderData> {
    private List<DataModel> mList;
    private Context ctx;

    DB_Helper dbHelper;
    Boolean onClick=false;
    RecyclerView recyclerView;
    Destiny destiny;
    public AdapterMediaInformasi(Context ctx, List<DataModel> mList){
        this.ctx = ctx;
        this.mList = mList;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_kabar_home,viewGroup,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final HolderData holderData, int posistion) {
        destiny = new Destiny();
        final DataModel dm = mList.get(posistion);
        try{
            holderData.Judul.setText(dm.getJudul_media_informasi());
            holderData.Deskripsi.setText(destiny.SmallDescription(destiny.FilterTextToJava(dm.getDeskripsi_media_informasi())));
            holderData.Tanggal.setText(destiny.MagicDateChange(dm.getCreated_at_media_informasi()));
            Glide.with(ctx)
                    .load(destiny.CheckerImageYoutube(dm.getLink_youtube_media_informasi(),dm.getCover_media_informasi()))
                    .into(holderData.Image);
            holderData.card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (dm.getTipe_media_informasi().equals("education_talkshow")){
                        Intent i = new Intent(ctx, DetailEducationTalkshowActivity.class);
                        i.putExtra("JUDUL", dm.getJudul_media_informasi());
                        i.putExtra("ISI",dm.getDeskripsi_media_informasi());
                        i.putExtra("TANGGAL",dm.getCreated_at_media_informasi());
                        i.putExtra("GAMBAR",destiny.BASE_URL()+dm.getCover_media_informasi());
                        i.putExtra("YOUTUBE",dm.getLink_youtube_media_informasi());
                        ctx.startActivity(i);
                    }else if(dm.getTipe_media_informasi().equals("education_podcast")){
                        Intent i = new Intent(ctx, DetailEducationPodcastActivity.class);
                        i.putExtra("JUDUL", dm.getJudul_media_informasi());
                        i.putExtra("ISI",dm.getDeskripsi_media_informasi());
                        i.putExtra("TANGGAL",dm.getCreated_at_media_informasi());
                        i.putExtra("GAMBAR",destiny.BASE_URL()+dm.getCover_media_informasi());
                        i.putExtra("YOUTUBE",dm.getLink_youtube_media_informasi());
                        ctx.startActivity(i);
                    }else if(dm.getTipe_media_informasi().equals("education_news")){
                        Intent i = new Intent(ctx, DetailEducationNewsActivity.class);
                        i.putExtra("JUDUL", dm.getJudul_media_informasi());
                        i.putExtra("ISI",dm.getDeskripsi_media_informasi());
                        i.putExtra("TANGGAL",dm.getCreated_at_media_informasi());
                        i.putExtra("GAMBAR",destiny.BASE_URL()+dm.getCover_media_informasi());
                        i.putExtra("YOUTUBE",dm.getLink_youtube_media_informasi());
                        ctx.startActivity(i);
                    }else if(dm.getTipe_media_informasi().equals("giveaway")){
                        Intent i = new Intent(ctx, DetailGiveawayActivity.class);
                        i.putExtra("JUDUL", dm.getJudul_media_informasi());
                        i.putExtra("ISI",dm.getDeskripsi_media_informasi());
                        i.putExtra("TANGGAL",dm.getCreated_at_media_informasi());
                        i.putExtra("GAMBAR",destiny.BASE_URL()+dm.getCover_media_informasi());
                        i.putExtra("YOUTUBE",dm.getLink_youtube_media_informasi());
                        ctx.startActivity(i);
                    }
                }
            });
        }catch (Exception e){

        }

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
