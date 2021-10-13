package com.destiny.cianjursipp.Adapter.Universal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.destiny.cianjursipp.Method.Destiny;
import com.destiny.cianjursipp.Model.DataModel;
import com.destiny.cianjursipp.R;

import java.util.ArrayList;
import java.util.List;

public class AutoCompleteSekolahAdapter extends ArrayAdapter<DataModel> {
    private List<DataModel> dm;
    private RecyclerView rv;
    Destiny destiny;
    public AutoCompleteSekolahAdapter(@NonNull Context context, @NonNull List<DataModel> dm){
        super(context,0,dm);
        this.dm = new ArrayList<>(dm);
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return AutoCompleteFilter;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        destiny = new Destiny();
        if (convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_sekolah,parent,false
            );
        }
        TextView NamaSekolah = convertView.findViewById(R.id.tvNamaSekolah);
        ImageView LogoSekolah = convertView.findViewById(R.id.ivLogo);
        TextView AlamatSekolah = convertView.findViewById(R.id.tvAlamat);

        DataModel dm = getItem(position);
        if (dm != null){
            NamaSekolah.setText(dm.getNama_sekolah());
            AlamatSekolah.setText(dm.getAlamat_sekolah());
            Glide.with(getContext())
                    .load(destiny.BASE_URL()+dm.getLogo_sekolah())
                    .into(LogoSekolah);
        }
        return convertView;
    }

    private Filter AutoCompleteFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            List<DataModel> suggestion = new ArrayList<>();
            if (constraint == null || constraint.length() == 0){
                suggestion.addAll(dm);
            }else{
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (DataModel item : dm){
                    if (item.getNama_sekolah().toLowerCase().contains(filterPattern)){
                        suggestion.add(item);
                    }
                }
            }
            results.values = suggestion;
            results.count = suggestion.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            clear();
            addAll((List) results.values);
            notifyDataSetChanged();
        }

        @Override
        public CharSequence convertResultToString(Object resultValue){
            return ((DataModel) resultValue).getNama_sekolah();
        }
    };
}

