package com.destiny.cianjursipp.Adapter.Universal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.destiny.cianjursipp.Method.Destiny;
import com.destiny.cianjursipp.Model.DataModel;
import com.destiny.cianjursipp.R;
import com.destiny.cianjursipp.SharedPreferance.DB_Helper;

import java.util.List;

public class AdapterJadwalPelajaran extends RecyclerView.Adapter<AdapterJadwalPelajaran.HolderData>  {
    private List<DataModel> mList;
    private Context ctx;

    DB_Helper dbHelper;
    Boolean onClick=false;
    RecyclerView recyclerView;
    Destiny destiny;
    public AdapterJadwalPelajaran(Context ctx, List<DataModel> mList){
        this.ctx = ctx;
        this.mList = mList;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_jadwal_pelajaran,viewGroup,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final HolderData holderData, int posistion) {
        destiny = new Destiny();
        final DataModel dm = mList.get(posistion);
        holderData.Dari.setText(dm.getJam_mulai());
        holderData.Sampai.setText(dm.getJam_selesai());
        holderData.Mapel.setText(dm.getNama_mapel());
        holderData.Guru.setText(dm.getNama_guru());
//        holderData.card.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(ctx, DetailKabarSekolahActivity.class);
//                i.putExtra("JUDUL", dm.getJudul_kabar());
//                i.putExtra("ISI",dm.getIsi_kabar());
//                i.putExtra("TANGGAL",dm.getCreated_at_kabar());
//                i.putExtra("GAMBAR",destiny.BASE_URL()+dm.getCover_kabar());
//                ctx.startActivity(i);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HolderData extends RecyclerView.ViewHolder{ ;
        TextView Dari,Sampai,Mapel,Guru;
        LinearLayout card;
        public HolderData(View v){
            super(v);
            Dari = v.findViewById(R.id.tvDari);
            Sampai = v.findViewById(R.id.tvSampai);
            Mapel = v.findViewById(R.id.tvNamaMapel);
            Guru = v.findViewById(R.id.tvGuru);
            card = v.findViewById(R.id.LayoutCardView);
        }
    }
}

