package com.destiny.cianjursipp.Adapter.Universal;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.destiny.cianjursipp.Activity.Absensi.AbsensiKelasActivity;
import com.destiny.cianjursipp.Method.Destiny;
import com.destiny.cianjursipp.Model.DataModel;
import com.destiny.cianjursipp.R;

import java.util.List;

public class AdapterGuruMapel extends RecyclerView.Adapter<AdapterGuruMapel.HolderData>  {
    private List<DataModel> mList;
    private Context ctx;
    String ID,Piket;
    Destiny destiny;
    public AdapterGuruMapel(Context ctx, List<DataModel> mList,String ID,String Piket){
        this.ctx = ctx;
        this.mList = mList;
        this.ID = ID;
        this.Piket = Piket;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_absensi_kelas,viewGroup,false);
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
            public void onClick(View view) {
                Intent i = new Intent(ctx, AbsensiKelasActivity.class);
                i.putExtra("ID", ID);
                i.putExtra("ID_MAPEL",dm.getId_mapel());
                i.putExtra("ID_GURU",Piket);
                i.putExtra("PIKET",Piket);
                i.putExtra("NAMA",dm.getNama_mapel());
                ctx.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HolderData extends RecyclerView.ViewHolder{ ;
        TextView Nama;
        LinearLayout card;
        public HolderData(View v){
            super(v);
            Nama = v.findViewById(R.id.tvNama);
            card = v.findViewById(R.id.LayoutCardView);
        }
    }
}
