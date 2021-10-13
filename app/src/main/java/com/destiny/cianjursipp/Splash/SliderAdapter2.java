package com.destiny.cianjursipp.Splash;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.destiny.cianjursipp.Activity.menu.PPDB.FormulirPPDBActivity;
import com.destiny.cianjursipp.Method.Destiny;
import com.destiny.cianjursipp.R;
import com.destiny.cianjursipp.SharedPreferance.DB_Helper;

public class SliderAdapter2 extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;
    String Username,Password,Nama,Token,Level,Photo;
    DB_Helper dbHelper;
    String id_sekolah;
    Dialog dialog;
    Button Submit,Close;
    public SliderAdapter2(Context context,String id_sekolah){
        this.context = context;
        this.id_sekolah = id_sekolah;
    }
    public int[] id ={
            0,
            1,
            2,
            3
    };

    public int[] slide_1 ={
            R.drawable.profil_sekolah,
            R.drawable.jadwal_pelajaran,
            R.drawable.perpustakaan_online,
            R.drawable.pembayaran
    };
    public int[] slide_2 ={
            R.drawable.agenda_sekolah,
            R.drawable.kehadiran,
            R.drawable.live_streaming,
            R.drawable.biaya_akademik
    };
    public int[] slide_3 ={
            R.drawable.prestasi,
            R.drawable.evadir,
            R.drawable.try_out,
            R.drawable.tabungan
    };
    public int[] slide_4 ={
            R.drawable.ppdb,
            R.drawable.tugas,
            R.drawable.loker,
            R.drawable.loker
    };
    //Removed
    public int[] slide_5 ={
            R.drawable.ppdb
    };

    public int[] slide_6 ={
            R.drawable.kabar_sekolah,
            R.drawable.teman,
            R.drawable.teaching_factory,
            R.drawable.rkas
    };
    public int[] slide_7 ={
            R.drawable.eskul,
            R.drawable.ujian,
            R.drawable.marketplace,
            R.drawable.koperasi
    };
    public int[] slide_8 ={
            R.drawable.media_pelajaran,
            R.drawable.e_raport,
            R.drawable.gallery,
            R.drawable.gallery
    };
    public int[] slide_9 ={
            R.drawable.struktur_organisasi,
            R.drawable.guru,
            R.drawable.penelusuran_lulusan,
            R.drawable.penelusuran_lulusan
    };
    //Removed
    public int[] slide_10 ={
            R.drawable.ppdb
    };

    public String[] Tslide_1 ={
            "Profil\nSekolah",
            "Jadwal\nPelajaran",
            "Perpustakaan\nOnline",
            "Pembayaran"
    };
    public String[] Tslide_2 ={
            "Agenda\nSekolah",
            "Kehadiran",
            "Live\nStreaming",
            "Biaya\nAkademik"
    };
    public String[] Tslide_3 ={
            "Prestasi",
            "Evadir",
            "Try Out",
            "Tabungan"
    };
    public String[] Tslide_4 ={
            "PPDB",
            "Tugas",
            "Loker\n(Bursa kerja)",
            ""
    };

    //Removed
    public String[] Tslide_5 ={
            "Sistem Informasi Yang Berkearifan Serta Cerdas dan Mencerdaskan"
    };
    //Removed

    public String[] Tslide_6 ={
            "Kabar\nSekolah",
            "Teman",
            "Teaching\nFactory",
            "RKAS"
    };
    public String[] Tslide_7 ={
            "Ekstrakulikuler",
            "Ujian",
            "Marketplace",
            "Koperasi"
    };
    public String[] Tslide_8 ={
            "Media\nPembelajaran",
            "E-Raport",
            "Galleri",
            ""
    };
    public String[] Tslide_9 ={
            "Struktur\nOrganiasi",
            "Guru",
            "Penelusuran\nLulusan",
            ""
    };

    //Removed
    public String[] Tslide_10 ={
            "Sistem Informasi Yang Berkearifan Serta Cerdas dan Mencerdaskan"
    };
    //Removed

    public String[] slide_tittle ={
            "Layanan Publik & Informasi",
            "Administrasi & Akademik",
            "Fasilitas",
            "Administrasi & Akademik"
    };


    @Override
    public int getCount() {
        return slide_tittle.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (LinearLayout) o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_home,container,false);
        Destiny destiny = new Destiny();
        dbHelper = new DB_Helper(context);
        Cursor cursor = dbHelper.checkUser();
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_penelusuran_lulusan);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.btn_rounded_white);
        Submit = dialog.findViewById(R.id.btnSubmit);
        Close = dialog.findViewById(R.id.btnClose);
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                Username = cursor.getString(0);
                Password = cursor.getString(1);
                Nama = cursor.getString(2);
                Token = cursor.getString(3);
                Level = cursor.getString(4);
                Photo = cursor.getString(5);
            }
        }
        Close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.hide();
            }
        });
        TextView tittle = view.findViewById(R.id.tvTittle);
        //1
        LinearLayout LOne = view.findViewById(R.id.linear1);
        TextView TOne = view.findViewById(R.id.tv1);
        ImageView IOne = view.findViewById(R.id.iv1);

        //2
        LinearLayout LTwo = view.findViewById(R.id.linear2);
        TextView TTwo = view.findViewById(R.id.tv2);
        ImageView ITwo = view.findViewById(R.id.iv2);

        //3
        LinearLayout LThree = view.findViewById(R.id.linear3);
        TextView TThree = view.findViewById(R.id.tv3);
        ImageView IThree = view.findViewById(R.id.iv3);

        //4
        LinearLayout LFour = view.findViewById(R.id.linear4);
        TextView TFour = view.findViewById(R.id.tv4);
        ImageView IFour = view.findViewById(R.id.iv4);

        //5
//        LinearLayout LFive = view.findViewById(R.id.linear5);
//        TextView TFive = view.findViewById(R.id.tv5);
//        ImageView IFive = view.findViewById(R.id.iv5);

        //6
        LinearLayout LSix = view.findViewById(R.id.linear6);
        TextView TSix = view.findViewById(R.id.tv6);
        ImageView ISix = view.findViewById(R.id.iv6);

        //7
        LinearLayout LSeven = view.findViewById(R.id.linear7);
        TextView TSeven = view.findViewById(R.id.tv7);
        ImageView ISeven = view.findViewById(R.id.iv7);

        //8
        LinearLayout LEight = view.findViewById(R.id.linear8);
        TextView TEight = view.findViewById(R.id.tv8);
        ImageView IEight = view.findViewById(R.id.iv8);

        //9
        LinearLayout LNine = view.findViewById(R.id.linear9);
        TextView TNine = view.findViewById(R.id.tv9);
        ImageView INine = view.findViewById(R.id.iv9);

        //10
//        LinearLayout LTen = view.findViewById(R.id.linear10);
//        TextView TTen = view.findViewById(R.id.tv10);
//        ImageView ITen = view.findViewById(R.id.iv10);

        tittle.setText(slide_tittle[position]);
        //1
        if (!Tslide_1[position].isEmpty()){
            IOne.setImageResource(slide_1[position]);
            TOne.setText(Tslide_1[position]);
        }else{
            LOne.setVisibility(View.GONE);
        }


        //2
        if (!Tslide_2[position].isEmpty()){
            ITwo.setImageResource(slide_2[position]);
            TTwo.setText(Tslide_2[position]);
        }else{
            LTwo.setVisibility(View.GONE);
        }


        //3
        if (!Tslide_3[position].isEmpty()){
            IThree.setImageResource(slide_3[position]);
            TThree.setText(Tslide_3[position]);
        }else{
            LThree.setVisibility(View.GONE);
        }

        //4
        if (!Tslide_4[position].isEmpty()){
            IFour.setImageResource(slide_4[position]);
            TFour.setText(Tslide_4[position]);
        }else{
            LFour.setVisibility(View.GONE);
        }

        //5


        //6
        if (!Tslide_6[position].isEmpty()){
            ISix.setImageResource(slide_6[position]);
            TSix.setText(Tslide_6[position]);
        }else{
            LSix.setVisibility(View.GONE);
        }

        //7
        if (!Tslide_7[position].isEmpty()){
            ISeven.setImageResource(slide_7[position]);
            TSeven.setText(Tslide_7[position]);
        }else{
            LSeven.setVisibility(View.GONE);
        }

        //8
        if (!Tslide_8[position].isEmpty()){
            IEight.setImageResource(slide_8[position]);
            TEight.setText(Tslide_8[position]);
        }else{
            LEight.setVisibility(View.GONE);
        }

        //9
        if (!Tslide_9[position].isEmpty()){
            INine.setImageResource(slide_9[position]);
            TNine.setText(Tslide_9[position]);
        }else{
            LNine.setVisibility(View.GONE);
        }

        //10

        LOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Tslide_1[position].equals("Profil\nSekolah")){
                    destiny.ChangeActivity(context,"Profile Sekolah",Level);
                }else if (Tslide_1[position].equals("Jadwal\nPelajaran")){
                    destiny.ChangeActivity(context,"Jadwal Pelajaran",Level);
                }else if (Tslide_1[position].equals("Perpustakaan\nOnline")){
                    destiny.ChangeActivity(context,"Perpustakaan Online",Level);
                }else{
                    destiny.ChangeActivity(context,"Pembayaran",Level);
                }
            }
        });

        LTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Tslide_2[position].equals("Agenda\nSekolah")){
                    destiny.ChangeActivity(context,"Agenda Sekolah",Level);
                }else if (Tslide_2[position].equals("Kehadiran")){
                    destiny.ChangeActivity(context,"Kehadiran",Level);
                }else if (Tslide_2[position].equals("Live\nStreaming")){
                    destiny.ChangeActivity(context,"Live Streaming",Level);
                }else{
                    destiny.ChangeActivity(context,"Biaya Akademik",Level);
                }
            }
        });

        LThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Tslide_3[position].equals("Prestasi")){
                    destiny.ChangeActivity(context,"Prestasi",Level);
                }else if (Tslide_3[position].equals("Evadir")){
                    destiny.ChangeActivity(context,"Evadir",Level);
                }else if (Tslide_3[position].equals("Try Out")){
                    destiny.ChangeActivity(context,"Try Out",Level);
                }else{
                    destiny.ChangeActivity(context,"Tabungan",Level);
                }
            }
        });

        LFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Tslide_4[position].equals("PPDB")){
                    Intent intent = new Intent(context, FormulirPPDBActivity.class);
                    intent.putExtra("ID", id_sekolah);
                    context.startActivity(intent);
                }else if (Tslide_4[position].equals("Tugas")){
                    destiny.ChangeActivity(context,"Tugas",Level);
                }else if (Tslide_4[position].equals("Loker\n(Bursa kerja)")){
                    destiny.ChangeActivity(context,"Loker",Level);
                }else{
                    destiny.ChangeActivity(context,"Tabungan",Level);
                }
            }
        });

        LSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Tslide_6[position].equals("Kabar\nSekolah")){
                    destiny.ChangeActivity(context,"Kabar Sekolah",Level);
                }else if (Tslide_6[position].equals("Teman")){
                    destiny.ChangeActivity(context,"Teman",Level);
                }else if (Tslide_6[position].equals("Teaching\nFactory")){
                    destiny.ChangeActivity(context,"Teaching Factory",Level);
                }else{
                    destiny.ChangeActivity(context,"RKAS",Level);
                }
            }
        });

//        if (Tslide_7[position].equals("Ujian")){
//            if (!Level.equals("siswa")){
//                LSeven.setAlpha(0.5f);
//            }else{
//                LSeven.setAlpha(1f);
//            }
//        }else{
//            LSeven.setAlpha(1f);
//        }
        LSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Tslide_7[position].equals("Ekstrakulikuler")){
                    destiny.ChangeActivity(context,"Eskul",Level);
                }else if (Tslide_7[position].equals("Ujian")){
                    destiny.ChangeActivity(context,"Ujian",Level);
                }else if (Tslide_7[position].equals("Marketplace")){
                    destiny.ChangeActivity(context,"Marketplace",Level);
                }else{
                    destiny.ChangeActivity(context,"Koperasi",Level);
                }
            }
        });

        LEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Tslide_8[position].equals("Media\nPembelajaran")){
                    destiny.ChangeActivity(context,"Media Pembelajaran",Level);
                }else if (Tslide_8[position].equals("E-Raport")){
                    destiny.ChangeActivity(context,"E Raport",Level);
                }else if (Tslide_8[position].equals("Galleri")){
                    destiny.ChangeActivity(context,"Gallery",Level);
                }else{
                    destiny.ChangeActivity(context,"Koperasi",Level);
                }
            }
        });

        LNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Tslide_9[position].equals("Struktur\nOrganiasi")){
                    destiny.ChangeActivity(context,"Struktur Sekolah",Level);
                }else if (Tslide_9[position].equals("Guru")){
                    destiny.ChangeActivity(context,"Guru",Level);
                }else if (Tslide_9[position].equals("Penelusuran\nLulusan")){
                    dialog.show();
//                    destiny.ChangeActivity(context,"Penelusuran Lulusan",Level);
                }else{
                    destiny.ChangeActivity(context,"Koperasi",Level);
                }
            }
        });

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }
}

