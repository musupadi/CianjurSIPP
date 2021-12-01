package com.destiny.cianjursipp.Splash;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.destiny.cianjursipp.R;

public class SliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){
        this.context = context;
    }
    public int[] id ={
            0,
            1,
            2,
            3
    };

    public int[] slide_image ={
            R.drawable.akram_zyarga,
            R.drawable.gambar_1,
            R.drawable.gambar_2,
            R.drawable.gambar_3,
    };

    public String[] slide_nama ={
            "Sistem Informasi Yang Berkearifan Serta Cerdas dan Mencerdaskan",
            "Media Pembelajaran Yang Interaktif dan Mudah dipelajari oleh Siswa",
            "Membantu Mendampingi Guru Untuk Maju dan Hebat Dengan Kekuatannya Sendiri",
            "Memudahkan Siswa dan Guru Dalam Melaksanakan Proses Belajar Mengajar"
    };


    @Override
    public int getCount() {
        return slide_nama.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (RelativeLayout) o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.splash_slider,container,false);

        ImageView ivGambar = view.findViewById(R.id.ivImage);
        TextView textGambar = view.findViewById(R.id.tvTextImage);
        TextView text = view.findViewById(R.id.tvText);
        ivGambar.setImageResource(slide_image[position]);
        text.setText(slide_nama[position]);
        if (position>0){
            textGambar.setVisibility(View.GONE);
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }
}

