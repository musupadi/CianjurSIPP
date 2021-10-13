package com.destiny.cianjursipp.Activity.ui;

import android.app.Dialog;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.destiny.cianjursipp.API.ApiRequest;
import com.destiny.cianjursipp.API.RetroServer;
import com.destiny.cianjursipp.Activity.HomeActivity;
import com.destiny.cianjursipp.Activity.LoginActivity;
import com.destiny.cianjursipp.Activity.MainActivity;
import com.destiny.cianjursipp.Activity.menu.KabarSekolah.DetailKabarSekolahActivity;
import com.destiny.cianjursipp.Activity.menu.ProfileSekolah.ProfileSekolahActivity;
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


public class UserFragment extends Fragment {
    Button logout;
    DB_Helper db_helper;
    TextView NISN,NISN2,nama;
    ImageView profile;
    private static final String TAG = MainActivity.class.getSimpleName();
    Destiny destiny;

    DB_Helper dbHelper;
    String Username,Password,Nama,Token,Level,Photo;
    Boolean Gambar = false;
    TextView tvGambar;
    ImageView ivGambar;
    LinearLayout PilihGambar;
    LinearLayout Tutup,Upload;
    Dialog dialog,dialog2;
    Button UbahPassword;
    EditText OldPassword,NewPassword,NewPassword2;
    Button Submit;
    Button Close;
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
    String postFoto= "";
    LinearLayout CariFoto;
    TextView tingkatan,telepon;
    public UserFragment() {
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
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            destiny = new Destiny();
            db_helper = new DB_Helper(getActivity());
            NISN  = view.findViewById(R.id.tvNISN);
            NISN2 = view.findViewById(R.id.tvNISN2);
            nama = view.findViewById(R.id.tvNama);
            tingkatan = view.findViewById(R.id.tvTingkatan);
            profile = view.findViewById(R.id.ivProfile);
            logout = view.findViewById(R.id.btnLogot);
            UbahPassword = view.findViewById(R.id.btnUbahPassword);
            telepon = view.findViewById(R.id.tvTelepon);
            dialog = new Dialog(getActivity());
            dialog.setContentView(R.layout.dialog_change_foto);
            PilihGambar = dialog.findViewById(R.id.linearPilihFoto);
            dialog2 = new Dialog(getActivity());
            dialog2.setContentView(R.layout.dialog_change_password);
            Tutup = dialog.findViewById(R.id.linearTutup);
            Upload = dialog.findViewById(R.id.linearUpload);
            ivGambar = dialog.findViewById(R.id.ivGambar);
            tvGambar = dialog.findViewById(R.id.tvGambar);
            OldPassword = dialog2.findViewById(R.id.etOldPassword);
            NewPassword = dialog2.findViewById(R.id.etPasswordBaru);
            NewPassword2 = dialog2.findViewById(R.id.etPasswordBaru2);
            Close = dialog2.findViewById(R.id.btnClose);
            Submit = dialog2.findViewById(R.id.btnSubmit);
            ivGambar.setVisibility(View.GONE);
            tvGambar.setVisibility(View.GONE);
            Upload.setVisibility(View.GONE);
            logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    db_helper.Logout();
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
            });
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
            Logic();
            OnClick();
            DialogOnClick();
        }catch (Exception e){

        }
    }
    private void OnClick(){
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });
        UbahPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog2.show();
            }
        });
    }
    private void DialogOnClick(){
        PilihGambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MaterialDialog.Builder(getActivity())
                        .title("Pilih Gambar")
                        .items(R.array.uploadImages)
                        .itemsIds(R.array.itemIds)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                switch (which) {
                                    case 0:
                                        Upload.setVisibility(View.VISIBLE);
                                        Gambar = true;
                                        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                        startActivityForResult(galleryIntent, REQUEST_PICK_PHOTO);
                                        ivGambar.setVisibility(View.VISIBLE);
                                        tvGambar.setVisibility(View.VISIBLE);
                                        break;
                                    case 1:
                                        Upload.setVisibility(View.VISIBLE);
                                        Gambar = true;
                                        captureImage();
                                        ivGambar.setVisibility(View.VISIBLE);
                                        tvGambar.setVisibility(View.VISIBLE);
                                        break;
                                    case 2:
                                        Upload.setVisibility(View.GONE);
                                        ivGambar.setImageResource(R.drawable.ic_launcher_background);
                                        ivGambar.setVisibility(View.GONE);
                                        tvGambar.setVisibility(View.GONE);
                                        Gambar = false;
                                        break;
                                }
                            }
                        })
                        .show();
            }
        });
        Tutup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.hide();
            }
        });
        Upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
                File file = new File(postFoto);
                RequestBody fileReqBody = RequestBody.create(MediaType.parse("image/*"), file);
                MultipartBody.Part partPhoto = MultipartBody.Part.createFormData("photo", file.getName(), fileReqBody);
                Call<ResponseModel> Data = api.ChangeFoto(
                        destiny.AUTH(Token),
                        partPhoto
                );
                Data.enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                        try {
                            if (response.body().getStatusCode().equals("000")){
                                Toast.makeText(getActivity(), response.body().getStatusMessage(), Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getActivity(), HomeActivity.class);
                                i.putExtra("PAGING","USER");
                                getActivity().startActivity(i);
                                getActivity().finish();
                            }else{
                                destiny.AutoLogin(Username,Password,getActivity());
                                Toast.makeText(getActivity(), "Mohon Dicoba lagi", Toast.LENGTH_SHORT).show();
                            }
                        }catch (Exception e){
                            Toast.makeText(getActivity(), "Terjadi Kesalahan "+e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {
                        Toast.makeText(getActivity(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        Close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog2.hide();
            }
        });
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (NewPassword.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Password Baru Kosong", Toast.LENGTH_SHORT).show();
                }else if(NewPassword2.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Password Konfirmasi Kosong", Toast.LENGTH_SHORT).show();
                }else if(OldPassword.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Password Lama Kosong", Toast.LENGTH_SHORT).show();
                }else if(NewPassword.getText().toString().equals(NewPassword2.getText().toString())){
                    ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
                    final Call<ResponseModel> data =api.UbahPassword(
                            destiny.AUTH(Token),
                            NewPassword.getText().toString(),
                            NewPassword2.getText().toString(),
                            OldPassword.getText().toString());
                    data.enqueue(new Callback<ResponseModel>() {
                        @Override
                        public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                            try {
                                if (response.body().getStatusCode().equals("000")){
                                    Toast.makeText(getActivity(), response.body().getStatusMessage(), Toast.LENGTH_SHORT).show();
                                    destiny.AutoLogin(Username,NewPassword.getText().toString(),getActivity());
                                    Intent intent = new Intent(getActivity(),MainActivity.class);
                                    startActivity(intent);
                                    getActivity().finish();
                                }else if (response.body().getStatusCode().equals("004")){
                                    Toast.makeText(getActivity(), response.body().getStatusMessage(), Toast.LENGTH_SHORT).show();
                                }else{
                                    destiny.AutoLogin(Username,Password,getActivity());
                                    Toast.makeText(getActivity(), "Mohon Dicoba lagi", Toast.LENGTH_SHORT).show();
                                }
                            }catch (Exception e){
                                Toast.makeText(getActivity(), "Terjadi Kesalahan "+e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseModel> call, Throwable t) {
                            Toast.makeText(getActivity(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();
                        }
                    });
                }else{
                    Toast.makeText(getActivity(), "Password Konfirmasi dan Password harus sama !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void GetSekolah(){
        ApiRequest api2 = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseModel> Point = api2.ProfileSekolah(destiny.AUTH(Token));
        Point.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                try {
                    if (response.body().getStatusCode().equals("000")){
                        tingkatan.setText(response.body().getData().get(0).getNama_sekolah());

                    }else if (response.body().getStatusCode().equals("001") || response.body().getStatusCode().equals("002")){

                    }else{
                        Toast.makeText(getActivity(), "Terjadi Kesalahan ", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Log.i("Error : ",e.toString());
                    Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
                    dbHelper.Logout();
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(getActivity(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void Logic() {
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        final Call<ResponseModel> login = api.login(Username, Password);
        login.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                try {
                    if (response.body().getStatusCode().equals("000")) {
                        try {
                            nama.setText(response.body().getData().get(0).getName());
                            tingkatan.setText(response.body().getData().get(0).getNama_sekolah());
                            telepon.setText(response.body().getData().get(0).getTelepon());
                            NISN.setText(Username);
                            NISN2.setText(Username);
                            GetSekolah();
                            if (response.body().getData().get(0).getPhoto().equals("") || response.body().getData().get(0).getPhoto().isEmpty()) {
                                profile.setImageResource(R.drawable.childern);
                            } else {
                                Glide.with(getActivity())
                                        .load(destiny.BASE_URL() + response.body().getData().get(0).getPhoto())
                                        .into(profile);
                            }
                        } catch (Exception e) {

                        }
                    } else {
                        Toast.makeText(getActivity(), response.body().getStatusMessage(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(getActivity(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();
                Log.i("Login Logic : ", t.toString());
            }
        });
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
                Log.d(TAG, "Oops! Failed create "
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
                    postFoto = mediaPath;
                    String filename=postFoto.substring(postFoto.lastIndexOf("/")+1);
                    ivGambar.setVisibility(View.VISIBLE);
                    tvGambar.setVisibility(View.VISIBLE);
                    ivGambar.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
                    tvGambar.setText(filename);
                    Gambar=false;
                    Toast.makeText(getActivity(), filename, Toast.LENGTH_SHORT).show();
                }
            }
        }else if (requestCode == CAMERA_PIC_REQUEST){
            if(Gambar){
                if (Build.VERSION.SDK_INT > 21) {
                    Glide.with(this).load(mImageFileLocation).into(ivGambar);
                    postFoto = mImageFileLocation;
                }else{
                    Glide.with(this).load(fileUri).into(ivGambar);
                    postFoto = fileUri.getPath();
                }
                String filename=postFoto.substring(postFoto.lastIndexOf("/")+1);
                ivGambar.setVisibility(View.VISIBLE);
                tvGambar.setVisibility(View.VISIBLE);
                tvGambar.setText(filename);
                Gambar=false;
            }
        }
    }
}