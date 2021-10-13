package com.destiny.cianjursipp.Adapter.Home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.destiny.cianjursipp.Activity.menu.MediaPembelajaran.MediaPembelajaranActivity;
import com.destiny.cianjursipp.Activity.menu.SumberPustaka.LembagaSumberPustakaActivity;
import com.destiny.cianjursipp.Activity.menu.SumberPustaka.NewSumberPustaka1Activity;
import com.destiny.cianjursipp.Activity.menu.SumberPustaka.NewSumberPustaka2Activity;
import com.destiny.cianjursipp.Activity.menu.Tugas.TugasActivity;
import com.destiny.cianjursipp.Activity.menu.Ujian.DetailUjianActivity;
import com.destiny.cianjursipp.Method.Destiny;
import com.destiny.cianjursipp.Model.DataModel;
import com.destiny.cianjursipp.R;
import com.destiny.cianjursipp.SharedPreferance.DB_Helper;

import java.util.List;

public class AdapterSumberPustakaLembaga extends RecyclerView.Adapter<AdapterSumberPustakaLembaga.HolderData> {
    private List<DataModel> mList;
    private Context ctx;

    DB_Helper dbHelper;
    Boolean onClick=false;
    RecyclerView recyclerView;
    Destiny destiny;
    String ONCLICK;
    public AdapterSumberPustakaLembaga(Context ctx, List<DataModel> mList, String ONCLICK){
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
        holderData.Nama.setText(dm.getLembaga_sekolah());
        holderData.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ctx, LembagaSumberPustakaActivity.class);
                i.putExtra("ID_LEMBAGA", dm.getLembaga_sekolah());
                ctx.startActivity(i);
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
