package com.destiny.cianjursipp.Adapter.Home;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.destiny.cianjursipp.Activity.menu.MediaInformasi.EducationTalkshow.DetailEducationTalkshowActivity;
import com.destiny.cianjursipp.Activity.menu.MediaPembelajaran.MediaPembelajaranActivity;
import com.destiny.cianjursipp.Activity.menu.Tugas.TugasActivity;
import com.destiny.cianjursipp.Method.Destiny;
import com.destiny.cianjursipp.Model.DataModel;
import com.destiny.cianjursipp.R;
import com.destiny.cianjursipp.SharedPreferance.DB_Helper;

import java.util.List;

public class AdapterMapel extends RecyclerView.Adapter<AdapterMapel.HolderData> {
    private List<DataModel> mList;
    private Context ctx;

    DB_Helper dbHelper;
    Boolean onClick=false;
    RecyclerView recyclerView;
    Destiny destiny;
    String ONCLICK;
    public AdapterMapel(Context ctx, List<DataModel> mList,String ONCLICK){
        this.ctx = ctx;
        this.mList = mList;
        this.ONCLICK = ONCLICK;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_mapel,viewGroup,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final HolderData holderData, int posistion) {
        destiny = new Destiny();
        final DataModel dm = mList.get(posistion);
        holderData.Nama.setText(dm.getNama_mapel());
        holderData.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ONCLICK.equals("TUGAS")){
                    Intent i = new Intent(ctx, TugasActivity.class);
                    i.putExtra("ID", dm.getId_mapel());
                    i.putExtra("NAMA",dm.getNama_mapel());
                    ctx.startActivity(i);
                }else if (ONCLICK.equals("MEDIA")){
                    Intent i = new Intent(ctx, MediaPembelajaranActivity.class);
                    i.putExtra("ID", dm.getId_mapel());
                    i.putExtra("NAMA",dm.getNama_mapel());
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
        TextView Nama;
        LinearLayout card;
        public HolderData(View v){
            super(v);
            Nama = v.findViewById(R.id.tvNama);
            card = v.findViewById(R.id.LayoutCardView);
        }
    }
}
