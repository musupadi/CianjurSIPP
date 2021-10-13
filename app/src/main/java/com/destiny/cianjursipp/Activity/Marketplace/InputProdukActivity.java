package com.destiny.cianjursipp.Activity.Marketplace;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.destiny.cianjursipp.API.ApiRequest;
import com.destiny.cianjursipp.API.RetroServer;
import com.destiny.cianjursipp.Activity.menu.PPDB.FormulirPPDBActivity;
import com.destiny.cianjursipp.BuildConfig;
import com.destiny.cianjursipp.Method.Destiny;
import com.destiny.cianjursipp.Model.Pena.ResponseModel;
import com.destiny.cianjursipp.R;
import com.destiny.cianjursipp.SharedPreferance.DB_Helper;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Logger;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InputProdukActivity extends AppCompatActivity {
    DB_Helper dbHelper;
    String Username,Password,Nama,Token,Level,Photo;

    EditText NamaProduk,Deskripsi,Harga,NomorTelpon;
    Destiny destiny;

    //Cover
    LinearLayout lGambar1;
    ImageView ivGambar1;
    TextView tvGambar1;
    //Gambar1
    LinearLayout lGambar2;
    ImageView ivGambar2;
    TextView tvGambar2;
    EditText tvDeskripsi2;
    //Gambar2
    LinearLayout lGambar3;
    ImageView ivGambar3;
    TextView tvGambar3;
    EditText tvDeskripsi3;
    //Gambar3
    LinearLayout lGambar4;
    ImageView ivGambar4;
    TextView tvGambar4;
    TextView tvDeskripsi4;

    Button Tambah1,Tambah2,Tambah3;
    Button Upload1,Upload2,Upload3,Upload4;

    Button Batal,Simpan;

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
    int gambar=1;

    Boolean Gambar1 = false;
    Boolean Gambar2 = false;
    Boolean Gambar3 = false;
    Boolean Gambar4 = false;
    RelativeLayout Back;

    String ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_produk);
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

        NamaProduk = findViewById(R.id.etNamaProduk);
        Deskripsi = findViewById(R.id.etDeskripsiProduk);
        Harga = findViewById(R.id.etHargaProduk);
        NomorTelpon = findViewById(R.id.etNomorTelpon);
        Simpan = findViewById(R.id.btnSimpan);
        Batal = findViewById(R.id.btnBatal);

        Tambah1 = findViewById(R.id.btnTambah1);
        Tambah2 = findViewById(R.id.btnTambah2);
        Tambah3 = findViewById(R.id.btnTambah3);
        //Akta
        lGambar1 = findViewById(R.id.linearCover);
        ivGambar1 = findViewById(R.id.ivCoverProduk);
        tvGambar1 = findViewById(R.id.tvCover);
        //KK
        lGambar2 = findViewById(R.id.linearGambar1);
        ivGambar2 = findViewById(R.id.ivProduk1);
        tvGambar2 = findViewById(R.id.tvProduk1);
        tvDeskripsi2 = findViewById(R.id.etDeskripsiFoto1);
        //Data Siswa
        lGambar3 = findViewById(R.id.linearGambar2);
        ivGambar3 = findViewById(R.id.ivProduk2);
        tvGambar3 = findViewById(R.id.tvProduk2);
        tvDeskripsi3 = findViewById(R.id.etDeskripsiFoto2);
        //Foto
        lGambar4 = findViewById(R.id.linearGambar3);
        ivGambar4 = findViewById(R.id.ivProduk3);
        tvGambar4 = findViewById(R.id.tvProduk3);
        tvDeskripsi4 = findViewById(R.id.etDeskripsiFoto3);

        Upload1 = findViewById(R.id.btnUpload);
        Upload2 = findViewById(R.id.btnUpload1);
        Upload3 = findViewById(R.id.btnUpload2);
        Upload4 = findViewById(R.id.btnUpload3);
        Tambah3.setVisibility(View.GONE);

        Tambah1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lGambar3.setVisibility(View.VISIBLE);
                gambar = 3;
            }
        });
        Tambah2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lGambar4.setVisibility(View.VISIBLE);
                gambar = 4;
            }
        });

        Upload1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(InputProdukActivity.this)
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
                                        ivGambar1.setVisibility(View.VISIBLE);
                                        break;
                                    case 1:
                                        Gambar1 = true;
                                        captureImage();
                                        ivGambar1.setVisibility(View.VISIBLE);
                                        break;
                                }
                            }
                        })
                        .show();
            }
        });
        //Cover
        Upload1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(InputProdukActivity.this)
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
                                        ivGambar1.setVisibility(View.VISIBLE);
                                        break;
                                    case 1:
                                        Gambar1 = true;
                                        captureImage();
                                        ivGambar1.setVisibility(View.VISIBLE);
                                        break;
                                }
                            }
                        })
                        .show();
            }
        });
        //Gambar 1
        Upload2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(InputProdukActivity.this)
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
                                        ivGambar2.setVisibility(View.VISIBLE);
                                        break;
                                    case 1:
                                        Gambar2 = true;
                                        captureImage();
                                        ivGambar2.setVisibility(View.VISIBLE);
                                        break;
                                }
                            }
                        })
                        .show();
            }
        });
        //Gambar2
        Upload3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(InputProdukActivity.this)
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
                                        ivGambar3.setVisibility(View.VISIBLE);
                                        break;
                                    case 1:
                                        Gambar3 = true;
                                        captureImage();
                                        ivGambar3.setVisibility(View.VISIBLE);
                                        break;
                                }
                            }
                        })
                        .show();
            }
        });
        //Gambar3
        Upload4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(InputProdukActivity.this)
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
                                        ivGambar4.setVisibility(View.VISIBLE);
                                        break;
                                    case 1:
                                        Gambar4 = true;
                                        captureImage();
                                        ivGambar4.setVisibility(View.VISIBLE);
                                        break;
                                }
                            }
                        })
                        .show();
            }
        });

        Simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logic();
            }
        });
    }

    private void Logic(){

        final ProgressDialog pd = new ProgressDialog(InputProdukActivity.this);
        pd.setMessage("Sedang Memasukan Data Peserta");
        pd.show();
        pd.setCancelable(false);
        if (gambar==3){
            File file1 = new File(postFoto1);
            RequestBody fileReqBody1 = RequestBody.create(MediaType.parse("image/*"), file1);
            MultipartBody.Part Cover = MultipartBody.Part.createFormData("cover", file1.getName(), fileReqBody1);

            File file2 = new File(postFoto2);
            RequestBody fileReqBody2 = RequestBody.create(MediaType.parse("image/*"), file2);
            MultipartBody.Part Gambar2 = MultipartBody.Part.createFormData("photoproduk[]", file2.getName(), fileReqBody2);

            File file3 = new File(postFoto3);
            RequestBody fileReqBody3 = RequestBody.create(MediaType.parse("image/*"), file3);
            MultipartBody.Part Gambar3 = MultipartBody.Part.createFormData("photoproduk[]", file3.getName(), fileReqBody3);

            File file4 = new File(postFoto4);
            RequestBody fileReqBody4 = RequestBody.create(MediaType.parse("image/*"), file4);
            MultipartBody.Part Gambar4 = MultipartBody.Part.createFormData("photoproduk[]", file4.getName(), fileReqBody4);

            ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
            Call<ResponseModel> InputProduk = api.ProdukSiswa(destiny.AUTH(Token),
                    RequestBody.create(MediaType.parse("text/plain"),NamaProduk.getText().toString()),
                    RequestBody.create(MediaType.parse("text/plain"),Deskripsi.getText().toString()),
                    RequestBody.create(MediaType.parse("text/plain"),Harga.getText().toString()),
                    RequestBody.create(MediaType.parse("text/plain"),NomorTelpon.getText().toString()),
                    Cover,
                    Gambar2,
                    RequestBody.create(MediaType.parse("text/plain"),tvDeskripsi2.getText().toString()),
                    Gambar3,
                    RequestBody.create(MediaType.parse("text/plain"),tvDeskripsi3.getText().toString())
            );
            InputProduk.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    pd.hide();
                    try {
                        Toast.makeText(InputProdukActivity.this, response.body().getStatusMessage(), Toast.LENGTH_SHORT).show();
                        if (response.body().getStatusCode().equals("000")){
                            Intent intent = new Intent(InputProdukActivity.this,MarketplaceSiswaActivity.class);
                            startActivity(intent);
                        }

                    }catch (Exception e){
                        Toast.makeText(InputProdukActivity.this, "Terjadi Kesalahan pada API", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    pd.hide();
                    Toast.makeText(InputProdukActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
                }
            });
        }else if (gambar==4){
            File file1 = new File(postFoto1);
            RequestBody fileReqBody1 = RequestBody.create(MediaType.parse("image/*"), file1);
            MultipartBody.Part Cover = MultipartBody.Part.createFormData("cover", file1.getName(), fileReqBody1);

            File file2 = new File(postFoto2);
            RequestBody fileReqBody2 = RequestBody.create(MediaType.parse("image/*"), file2);
            MultipartBody.Part Gambar2 = MultipartBody.Part.createFormData("photoproduk[]", file2.getName(), fileReqBody2);

            File file3 = new File(postFoto3);
            RequestBody fileReqBody3 = RequestBody.create(MediaType.parse("image/*"), file3);
            MultipartBody.Part Gambar3 = MultipartBody.Part.createFormData("photoproduk[]", file3.getName(), fileReqBody3);

            File file4 = new File(postFoto4);
            RequestBody fileReqBody4 = RequestBody.create(MediaType.parse("image/*"), file4);
            MultipartBody.Part Gambar4 = MultipartBody.Part.createFormData("photoproduk[]", file4.getName(), fileReqBody4);

            ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
            Call<ResponseModel> InputProduk = api.ProdukSiswa(destiny.AUTH(Token),
                    RequestBody.create(MediaType.parse("text/plain"),NamaProduk.getText().toString()),
                    RequestBody.create(MediaType.parse("text/plain"),Deskripsi.getText().toString()),
                    RequestBody.create(MediaType.parse("text/plain"),Harga.getText().toString()),
                    RequestBody.create(MediaType.parse("text/plain"),NomorTelpon.getText().toString()),
                    Cover,
                    Gambar2,
                    RequestBody.create(MediaType.parse("text/plain"),tvDeskripsi2.getText().toString()),
                    Gambar3,
                    RequestBody.create(MediaType.parse("text/plain"),tvDeskripsi3.getText().toString()),
                    Gambar4,
                    RequestBody.create(MediaType.parse("text/plain"),tvDeskripsi4.getText().toString())
            );
            InputProduk.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    pd.hide();
                    try {
                        if (response.body().getStatusCode().equals("000")){
                            Intent intent = new Intent(InputProdukActivity.this,MarketplaceSiswaActivity.class);
                            startActivity(intent);
                        }
                        Toast.makeText(InputProdukActivity.this, response.body().getStatusMessage(), Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        Toast.makeText(InputProdukActivity.this, "Terjadi Kesalahan pada API", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    pd.hide();
                    Toast.makeText(InputProdukActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            File file1 = new File(postFoto1);
            RequestBody fileReqBody1 = RequestBody.create(MediaType.parse("image/*"), file1);
            MultipartBody.Part Cover = MultipartBody.Part.createFormData("cover", file1.getName(), fileReqBody1);

            File file2 = new File(postFoto2);
            RequestBody fileReqBody2 = RequestBody.create(MediaType.parse("image/*"), file2);
            MultipartBody.Part Gambar2 = MultipartBody.Part.createFormData("photoproduk[]", file2.getName(), fileReqBody2);

            ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
            Call<ResponseModel> InputProduk = api.ProdukSiswa(destiny.AUTH(Token),
                    RequestBody.create(MediaType.parse("text/plain"),NamaProduk.getText().toString()),
                    RequestBody.create(MediaType.parse("text/plain"),Deskripsi.getText().toString()),
                    RequestBody.create(MediaType.parse("text/plain"),Harga.getText().toString()),
                    RequestBody.create(MediaType.parse("text/plain"),NomorTelpon.getText().toString()),
                    Cover,
                    Gambar2,
                    RequestBody.create(MediaType.parse("text/plain"),tvDeskripsi2.getText().toString())
            );
            InputProduk.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    pd.hide();
                    try {
                        if (response.body().getStatusCode().equals("000")){
                            Intent intent = new Intent(InputProdukActivity.this,MarketplaceSiswaActivity.class);
                            startActivity(intent);
                        }
                        Toast.makeText(InputProdukActivity.this, response.body().getStatusMessage(), Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        Toast.makeText(InputProdukActivity.this, "Terjadi Kesalahan pada API", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    pd.hide();
                    Toast.makeText(InputProdukActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
                }
            });
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
}