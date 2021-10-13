package com.destiny.cianjursipp.Activity.menu.JadwalPelajaran;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.destiny.cianjursipp.API.ApiRequest;
import com.destiny.cianjursipp.API.RetroServer;
import com.destiny.cianjursipp.Activity.LoginActivity;
import com.destiny.cianjursipp.Adapter.Universal.AdapterJadwalPelajaran;
import com.destiny.cianjursipp.Method.Destiny;
import com.destiny.cianjursipp.Model.DataModel;
import com.destiny.cianjursipp.Model.Pena.ResponseModel;
import com.destiny.cianjursipp.R;
import com.destiny.cianjursipp.SharedPreferance.DB_Helper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JadwalPelajaranActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    Destiny destiny;
    LinearLayout linearTanggal;
    TextView tvTanggal;
    DB_Helper dbHelper;
    String Username, Password, Nama, Token, Level, Photo;
    RecyclerView recycler;
    private List<DataModel> mItems = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    String DATES;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_pelajaran);
        destiny = new Destiny();
        DECLARATION();
        dbHelper = new DB_Helper(this);
        Cursor cursor = dbHelper.checkUser();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Username = cursor.getString(0);
                Password = cursor.getString(1);
                Nama = cursor.getString(2);
                Token = cursor.getString(3);
                Level = cursor.getString(4);
                Photo = cursor.getString(5);
            }
        }
        Logic();
        ONCLICK();
    }

    private void ONCLICK() {
        linearTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker();
            }
        });
    }

    private void DECLARATION() {
        recycler = findViewById(R.id.recycler);
        linearTanggal = findViewById(R.id.linearTanggal);
        tvTanggal = findViewById(R.id.tvTanggal);
        tvTanggal.setText(destiny.thisDay());
        DATES = destiny.getThisDayDB();
        Toast.makeText(this, DATES, Toast.LENGTH_SHORT).show();
    }

    private void Logic() {
        mManager = new LinearLayoutManager(JadwalPelajaranActivity.this, LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(mManager);
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseModel> KabarBerita = api.GetMapel(destiny.AUTH(Token), DATES);
        KabarBerita.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                try {
                    if (response.body().getStatusCode().equals("000")) {
                        mItems = response.body().getData();
                        mAdapter = new AdapterJadwalPelajaran(JadwalPelajaranActivity.this, mItems);
                        recycler.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                    } else if (response.body().getStatusCode().equals("001") || response.body().getStatusCode().equals("002")) {
                        destiny.AutoLogin(Username, Password, JadwalPelajaranActivity.this);
                        Logic();
                    } else {
                        Toast.makeText(JadwalPelajaranActivity.this, "Terjadi Kesalahan ", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(JadwalPelajaranActivity.this, "Terjadi Kesalahan User akan Terlogout", Toast.LENGTH_SHORT).show();
                    dbHelper.Logout();
                    Intent intent = new Intent(JadwalPelajaranActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(JadwalPelajaranActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void showDatePicker() {
        DatePickerDialog dialog = new DatePickerDialog(JadwalPelajaranActivity.this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        int Month = month + 1;
        String date = year + "-" + Month + "-" + dayOfMonth;
        DATES = date;
        tvTanggal.setText(destiny.DateChanges(String.valueOf(year), String.valueOf(Month), String.valueOf(dayOfMonth)));
        Toast.makeText(this, date, Toast.LENGTH_SHORT).show();
        Logic();
    }
}
