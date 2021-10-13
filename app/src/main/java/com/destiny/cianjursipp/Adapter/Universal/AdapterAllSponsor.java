package com.destiny.cianjursipp.Adapter.Universal;

import android.content.Context;
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
import com.destiny.cianjursipp.Activity.menu.Sponsor.DetailSponsorActivity;
import com.destiny.cianjursipp.Method.Destiny;
import com.destiny.cianjursipp.Model.DataModel;
import com.destiny.cianjursipp.R;
import com.destiny.cianjursipp.SharedPreferance.DB_Helper;

import java.util.List;

public class AdapterAllSponsor extends RecyclerView.Adapter<AdapterAllSponsor.HolderData> {
    private List<DataModel> mList;
    private Context ctx;

    DB_Helper dbHelper;
    Boolean onClick=false;
    RecyclerView recyclerView;
    Destiny destiny;
    public AdapterAllSponsor(Context ctx, List<DataModel> mList){
        this.ctx = ctx;
        this.mList = mList;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_sponsor_semua,viewGroup,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final HolderData holderData, int posistion) {
        destiny = new Destiny();
        final DataModel dm = mList.get(posistion);
        holderData.Judul.setText(dm.getJudul_sponsor());
        Glide.with(ctx)
                .load(destiny.BASE_URL()+dm.getFile_image_sponsor())
                .into(holderData.Image);
        holderData.Website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dm.getAlamat_web_sponsor().isEmpty() || dm.getAlamat_web_sponsor().equals("")){
                    Toast.makeText(ctx, "Website Belum Terisi", Toast.LENGTH_SHORT).show();
                }else{
                    try {
                        Uri uri = Uri.parse(dm.getAlamat_web_sponsor()); // missing 'http://' will cause crashed
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        ctx.startActivity(intent);
                    }catch (Exception e){
                        Toast.makeText(ctx, "Terjadi Kesalahan dengan websitenya", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        holderData.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ctx, DetailSponsorActivity.class);
                i.putExtra("ID", dm.getId_sponsor());
                i.putExtra("NAMA", dm.getJudul_sponsor());
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
        TextView Judul;
        Button Website;
        LinearLayout card;
        public HolderData(View v){
            super(v);
            Image = v.findViewById(R.id.ivGambar);
            Judul = v.findViewById(R.id.tvSponsor);
            Website = v.findViewById(R.id.btnWebsite);
            card = v.findViewById(R.id.LayoutCardView);
        }
    }
}

