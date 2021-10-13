package com.destiny.cianjursipp.Adapter.Universal;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.destiny.cianjursipp.Activity.Marketplace.DetailProdukActivity;
import com.destiny.cianjursipp.Method.Destiny;
import com.destiny.cianjursipp.Model.Pena.Marketplace.ProdukModel;
import com.destiny.cianjursipp.Model.Produk;
import com.destiny.cianjursipp.R;
import com.destiny.cianjursipp.SharedPreferance.DB_Helper;

import java.util.List;

public class AdapterProduk extends RecyclerView.Adapter<AdapterProduk.HolderData> {
    private List<ProdukModel> mList;
    private Context ctx;

    DB_Helper dbHelper;
    Boolean onClick=false;
    RecyclerView recyclerView;
    Destiny destiny;
    public AdapterProduk(Context ctx, List<ProdukModel> mList){
        this.ctx = ctx;
        this.mList = mList;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_produk,viewGroup,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final HolderData holderData, int posistion) {
        destiny = new Destiny();
        final ProdukModel dm = mList.get(posistion);
        holderData.Sekolah.setText(dm.getNama_produk_siswa());
        holderData.Penjual.setText(dm.getNama_sekolah());
        holderData.Harga.setText("Rp "+dm.getHarga_produk_siswa());
        if (dm.getCover_produk_siswa().equals("")){
            holderData.Image.setImageResource(R.drawable.childern);
        }else{
            Glide.with(ctx)
                    .load(destiny.BASE_URL()+dm.getCover_produk_siswa())
                    .into(holderData.Image);
        }
//        if (dm.getLink_youtube_kabar().equals("") || dm.getLink_youtube_kabar().isEmpty()){
//
//        }else{
//            Glide.with(ctx)
//                    .load(destiny.BASE_URL()+dm.getCover_kabar())
//                    .into(holderData.Image);
//        }
//        Toast.makeText(ctx, destiny.CheckerImageYoutube(dm.getLink_youtube_kabar(),dm.getCover_kabar()), Toast.LENGTH_SHORT).show();
        holderData.Detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ctx, DetailProdukActivity.class);
                i.putExtra("NAMA", dm.getNama_produk_siswa());
                i.putExtra("SEKOLAH",dm.getNama_sekolah());
                i.putExtra("HARGA","Rp "+dm.getHarga_produk_siswa());
                i.putExtra("GAMBAR",destiny.BASE_URL()+dm.getCover_produk_siswa());
                i.putExtra("PENGURUS",dm.getNama_pengurus());
                i.putExtra("TELPON",dm.getNo_telp_pengurus());
                i.putExtra("DESKRIPSI",dm.getDeskripsi_produk_siswa());
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
        TextView Sekolah,Penjual,Harga;
        Button Detail;
        LinearLayout card;
        public HolderData(View v){
            super(v);
            Image = v.findViewById(R.id.ivLogo);
            Sekolah = v.findViewById(R.id.tvNamaSekolah);
            Penjual = v.findViewById(R.id.tvNamaPenjual);
            Harga = v.findViewById(R.id.tvHarga);
            Detail = v.findViewById(R.id.btnDetail);
            card = v.findViewById(R.id.LayoutCardView);
        }
    }
}
