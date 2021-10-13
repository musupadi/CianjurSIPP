package com.destiny.cianjursipp.Adapter.Home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.destiny.cianjursipp.Method.Destiny;
import com.destiny.cianjursipp.Model.DataModel;
import com.destiny.cianjursipp.R;
import com.destiny.cianjursipp.SharedPreferance.DB_Helper;

import java.util.List;

public class AdapterTeman extends RecyclerView.Adapter<AdapterTeman.HolderData> {
    private List<DataModel> mList;
    private Context ctx;

    Destiny destiny;
    public AdapterTeman (Context ctx, List<DataModel> mList){
        this.ctx = ctx;
        this.mList = mList;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_guru,viewGroup,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final HolderData holderData, int posistion) {
        destiny = new Destiny();
        final DataModel dm = mList.get(posistion);
        holderData.nip.setText(dm.getNama_kelas());
        holderData.nama.setText(dm.getNama_siswa());
        if (!dm.getFoto().equals("") || !dm.getFoto().isEmpty()){
            Glide.with(ctx)
                    .load(destiny.BASE_URL()+dm.getFoto())
                    .into(holderData.Image);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        ImageView Image;
        TextView nip,nama;
        public HolderData(View v){
            super(v);
            Image = v.findViewById(R.id.ivGambar);
            nip = v.findViewById(R.id.tvNIP);
            nama = v.findViewById(R.id.tvNama);
        }
    }
}







