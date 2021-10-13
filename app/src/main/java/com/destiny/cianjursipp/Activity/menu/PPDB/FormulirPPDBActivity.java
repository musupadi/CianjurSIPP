package com.destiny.cianjursipp.Activity.menu.PPDB;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.destiny.cianjursipp.BuildConfig;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.destiny.cianjursipp.API.ApiRequest;
import com.destiny.cianjursipp.API.RetroServer;
import com.destiny.cianjursipp.Activity.HomeActivity;
import com.destiny.cianjursipp.Activity.LoginActivity;
import com.destiny.cianjursipp.Activity.MainActivity;
import com.destiny.cianjursipp.Method.Destiny;
import com.destiny.cianjursipp.Model.DataModel;
import com.destiny.cianjursipp.Model.Pena.ResponseModel;
import com.destiny.cianjursipp.R;
import com.destiny.cianjursipp.SharedPreferance.DB_Helper;
import com.destiny.cianjursipp.Spinner.SpinnerTahunAjaran;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormulirPPDBActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    String Tanggals = "Anak";
    LinearLayout LTanggalLahir,LTanggalLahirAyah,LTanggalLahirIbu;
    TextView TanggalLahir,TanggalLahirAyah,TanggalLahirIbu;
    String tanggal = "1994-01-15";
    String tanggalIbu = "1994-01-15";
    String tanggalAyah = "1994-01-15";

    Destiny destiny;
    Button uploadAkta,uploadKK,uploadDataPribadi,uploadFoto;
    TextView form,tvid;
    ImageView LogoSekolah;
    Spinner spAjaran,spKebutuhanKhusus;
    private List<DataModel> mItems = new ArrayList<>();
    //Jenis Kelamin
    RadioButton Laki,Perempuan;
    Boolean Kelamin = false;
    //Akta
    RadioButton AdaAkta,TidakAdaAkta;
    Boolean Akta = false;
    LinearLayout lGambar1;
    ImageView ivGambar1;
    TextView tvGambar1;
    //KK
    RadioButton AdaKK,TidakAdaKK;
    Boolean KK = false;
    LinearLayout lGambar2;
    ImageView ivGambar2;
    TextView tvGambar2;
    //Data Pribadi
    RadioButton AdaDataPribadi,TidakAdaDataPribadi;
    Boolean DataPribadi = false;
    LinearLayout lGambar3;
    ImageView ivGambar3;
    TextView tvGambar3;
    Button simpan;
    //Foto
    RadioButton AdaFoto,TidakAdaFoto;
    Boolean Foto = false;
    LinearLayout lGambar4;
    ImageView ivGambar4;
    TextView tvGambar4;

    DB_Helper dbHelper;
    String Username,Password,Nama,Token,Level,Photo;

    EditText Email,Alamat,NomorUjian,NISN,NIK,NamaLengkap,NomorTelpon,TempatLahir,NamaAyah,NamaIbu,Telpon,
            Agama,RT,RW,Kecamatan,PendidikanAyah,PekerjaanAyah,PenghasilanAyah,NIKAyah,PendidikanIbu,PekerjaanIbu,PenghasilanIbu,NIKIbu,
            SekolahAsal,AnakKeberapa,BeratBadan,TinggiBadan,LingkarKepala,JumlahSaudara,JarakRumahKeSekolah;

    //Dellaroy Logic
    private static final int REQUEST_TAKE_PHOTO = 0;
    private static final int REQUEST_PICK_PHOTO = 2;
    private Uri mMediaUri;
    private static final int CAMERA_PIC_REQUEST = 1111;


    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;

    public static final int MEDIA_TYPE_IMAGE = 1;

    private Uri fileUri;

    private String mediaPath;

    private Button btnCapturePicture;

    private String mImageFileLocation = "";
    public static final String IMAGE_DIRECTORY_NAME = "Android File Upload";
    ProgressDialog pDialog;
    String postFoto1= "";
    String postFoto2= "";
    String postFoto3= "";
    String postFoto4= "";
    //ONCLICK
    Boolean Gambar1 = false;
    Boolean Gambar2 = false;
    Boolean Gambar3 = false;
    Boolean Gambar4 = false;
    RelativeLayout Back;

    String ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulir_p_p_d_b);
        Intent intent = getIntent();
        ID = intent.getExtras().getString("ID");
        destiny = new Destiny();
        dbHelper = new DB_Helper(this);
        Cursor cursor = dbHelper.checkUser();
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
        //
        form = findViewById(R.id.tvForm);
        LogoSekolah = findViewById(R.id.ivLogoSekolah);
        spAjaran = findViewById(R.id.spAjaran);
        tvid = findViewById(R.id.tvIdTahunAjaran);
        spKebutuhanKhusus = findViewById(R.id.spKebutuhanKhusus);
        //Data Edit Text
        NomorUjian = findViewById(R.id.etNomorUjian);
        NISN = findViewById(R.id.etNISN);
        NIK = findViewById(R.id.etNIK);
        NamaLengkap = findViewById(R.id.etNamaLengkapSiswa);
        NomorTelpon = findViewById(R.id.etNomorTelpon);
        TempatLahir = findViewById(R.id.etTempatLahir);
        NamaAyah = findViewById(R.id.etNamaAyahKandung);
        NamaIbu = findViewById(R.id.etNamaIbuKandung);
        Telpon = findViewById(R.id.etNoTelponOrtu);
        Email = findViewById(R.id.etEmail);
        Alamat = findViewById(R.id.etAlamat);
        Agama = findViewById(R.id.etAgama);
        RT = findViewById(R.id.etRt);
        RW = findViewById(R.id.etRw);
        Kecamatan = findViewById(R.id.etKecematan);
        PendidikanAyah = findViewById(R.id.etPendidikanAyah);
        PendidikanIbu = findViewById(R.id.etPendidikanIbu);
        PekerjaanAyah = findViewById(R.id.etPekerjaanAyah);
        PekerjaanIbu = findViewById(R.id.etPekerjaanIbu);
        NIKAyah = findViewById(R.id.etNikAyah);
        NIKIbu = findViewById(R.id.etNikIbu);
        PenghasilanAyah = findViewById(R.id.etPenghasilanAyah);
        PenghasilanIbu = findViewById(R.id.etPenghasilanIbu);
        SekolahAsal = findViewById(R.id.etSekolahAsal);
        AnakKeberapa = findViewById(R.id.etAnakKeberapa);
        BeratBadan = findViewById(R.id.etBeratBadan);
        TinggiBadan = findViewById(R.id.etTinggiBadan);
        LingkarKepala = findViewById(R.id.etLingkarKepala);
        JumlahSaudara = findViewById(R.id.etJumlahSaudaraKandung);
        JarakRumahKeSekolah = findViewById(R.id.etJarakRumahKeSekolah);

        simpan = findViewById(R.id.btnSimpan);
        LTanggalLahir = findViewById(R.id.linearTanggalLahir);
        TanggalLahir = findViewById(R.id.tvTanggalLahir);
        LTanggalLahirAyah = findViewById(R.id.linearTanggalLahirAyah);
        TanggalLahirAyah = findViewById(R.id.tvTanggalLahirAyah);
        LTanggalLahirIbu = findViewById(R.id.linearTanggalLahirIbu);
        TanggalLahirIbu = findViewById(R.id.tvTanggalLahirIbu);
        //
        uploadAkta = findViewById(R.id.btnUploadAkta);
        uploadKK = findViewById(R.id.btnUploadKK);
        uploadDataPribadi = findViewById(R.id.btnUploadDataPribadi);
        uploadFoto = findViewById(R.id.btnUploadFoto);
        Laki = findViewById(R.id.radioLakilaki);
        Perempuan = findViewById(R.id.radioPerempuan);
        AdaAkta = findViewById(R.id.radioAdaAkta);
        TidakAdaAkta = findViewById(R.id.radioTidakAdaAkta);
        AdaKK = findViewById(R.id.radioAdaKK);
        TidakAdaKK = findViewById(R.id.radioTidakAdaKK);
        AdaDataPribadi = findViewById(R.id.radioAdaDataPribadi);
        TidakAdaDataPribadi = findViewById(R.id.radioTidakAdaDataPribadi);
        AdaFoto = findViewById(R.id.radioAdaFoto);
        TidakAdaFoto = findViewById(R.id.radioTidakAdaFoto);
        //Akta
        lGambar1 = findViewById(R.id.linearGambarAkta);
        ivGambar1 = findViewById(R.id.ivGambarAkta);
        tvGambar1 = findViewById(R.id.tvGambarAkta);
        //KK
        lGambar2 = findViewById(R.id.linearGambarKK);
        ivGambar2 = findViewById(R.id.ivGambarKK);
        tvGambar2 = findViewById(R.id.tvGambarKK);
        //Data Siswa
        lGambar3 = findViewById(R.id.linearGambarDataPribadi);
        ivGambar3 = findViewById(R.id.ivGambarDataPribadi);
        tvGambar3 = findViewById(R.id.tvGambarDataPribadi);
        //Foto
        lGambar4 = findViewById(R.id.linearGambarFoto);
        ivGambar4 = findViewById(R.id.ivGambarFoto);
        tvGambar4 = findViewById(R.id.tvGambarFoto);

        uploadAkta.setVisibility(View.GONE);
        uploadKK.setVisibility(View.GONE);
        uploadDataPribadi.setVisibility(View.GONE);
        uploadKK.setVisibility(View.GONE);
        LTanggalLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tanggals = "Anak";
                showDatePicker();
            }
        });
        LTanggalLahirIbu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tanggals = "Ibu";
                showDatePicker();
            }
        });
        LTanggalLahirAyah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tanggals = "Ayah";
                showDatePicker();
            }
        });
        GetSekolah();
        TanggalLahir.setText(destiny.thisDay());
        TanggalLahirIbu.setText(destiny.thisDay());
        TanggalLahirAyah.setText(destiny.thisDay());
        LogicKelamin();
        LogicAkta();
        LogicKK();
        LogicDataPribadi();
        LogicFoto();
        DataTahunAjaran();
        if (ID!=null){
            Password = ID;
        }
        spAjaran.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    DataModel clickedItem = (DataModel) parent.getItemAtPosition(position);
                    int clickedItems = Integer.parseInt(clickedItem.getId_tahun_ajaran());
                    tvid.setText(String.valueOf(clickedItems));
                }catch (Exception e){

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        uploadAkta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(FormulirPPDBActivity.this)
                        .title("Pilih Gambar")
                        .items(R.array.uploadImages)
                        .itemsIds(R.array.itemIds)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                switch (which) {
                                    case 0:
                                        Gambar1 = true;
                                        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                        startActivityForResult(galleryIntent, REQUEST_PICK_PHOTO);
                                        lGambar1.setVisibility(View.VISIBLE);
                                        break;
                                    case 1:
                                        Gambar1 = true;
                                        captureImage();
                                        lGambar1.setVisibility(View.VISIBLE);
                                        break;
                                }
                            }
                        })
                        .show();
            }
        });
        uploadKK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(FormulirPPDBActivity.this)
                        .title("Pilih Gambar")
                        .items(R.array.uploadImages)
                        .itemsIds(R.array.itemIds)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                switch (which) {
                                    case 0:
                                        Gambar2 = true;
                                        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                        startActivityForResult(galleryIntent, REQUEST_PICK_PHOTO);
                                        lGambar2.setVisibility(View.VISIBLE);
                                        break;
                                    case 1:
                                        Gambar2 = true;
                                        captureImage();
                                        lGambar2.setVisibility(View.VISIBLE);
                                        break;
                                }
                            }
                        })
                        .show();
            }
        });
        uploadDataPribadi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(FormulirPPDBActivity.this)
                        .title("Pilih Gambar")
                        .items(R.array.uploadImages)
                        .itemsIds(R.array.itemIds)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                switch (which) {
                                    case 0:
                                        Gambar3 = true;
                                        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                        startActivityForResult(galleryIntent, REQUEST_PICK_PHOTO);
                                        lGambar3.setVisibility(View.VISIBLE);
                                        break;
                                    case 1:
                                        Gambar3 = true;
                                        captureImage();
                                        lGambar3.setVisibility(View.VISIBLE);
                                        break;
                                }
                            }
                        })
                        .show();
            }
        });
        uploadFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(FormulirPPDBActivity.this)
                        .title("Pilih Gambar")
                        .items(R.array.uploadImages)
                        .itemsIds(R.array.itemIds)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                switch (which) {
                                    case 0:
                                        Gambar4 = true;
                                        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                        startActivityForResult(galleryIntent, REQUEST_PICK_PHOTO);
                                        lGambar4.setVisibility(View.VISIBLE);
                                        break;
                                    case 1:
                                        Gambar4 = true;
                                        captureImage();
                                        lGambar4.setVisibility(View.VISIBLE);
                                        break;
                                }
                            }
                        })
                        .show();
            }
        });
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Checker().equals("OK")){
                    String k = "L";
                    if (!Kelamin){
                        k="P";
                    }
                    if (!Akta && !KK && !DataPribadi && !Foto){
                        //0000


                        final ProgressDialog pd = new ProgressDialog(FormulirPPDBActivity.this);
                        pd.setMessage("Sedang Memasukan Data Peserta");
                        pd.show();
                        pd.setCancelable(false);
                        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
                        Call<ResponseModel> FormPPDB = api.PPDB(
                                destiny.AUTH(Token),
                                //New
                                RequestBody.create(MediaType.parse("text/plain"),Agama.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),RT.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),RW.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Kecamatan.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),tanggalAyah),
                                RequestBody.create(MediaType.parse("text/plain"),PendidikanAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),PekerjaanAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),PenghasilanAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NIKAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),tanggalIbu),
                                RequestBody.create(MediaType.parse("text/plain"),PendidikanIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),PekerjaanIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),PenghasilanIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NIKIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),spKebutuhanKhusus.getSelectedItem().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),SekolahAsal.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),AnakKeberapa.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),BeratBadan.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),TinggiBadan.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),LingkarKepala.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),JumlahSaudara.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),JarakRumahKeSekolah.getText().toString()),
                                //New
                                RequestBody.create(MediaType.parse("text/plain"),tvid.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Alamat.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Email.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NomorUjian.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NISN.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NIK.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Password),
                                RequestBody.create(MediaType.parse("text/plain"),NamaLengkap.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NomorTelpon.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),k),
                                RequestBody.create(MediaType.parse("text/plain"),TempatLahir.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),tanggal),
                                RequestBody.create(MediaType.parse("text/plain"),NamaAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NamaIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Telpon.getText().toString())
                        );
                        FormPPDB.enqueue(new Callback<ResponseModel>() {
                            @Override
                            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                                try {
                                    pd.hide();
                                    Toast.makeText(FormulirPPDBActivity.this, response.body().getStatusMessage(), Toast.LENGTH_SHORT).show();
                                    if (response.body().getStatusCode().equals("000")){
                                        Intent intent = new Intent(FormulirPPDBActivity.this, HomeActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }catch (Exception e){
                                    pd.hide();
                                    Toast.makeText(FormulirPPDBActivity.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseModel> call, Throwable t) {
                                pd.hide();
                                Log.d("Error : ",t.toString());
                                Toast.makeText(FormulirPPDBActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
                            }
                        });
//                    File fileGallery1 = new File(postGallery1);
//                    RequestBody fileReqBodyGallery1 = RequestBody.create(MediaType.parse("image/*"), fileGallery1);
//                    MultipartBody.Part partGallery1 = MultipartBody.Part.createFormData("file_syarat[]", fileGallery1.getName(), fileReqBodyGallery1);


                    }else if (!Akta && !KK && !DataPribadi && Foto){
                        //0001
                        final ProgressDialog pd = new ProgressDialog(FormulirPPDBActivity.this);
                        pd.setMessage("Sedang Memasukan Data Peserta");
                        pd.show();
                        pd.setCancelable(false);

                        File file4 = new File(postFoto4);
                        RequestBody fileReqBody4 = RequestBody.create(MediaType.parse("image/*"), file4);
                        MultipartBody.Part PasFoto = MultipartBody.Part.createFormData("pasfoto3x4", file4.getName(), fileReqBody4);

                        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
                        Call<ResponseModel> FormPPDB = api.PPDB(
                                destiny.AUTH(Token),
                                //New
                                RequestBody.create(MediaType.parse("text/plain"),Agama.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),RT.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),RW.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Kecamatan.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),tanggalAyah),
                                RequestBody.create(MediaType.parse("text/plain"),PendidikanAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),PekerjaanAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),PenghasilanAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NIKAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),tanggalIbu),
                                RequestBody.create(MediaType.parse("text/plain"),PendidikanIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),PekerjaanIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),PenghasilanIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NIKIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),spKebutuhanKhusus.getSelectedItem().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),SekolahAsal.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),AnakKeberapa.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),BeratBadan.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),TinggiBadan.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),LingkarKepala.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),JumlahSaudara.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),JarakRumahKeSekolah.getText().toString()),
                                //New
                                RequestBody.create(MediaType.parse("text/plain"),tvid.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Alamat.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Email.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NomorUjian.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NISN.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NIK.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Password),
                                RequestBody.create(MediaType.parse("text/plain"),NamaLengkap.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NomorTelpon.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),k),
                                RequestBody.create(MediaType.parse("text/plain"),TempatLahir.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),tanggal),
                                RequestBody.create(MediaType.parse("text/plain"),NamaAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NamaIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Telpon.getText().toString()),
                                PasFoto
                        );
                        FormPPDB.enqueue(new Callback<ResponseModel>() {
                            @Override
                            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                                try {
                                    pd.hide();
                                    Toast.makeText(FormulirPPDBActivity.this, response.body().getStatusMessage(), Toast.LENGTH_SHORT).show();
                                    if (response.body().getStatusCode().equals("000")){
                                        Intent intent = new Intent(FormulirPPDBActivity.this, HomeActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }catch (Exception e){
                                    pd.hide();
                                    Toast.makeText(FormulirPPDBActivity.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseModel> call, Throwable t) {
                                pd.hide();
                                Toast.makeText(FormulirPPDBActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }else if (!Akta && !KK && DataPribadi && !Foto){
                        //0010
                        final ProgressDialog pd = new ProgressDialog(FormulirPPDBActivity.this);
                        pd.setMessage("Sedang Memasukan Data Peserta");
                        pd.show();
                        pd.setCancelable(false);

                        File file3 = new File(postFoto3);
                        RequestBody fileReqBody3 = RequestBody.create(MediaType.parse("image/*"), file3);
                        MultipartBody.Part DataPribadis = MultipartBody.Part.createFormData("datapribadisiswa", file3.getName(), fileReqBody3);

//                        File file4 = new File(postFoto4);
//                        RequestBody fileReqBody = RequestBody.create(MediaType.parse("image/*"), file4);
//                        MultipartBody.Part PasFoto = MultipartBody.Part.createFormData("pasfoto3x4", file4.getName(), fileReqBody);

                        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
                        Call<ResponseModel> FormPPDB = api.PPDB(
                                destiny.AUTH(Token),
                                //New
                                RequestBody.create(MediaType.parse("text/plain"),Agama.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),RT.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),RW.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Kecamatan.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),tanggalAyah),
                                RequestBody.create(MediaType.parse("text/plain"),PendidikanAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),PekerjaanAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),PenghasilanAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NIKAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),tanggalIbu),
                                RequestBody.create(MediaType.parse("text/plain"),PendidikanIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),PekerjaanIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),PenghasilanIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NIKIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),spKebutuhanKhusus.getSelectedItem().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),SekolahAsal.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),AnakKeberapa.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),BeratBadan.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),TinggiBadan.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),LingkarKepala.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),JumlahSaudara.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),JarakRumahKeSekolah.getText().toString()),
                                //New
                                RequestBody.create(MediaType.parse("text/plain"),tvid.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Alamat.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Email.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NomorUjian.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NISN.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NIK.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Password),
                                RequestBody.create(MediaType.parse("text/plain"),NamaLengkap.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NomorTelpon.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),k),
                                RequestBody.create(MediaType.parse("text/plain"),TempatLahir.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),tanggal),
                                RequestBody.create(MediaType.parse("text/plain"),NamaAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NamaIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Telpon.getText().toString()),
                                DataPribadis
                        );
                        FormPPDB.enqueue(new Callback<ResponseModel>() {
                            @Override
                            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                                try {
                                    pd.hide();
                                    Toast.makeText(FormulirPPDBActivity.this, response.body().getStatusMessage(), Toast.LENGTH_SHORT).show();
                                    if (response.body().getStatusCode().equals("000")){
                                        Intent intent = new Intent(FormulirPPDBActivity.this, HomeActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }catch (Exception e){
                                    pd.hide();
                                    Toast.makeText(FormulirPPDBActivity.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseModel> call, Throwable t) {
                                pd.hide();
                                Toast.makeText(FormulirPPDBActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }else if (!Akta && !KK && DataPribadi && Foto){
                        //0011
                        final ProgressDialog pd = new ProgressDialog(FormulirPPDBActivity.this);
                        pd.setMessage("Sedang Memasukan Data Peserta");
                        pd.show();
                        pd.setCancelable(false);

                        File file3 = new File(postFoto3);
                        RequestBody fileReqBody3 = RequestBody.create(MediaType.parse("image/*"), file3);
                        MultipartBody.Part DataPribadis = MultipartBody.Part.createFormData("datapribadisiswa", file3.getName(), fileReqBody3);

                        File file4 = new File(postFoto4);
                        RequestBody fileReqBody4 = RequestBody.create(MediaType.parse("image/*"), file4);
                        MultipartBody.Part PasFoto = MultipartBody.Part.createFormData("pasfoto3x4", file4.getName(), fileReqBody4);

                        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
                        Call<ResponseModel> FormPPDB = api.PPDB(
                                destiny.AUTH(Token),
                                //New
                                RequestBody.create(MediaType.parse("text/plain"),Agama.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),RT.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),RW.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Kecamatan.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),tanggalAyah),
                                RequestBody.create(MediaType.parse("text/plain"),PendidikanAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),PekerjaanAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),PenghasilanAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NIKAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),tanggalIbu),
                                RequestBody.create(MediaType.parse("text/plain"),PendidikanIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),PekerjaanIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),PenghasilanIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NIKIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),spKebutuhanKhusus.getSelectedItem().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),SekolahAsal.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),AnakKeberapa.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),BeratBadan.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),TinggiBadan.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),LingkarKepala.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),JumlahSaudara.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),JarakRumahKeSekolah.getText().toString()),
                                //New
                                RequestBody.create(MediaType.parse("text/plain"),tvid.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Alamat.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Email.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NomorUjian.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NISN.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NIK.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Password),
                                RequestBody.create(MediaType.parse("text/plain"),NamaLengkap.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NomorTelpon.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),k),
                                RequestBody.create(MediaType.parse("text/plain"),TempatLahir.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),tanggal),
                                RequestBody.create(MediaType.parse("text/plain"),NamaAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NamaIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Telpon.getText().toString()),
                                DataPribadis,
                                PasFoto
                        );
                        FormPPDB.enqueue(new Callback<ResponseModel>() {
                            @Override
                            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                                try {
                                    pd.hide();
                                    Toast.makeText(FormulirPPDBActivity.this, response.body().getStatusMessage(), Toast.LENGTH_SHORT).show();
                                    if (response.body().getStatusCode().equals("000")){
                                        Intent intent = new Intent(FormulirPPDBActivity.this, HomeActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }else{
                                        Toast.makeText(FormulirPPDBActivity.this, "NISN Sudah Terdaftar", Toast.LENGTH_SHORT).show();
                                    }
                                }catch (Exception e){
                                    pd.hide();
                                    Toast.makeText(FormulirPPDBActivity.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseModel> call, Throwable t) {
                                pd.hide();
                                Toast.makeText(FormulirPPDBActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }else if (!Akta && KK && !DataPribadi && !Foto){
                        //0100
                        final ProgressDialog pd = new ProgressDialog(FormulirPPDBActivity.this);
                        pd.setMessage("Sedang Memasukan Data Peserta");
                        pd.show();
                        pd.setCancelable(false);

                        File file2 = new File(postFoto2);
                        RequestBody fileReqBody2 = RequestBody.create(MediaType.parse("image/*"), file2);
                        MultipartBody.Part KartuKeluarga = MultipartBody.Part.createFormData("kartukeluargafotocopy", file2.getName(), fileReqBody2);

//                        File file3 = new File(postFoto3);
//                        RequestBody fileReqBody3 = RequestBody.create(MediaType.parse("image/*"), file3);
//                        MultipartBody.Part DataPribadis = MultipartBody.Part.createFormData("datapribadisiswa", file3.getName(), fileReqBody3);
//
//                        File file4 = new File(postFoto4);
//                        RequestBody fileReqBody4 = RequestBody.create(MediaType.parse("image/*"), file4);
//                        MultipartBody.Part PasFoto = MultipartBody.Part.createFormData("pasfoto3x4", file4.getName(), fileReqBody4);

                        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
                        Call<ResponseModel> FormPPDB = api.PPDB(
                                destiny.AUTH(Token),
                                //New
                                RequestBody.create(MediaType.parse("text/plain"),Agama.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),RT.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),RW.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Kecamatan.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),tanggalAyah),
                                RequestBody.create(MediaType.parse("text/plain"),PendidikanAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),PekerjaanAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),PenghasilanAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NIKAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),tanggalIbu),
                                RequestBody.create(MediaType.parse("text/plain"),PendidikanIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),PekerjaanIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),PenghasilanIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NIKIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),spKebutuhanKhusus.getSelectedItem().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),SekolahAsal.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),AnakKeberapa.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),BeratBadan.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),TinggiBadan.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),LingkarKepala.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),JumlahSaudara.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),JarakRumahKeSekolah.getText().toString()),
                                //New
                                RequestBody.create(MediaType.parse("text/plain"),tvid.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Alamat.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Email.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NomorUjian.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NISN.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NIK.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Password),
                                RequestBody.create(MediaType.parse("text/plain"),NamaLengkap.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NomorTelpon.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),k),
                                RequestBody.create(MediaType.parse("text/plain"),TempatLahir.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),tanggal),
                                RequestBody.create(MediaType.parse("text/plain"),NamaAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NamaIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Telpon.getText().toString()),
                                KartuKeluarga
                        );
                        FormPPDB.enqueue(new Callback<ResponseModel>() {
                            @Override
                            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                                try {
                                    pd.hide();
                                    Toast.makeText(FormulirPPDBActivity.this, response.body().getStatusMessage(), Toast.LENGTH_SHORT).show();
                                    if (response.body().getStatusCode().equals("000")){
                                        Intent intent = new Intent(FormulirPPDBActivity.this, HomeActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }else{
                                        Toast.makeText(FormulirPPDBActivity.this, "NISN Sudah Terdaftar", Toast.LENGTH_SHORT).show();
                                    }
                                }catch (Exception e){
                                    pd.hide();
                                    Toast.makeText(FormulirPPDBActivity.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseModel> call, Throwable t) {
                                pd.hide();
                                Toast.makeText(FormulirPPDBActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }else if (!Akta && KK && !DataPribadi && Foto){
                        //0101
                        final ProgressDialog pd = new ProgressDialog(FormulirPPDBActivity.this);
                        pd.setMessage("Sedang Memasukan Data Peserta");
                        pd.show();
                        pd.setCancelable(false);

                        File file2 = new File(postFoto2);
                        RequestBody fileReqBody2 = RequestBody.create(MediaType.parse("image/*"), file2);
                        MultipartBody.Part KartuKeluarga = MultipartBody.Part.createFormData("kartukeluargafotocopy", file2.getName(), fileReqBody2);

//                        File file3 = new File(postFoto3);
//                        RequestBody fileReqBody3 = RequestBody.create(MediaType.parse("image/*"), file3);
//                        MultipartBody.Part DataPribadis = MultipartBody.Part.createFormData("datapribadisiswa", file3.getName(), fileReqBody3);
//
                        File file4 = new File(postFoto4);
                        RequestBody fileReqBody4 = RequestBody.create(MediaType.parse("image/*"), file4);
                        MultipartBody.Part PasFoto = MultipartBody.Part.createFormData("pasfoto3x4", file4.getName(), fileReqBody4);

                        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
                        Call<ResponseModel> FormPPDB = api.PPDB(
                                destiny.AUTH(Token),
                                //New
                                RequestBody.create(MediaType.parse("text/plain"),Agama.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),RT.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),RW.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Kecamatan.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),tanggalAyah),
                                RequestBody.create(MediaType.parse("text/plain"),PendidikanAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),PekerjaanAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),PenghasilanAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NIKAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),tanggalIbu),
                                RequestBody.create(MediaType.parse("text/plain"),PendidikanIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),PekerjaanIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),PenghasilanIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NIKIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),spKebutuhanKhusus.getSelectedItem().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),SekolahAsal.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),AnakKeberapa.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),BeratBadan.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),TinggiBadan.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),LingkarKepala.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),JumlahSaudara.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),JarakRumahKeSekolah.getText().toString()),
                                //New
                                RequestBody.create(MediaType.parse("text/plain"),tvid.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Alamat.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Email.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NomorUjian.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NISN.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NIK.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Password),
                                RequestBody.create(MediaType.parse("text/plain"),NamaLengkap.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NomorTelpon.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),k),
                                RequestBody.create(MediaType.parse("text/plain"),TempatLahir.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),tanggal),
                                RequestBody.create(MediaType.parse("text/plain"),NamaAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NamaIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Telpon.getText().toString()),
                                KartuKeluarga,
                                PasFoto
                        );
                        FormPPDB.enqueue(new Callback<ResponseModel>() {
                            @Override
                            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                                try {
                                    pd.hide();
                                    Toast.makeText(FormulirPPDBActivity.this, response.body().getStatusMessage(), Toast.LENGTH_SHORT).show();
                                    if (response.body().getStatusCode().equals("000")){
                                        Intent intent = new Intent(FormulirPPDBActivity.this, HomeActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }else{
                                        Toast.makeText(FormulirPPDBActivity.this, "NISN Sudah Terdaftar", Toast.LENGTH_SHORT).show();
                                    }
                                }catch (Exception e){
                                    pd.hide();
                                    Toast.makeText(FormulirPPDBActivity.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseModel> call, Throwable t) {
                                pd.hide();
                                Toast.makeText(FormulirPPDBActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }else if (!Akta && KK && DataPribadi && !Foto){
                        //0110
                        final ProgressDialog pd = new ProgressDialog(FormulirPPDBActivity.this);
                        pd.setMessage("Sedang Memasukan Data Peserta");
                        pd.show();
                        pd.setCancelable(false);

                        File file2 = new File(postFoto2);
                        RequestBody fileReqBody2 = RequestBody.create(MediaType.parse("image/*"), file2);
                        MultipartBody.Part KartuKeluarga = MultipartBody.Part.createFormData("kartukeluargafotocopy", file2.getName(), fileReqBody2);

                        File file3 = new File(postFoto3);
                        RequestBody fileReqBody3 = RequestBody.create(MediaType.parse("image/*"), file3);
                        MultipartBody.Part DataPribadis = MultipartBody.Part.createFormData("datapribadisiswa", file3.getName(), fileReqBody3);
//
//                        File file4 = new File(postFoto4);
//                        RequestBody fileReqBody4 = RequestBody.create(MediaType.parse("image/*"), file4);
//                        MultipartBody.Part PasFoto = MultipartBody.Part.createFormData("pasfoto3x4", file4.getName(), fileReqBody4);

                        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
                        Call<ResponseModel> FormPPDB = api.PPDB(
                                destiny.AUTH(Token),
                                //New
                                RequestBody.create(MediaType.parse("text/plain"),Agama.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),RT.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),RW.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Kecamatan.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),tanggalAyah),
                                RequestBody.create(MediaType.parse("text/plain"),PendidikanAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),PekerjaanAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),PenghasilanAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NIKAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),tanggalIbu),
                                RequestBody.create(MediaType.parse("text/plain"),PendidikanIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),PekerjaanIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),PenghasilanIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NIKIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),spKebutuhanKhusus.getSelectedItem().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),SekolahAsal.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),AnakKeberapa.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),BeratBadan.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),TinggiBadan.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),LingkarKepala.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),JumlahSaudara.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),JarakRumahKeSekolah.getText().toString()),
                                //New
                                RequestBody.create(MediaType.parse("text/plain"),tvid.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Alamat.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Email.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NomorUjian.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NISN.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NIK.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Password),
                                RequestBody.create(MediaType.parse("text/plain"),NamaLengkap.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NomorTelpon.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),k),
                                RequestBody.create(MediaType.parse("text/plain"),TempatLahir.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),tanggal),
                                RequestBody.create(MediaType.parse("text/plain"),NamaAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NamaIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Telpon.getText().toString()),
                                KartuKeluarga,
                                DataPribadis
                        );
                        FormPPDB.enqueue(new Callback<ResponseModel>() {
                            @Override
                            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                                try {
                                    pd.hide();
                                    Toast.makeText(FormulirPPDBActivity.this, response.body().getStatusMessage(), Toast.LENGTH_SHORT).show();
                                    if (response.body().getStatusCode().equals("000")){
                                        Intent intent = new Intent(FormulirPPDBActivity.this, HomeActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }else{
                                        Toast.makeText(FormulirPPDBActivity.this, "NISN Sudah Terdaftar", Toast.LENGTH_SHORT).show();
                                    }
                                }catch (Exception e){
                                    pd.hide();
                                    Toast.makeText(FormulirPPDBActivity.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseModel> call, Throwable t) {
                                pd.hide();
                                Toast.makeText(FormulirPPDBActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }else if (!Akta && KK && DataPribadi && Foto){
                        //0111
                        final ProgressDialog pd = new ProgressDialog(FormulirPPDBActivity.this);
                        pd.setMessage("Sedang Memasukan Data Peserta");
                        pd.show();
                        pd.setCancelable(false);

                        File file2 = new File(postFoto2);
                        RequestBody fileReqBody2 = RequestBody.create(MediaType.parse("image/*"), file2);
                        MultipartBody.Part KartuKeluarga = MultipartBody.Part.createFormData("kartukeluargafotocopy", file2.getName(), fileReqBody2);

                        File file3 = new File(postFoto3);
                        RequestBody fileReqBody3 = RequestBody.create(MediaType.parse("image/*"), file3);
                        MultipartBody.Part DataPribadis = MultipartBody.Part.createFormData("datapribadisiswa", file3.getName(), fileReqBody3);
//
                        File file4 = new File(postFoto4);
                        RequestBody fileReqBody4 = RequestBody.create(MediaType.parse("image/*"), file4);
                        MultipartBody.Part PasFoto = MultipartBody.Part.createFormData("pasfoto3x4", file4.getName(), fileReqBody4);

                        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
                        Call<ResponseModel> FormPPDB = api.PPDB(
                                destiny.AUTH(Token),
                                //New
                                RequestBody.create(MediaType.parse("text/plain"),Agama.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),RT.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),RW.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Kecamatan.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),tanggalAyah),
                                RequestBody.create(MediaType.parse("text/plain"),PendidikanAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),PekerjaanAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),PenghasilanAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NIKAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),tanggalIbu),
                                RequestBody.create(MediaType.parse("text/plain"),PendidikanIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),PekerjaanIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),PenghasilanIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NIKIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),spKebutuhanKhusus.getSelectedItem().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),SekolahAsal.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),AnakKeberapa.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),BeratBadan.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),TinggiBadan.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),LingkarKepala.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),JumlahSaudara.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),JarakRumahKeSekolah.getText().toString()),
                                //New
                                RequestBody.create(MediaType.parse("text/plain"),tvid.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Alamat.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Email.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NomorUjian.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NISN.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NIK.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Password),
                                RequestBody.create(MediaType.parse("text/plain"),NamaLengkap.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NomorTelpon.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),k),
                                RequestBody.create(MediaType.parse("text/plain"),TempatLahir.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),tanggal),
                                RequestBody.create(MediaType.parse("text/plain"),NamaAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NamaIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Telpon.getText().toString()),
                                KartuKeluarga,
                                DataPribadis,
                                PasFoto
                        );
                        FormPPDB.enqueue(new Callback<ResponseModel>() {
                            @Override
                            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                                try {
                                    pd.hide();
                                    Toast.makeText(FormulirPPDBActivity.this, response.body().getStatusMessage(), Toast.LENGTH_SHORT).show();
                                    if (response.body().getStatusCode().equals("000")){
                                        Intent intent = new Intent(FormulirPPDBActivity.this, HomeActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }else{
                                        Toast.makeText(FormulirPPDBActivity.this, "NISN Sudah Terdaftar", Toast.LENGTH_SHORT).show();
                                    }
                                }catch (Exception e){
                                    pd.hide();
                                    Toast.makeText(FormulirPPDBActivity.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseModel> call, Throwable t) {
                                pd.hide();
                                Toast.makeText(FormulirPPDBActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }else if (Akta && !KK && !DataPribadi && !Foto){
                        //1000
                        final ProgressDialog pd = new ProgressDialog(FormulirPPDBActivity.this);
                        pd.setMessage("Sedang Memasukan Data Peserta");
                        pd.show();
                        pd.setCancelable(false);

                        File file1 = new File(postFoto1);
                        RequestBody fileReqBody1 = RequestBody.create(MediaType.parse("image/*"), file1);
                        MultipartBody.Part AktaKelahiran = MultipartBody.Part.createFormData("aktakelahiranfotocopy", file1.getName(), fileReqBody1);

//                        File file2 = new File(postFoto2);
//                        RequestBody fileReqBody2 = RequestBody.create(MediaType.parse("image/*"), file2);
//                        MultipartBody.Part KartuKeluarga = MultipartBody.Part.createFormData("kartukeluargafotocopy", file2.getName(), fileReqBody2);
//
//                        File file3 = new File(postFoto3);
//                        RequestBody fileReqBody3 = RequestBody.create(MediaType.parse("image/*"), file3);
//                        MultipartBody.Part DataPribadis = MultipartBody.Part.createFormData("datapribadisiswa", file3.getName(), fileReqBody3);
//
//                        File file4 = new File(postFoto4);
//                        RequestBody fileReqBody4 = RequestBody.create(MediaType.parse("image/*"), file4);
//                        MultipartBody.Part PasFoto = MultipartBody.Part.createFormData("pasfoto3x4", file4.getName(), fileReqBody4);

                        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
                        Call<ResponseModel> FormPPDB = api.PPDB(
                                destiny.AUTH(Token),
                                //New
                                RequestBody.create(MediaType.parse("text/plain"),Agama.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),RT.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),RW.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Kecamatan.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),tanggalAyah),
                                RequestBody.create(MediaType.parse("text/plain"),PendidikanAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),PekerjaanAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),PenghasilanAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NIKAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),tanggalIbu),
                                RequestBody.create(MediaType.parse("text/plain"),PendidikanIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),PekerjaanIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),PenghasilanIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NIKIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),spKebutuhanKhusus.getSelectedItem().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),SekolahAsal.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),AnakKeberapa.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),BeratBadan.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),TinggiBadan.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),LingkarKepala.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),JumlahSaudara.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),JarakRumahKeSekolah.getText().toString()),
                                //New
                                RequestBody.create(MediaType.parse("text/plain"),tvid.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Alamat.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Email.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NomorUjian.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NISN.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NIK.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Password),
                                RequestBody.create(MediaType.parse("text/plain"),NamaLengkap.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NomorTelpon.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),k),
                                RequestBody.create(MediaType.parse("text/plain"),TempatLahir.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),tanggal),
                                RequestBody.create(MediaType.parse("text/plain"),NamaAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NamaIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Telpon.getText().toString()),
                                AktaKelahiran
                        );
                        FormPPDB.enqueue(new Callback<ResponseModel>() {
                            @Override
                            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                                try {
                                    pd.hide();
                                    Toast.makeText(FormulirPPDBActivity.this, response.body().getStatusMessage(), Toast.LENGTH_SHORT).show();
                                    if (response.body().getStatusCode().equals("000")){
                                        Intent intent = new Intent(FormulirPPDBActivity.this, HomeActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }else{
                                        Toast.makeText(FormulirPPDBActivity.this, "NISN Sudah Terdaftar", Toast.LENGTH_SHORT).show();
                                    }
                                }catch (Exception e){
                                    pd.hide();
                                    Toast.makeText(FormulirPPDBActivity.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseModel> call, Throwable t) {
                                pd.hide();
                                Toast.makeText(FormulirPPDBActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }else if (Akta && !KK && !DataPribadi && Foto){
                        //1001
                        final ProgressDialog pd = new ProgressDialog(FormulirPPDBActivity.this);
                        pd.setMessage("Sedang Memasukan Data Peserta");
                        pd.show();
                        pd.setCancelable(false);

                        File file1 = new File(postFoto1);
                        RequestBody fileReqBody1 = RequestBody.create(MediaType.parse("image/*"), file1);
                        MultipartBody.Part AktaKelahiran = MultipartBody.Part.createFormData("aktakelahiranfotocopy", file1.getName(), fileReqBody1);

//                        File file2 = new File(postFoto2);
//                        RequestBody fileReqBody2 = RequestBody.create(MediaType.parse("image/*"), file2);
//                        MultipartBody.Part KartuKeluarga = MultipartBody.Part.createFormData("kartukeluargafotocopy", file2.getName(), fileReqBody2);
//
//                        File file3 = new File(postFoto3);
//                        RequestBody fileReqBody3 = RequestBody.create(MediaType.parse("image/*"), file3);
//                        MultipartBody.Part DataPribadis = MultipartBody.Part.createFormData("datapribadisiswa", file3.getName(), fileReqBody3);
//
                        File file4 = new File(postFoto4);
                        RequestBody fileReqBody4 = RequestBody.create(MediaType.parse("image/*"), file4);
                        MultipartBody.Part PasFoto = MultipartBody.Part.createFormData("pasfoto3x4", file4.getName(), fileReqBody4);

                        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
                        Call<ResponseModel> FormPPDB = api.PPDB(
                                destiny.AUTH(Token),
                                //New
                                RequestBody.create(MediaType.parse("text/plain"),Agama.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),RT.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),RW.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Kecamatan.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),tanggalAyah),
                                RequestBody.create(MediaType.parse("text/plain"),PendidikanAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),PekerjaanAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),PenghasilanAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NIKAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),tanggalIbu),
                                RequestBody.create(MediaType.parse("text/plain"),PendidikanIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),PekerjaanIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),PenghasilanIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NIKIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),spKebutuhanKhusus.getSelectedItem().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),SekolahAsal.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),AnakKeberapa.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),BeratBadan.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),TinggiBadan.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),LingkarKepala.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),JumlahSaudara.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),JarakRumahKeSekolah.getText().toString()),
                                //New
                                RequestBody.create(MediaType.parse("text/plain"),tvid.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Alamat.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Email.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NomorUjian.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NISN.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NIK.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Password),
                                RequestBody.create(MediaType.parse("text/plain"),NamaLengkap.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NomorTelpon.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),k),
                                RequestBody.create(MediaType.parse("text/plain"),TempatLahir.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),tanggal),
                                RequestBody.create(MediaType.parse("text/plain"),NamaAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NamaIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Telpon.getText().toString()),
                                AktaKelahiran,
                                PasFoto
                        );
                        FormPPDB.enqueue(new Callback<ResponseModel>() {
                            @Override
                            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                                try {
                                    pd.hide();
                                    Toast.makeText(FormulirPPDBActivity.this, response.body().getStatusMessage(), Toast.LENGTH_SHORT).show();
                                    if (response.body().getStatusCode().equals("000")){
                                        Intent intent = new Intent(FormulirPPDBActivity.this, HomeActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }else{
                                        Toast.makeText(FormulirPPDBActivity.this, "NISN Sudah Terdaftar", Toast.LENGTH_SHORT).show();
                                    }
                                }catch (Exception e){
                                    pd.hide();
                                    Toast.makeText(FormulirPPDBActivity.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseModel> call, Throwable t) {
                                pd.hide();
                                Toast.makeText(FormulirPPDBActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }else if (Akta && !KK && DataPribadi && !Foto){
                        //1010
                        final ProgressDialog pd = new ProgressDialog(FormulirPPDBActivity.this);
                        pd.setMessage("Sedang Memasukan Data Peserta");
                        pd.show();
                        pd.setCancelable(false);

                        File file1 = new File(postFoto1);
                        RequestBody fileReqBody1 = RequestBody.create(MediaType.parse("image/*"), file1);
                        MultipartBody.Part AktaKelahiran = MultipartBody.Part.createFormData("aktakelahiranfotocopy", file1.getName(), fileReqBody1);

//                        File file2 = new File(postFoto2);
//                        RequestBody fileReqBody2 = RequestBody.create(MediaType.parse("image/*"), file2);
//                        MultipartBody.Part KartuKeluarga = MultipartBody.Part.createFormData("kartukeluargafotocopy", file2.getName(), fileReqBody2);
//
                        File file3 = new File(postFoto3);
                        RequestBody fileReqBody3 = RequestBody.create(MediaType.parse("image/*"), file3);
                        MultipartBody.Part DataPribadis = MultipartBody.Part.createFormData("datapribadisiswa", file3.getName(), fileReqBody3);
//
//                        File file4 = new File(postFoto4);
//                        RequestBody fileReqBody4 = RequestBody.create(MediaType.parse("image/*"), file4);
//                        MultipartBody.Part PasFoto = MultipartBody.Part.createFormData("pasfoto3x4", file4.getName(), fileReqBody4);

                        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
                        Call<ResponseModel> FormPPDB = api.PPDB(
                                destiny.AUTH(Token),
                                //New
                                RequestBody.create(MediaType.parse("text/plain"),Agama.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),RT.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),RW.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Kecamatan.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),tanggalAyah),
                                RequestBody.create(MediaType.parse("text/plain"),PendidikanAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),PekerjaanAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),PenghasilanAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NIKAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),tanggalIbu),
                                RequestBody.create(MediaType.parse("text/plain"),PendidikanIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),PekerjaanIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),PenghasilanIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NIKIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),spKebutuhanKhusus.getSelectedItem().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),SekolahAsal.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),AnakKeberapa.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),BeratBadan.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),TinggiBadan.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),LingkarKepala.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),JumlahSaudara.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),JarakRumahKeSekolah.getText().toString()),
                                //New
                                RequestBody.create(MediaType.parse("text/plain"),tvid.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Alamat.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Email.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NomorUjian.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NISN.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NIK.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Password),
                                RequestBody.create(MediaType.parse("text/plain"),NamaLengkap.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NomorTelpon.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),k),
                                RequestBody.create(MediaType.parse("text/plain"),TempatLahir.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),tanggal),
                                RequestBody.create(MediaType.parse("text/plain"),NamaAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NamaIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Telpon.getText().toString()),
                                AktaKelahiran,
                                DataPribadis
                        );
                        FormPPDB.enqueue(new Callback<ResponseModel>() {
                            @Override
                            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                                try {
                                    pd.hide();
                                    Toast.makeText(FormulirPPDBActivity.this, response.body().getStatusMessage(), Toast.LENGTH_SHORT).show();
                                    if (response.body().getStatusCode().equals("000")){
                                        Intent intent = new Intent(FormulirPPDBActivity.this, HomeActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }else{
                                        Toast.makeText(FormulirPPDBActivity.this, "NISN Sudah Terdaftar", Toast.LENGTH_SHORT).show();
                                    }
                                }catch (Exception e){
                                    pd.hide();
                                    Toast.makeText(FormulirPPDBActivity.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseModel> call, Throwable t) {
                                pd.hide();
                                Toast.makeText(FormulirPPDBActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }else if (Akta && !KK && DataPribadi && Foto){
                        //1011
                        final ProgressDialog pd = new ProgressDialog(FormulirPPDBActivity.this);
                        pd.setMessage("Sedang Memasukan Data Peserta");
                        pd.show();
                        pd.setCancelable(false);

                        File file1 = new File(postFoto1);
                        RequestBody fileReqBody1 = RequestBody.create(MediaType.parse("image/*"), file1);
                        MultipartBody.Part AktaKelahiran = MultipartBody.Part.createFormData("aktakelahiranfotocopy", file1.getName(), fileReqBody1);

//                        File file2 = new File(postFoto2);
//                        RequestBody fileReqBody2 = RequestBody.create(MediaType.parse("image/*"), file2);
//                        MultipartBody.Part KartuKeluarga = MultipartBody.Part.createFormData("kartukeluargafotocopy", file2.getName(), fileReqBody2);
//
                        File file3 = new File(postFoto3);
                        RequestBody fileReqBody3 = RequestBody.create(MediaType.parse("image/*"), file3);
                        MultipartBody.Part DataPribadis = MultipartBody.Part.createFormData("datapribadisiswa", file3.getName(), fileReqBody3);

                        File file4 = new File(postFoto4);
                        RequestBody fileReqBody4 = RequestBody.create(MediaType.parse("image/*"), file4);
                        MultipartBody.Part PasFoto = MultipartBody.Part.createFormData("pasfoto3x4", file4.getName(), fileReqBody4);

                        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
                        Call<ResponseModel> FormPPDB = api.PPDB(
                                destiny.AUTH(Token),
                                //New
                                RequestBody.create(MediaType.parse("text/plain"),Agama.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),RT.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),RW.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Kecamatan.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),tanggalAyah),
                                RequestBody.create(MediaType.parse("text/plain"),PendidikanAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),PekerjaanAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),PenghasilanAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NIKAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),tanggalIbu),
                                RequestBody.create(MediaType.parse("text/plain"),PendidikanIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),PekerjaanIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),PenghasilanIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NIKIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),spKebutuhanKhusus.getSelectedItem().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),SekolahAsal.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),AnakKeberapa.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),BeratBadan.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),TinggiBadan.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),LingkarKepala.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),JumlahSaudara.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),JarakRumahKeSekolah.getText().toString()),
                                //New
                                RequestBody.create(MediaType.parse("text/plain"),tvid.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Alamat.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Email.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NomorUjian.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NISN.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NIK.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Password),
                                RequestBody.create(MediaType.parse("text/plain"),NamaLengkap.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NomorTelpon.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),k),
                                RequestBody.create(MediaType.parse("text/plain"),TempatLahir.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),tanggal),
                                RequestBody.create(MediaType.parse("text/plain"),NamaAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NamaIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Telpon.getText().toString()),
                                AktaKelahiran,
                                DataPribadis,
                                PasFoto
                        );
                        FormPPDB.enqueue(new Callback<ResponseModel>() {
                            @Override
                            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                                try {
                                    pd.hide();
                                    Toast.makeText(FormulirPPDBActivity.this, response.body().getStatusMessage(), Toast.LENGTH_SHORT).show();
                                    if (response.body().getStatusCode().equals("000")){
                                        Intent intent = new Intent(FormulirPPDBActivity.this, HomeActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }else{
                                        Toast.makeText(FormulirPPDBActivity.this, "NISN Sudah Terdaftar", Toast.LENGTH_SHORT).show();
                                    }
                                }catch (Exception e){
                                    pd.hide();
                                    Toast.makeText(FormulirPPDBActivity.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseModel> call, Throwable t) {
                                pd.hide();
                                Toast.makeText(FormulirPPDBActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }else if (Akta && KK && !DataPribadi && !Foto){
                        //1100
                        final ProgressDialog pd = new ProgressDialog(FormulirPPDBActivity.this);
                        pd.setMessage("Sedang Memasukan Data Peserta");
                        pd.show();
                        pd.setCancelable(false);

                        File file1 = new File(postFoto1);
                        RequestBody fileReqBody1 = RequestBody.create(MediaType.parse("image/*"), file1);
                        MultipartBody.Part AktaKelahiran = MultipartBody.Part.createFormData("aktakelahiranfotocopy", file1.getName(), fileReqBody1);

                        File file2 = new File(postFoto2);
                        RequestBody fileReqBody2 = RequestBody.create(MediaType.parse("image/*"), file2);
                        MultipartBody.Part KartuKeluarga = MultipartBody.Part.createFormData("kartukeluargafotocopy", file2.getName(), fileReqBody2);

//                        File file3 = new File(postFoto3);
//                        RequestBody fileReqBody3 = RequestBody.create(MediaType.parse("image/*"), file3);
//                        MultipartBody.Part DataPribadis = MultipartBody.Part.createFormData("datapribadisiswa", file3.getName(), fileReqBody3);
//
//                        File file4 = new File(postFoto4);
//                        RequestBody fileReqBody4 = RequestBody.create(MediaType.parse("image/*"), file4);
//                        MultipartBody.Part PasFoto = MultipartBody.Part.createFormData("pasfoto3x4", file4.getName(), fileReqBody4);

                        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
                        Call<ResponseModel> FormPPDB = api.PPDB(
                                destiny.AUTH(Token),
                                //New
                                RequestBody.create(MediaType.parse("text/plain"),Agama.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),RT.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),RW.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Kecamatan.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),tanggalAyah),
                                RequestBody.create(MediaType.parse("text/plain"),PendidikanAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),PekerjaanAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),PenghasilanAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NIKAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),tanggalIbu),
                                RequestBody.create(MediaType.parse("text/plain"),PendidikanIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),PekerjaanIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),PenghasilanIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NIKIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),spKebutuhanKhusus.getSelectedItem().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),SekolahAsal.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),AnakKeberapa.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),BeratBadan.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),TinggiBadan.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),LingkarKepala.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),JumlahSaudara.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),JarakRumahKeSekolah.getText().toString()),
                                //New
                                RequestBody.create(MediaType.parse("text/plain"),tvid.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Alamat.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Email.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NomorUjian.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NISN.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NIK.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Password),
                                RequestBody.create(MediaType.parse("text/plain"),NamaLengkap.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NomorTelpon.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),k),
                                RequestBody.create(MediaType.parse("text/plain"),TempatLahir.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),tanggal),
                                RequestBody.create(MediaType.parse("text/plain"),NamaAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NamaIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Telpon.getText().toString()),
                                AktaKelahiran,
                                KartuKeluarga
                        );
                        FormPPDB.enqueue(new Callback<ResponseModel>() {
                            @Override
                            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                                try {
                                    pd.hide();
                                    Toast.makeText(FormulirPPDBActivity.this, response.body().getStatusMessage(), Toast.LENGTH_SHORT).show();
                                    if (response.body().getStatusCode().equals("000")){
                                        Intent intent = new Intent(FormulirPPDBActivity.this, HomeActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }else{
                                        Toast.makeText(FormulirPPDBActivity.this, "NISN Sudah Terdaftar", Toast.LENGTH_SHORT).show();
                                    }
                                }catch (Exception e){
                                    pd.hide();
                                    Toast.makeText(FormulirPPDBActivity.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseModel> call, Throwable t) {
                                pd.hide();
                                Toast.makeText(FormulirPPDBActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }else if (Akta && KK && !DataPribadi && Foto){
                        //1101
                        final ProgressDialog pd = new ProgressDialog(FormulirPPDBActivity.this);
                        pd.setMessage("Sedang Memasukan Data Peserta");
                        pd.show();
                        pd.setCancelable(false);

                        File file1 = new File(postFoto1);
                        RequestBody fileReqBody1 = RequestBody.create(MediaType.parse("image/*"), file1);
                        MultipartBody.Part AktaKelahiran = MultipartBody.Part.createFormData("aktakelahiranfotocopy", file1.getName(), fileReqBody1);

                        File file2 = new File(postFoto2);
                        RequestBody fileReqBody2 = RequestBody.create(MediaType.parse("image/*"), file2);
                        MultipartBody.Part KartuKeluarga = MultipartBody.Part.createFormData("kartukeluargafotocopy", file2.getName(), fileReqBody2);

//                        File file3 = new File(postFoto3);
//                        RequestBody fileReqBody3 = RequestBody.create(MediaType.parse("image/*"), file3);
//                        MultipartBody.Part DataPribadis = MultipartBody.Part.createFormData("datapribadisiswa", file3.getName(), fileReqBody3);
//
                        File file4 = new File(postFoto4);
                        RequestBody fileReqBody4 = RequestBody.create(MediaType.parse("image/*"), file4);
                        MultipartBody.Part PasFoto = MultipartBody.Part.createFormData("pasfoto3x4", file4.getName(), fileReqBody4);

                        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
                        Call<ResponseModel> FormPPDB = api.PPDB(
                                destiny.AUTH(Token),
                                //New
                                RequestBody.create(MediaType.parse("text/plain"),Agama.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),RT.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),RW.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Kecamatan.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),tanggalAyah),
                                RequestBody.create(MediaType.parse("text/plain"),PendidikanAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),PekerjaanAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),PenghasilanAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NIKAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),tanggalIbu),
                                RequestBody.create(MediaType.parse("text/plain"),PendidikanIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),PekerjaanIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),PenghasilanIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NIKIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),spKebutuhanKhusus.getSelectedItem().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),SekolahAsal.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),AnakKeberapa.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),BeratBadan.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),TinggiBadan.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),LingkarKepala.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),JumlahSaudara.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),JarakRumahKeSekolah.getText().toString()),
                                //New
                                RequestBody.create(MediaType.parse("text/plain"),tvid.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Alamat.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Email.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NomorUjian.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NISN.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NIK.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Password),
                                RequestBody.create(MediaType.parse("text/plain"),NamaLengkap.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NomorTelpon.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),k),
                                RequestBody.create(MediaType.parse("text/plain"),TempatLahir.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),tanggal),
                                RequestBody.create(MediaType.parse("text/plain"),NamaAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NamaIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Telpon.getText().toString()),
                                AktaKelahiran,
                                KartuKeluarga,
                                PasFoto
                        );
                        FormPPDB.enqueue(new Callback<ResponseModel>() {
                            @Override
                            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                                try {
                                    pd.hide();
                                    Toast.makeText(FormulirPPDBActivity.this, response.body().getStatusMessage(), Toast.LENGTH_SHORT).show();
                                    if (response.body().getStatusCode().equals("000")){
                                        Intent intent = new Intent(FormulirPPDBActivity.this, HomeActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }else{
                                        Toast.makeText(FormulirPPDBActivity.this, "NISN Sudah Terdaftar", Toast.LENGTH_SHORT).show();
                                    }
                                }catch (Exception e){
                                    pd.hide();
                                    Toast.makeText(FormulirPPDBActivity.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseModel> call, Throwable t) {
                                pd.hide();
                                Toast.makeText(FormulirPPDBActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }else if (Akta && KK && DataPribadi && !Foto){
                        //1110
                        final ProgressDialog pd = new ProgressDialog(FormulirPPDBActivity.this);
                        pd.setMessage("Sedang Memasukan Data Peserta");
                        pd.show();
                        pd.setCancelable(false);

                        File file1 = new File(postFoto1);
                        RequestBody fileReqBody1 = RequestBody.create(MediaType.parse("image/*"), file1);
                        MultipartBody.Part AktaKelahiran = MultipartBody.Part.createFormData("aktakelahiranfotocopy", file1.getName(), fileReqBody1);

                        File file2 = new File(postFoto2);
                        RequestBody fileReqBody2 = RequestBody.create(MediaType.parse("image/*"), file2);
                        MultipartBody.Part KartuKeluarga = MultipartBody.Part.createFormData("kartukeluargafotocopy", file2.getName(), fileReqBody2);

                        File file3 = new File(postFoto3);
                        RequestBody fileReqBody3 = RequestBody.create(MediaType.parse("image/*"), file3);
                        MultipartBody.Part DataPribadis = MultipartBody.Part.createFormData("datapribadisiswa", file3.getName(), fileReqBody3);

//                        File file4 = new File(postFoto4);
//                        RequestBody fileReqBody4 = RequestBody.create(MediaType.parse("image/*"), file4);
//                        MultipartBody.Part PasFoto = MultipartBody.Part.createFormData("pasfoto3x4", file4.getName(), fileReqBody4);

                        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
                        Call<ResponseModel> FormPPDB = api.PPDB(
                                destiny.AUTH(Token),
                                //New
                                RequestBody.create(MediaType.parse("text/plain"),Agama.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),RT.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),RW.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Kecamatan.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),tanggalAyah),
                                RequestBody.create(MediaType.parse("text/plain"),PendidikanAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),PekerjaanAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),PenghasilanAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NIKAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),tanggalIbu),
                                RequestBody.create(MediaType.parse("text/plain"),PendidikanIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),PekerjaanIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),PenghasilanIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NIKIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),spKebutuhanKhusus.getSelectedItem().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),SekolahAsal.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),AnakKeberapa.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),BeratBadan.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),TinggiBadan.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),LingkarKepala.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),JumlahSaudara.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),JarakRumahKeSekolah.getText().toString()),
                                //New
                                RequestBody.create(MediaType.parse("text/plain"),tvid.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Alamat.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Email.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NomorUjian.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NISN.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NIK.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Password),
                                RequestBody.create(MediaType.parse("text/plain"),NamaLengkap.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NomorTelpon.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),k),
                                RequestBody.create(MediaType.parse("text/plain"),TempatLahir.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),tanggal),
                                RequestBody.create(MediaType.parse("text/plain"),NamaAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NamaIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Telpon.getText().toString()),
                                AktaKelahiran,
                                KartuKeluarga,
                                DataPribadis
                        );
                        FormPPDB.enqueue(new Callback<ResponseModel>() {
                            @Override
                            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                                try {
                                    pd.hide();
                                    Toast.makeText(FormulirPPDBActivity.this, response.body().getStatusMessage(), Toast.LENGTH_SHORT).show();
                                    if (response.body().getStatusCode().equals("000")){
                                        Intent intent = new Intent(FormulirPPDBActivity.this, HomeActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }else{
                                        Toast.makeText(FormulirPPDBActivity.this, "NISN Sudah Terdaftar", Toast.LENGTH_SHORT).show();
                                    }
                                }catch (Exception e){
                                    pd.hide();
                                    Toast.makeText(FormulirPPDBActivity.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseModel> call, Throwable t) {
                                pd.hide();
                                Toast.makeText(FormulirPPDBActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }else if (Akta && KK && DataPribadi && Foto){
                        //1111
                        final ProgressDialog pd = new ProgressDialog(FormulirPPDBActivity.this);
                        pd.setMessage("Sedang Memasukan Data Peserta");
                        pd.show();
                        pd.setCancelable(false);

                        File file1 = new File(postFoto1);
                        RequestBody fileReqBody1 = RequestBody.create(MediaType.parse("image/*"), file1);
                        MultipartBody.Part AktaKelahiran = MultipartBody.Part.createFormData("aktakelahiranfotocopy", file1.getName(), fileReqBody1);

                        File file2 = new File(postFoto2);
                        RequestBody fileReqBody2 = RequestBody.create(MediaType.parse("image/*"), file2);
                        MultipartBody.Part KartuKeluarga = MultipartBody.Part.createFormData("kartukeluargafotocopy", file2.getName(), fileReqBody2);

                        File file3 = new File(postFoto3);
                        RequestBody fileReqBody3 = RequestBody.create(MediaType.parse("image/*"), file3);
                        MultipartBody.Part DataPribadis = MultipartBody.Part.createFormData("datapribadisiswa", file3.getName(), fileReqBody3);

                        File file4 = new File(postFoto4);
                        RequestBody fileReqBody4 = RequestBody.create(MediaType.parse("image/*"), file4);
                        MultipartBody.Part PasFoto = MultipartBody.Part.createFormData("pasfoto3x4", file4.getName(), fileReqBody4);

                        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
                        Call<ResponseModel> FormPPDB = api.PPDB(
                                destiny.AUTH(Token),
                                //New
                                RequestBody.create(MediaType.parse("text/plain"),Agama.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),RT.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),RW.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Kecamatan.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),tanggalAyah),
                                RequestBody.create(MediaType.parse("text/plain"),PendidikanAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),PekerjaanAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),PenghasilanAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NIKAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),tanggalIbu),
                                RequestBody.create(MediaType.parse("text/plain"),PendidikanIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),PekerjaanIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),PenghasilanIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NIKIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),spKebutuhanKhusus.getSelectedItem().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),SekolahAsal.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),AnakKeberapa.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),BeratBadan.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),TinggiBadan.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),LingkarKepala.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),JumlahSaudara.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),JarakRumahKeSekolah.getText().toString()),
                                //New
                                RequestBody.create(MediaType.parse("text/plain"),tvid.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Alamat.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Email.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NomorUjian.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NISN.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NIK.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Password),
                                RequestBody.create(MediaType.parse("text/plain"),NamaLengkap.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NomorTelpon.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),k),
                                RequestBody.create(MediaType.parse("text/plain"),TempatLahir.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),tanggal),
                                RequestBody.create(MediaType.parse("text/plain"),NamaAyah.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),NamaIbu.getText().toString()),
                                RequestBody.create(MediaType.parse("text/plain"),Telpon.getText().toString()),
                                AktaKelahiran,
                                KartuKeluarga,
                                DataPribadis,
                                PasFoto
                        );
                        FormPPDB.enqueue(new Callback<ResponseModel>() {
                            @Override
                            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                                try {
                                    pd.hide();
                                    Toast.makeText(FormulirPPDBActivity.this, response.body().getStatusMessage(), Toast.LENGTH_SHORT).show();
                                    if (response.body().getStatusCode().equals("000")){
                                        Intent intent = new Intent(FormulirPPDBActivity.this, HomeActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }else{
                                        Toast.makeText(FormulirPPDBActivity.this, "NISN Sudah Terdaftar", Toast.LENGTH_SHORT).show();
                                    }
                                }catch (Exception e){
                                    pd.hide();
                                    Toast.makeText(FormulirPPDBActivity.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseModel> call, Throwable t) {
                                pd.hide();
                                Toast.makeText(FormulirPPDBActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }else{
                    Toast.makeText(FormulirPPDBActivity.this, Checker(), Toast.LENGTH_SHORT).show();
                }
            }
        });
//        Back = findViewById(R.id.relativeBack);
//        Back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                destiny.Back(FormulirPPDBActivity.this);
//            }
//        });
    }
    private void DataTahunAjaran(){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        final Call<ResponseModel> Data =api.TahunAjaran(destiny.AUTH(Token));
        Data.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.body().getStatusCode().equals("000")){
                    try {
                        mItems=response.body().getData();
                        SpinnerTahunAjaran adapter = new SpinnerTahunAjaran(FormulirPPDBActivity.this,mItems);
                        spAjaran.setAdapter(adapter);
                    }catch (Exception e){

                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(FormulirPPDBActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private String Checker(){
        String OK = "OK";
        if (NomorUjian.getText().toString().isEmpty()){
            OK = "Nomor Ujian Tidak Boleh Kosong";
        }else if (NISN.getText().toString().isEmpty()){
            OK = "NISN Tidak Boleh Kosong";
        }else if (NIK.getText().toString().isEmpty()){
            OK = "NIK Tidak Boleh Kosong";
        }else if (NamaLengkap.getText().toString().isEmpty()){
            OK = "Nama Lengkap Tidak Boleh Kosong";
        }else if (NomorTelpon.getText().toString().isEmpty()){
            OK = "Nomor Telpon Tidak Boleh Kosong";
        }else if (TempatLahir.getText().toString().isEmpty()){
            OK = "Tempat Lahir Tidak Boleh Kosong";
        }else if (NamaAyah.getText().toString().isEmpty()){
            OK = "Nama Ayah Kandung Tidak Boleh Kosong";
        }else if (NamaIbu.getText().toString().isEmpty()){
            OK = "Nama Ibu Kandung Tidak Boleh Kosong";
        }else if (Telpon.getText().toString().isEmpty()){
            OK = "Nomor Telpon orang Tua Tidak Boleh Kosong";
        }
        return OK;
    }

    @Override
    public void onBackPressed() {
        destiny.Back(FormulirPPDBActivity.this);
    }

    private void LogicKelamin(){
        Laki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Kelamin=true;
            }
        });
        Perempuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Kelamin=false;
            }
        });
    }
    private void LogicAkta(){
        AdaAkta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Akta = true;
                uploadAkta.setVisibility(View.VISIBLE);
                if (!tvGambar1.getText().toString().equals("File Kosong Harap Pilih Gambar")){
                    lGambar1.setVisibility(View.VISIBLE);
                }else{
                    lGambar1.setVisibility(View.GONE);
                }
            }
        });
        TidakAdaAkta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Akta = false;
                uploadAkta.setVisibility(View.GONE);
                lGambar1.setVisibility(View.GONE);
            }
        });
    }
    private void LogicKK(){
        AdaKK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KK = true;
                uploadKK.setVisibility(View.VISIBLE);
                if (!tvGambar2.getText().toString().equals("File Kosong Harap Pilih Gambar")){
                    lGambar2.setVisibility(View.VISIBLE);
                }else{
                    lGambar2.setVisibility(View.GONE);
                }
            }
        });
        TidakAdaKK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KK = false;
                uploadKK.setVisibility(View.GONE);
                lGambar2.setVisibility(View.GONE);
            }
        });
    }
    private void LogicDataPribadi(){
        AdaDataPribadi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataPribadi = true;
                uploadDataPribadi.setVisibility(View.VISIBLE);
                if (!tvGambar3.getText().toString().equals("File Kosong Harap Pilih Gambar")){
                    lGambar3.setVisibility(View.VISIBLE);
                }else{
                    lGambar3.setVisibility(View.GONE);
                }
            }
        });
        TidakAdaDataPribadi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataPribadi = false;
                uploadDataPribadi.setVisibility(View.GONE);
                lGambar3.setVisibility(View.GONE);
            }
        });
    }
    private void LogicFoto(){
        AdaFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Foto = true;
                uploadFoto.setVisibility(View.VISIBLE);
                if (!tvGambar4.getText().toString().equals("File Kosong Harap Pilih Gambar")){
                    lGambar4.setVisibility(View.VISIBLE);
                }else{
                    lGambar4.setVisibility(View.GONE);
                }
            }
        });
        TidakAdaFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Foto = false;
                uploadFoto.setVisibility(View.GONE);
                lGambar4.setVisibility(View.GONE);
            }
        });
    }
    private void showDatePicker(){
        DatePickerDialog dialog = new DatePickerDialog(this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String FajarKontol = "01";
        String UcupKontol = "01";
        int m = month+1;
        if (m<10){
            FajarKontol="0"+String.valueOf(month+1);
        }else{
            FajarKontol=String.valueOf(month+1);
        }
        if (dayOfMonth<10){
            UcupKontol="0"+String.valueOf(dayOfMonth);
        }else{
            UcupKontol=String.valueOf(dayOfMonth);
        }
        String date = year+"-"+FajarKontol+"-"+UcupKontol;
        if (Tanggals.equals("Anak")){
            TanggalLahir.setText(destiny.DateChanges(String.valueOf(year),FajarKontol,UcupKontol));
            tanggal = date;
        }else if (Tanggals.equals("Ibu")){
            TanggalLahirIbu.setText(destiny.DateChanges(String.valueOf(year),FajarKontol,UcupKontol));
            tanggalIbu = date;
        }else if(Tanggals.equals("Ayah")){
            TanggalLahirAyah.setText(destiny.DateChanges(String.valueOf(year),FajarKontol,UcupKontol));
            tanggalAyah = date;
        }

    }
    //Dellaroy Logic
    private void captureImage() {
        if (Build.VERSION.SDK_INT > 21) { //use this if Lollipop_Mr1 (API 22) or above
            Intent callCameraApplicationIntent = new Intent();
            callCameraApplicationIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);

            // We give some instruction to the intent to save the image
            File photoFile = null;

            try {
                // If the createImageFile will be successful, the photo file will have the address of the file
                photoFile = createImageFile();
                // Here we call the function that will try to catch the exception made by the throw function
            } catch (IOException e) {
                Logger.getAnonymousLogger().info("Exception error in generating the file");
                e.printStackTrace();
            }
            // Here we add an extra file to the intent to put the address on to. For this purpose we use the FileProvider, declared in the AndroidManifest.
            Uri outputUri = FileProvider.getUriForFile(
                    this,
                    BuildConfig.APPLICATION_ID + ".provider",
                    photoFile);
            callCameraApplicationIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);

            // The following is a new line with a trying attempt
            callCameraApplicationIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);

            Logger.getAnonymousLogger().info("Calling the camera App by intent");

            // The following strings calls the camera app and wait for his file in return.
            startActivityForResult(callCameraApplicationIntent, CAMERA_PIC_REQUEST);
        } else {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);

            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

            // start the image capture Intent
            startActivityForResult(intent, CAMERA_PIC_REQUEST);
        }


    }

    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    private static File getOutputMediaFile(int type) {

        // External sdcard location
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                IMAGE_DIRECTORY_NAME);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("TEST", "Oops! Failed create "
                        + IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG_" + ".jpg");
        }  else {
            return null;
        }

        return mediaFile;
    }
    File createImageFile() throws IOException {
        Logger.getAnonymousLogger().info("Generating the image - method started");

        // Here we create a "non-collision file name", alternatively said, "an unique filename" using the "timeStamp" functionality
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmSS").format(new Date());
        String imageFileName = "IMAGE_" + timeStamp;
        // Here we specify the environment location and the exact path where we want to save the so-created file
        File storageDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES + "/photo_saving_app");
        Logger.getAnonymousLogger().info("Storage directory set");

        // Then we create the storage directory if does not exists
        if (!storageDirectory.exists()) storageDirectory.mkdir();

        // Here we create the file using a prefix, a suffix and a directory
        File image = new File(storageDirectory, imageFileName + ".jpg");
        // File image = File.createTempFile(imageFileName, ".jpg", storageDirectory);

        // Here the location is saved into the string mImageFileLocation
        Logger.getAnonymousLogger().info("File name and path set");

        mImageFileLocation = image.getAbsolutePath();
        // fileUri = Uri.parse(mImageFileLocation);
        // The file is returned to the previous intent across the camera application
        return image;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_TAKE_PHOTO || requestCode == REQUEST_PICK_PHOTO) {
            if (data != null) {
                // Get the Image from data
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                assert cursor != null;
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                mediaPath = cursor.getString(columnIndex);

                // Set the Image in ImageView for Previewing the Media

//                    imageView.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
                cursor.close();
                if(Gambar1) {
                    postFoto1 = mediaPath;
                    String filename = postFoto1.substring(postFoto1.lastIndexOf("/") + 1);
                    lGambar1.setVisibility(View.VISIBLE);
                    ivGambar1.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
                    tvGambar1.setText(filename);
                    Gambar1 = false;
                    Toast.makeText(this, filename, Toast.LENGTH_SHORT).show();
                }else if(Gambar2){
                    postFoto2 = mediaPath;
                    String filename = postFoto2.substring(postFoto2.lastIndexOf("/") + 1);
                    lGambar2.setVisibility(View.VISIBLE);
                    ivGambar2.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
                    tvGambar2.setText(filename);
                    Gambar2 = false;
                }else if(Gambar3){
                    postFoto3 = mediaPath;
                    String filename = postFoto3.substring(postFoto3.lastIndexOf("/") + 1);
                    lGambar3.setVisibility(View.VISIBLE);
                    ivGambar3.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
                    tvGambar3.setText(filename);
                    Gambar3 = false;
                }else if(Gambar4){
                    postFoto4 = mediaPath;
                    String filename = postFoto4.substring(postFoto4.lastIndexOf("/") + 1);
                    lGambar4.setVisibility(View.VISIBLE);
                    ivGambar4.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
                    tvGambar4.setText(filename);
                    Gambar4 = false;
                }
            }
        }else if (requestCode == CAMERA_PIC_REQUEST){
            if (Gambar1){
                if (Build.VERSION.SDK_INT > 21) {
                    Glide.with(this).load(mImageFileLocation).into(ivGambar1);
                    postFoto1 = mImageFileLocation;
                }else{
                    Glide.with(this).load(fileUri).into(ivGambar1);
                    postFoto1 = fileUri.getPath();
                }
                String filename=postFoto1.substring(postFoto1.lastIndexOf("/")+1);
                lGambar1.setVisibility(View.VISIBLE);
                tvGambar1.setVisibility(View.VISIBLE);
                tvGambar1.setText(filename);
                Gambar1=false;
            }else if(Gambar2){
                if (Build.VERSION.SDK_INT > 21) {
                    Glide.with(this).load(mImageFileLocation).into(ivGambar2);
                    postFoto2 = mImageFileLocation;
                }else{
                    Glide.with(this).load(fileUri).into(ivGambar2);
                    postFoto2 = fileUri.getPath();
                }
                String filename=postFoto2.substring(postFoto2.lastIndexOf("/")+1);
                lGambar2.setVisibility(View.VISIBLE);
                tvGambar2.setVisibility(View.VISIBLE);
                tvGambar2.setText(filename);
                Gambar2=false;
            }else if(Gambar3){
                if (Build.VERSION.SDK_INT > 21) {
                    Glide.with(this).load(mImageFileLocation).into(ivGambar3);
                    postFoto3 = mImageFileLocation;
                }else{
                    Glide.with(this).load(fileUri).into(ivGambar3);
                    postFoto3 = fileUri.getPath();
                }
                String filename=postFoto3.substring(postFoto3.lastIndexOf("/")+1);
                lGambar3.setVisibility(View.VISIBLE);
                tvGambar3.setVisibility(View.VISIBLE);
                tvGambar3.setText(filename);
                Gambar3=false;
            }else if(Gambar4){
                if (Build.VERSION.SDK_INT > 21) {
                    Glide.with(this).load(mImageFileLocation).into(ivGambar4);
                    postFoto4 = mImageFileLocation;
                }else{
                    Glide.with(this).load(fileUri).into(ivGambar4);
                    postFoto4 = fileUri.getPath();
                }
                String filename=postFoto4.substring(postFoto4.lastIndexOf("/")+1);
                lGambar4.setVisibility(View.VISIBLE);
                tvGambar4.setVisibility(View.VISIBLE);
                tvGambar4.setText(filename);
                Gambar4=false;
            }
        }
    }
    private void GetSekolah(){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseModel> Point = api.ProfileSekolah(destiny.AUTH(Token));
        Point.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                try {
                    if (response.body().getStatusCode().equals("000")){
                        form.setText("Formulir Pendaftaran Peserta Didik Baru \n"+response.body().getData().get(0).getNama_sekolah());
                        Glide.with(FormulirPPDBActivity.this)
                                .load(destiny.BASE_URL()+response.body().getData().get(0).getLogo_sekolah())
                                .into(LogoSekolah);
                    }else if (response.body().getStatusCode().equals("001") || response.body().getStatusCode().equals("002")){
                        destiny.AutoLogin(Username,Password,FormulirPPDBActivity.this);
                        Intent intent = new Intent(FormulirPPDBActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(FormulirPPDBActivity.this, "Terjadi Kesalahan ", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(FormulirPPDBActivity.this, "Terjadi Kesalahan User akan Terlogout", Toast.LENGTH_SHORT).show();
                    dbHelper.Logout();
                    Intent intent = new Intent(FormulirPPDBActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(FormulirPPDBActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}