package com.destiny.cianjursipp.Adapter.Universal;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.destiny.cianjursipp.API.ApiRequest;
import com.destiny.cianjursipp.API.RetroServer;
import com.destiny.cianjursipp.Activity.HomeActivity;
import com.destiny.cianjursipp.Method.Destiny;
import com.destiny.cianjursipp.Model.DataModel;
import com.destiny.cianjursipp.Model.Pena.ResponseModel;
import com.destiny.cianjursipp.R;
import com.destiny.cianjursipp.SharedPreferance.DB_Helper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterSekolah extends RecyclerView.Adapter<AdapterSekolah.HolderData> {
    private List<DataModel> mList;
    private Context ctx;

    DB_Helper dbHelper;
    Boolean onClick=false;
    RecyclerView recyclerView;
    Destiny destiny;
    public AdapterSekolah(Context ctx, List<DataModel> mList){
        this.ctx = ctx;
        this.mList = mList;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_sekolah,viewGroup,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final HolderData holderData, int posistion) {
        destiny = new Destiny();
        final DataModel dm = mList.get(posistion);
        holderData.Judul.setText(dm.getNama_sekolah());
        holderData.Alamat.setText(dm.getAlamat_sekolah());
        Glide.with(ctx)
                .load(destiny.BASE_URL()+dm.getLogo_sekolah())
                .into(holderData.Image);
        holderData.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logic("guest",dm.getId_sekolah());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        ImageView Image;
        TextView Judul,Alamat;
        CardView card;
        public HolderData(View v){
            super(v);
            Image = v.findViewById(R.id.ivLogo);
            Judul = v.findViewById(R.id.tvNamaSekolah);
            Alamat = v.findViewById(R.id.tvAlamat);
            card = v.findViewById(R.id.card);
        }
    }

    private void Logic(final String user, final String id){
        final ProgressDialog pd = new ProgressDialog(ctx);
        pd.setMessage("Sedang Memasuki Halaman Sekolah");
        pd.show();
        pd.setCancelable(false);
        dbHelper = new DB_Helper(ctx);
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        final Call<ResponseModel> login =api.login(user,id);
        login.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                try {
                    if (response.body().getStatusCode().equals("000")){
                        dbHelper.SaveUser(user,id,response.body().getData().get(0).getName(),response.body().getData().get(0).getAccessToken(),response.body().getData().get(0).getAs(),response.body().getData().get(0).getPhoto(),response.body().getData().get(0).getKelas());
                        Intent intent = new Intent(ctx, HomeActivity.class);
                        ctx.startActivity(intent);
                    }else{
                        Toast.makeText(ctx, response.body().getStatusMessage(), Toast.LENGTH_SHORT).show();
                    }
                    pd.hide();
                }catch (Exception e){
                    Toast.makeText(ctx, "Terjadi Kesalahan "+e.toString(), Toast.LENGTH_SHORT).show();
                    pd.hide();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
//                Toast.makeText(LoginActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
                Toast.makeText(ctx, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
                pd.hide();
                Log.i("Login Logic : ",t.toString());
            }
        });
    }
}


