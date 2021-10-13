package com.destiny.cianjursipp.Adapter.Home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.destiny.cianjursipp.Method.Destiny;
import com.destiny.cianjursipp.Model.DataModel;
import com.destiny.cianjursipp.Model.SubTema;
import com.destiny.cianjursipp.R;
import com.destiny.cianjursipp.SharedPreferance.DB_Helper;

import java.util.ArrayList;
import java.util.List;

public class AdapterTema extends RecyclerView.Adapter<AdapterTema.HolderData> {
    private List<DataModel> mList;
    private Context ctx;

    DB_Helper dbHelper;
    Boolean onClick=true;
    RecyclerView recyclerView;
    Destiny destiny;

    private List<SubTema> mItems = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    public AdapterTema(Context ctx, List<DataModel> mList){
        this.ctx = ctx;
        this.mList = mList;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_media_pembelajaran,viewGroup,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final HolderData holderData, int posistion) {
        destiny = new Destiny();
        final DataModel dm = mList.get(posistion);
        mManager = new GridLayoutManager(ctx,1);
        holderData.rv.setLayoutManager(mManager);
        holderData.Nama.setText(dm.getNama_tema());
        holderData.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dm.getSubTema().size()>0){
                    if (onClick){
                        onClick=false;
                        holderData.rv.setVisibility(View.VISIBLE);
                        holderData.img.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                    }else{
                        onClick=true;
                        holderData.rv.setVisibility(View.GONE);
                        holderData.img.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                    }
                }else{
                    Toast.makeText(ctx, "Tema ini Tidak Memiliki Sub Tema", Toast.LENGTH_SHORT).show();
                }
            }
        });
        if (dm.getSubTema().size()>0){
            mItems = dm.getSubTema();
            mAdapter = new AdapterSubTema(ctx,mItems,posistion);
            holderData.rv.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        }
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
        ImageView img;
        public HolderData(View v){
            super(v);
            Nama = v.findViewById(R.id.tvNama);
            rv= v.findViewById(R.id.recycler);
            img = v.findViewById(R.id.ivImage);
            card = v.findViewById(R.id.LayoutCardView);
        }
    }
}

