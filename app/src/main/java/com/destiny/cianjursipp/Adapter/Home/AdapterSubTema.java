package com.destiny.cianjursipp.Adapter.Home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.destiny.cianjursipp.Activity.menu.MediaPembelajaran.ListMediaPembelajaranActivity;
import com.destiny.cianjursipp.Method.Destiny;
import com.destiny.cianjursipp.Model.SubTema;
import com.destiny.cianjursipp.R;
import com.destiny.cianjursipp.SharedPreferance.DB_Helper;

import java.util.List;

public class AdapterSubTema extends RecyclerView.Adapter<AdapterSubTema.HolderData> {
    private List<SubTema> mList;
    private Context ctx;
    int Tema;
    DB_Helper dbHelper;
    Boolean onClick=false;
    RecyclerView recyclerView;
    Destiny destiny;

    //    private List<Media> mItems = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    public AdapterSubTema(Context ctx, List<SubTema> mList,int Tema){
        this.ctx = ctx;
        this.mList = mList;
        this.Tema = Tema;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_submodul,viewGroup,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final HolderData holderData, final int posistion) {
        destiny = new Destiny();
        final SubTema dm = mList.get(posistion);
        holderData.Nama.setText(dm.getNama_subtema());
        holderData.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dm.getMedia().size()>0){
                    Intent i = new Intent(ctx, ListMediaPembelajaranActivity.class);
                    i.putExtra("NAMA", dm.getNama_subtema());
                    i.putExtra("TEMA", String.valueOf(Tema));
                    i.putExtra("SUBTEMA", String.valueOf(posistion));
                    ctx.startActivity(i);
                }else{
                    Toast.makeText(ctx, "Sub Tema ini Tidak Memiliki Media", Toast.LENGTH_SHORT).show();
                }
            }
        });
//        holderData.Jabatan.setText(dm.getJabatan());
//        Glide.with(ctx)
//                .load(destiny.BASE_URL()+dm.getFile_foto_struktur())
//                .into(holderData.Image);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        TextView Nama;
        RecyclerView rv;
        LinearLayout card;
        public HolderData(View v){
            super(v);
            Nama = v.findViewById(R.id.tvNama);
            rv= v.findViewById(R.id.recycler);
            card = v.findViewById(R.id.card_view);
        }
    }
}


