package com.destiny.cianjursipp.Adapter.Home;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.destiny.cianjursipp.Method.Destiny;
import com.destiny.cianjursipp.R;
import com.destiny.cianjursipp.SharedPreferance.DB_Helper;

import java.util.ArrayList;

public class AdapterSoalPG extends RecyclerView.Adapter<AdapterSoalPG.HolderData> {
    private Context ctx;
    Destiny destiny;
    int Soal;
    ArrayList<String> JAWABAN = new ArrayList<String>();
    DB_Helper dbHelper;
    String Nomor,Jawaban;
    public AdapterSoalPG(Context ctx,int Soal,ArrayList<String> JAWABAN){
        this.ctx = ctx;
        this.Soal = Soal;
        this.JAWABAN = JAWABAN;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_soal_pg,viewGroup,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final HolderData holderData, final int posistion) {
        destiny = new Destiny();
        int POSITION = posistion+1;
        dbHelper = new DB_Helper(ctx);
        Cursor cursor = dbHelper.checkTugas(String.valueOf(POSITION));
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                Nomor = cursor.getString(0);
                Jawaban = cursor.getString(1);
            }
        }
        holderData.Nomor.setText("Soal Nomor : "+POSITION);
        try {
            if (Jawaban!=null){
                JAWABAN.add(Jawaban);
                if (JAWABAN.size()>Soal){
                    JAWABAN.remove(posistion);
                }
                if (JAWABAN.get(posistion).equals("")){
                    DEFAULT(holderData.A,holderData.B,holderData.C,holderData.D,holderData.E);
                }
                if (Jawaban.equals("a")){
                    DEFAULT(holderData.A,holderData.B,holderData.C,holderData.D,holderData.E);
                    Clicked(holderData.A,"a",posistion);
//                    try {
//                        dbHelper.SaveJawaban(String.valueOf(POSITION),"a");
//                    }catch (Exception e){
//                        dbHelper.UpdateJawaban(String.valueOf(POSITION),"a");
//                    }
                }else if (Jawaban.equals("b")){
                    DEFAULT(holderData.A,holderData.B,holderData.C,holderData.D,holderData.E);
                    Clicked(holderData.B,"b",posistion);
//                    try {
//                        dbHelper.SaveJawaban(String.valueOf(POSITION),"b");
//                    }catch (Exception e){
//                        dbHelper.UpdateJawaban(String.valueOf(POSITION),"b");
//                    }
                }else if (Jawaban.equals("c")){
                    DEFAULT(holderData.A,holderData.B,holderData.C,holderData.D,holderData.E);
                    Clicked(holderData.C,"c",posistion);
//                    try {
//                        dbHelper.SaveJawaban(String.valueOf(POSITION),"c");
//                    }catch (Exception e){
//                        dbHelper.UpdateJawaban(String.valueOf(POSITION),"c");
//                    }
                }else if (Jawaban.equals("d")){
                    DEFAULT(holderData.A,holderData.B,holderData.C,holderData.D,holderData.E);
                    Clicked(holderData.D,"d",posistion);
//                    try {
//                        dbHelper.SaveJawaban(String.valueOf(POSITION),"d");
//                    }catch (Exception e){
//                        dbHelper.UpdateJawaban(String.valueOf(POSITION),"d");
//                    }
                }else if (Jawaban.equals("e")){
                    DEFAULT(holderData.A,holderData.B,holderData.C,holderData.D,holderData.E);
                    Clicked(holderData.E,"e",posistion);
//                    try {
//                        dbHelper.SaveJawaban(String.valueOf(POSITION),"e");
//                    }catch (Exception e){
//                        dbHelper.UpdateJawaban(String.valueOf(POSITION),"e");
//                    }
                }
            }else{
                JAWABAN.add("");
                if (JAWABAN.size()>Soal){
                    JAWABAN.remove(posistion);
                }
                if (JAWABAN.get(posistion).equals("")){
                    DEFAULT(holderData.A,holderData.B,holderData.C,holderData.D,holderData.E);
                }
                holderData.A.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DEFAULT(holderData.A,holderData.B,holderData.C,holderData.D,holderData.E);
                        Clicked(holderData.A,"a",posistion);
//                        dbHelper.SaveJawaban(String.valueOf(POSITION),"a");
                    }
                });
                holderData.B.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DEFAULT(holderData.A,holderData.B,holderData.C,holderData.D,holderData.E);
                        Clicked(holderData.B,"b",posistion);
//                        dbHelper.SaveJawaban(String.valueOf(POSITION),"b");
                    }
                });
                holderData.C.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DEFAULT(holderData.A,holderData.B,holderData.C,holderData.D,holderData.E);
                        Clicked(holderData.C,"c",posistion);
//                        dbHelper.SaveJawaban(String.valueOf(POSITION),"c");
                    }
                });
                holderData.D.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DEFAULT(holderData.A,holderData.B,holderData.C,holderData.D,holderData.E);
                        Clicked(holderData.D,"d",posistion);
//                        dbHelper.SaveJawaban(String.valueOf(POSITION),"d");
                    }
                });
                holderData.E.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DEFAULT(holderData.A,holderData.B,holderData.C,holderData.D,holderData.E);
                        Clicked(holderData.E,"e",posistion);
//                        dbHelper.SaveJawaban(String.valueOf(POSITION),"e");
                    }
                });
            }
        }catch (Exception e){
            JAWABAN.add("");
        }
    }

    @Override
    public int getItemCount() {
        return Soal;
    }

    class HolderData extends RecyclerView.ViewHolder{
        LinearLayout LA,LB,LC,LD,LE;
        Button A,B,C,D,E;
        TextView Nomor;
        public HolderData(View v){
            super(v);
            LA = v.findViewById(R.id.linearA);
            LB = v.findViewById(R.id.linearB);
            LC = v.findViewById(R.id.linearC);
            LD = v.findViewById(R.id.linearD);
            LE = v.findViewById(R.id.linearE);
            A = v.findViewById(R.id.btnA);
            B = v.findViewById(R.id.btnB);
            C = v.findViewById(R.id.btnC);
            D = v.findViewById(R.id.btnD);
            E = v.findViewById(R.id.btnE);
            Nomor = v.findViewById(R.id.tvNomorJawaban);
        }
    }
    public void DEFAULT(Button a,Button b,Button c,Button d,Button e){
        a.setBackgroundResource(R.drawable.round_background);
        b.setBackgroundResource(R.drawable.round_background);
        c.setBackgroundResource(R.drawable.round_background);
        d.setBackgroundResource(R.drawable.round_background);
        e.setBackgroundResource(R.drawable.round_background);
        a.setTextColor(Color.rgb(1,85,142));
        b.setTextColor(Color.rgb(1,85,142));
        c.setTextColor(Color.rgb(1,85,142));
        d.setTextColor(Color.rgb(1,85,142));
        e.setTextColor(Color.rgb(1,85,142));
    }
    public void Clicked(Button btn,String Jawab,int position){
        btn.setBackgroundResource(R.drawable.round_blue);
        btn.setTextColor(Color.rgb(255,255,255));
        JAWABAN.set(position,Jawab);
    }
}


