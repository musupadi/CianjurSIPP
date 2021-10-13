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
import com.destiny.cianjursipp.Activity.menu.Eskul.DetailEskulActivity;
import com.destiny.cianjursipp.Method.Destiny;
import com.destiny.cianjursipp.Model.Eskul.Eskul;
import com.destiny.cianjursipp.R;
import com.destiny.cianjursipp.SharedPreferance.DB_Helper;

import java.util.List;

public class AdapterEskul extends RecyclerView.Adapter<AdapterEskul.HolderData> {
    private List<Eskul> mList;
    private Context ctx;

    DB_Helper dbHelper;
    Boolean onClick=false;
    RecyclerView recyclerView;
    Destiny destiny;
    String Eskul;
    DB_Helper db_helper;
    public AdapterEskul(Context ctx, List<Eskul> mList){
        this.ctx = ctx;
        this.mList = mList;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_eskul,viewGroup,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final HolderData holderData, int posistion) {
        destiny = new Destiny();
        db_helper = new DB_Helper(ctx);
        final Eskul dm = mList.get(posistion);
        holderData.Judul.setText(dm.getNama_ekskul());
        Glide.with(ctx)
                .load(destiny.BASE_URL()+dm.getCover_ekskul())
                .into(holderData.Image);

        holderData.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ctx, DetailEskulActivity.class);
                db_helper.SaveEskul(String.valueOf(posistion),dm.getId_ekskul());
                i.putExtra("ESKUL", dm.getNama_ekskul());
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
        LinearLayout card;
        public HolderData(View v){
            super(v);
            Image = v.findViewById(R.id.ivGambar);
            Judul = v.findViewById(R.id.tvNamaEskul);
            card = v.findViewById(R.id.LayoutCardView);
        }
    }
}

