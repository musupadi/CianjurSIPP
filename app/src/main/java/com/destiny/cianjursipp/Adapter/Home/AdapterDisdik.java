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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.destiny.cianjursipp.Activity.menu.InfoDinas.DinasPendidikanActivity;
import com.destiny.cianjursipp.Method.Destiny;
import com.destiny.cianjursipp.Model.DataModel;
import com.destiny.cianjursipp.R;
import com.destiny.cianjursipp.SharedPreferance.DB_Helper;

import java.util.List;

public class AdapterDisdik extends RecyclerView.Adapter<AdapterDisdik.HolderData>  {
    private List<DataModel> mList;
    private Context ctx;

    DB_Helper dbHelper;
    Boolean onClick=false;
    RecyclerView recyclerView;
    Destiny destiny;
    String zyarga;
    public AdapterDisdik(Context ctx, List<DataModel> mList,String zyarga){
        this.ctx = ctx;
        this.mList = mList;
        this.zyarga = zyarga;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_disdik,viewGroup,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final HolderData holderData, int posistion) {
        holderData.card.setVisibility(View.GONE);
        destiny = new Destiny();
        final DataModel dm = mList.get(posistion);
        if (zyarga.equals("Surat PDF")){
            holderData.card2.setVisibility(View.GONE);
            if (dm.getTipe_info().equals("dokumen")){
                holderData.card.setVisibility(View.VISIBLE);
                holderData.Nama.setText(dm.getNama_info_dinas());
                holderData.card.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(destiny.BASE_URL()+dm.getFile_info_dinas()));
                        ctx.startActivity(browserIntent);
                    }
                });
            }
        }else{
            holderData.card.setVisibility(View.GONE);
            if (dm.getTipe_info().equals("artikel")){
                holderData.card2.setVisibility(View.VISIBLE);
                holderData.Judul.setText(dm.getNama_info_dinas());
                holderData.Deskripsi.setText(destiny.SmallDescription(destiny.FilterTextToJava(dm.getDeskripsi_info_dinas())));
                holderData.Tanggal.setText(destiny.MagicDateChange(dm.getTgl_upload_info()));
                Glide.with(ctx)
                        .load(destiny.BASE_URL()+dm.getFile_info_dinas())
                        .into(holderData.Image);
                holderData.card2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(ctx, DinasPendidikanActivity.class);
                        i.putExtra("JUDUL", dm.getNama_info_dinas());
                        i.putExtra("ISI",dm.getDeskripsi_info_dinas());
                        i.putExtra("TANGGAL",dm.getTgl_upload_info());
                        i.putExtra("GAMBAR",destiny.BASE_URL()+dm.getFile_info_dinas());
                        i.putExtra("YOUTUBE","");
                        ctx.startActivity(i);
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HolderData extends RecyclerView.ViewHolder{ ;
        TextView Nama;
        ImageView Image;
        TextView Judul,Deskripsi,Tanggal;
        LinearLayout card,card2;
        public HolderData(View v){
            super(v);
            Nama = v.findViewById(R.id.tvNama);
            card = v.findViewById(R.id.card_view);
            card2 = v.findViewById(R.id.card_view2);
            Image = v.findViewById(R.id.ivGambar);
            Judul = v.findViewById(R.id.tvJudul);
            Deskripsi = v.findViewById(R.id.tvDeskripsi);
            Tanggal = v.findViewById(R.id.tvTanggal);
        }
    }
}

