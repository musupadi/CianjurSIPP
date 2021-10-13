package com.destiny.cianjursipp.Adapter.Home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.destiny.cianjursipp.Activity.menu.KabarSekolah.DetailKabarSekolahActivity;
import com.destiny.cianjursipp.Method.Destiny;
import com.destiny.cianjursipp.Model.DataModel;
import com.destiny.cianjursipp.R;

import java.util.List;

public class AdapterBanner extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;
    private List<DataModel> mList;
    Destiny destiny;

    public AdapterBanner(Context context, List<DataModel> mList){
        this.context = context;
        this.mList = mList;
    }


    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (LinearLayout) o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.list_banner,container,false);
        final DataModel dm = mList.get(position);
        destiny = new Destiny();
        ImageView Image = view.findViewById(R.id.ivGambar);
        Glide.with(context)
                .load(destiny.CheckerImageYoutube(dm.getLink_youtube_info_publik(),dm.getCover_info_publik()))
                .into(Image);
        Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DetailKabarSekolahActivity.class);
                i.putExtra("JUDUL", dm.getJudul_info_publik());
                i.putExtra("ISI",dm.getIsi_info_publik());
                i.putExtra("TANGGAL",dm.getCreated_at_info_publik());
                i.putExtra("GAMBAR",destiny.BASE_URL()+dm.getCover_info_publik());
                i.putExtra("YOUTUBE",dm.getLink_youtube_info_publik() );
                context.startActivity(i);
            }
        });
//        TextView textGambar = view.findViewById(R.id.tvTextImage);
//        TextView text = view.findViewById(R.id.tvText);
//        ivGambar.setImageResource(slide_image[position]);
//        text.setText(slide_nama[position]);
//        if (position>0){
//            textGambar.setVisibility(View.GONE);
//        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }
}


