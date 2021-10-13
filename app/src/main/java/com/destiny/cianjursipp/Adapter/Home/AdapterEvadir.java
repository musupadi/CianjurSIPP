package com.destiny.cianjursipp.Adapter.Home;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.destiny.cianjursipp.Method.Destiny;
import com.destiny.cianjursipp.Model.Data;
import com.destiny.cianjursipp.Model.DataModel;
import com.destiny.cianjursipp.R;
import com.destiny.cianjursipp.SharedPreferance.DB_Helper;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class AdapterEvadir extends RecyclerView.Adapter<AdapterEvadir.HolderData>  {
    private List<DataModel> mList;
    private List<Data> Evadir;
    private Context ctx;
    Destiny destiny;
    DB_Helper dbHelper;
    String Username,Password,Nama,Token,Level,Photo;
    Boolean onClick=false;
    RecyclerView recyclerView;
    Button Nilai;
    private List<DataModel> mItems = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    String skor = "1";
    JSONArray jsonArray;
    ArrayList<String> ID_EVADIR = new ArrayList<String>();
    ArrayList<String> SKOR = new ArrayList<String>();
    ArrayList<String> ID_KATEGORI = new ArrayList<String>();
    public AdapterEvadir(Context ctx, List<DataModel> mList, List<Data> Evadir, JSONArray jsonArray,ArrayList<String> ID_EVADIR,ArrayList<String> ID_KATEGORI,ArrayList<String> SKOR){
        this.ctx = ctx;
        this.mList = mList;
        this.Evadir = Evadir;
        this.jsonArray = jsonArray;
        this.ID_EVADIR = ID_EVADIR;
        this.ID_KATEGORI = ID_KATEGORI;
        this.SKOR = SKOR;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_evadir,viewGroup,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final HolderData holderData, final int posistion) {
        destiny = new Destiny();
        final DataModel dm = mList.get(posistion);
        holderData.Nama.setText(dm.getNama_evadir());
        holderData.star1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                skor = "1";
                StarChecker(holderData.star1,holderData.star2,holderData.star3,holderData.star4,holderData.star5,skor);
            }
        });
        holderData.star2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                skor = "2";
                StarChecker(holderData.star1,holderData.star2,holderData.star3,holderData.star4,holderData.star5,skor);
            }
        });
        holderData.star3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                skor = "3";
                StarChecker(holderData.star1,holderData.star2,holderData.star3,holderData.star4,holderData.star5,skor);
            }
        });
        holderData.star4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                skor = "4";
                StarChecker(holderData.star1,holderData.star2,holderData.star3,holderData.star4,holderData.star5,skor);
            }
        });
        holderData.star5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                skor = "5";
                StarChecker(holderData.star1,holderData.star2,holderData.star3,holderData.star4,holderData.star5,skor);
            }
        });
        if (dm.getAnswer().size()<1){
            StarChecker(holderData.star1,holderData.star2,holderData.star3,holderData.star4,holderData.star5,"0");
        }else{
            StarChecker(holderData.star1,holderData.star2,holderData.star3,holderData.star4,holderData.star5,dm.getAnswer().get(0).getScore());
        }

        holderData.Nilai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Evadir.add(new Data(ids,Skor,id_kategori));
                ID_EVADIR.add(dm.getId_evadir());

                SKOR.add(skor);
                ID_KATEGORI.add(dm.getId_kategori());
                holderData.card.setAlpha(0.5f);
                holderData.star1.setEnabled(false);
                holderData.star2.setEnabled(false);
                holderData.star3.setEnabled(false);
                holderData.star4.setEnabled(false);
                holderData.star5.setEnabled(false);
//                Ensure(dm.getNama_evadir(),skor,dm.getId_evadir(),dm.getId_kategori(),holderData.card);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HolderData extends RecyclerView.ViewHolder{ ;
        TextView Nama;
        ImageView star1,star2,star3,star4,star5;
        CardView card;
        Button Nilai;
        public HolderData(View v){
            super(v);
            Nama = v.findViewById(R.id.tvNama);
            Nilai = v.findViewById(R.id.btnNilai);
            star1 = v.findViewById(R.id.ivStar1);
            star2 = v.findViewById(R.id.ivStar2);
            star3 = v.findViewById(R.id.ivStar3);
            star4 = v.findViewById(R.id.ivStar4);
            star5 = v.findViewById(R.id.ivStar5);
            card = v.findViewById(R.id.card_view);
        }
    }
    private void Ensure(String Nama, final String Skor, final String ids, final String id_kategori, final CardView card){
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);

        // Set a title for alert dialog
        builder.setTitle("Pemberitahuan");

        // Ask the final question
        builder.setMessage("Apakah anda Ingin memberikan Nilai pada Evaluasi "+Nama+" Dengan Skor : "+Skor);

        // Set the alert dialog yes button click listener
        builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do something when user clicked the Yes button
                // Set the TextView visibility GONE

            }
        });

        // Set the alert dialog no button click listener
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do something when No button clicked
            }
        });

        AlertDialog dialog = builder.create();
        // Display the alert dialog on interface
        dialog.show();
    }
    private void  StarChecker(ImageView star1,ImageView star2,ImageView star3,ImageView star4,ImageView star5,String Checker){
        if (Checker.equals("1")){
            star1.setImageResource(R.drawable.star);
            star2.setImageResource(R.drawable.kosong_star);
            star3.setImageResource(R.drawable.kosong_star);
            star4.setImageResource(R.drawable.kosong_star);
            star5.setImageResource(R.drawable.kosong_star);
        }else if (Checker.equals("2")){
            star1.setImageResource(R.drawable.star);
            star2.setImageResource(R.drawable.star);
            star3.setImageResource(R.drawable.kosong_star);
            star4.setImageResource(R.drawable.kosong_star);
            star5.setImageResource(R.drawable.kosong_star);
        }else if(Checker.equals("3")){
            star1.setImageResource(R.drawable.star);
            star2.setImageResource(R.drawable.star);
            star3.setImageResource(R.drawable.star);
            star4.setImageResource(R.drawable.kosong_star);
            star5.setImageResource(R.drawable.kosong_star);
        }else if (Checker.equals("4")){
            star1.setImageResource(R.drawable.star);
            star2.setImageResource(R.drawable.star);
            star3.setImageResource(R.drawable.star);
            star4.setImageResource(R.drawable.star);
            star5.setImageResource(R.drawable.kosong_star);
        }else if (Checker.equals("5")){
            star1.setImageResource(R.drawable.star);
            star2.setImageResource(R.drawable.star);
            star3.setImageResource(R.drawable.star);
            star4.setImageResource(R.drawable.star);
            star5.setImageResource(R.drawable.star);
        }else{
            star1.setImageResource(R.drawable.kosong_star);
            star2.setImageResource(R.drawable.kosong_star);
            star3.setImageResource(R.drawable.kosong_star);
            star4.setImageResource(R.drawable.kosong_star);
            star5.setImageResource(R.drawable.kosong_star);
        }
    }
}

