package com.destiny.cianjursipp.Adapter.Home;

import android.content.ActivityNotFoundException;
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
import com.destiny.cianjursipp.Method.Destiny;
import com.destiny.cianjursipp.Model.DataModel;
import com.destiny.cianjursipp.R;
import com.destiny.cianjursipp.SharedPreferance.DB_Helper;

import java.util.List;

public class AdapterLoker extends RecyclerView.Adapter<AdapterLoker.HolderData> {
    private List<DataModel> mList;
    private Context ctx;

    DB_Helper dbHelper;
    Boolean onClick=false;
    RecyclerView recyclerView;
    Destiny destiny;
    public AdapterLoker(Context ctx, List<DataModel> mList){
        this.ctx = ctx;
        this.mList = mList;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_loker,viewGroup,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final HolderData holderData, int posistion) {
        destiny = new Destiny();
        final DataModel dm = mList.get(posistion);
        holderData.Nama.setText(dm.getNama_perusahaan());
        holderData.Jurusan.setText(dm.getNama_loker());
        holderData.Tanggal.setText(dm.getCreated_at_loker());
        holderData.Deskripsi.setText(destiny.SmallDescription(dm.getDeskripsi_loker()));
        holderData.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(dm.getLink_loker()));
                    ctx.startActivity(myIntent);
                } catch (Exception e) {
                    Toast.makeText(ctx, "Link Loker tidak Valid", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.drawable.kontol_imam);
        Glide.with(ctx)
                .load(destiny.BASE_URL()+dm.getCover_loker())
                .apply(options)
                .into(holderData.Image);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        ImageView Image;
        TextView Tanggal,Nama,Jurusan,Deskripsi;
        LinearLayout card;
        public HolderData(View v){
            super(v);
            Image = v.findViewById(R.id.ivGambar);
            Tanggal = v.findViewById(R.id.tvTanggal);
            Nama = v.findViewById(R.id.tvNama);
            Jurusan = v.findViewById(R.id.tvJurusan);
            Deskripsi = v.findViewById(R.id.tvDeskripsiSingkat);
            card = v.findViewById(R.id.LayoutCardView);
        }
    }
}
