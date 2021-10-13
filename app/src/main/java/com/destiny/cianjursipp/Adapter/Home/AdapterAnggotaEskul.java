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
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.destiny.cianjursipp.Method.Destiny;
import com.destiny.cianjursipp.Model.DataModel;
import com.destiny.cianjursipp.Model.Eskul.Anggota;
import com.destiny.cianjursipp.R;
import com.destiny.cianjursipp.SharedPreferance.DB_Helper;

import java.util.List;

public class AdapterAnggotaEskul extends RecyclerView.Adapter<AdapterAnggotaEskul.HolderData> {
    private List<DataModel> mList;
    private Context ctx;

    DB_Helper dbHelper;
    Boolean onClick=false;
    RecyclerView recyclerView;
    Destiny destiny;
    public AdapterAnggotaEskul (Context ctx, List<DataModel> mList){
        this.ctx = ctx;
        this.mList = mList;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_anggota_eskul,viewGroup,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final HolderData holderData, int posistion) {
        destiny = new Destiny();
        final DataModel dm = mList.get(posistion);
        if (mList.size() > 0){
            holderData.No.setText(String.valueOf(posistion+1));
            holderData.Nama.setText(dm.getNama_siswa());
            holderData.Kelas.setText(dm.getNama_kelas());
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        TextView No, Nama, Kelas;
        LinearLayout card;

        public HolderData(View v) {
            super(v);
            No = v.findViewById(R.id.tvNoEskul);
            Nama = v.findViewById(R.id.tvNamaAnggota);
            Kelas = v.findViewById(R.id.tvNamaKelas);
            card = v.findViewById(R.id.LayoutCardView);
        }
    }
}







