package com.destiny.cianjursipp.Activity.ui;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.BuildConfig;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.destiny.cianjursipp.API.ApiRequest;
import com.destiny.cianjursipp.API.RetroServer;
import com.destiny.cianjursipp.Activity.MainActivity;
import com.destiny.cianjursipp.Method.Destiny;
import com.destiny.cianjursipp.Model.Pena.ResponseModel;
import com.destiny.cianjursipp.R;
import com.destiny.cianjursipp.SharedPreferance.DB_Helper;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Logger;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IzinFragment extends Fragment implements DatePickerDialog.OnDateSetListener{
    LinearLayout linearDari,linearSampai;
    TextView dari,sampai;
    String Dari,Sampai;
    Boolean D=false;
    Boolean S=false;
    Destiny destiny;
    LinearLayout lGambar;
    ImageView ivGambar;
    TextView tvGambar;
    Button upload,ajukan;
    DB_Helper dbHelper;
    String Username,Password,Nama,Token,Level,Photo,ID;
    EditText deskripsi;
    Spinner izin;
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
    String postBukti= "";
    //ONCLICK
    Boolean Gambar = false;
    public IzinFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_izin, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            destiny = new Destiny();
            izin = view.findViewById(R.id.spIzin);
            lGambar = view.findViewById(R.id.linearGambar);
            ivGambar = view.findViewById(R.id.ivGambar);
            tvGambar = view.findViewById(R.id.tvGambar);
            upload = view.findViewById(R.id.btnUpload);
            lGambar.setVisibility(View.GONE);
            linearDari = view.findViewById(R.id.linearDariTanggal);
            linearSampai = view.findViewById(R.id.linearSampaiTanggal);
            dari = view.findViewById(R.id.tvDariTanggal);
            sampai = view.findViewById(R.id.tvSampaiTanggal);
            ajukan = view.findViewById(R.id.btnAjukan);
            deskripsi = view.findViewById(R.id.etDeskripsi);
            dbHelper = new DB_Helper(getActivity());
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

            dari.setText(destiny.thisDay());
            sampai.setText(destiny.thisDay());
            Dari = destiny.Today();
            Sampai = destiny.Today();
            linearDari.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    D=true;
                    showDatePicker();
                }
            });
            linearSampai.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    S=true;
                    showDatePicker();
                }
            });
            ajukan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Checker();
                }
            });
            upload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new MaterialDialog.Builder(getActivity())
                            .title("Pilih Gambar")
                            .items(R.array.uploadImages)
                            .itemsIds(R.array.itemIds)
                            .itemsCallback(new MaterialDialog.ListCallback() {
                                @Override
                                public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                    switch (which) {
                                        case 0:
                                            Gambar = true;
                                            Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                                                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                            startActivityForResult(galleryIntent, REQUEST_PICK_PHOTO);
                                            lGambar.setVisibility(View.VISIBLE);
                                            tvGambar.setVisibility(View.VISIBLE);
                                            break;
                                        case 1:
                                            captureImage();
                                            lGambar.setVisibility(View.VISIBLE);
                                            tvGambar.setVisibility(View.VISIBLE);
                                            break;
                                    }
                                }
                            })
                            .show();
                }
            });
        }catch (Exception e){

        }
    }
    private void Checker(){
        if (deskripsi.getText().toString().isEmpty()){
            Toast.makeText(getActivity(), "Deskripsi Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
        }else if(postBukti.isEmpty()){
            Toast.makeText(getActivity(), "Harap Pilih Foto Bukti", Toast.LENGTH_SHORT).show();
        }else{
            Ajukan();
        }
    }
    private void Ajukan(){
        final ProgressDialog pd = new ProgressDialog(getActivity());
        pd.setMessage("Sedang Menyimpan data Izin");
        pd.setCancelable(false);
        pd.show();
        File file = new File(postBukti);
        RequestBody fileReqBody = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part partPhoto = MultipartBody.Part.createFormData("fileIzin", file.getName(), fileReqBody);
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseModel> Data=api.Izins(
                destiny.AUTH(Token),
                RequestBody.create(MediaType.parse("text/plain"),izin.getSelectedItem().toString()),
                RequestBody.create(MediaType.parse("text/plain"),deskripsi.getText().toString()),
                RequestBody.create(MediaType.parse("text/plain"),Dari),
                RequestBody.create(MediaType.parse("text/plain"),Sampai),
                partPhoto);
        Data.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                try {
                    if (response.body().getStatusCode().equals("000")){
                        Toast.makeText(getActivity(), "Berhasil Mengirim Data Izin", Toast.LENGTH_SHORT).show();
                        pd.hide();
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                    }else if (response.body().getStatusCode().equals("001") || response.body().getStatusCode().equals("002")){
                        destiny.AutoLogin(Username,Password,getActivity());
                        Toast.makeText(getActivity(), "Silahkan Coba Lagi", Toast.LENGTH_SHORT).show();
                        pd.hide();
                    }else{
                        Toast.makeText(getActivity(), "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                        pd.hide();
                    }
                }catch (Exception e){
                    Toast.makeText(getActivity(), "Terjadi Kesalahan Silahkan Coba lagi", Toast.LENGTH_SHORT).show();
                    pd.hide();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                pd.hide();
                Toast.makeText(getActivity(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void showDatePicker(){
        DatePickerDialog dialog = new DatePickerDialog(getActivity(),
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        int Month = month+1;
        String date = year+"-"+Month+"-"+dayOfMonth;
        if(D){
            dari.setText(destiny.DateChanges(String.valueOf(year),String.valueOf(Month),String.valueOf(dayOfMonth)));
            Dari = date;
        }else{
            sampai.setText(destiny.DateChanges(String.valueOf(year),String.valueOf(Month),String.valueOf(dayOfMonth)));
            Sampai = date;
        }
        D=false;
        S=false;
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
                    getActivity(),
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
                Log.d("Test", "Oops! Failed create "
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
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_TAKE_PHOTO || requestCode == REQUEST_PICK_PHOTO) {
            if (data != null) {
                // Get the Image from data
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getActivity().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                assert cursor != null;
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                mediaPath = cursor.getString(columnIndex);

                // Set the Image in ImageView for Previewing the Media

//                    imageView.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
                cursor.close();
                if(Gambar){
                    postBukti = mediaPath;
                    String filename=postBukti.substring(postBukti.lastIndexOf("/")+1);
                    lGambar.setVisibility(View.VISIBLE);
                    tvGambar.setVisibility(View.VISIBLE);
                    ivGambar.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
                    tvGambar.setText(filename);
                    Gambar=false;
                    Toast.makeText(getActivity(), filename, Toast.LENGTH_SHORT).show();
                }
            }
        }else if (requestCode == CAMERA_PIC_REQUEST){
            if (Build.VERSION.SDK_INT > 21) {
                Glide.with(this).load(mImageFileLocation).into(ivGambar);
                postBukti = mImageFileLocation;
            }else{
                Glide.with(this).load(fileUri).into(ivGambar);
                postBukti = fileUri.getPath();
            }
            String filename=postBukti.substring(postBukti.lastIndexOf("/")+1);
            lGambar.setVisibility(View.VISIBLE);
            tvGambar.setVisibility(View.VISIBLE);
            tvGambar.setText(filename);
            Gambar=false;
        }
    }
}