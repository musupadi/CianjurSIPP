package com.destiny.cianjursipp.Method;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.destiny.cianjursipp.API.ApiRequest;
import com.destiny.cianjursipp.API.RetroServer;
import com.destiny.cianjursipp.Activity.HomeActivity;
import com.destiny.cianjursipp.Activity.LoginActivity;
import com.destiny.cianjursipp.Activity.Marketplace.MarketplaceSiswaActivity;
import com.destiny.cianjursipp.Activity.menu.AgendaSekolah.AgendaSekolahActivity;
import com.destiny.cianjursipp.Activity.menu.BiayaAkademik.BiayaAkademikActivity;
import com.destiny.cianjursipp.Activity.menu.ERaport.ERaportActivity;
import com.destiny.cianjursipp.Activity.menu.Eskul.EskulActivity;
import com.destiny.cianjursipp.Activity.menu.Evadir.EvadirActivity;
import com.destiny.cianjursipp.Activity.menu.Gallery.GalleryActivity;
import com.destiny.cianjursipp.Activity.menu.Guru.GuruActivity;
import com.destiny.cianjursipp.Activity.menu.InfoDinas.InfoDinasActivity;
import com.destiny.cianjursipp.Activity.menu.JadwalPelajaran.JadwalPelajaranActivity;
import com.destiny.cianjursipp.Activity.menu.JumatNgaji.JumatNgajiActivity;
import com.destiny.cianjursipp.Activity.menu.KabarSekolah.KabarSekolahActivity;
import com.destiny.cianjursipp.Activity.menu.Kebudayaan.KebudayaanActivity;
import com.destiny.cianjursipp.Activity.menu.LowonganKerja.LowonganKerjaActivity;
import com.destiny.cianjursipp.Activity.menu.MediaInformasi.MediaInformasiActivity;
import com.destiny.cianjursipp.Activity.menu.MediaPembelajaran.MediaPembelajaranActivity;
import com.destiny.cianjursipp.Activity.menu.MediaPembelajaran.NewMediaPembelajaranActivity;
import com.destiny.cianjursipp.Activity.menu.PPDB.FormulirPPDBActivity;
import com.destiny.cianjursipp.Activity.menu.Pembayaran.PembayaranActivity;
import com.destiny.cianjursipp.Activity.menu.Prestasi.PrestasiActivity;
import com.destiny.cianjursipp.Activity.menu.ProfileSekolah.ProfileSekolahActivity;
import com.destiny.cianjursipp.Activity.menu.StrukturOrganisasi.StrukturOrganisasiActivity;
import com.destiny.cianjursipp.Activity.menu.Teman.TemanActivity;
import com.destiny.cianjursipp.Activity.menu.Tugas.NewTugasActivity;
import com.destiny.cianjursipp.Activity.menu.Tugas.TugasActivity;
import com.destiny.cianjursipp.Activity.menu.Ujian.UjianActivity;
import com.destiny.cianjursipp.Model.Eskul.Eskul;
import com.destiny.cianjursipp.Model.Pena.ResponseModel;
import com.destiny.cianjursipp.SharedPreferance.DB_Helper;
import com.shockwave.pdfium.PdfDocument;
import com.shockwave.pdfium.PdfiumCore;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Destiny {
    public String SmallDescription(String description){
        String Des = description;
        if (description.length() >= 100){
            Des = description.substring(0,100)+"...";
        }
        return Des;
    }
    public String DefaultADIntersential(){
        String AD = "ca-app-pub-3940256099942544/1033173712";
        return AD;
    }
    public String BelajarADIntersential(){
        String AD = "ca-app-pub-3874394648867984/8806135403";
        return AD;
    }

    public String DestinyADIntersential(){
        String AD = "ca-app-pub-1910572986729903/8952764621";
        return AD;
    }


    public void DownloadPDF(String url,String judul,Context ctx){
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        request.setTitle(judul);
        request.setDescription("Downloading "+judul);
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"/Belajar/"+judul+".pdf");
        DownloadManager manager = (DownloadManager)ctx.getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);
    }
    public String Nilai(String nilai){
        int index = nilai.indexOf('/');
        String nilai_min = nilai.substring(0,index);
        String nilai_max = nilai.substring(index+1);
        double NILAI_MIN = Double.valueOf(nilai_min);
        double NILAI_MAX = Double.valueOf(nilai_max);
        double NILAI = NILAI_MIN/NILAI_MAX*100;
        DecimalFormat df = new DecimalFormat("#");
        String nilais = df.format(NILAI);
        return nilais;
    }
    public void Baim_KONTOL(final Context ctx){
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);

        // Set a title for alert dialog
        builder.setTitle("Pemberitahuan");

        // Ask the final question
        builder.setMessage("Apakah Anda Yakin Ingin Logout ? ");

        // Set the alert dialog yes button click listener
        builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do something when user clicked the Yes button
                // Set the TextView visibility GONE
                DB_Helper db_helper = new DB_Helper(ctx);
                db_helper.Logout();
                Toast.makeText(ctx, "Anda Berhasil Logout", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ctx, LoginActivity.class);
                ctx.startActivity(intent);
            }
        });

        // Set the alert dialog no button click listener
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do something when No button clicked
            }
        });

        AlertDialog dialog = builder.create();
        // Display the alert dialog on interface
        dialog.show();
    }
    public String AUTH_BASE_64(){
        String username = "destiny_pss_tk";
        String password = "45sq2355sqav139ewxza";

        String base = username+":"+password;

        String authHeader = "Basic "+ Base64.encodeToString(base.getBytes(),Base64.NO_WRAP);
        return authHeader;
    }
    public String CheckerImageYoutube(String LinkYoutube,String Kabar){
        String link= BASE_URL()+Kabar;
        if (!LinkYoutube.equals("")){
            link = AutoTumbnailYoutube(GetIDYoutube(LinkYoutube));
        }
        return link;
    }
    public String AutoTumbnailYoutube(String code){
        String replace = "https://img.youtube.com/vi/"+code+"/mqdefault.jpg";
        return replace;
    }
    public String GetIDYoutube(String link){
        String replace1 = link.replace("https://www.youtube.com/watch?v=","");
        return replace1;
    }
    public String FajarNangis(String Jessica){
        String replace = Jessica.replace("&#39;","'");
        return replace;
    }
    public static String getThisDayDB(){
        DateFormat dateFormat1 = new SimpleDateFormat("dd");
        DateFormat dateFormat2 = new SimpleDateFormat("MM");
        DateFormat dateFormat3 = new SimpleDateFormat("yyyy");
        Date date = new Date();
        String Day = dateFormat1.format(date);
        String month = dateFormat2.format(date);
        String Year = dateFormat3.format(date);
        String dates = Year+"-"+month+"-"+Day;
        return dates;
    }
    public void generateImageFromPdf(Uri pdfUri,Context ctx) {
        int pageNumber = 0;
        PdfiumCore pdfiumCore = new PdfiumCore(ctx);
        try {
            //http://www.programcreek.com/java-api-examples/index.php?api=android.os.ParcelFileDescriptor
            ParcelFileDescriptor fd = ctx.getContentResolver().openFileDescriptor(pdfUri, "r");
            PdfDocument pdfDocument = pdfiumCore.newDocument(fd);
            pdfiumCore.openPage(pdfDocument, pageNumber);
            int width = pdfiumCore.getPageWidthPoint(pdfDocument, pageNumber);
            int height = pdfiumCore.getPageHeightPoint(pdfDocument, pageNumber);
            Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            pdfiumCore.renderPageBitmap(pdfDocument, bmp, pageNumber, 0, 0, width, height);
            saveImage(bmp);
            pdfiumCore.closeDocument(pdfDocument); // important!
        } catch(Exception e) {
            //todo with exception
        }
    }

    public final static String FOLDER = Environment.getExternalStorageDirectory() + "/PDF";
    private void saveImage(Bitmap bmp) {
        FileOutputStream out = null;
        try {
            File folder = new File(FOLDER);
            if (!folder.exists())
                folder.mkdirs();
            File file = new File(folder, "PDF.png");
            out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, out); // bmp is your Bitmap instance
        } catch (Exception e) {
            //todo with exception
        } finally {
            try {
                if (out != null)
                    out.close();
            } catch (Exception e) {
                //todo with exception
            }
        }
    }


    public Boolean CheckerPDF(String pdf){
        boolean gan = false;
        String PDF = pdf.substring(pdf.length()-4);
        if (PDF.equals(".pdf")){
            gan=true;
        }
        return gan;
    }
    public String FilterTextToJava(String text){
        String replaces = text.replace("</p>\\r\\n<ol>\\r\\n<li>","");
        String replace1 = replaces.replace("<p>","");
        String replace2 = replace1.replace("</p>","");
        String replace3 = replace2.replace("<span style=\"color: #ff6600;\">","");
        String replace4 = replace3.replace("</span>","");
        String replace5 = replace4.replace("<strong>","");
        String replace6 = replace5.replace("</strong>","");
        String replace7 = replace6.replace("<ol>","");
        String replace8 = replace7.replace("</ol>","");
        String replace9 = replace8.replace("<li>","");
        String replace10 = replace9.replace("</li>","");
        String replace11 = replace10.replace("<ul>","");
        String replace12 = replace11.replace("</ul>","");
        String replace13 = replace12.replace("\\n\\n","\\n");
        String replace14 = replace13.replace("<div>","");
        String replace15 = replace14.replace("</div>","");
        String replace16 = replace15.replace("<p>1.","");
        String replace17 = replace16.replace("<p style=\\\"text-align: left;\\\">","");
        String replace18 = replace17.replace("<a href=","");
        return replace18;
    }
    public void AutoLogin(final String username,final String password,final Context ctx){
        final DB_Helper dbHelper = new DB_Helper(ctx);
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        final Call<ResponseModel> login =api.login(username,password);
        login.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                try {
                    if (response.body().getStatusCode().equals("000")){
                        dbHelper.Logout();
                        dbHelper.SaveUser(username,password,response.body().getData().get(0).getName(),response.body().getData().get(0).getAccessToken(),response.body().getData().get(0).getAs(),response.body().getData().get(0).getPhoto(),response.body().getData().get(0).getKelas());
                    }else{
                        Toast.makeText(ctx, response.body().getStatusMessage(), Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(ctx, "Terjadi Kesalahan "+e.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
//                Toast.makeText(LoginActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
                Toast.makeText(ctx, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
                Log.i("Login Logic : ",t.toString());
            }
        });
    }
    public void Toast(Context context,String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
    public void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            deleteDir(dir);
        } catch (Exception e) { e.printStackTrace();}
    }
    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        } else if(dir!= null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }
    public Bitmap KontoruFajar(String url) {
        Bitmap bm = null;
        try {
            URL aURL = new URL(url);
            URLConnection conn = aURL.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();
        } catch (IOException e) {

        }
        return bm;
    }
    public String CheckLanguageID(String lang){
        String Lang="id";
        if (lang.equals("Indonesia")){
            Lang="id";
        }else if(lang.equals("Sunda")){
            Lang="su";
        }else if(lang.equals("English")){
            Lang="en";
        }else if (lang.equals("Arabic")){
            Lang="ar";
        }else if (lang.equals("Basque")){
            Lang="eu";
        }else if (lang.equals("Bengali")){
            Lang="bn";
        }else if (lang.equals("Portugese")){
            Lang="pt-PT";
        }else if (lang.equals("Bulgarian")){
            Lang="bg";
        }else if (lang.equals("Filipino")){
            Lang="fil";
        }else if (lang.equals("French")){
            Lang="fr";
        }else if (lang.equals("German")){
            Lang="fr";
        }else if (lang.equals("Greek")){
            Lang="el";
        }else if (lang.equals("Italian")){
            Lang="it";
        }else if (lang.equals("Japanese")){
            Lang="ja";
        }else if (lang.equals("Kannada")){
            Lang="kn";
        }else if (lang.equals("Korean")){
            Lang="ko";
        }else if (lang.equals("Russian")){
            Lang="ru";
        }else if (lang.equals("Chinese")){
            Lang="zh-CN";
        }else if (lang.equals("Spanish")){
            Lang="es";
        }else if (lang.equals("Thai")){
            Lang="th";
        }else if (lang.equals("Turkish")){
            Lang="tr";
        }else if (lang.equals("Ukrainian")){
            Lang="uk";
        }else if (lang.equals("Vietnamese")){
            Lang="vi";
        }
        return Lang;
    }
    public void ChangeActivity(Context ctx,String Class,String id){

        if (Class.equals("Profile Sekolah")){
            Intent intent = new Intent(ctx, ProfileSekolahActivity.class);
            ctx.startActivity(intent);
        }else if(Class.equals("Agenda Sekolah")){
            Intent intent = new Intent(ctx, AgendaSekolahActivity.class);
            ctx.startActivity(intent);
        }else if(Class.equals("Prestasi")){
            Intent intent = new Intent(ctx, PrestasiActivity.class);
            ctx.startActivity(intent);
        }else if(Class.equals("Struktur Sekolah")){
            Intent intent = new Intent(ctx, StrukturOrganisasiActivity.class);
            ctx.startActivity(intent);
        }else if(Class.equals("Jadwal Pelajaran")){
            if (id.equals("guest")){
                Toast.makeText(ctx, "Mohon Login Untuk Mengakses Menu", Toast.LENGTH_SHORT).show();
            }else{
                Intent intent = new Intent(ctx, JadwalPelajaranActivity.class);
                ctx.startActivity(intent);
            }
        }else if(Class.equals("Evadir")){
            if (id.equals("guest")){
                Toast.makeText(ctx, "Mohon Login Untuk Mengakses Menu", Toast.LENGTH_SHORT).show();
            }else if (id.equals("guru")){
                Toast.makeText(ctx, "Hanya Murid Yang dapat membuka Evadir", Toast.LENGTH_SHORT).show();
            }else{
                Intent intent = new Intent(ctx, EvadirActivity.class);
                ctx.startActivity(intent);
            }
        }else if(Class.equals("Media Pembelajaran")){
            Intent intent = new Intent(ctx, NewMediaPembelajaranActivity.class);
            ctx.startActivity(intent);
        }else if(Class.equals("Tugas")){
            if (id.equals("guest")){
                Toast.makeText(ctx, "Mohon Login Untuk Mengakses Menu", Toast.LENGTH_SHORT).show();
            }else{
                Intent intent = new Intent(ctx, NewTugasActivity.class);
                ctx.startActivity(intent);
            }
        }else if(Class.equals("E Raport")){
            if (id.equals("guest")){
                Toast.makeText(ctx, "Mohon Login Untuk Mengakses Menu", Toast.LENGTH_SHORT).show();
            }else{
                Intent intent = new Intent(ctx, ERaportActivity.class);
                ctx.startActivity(intent);
            }
        }else if(Class.equals("Marketplace")){
            Intent intent = new Intent(ctx, MarketplaceSiswaActivity.class);
            ctx.startActivity(intent);
        }else if(Class.equals("Guru")){
            Intent intent = new Intent(ctx, GuruActivity.class);
            ctx.startActivity(intent);
        }else if(Class.equals("Biaya Akademik")){
            Intent intent = new Intent(ctx, BiayaAkademikActivity.class);
            ctx.startActivity(intent);
        }else if(Class.equals("Pembayaran")){
            if (id.equals("guest")){
                Toast.makeText(ctx, "Mohon Login Untuk Mengakses Menu", Toast.LENGTH_SHORT).show();
            }else{
                Intent intent = new Intent(ctx, PembayaranActivity.class);
                ctx.startActivity(intent);
            }
        }else if(Class.equals("ROB")){
//            Intent intent = new Intent(ctx, ROBDanaActivity.class);
//            ctx.startActivity(intent);
        }else if(Class.equals("E-Raport")){
            if (id.equals("guest")){
                Toast.makeText(ctx, "Mohon Login Untuk Mengakses Menu", Toast.LENGTH_SHORT).show();
            }else{
                Intent intent = new Intent(ctx, ERaportActivity.class);
                ctx.startActivity(intent);
            }
        }else if(Class.equals("Gallery")){
            Intent intent = new Intent(ctx, GalleryActivity.class);
            ctx.startActivity(intent);
        }else if(Class.equals("Dinas Pendidikan")){
            Intent intent = new Intent(ctx, InfoDinasActivity.class);
            ctx.startActivity(intent);
        }else if(Class.equals("Kebudayaan")){
            Intent intent = new Intent(ctx, KebudayaanActivity.class);
            ctx.startActivity(intent);
        }else if(Class.equals("Jumat Ngaji")){
            Intent intent = new Intent(ctx, JumatNgajiActivity.class);
            ctx.startActivity(intent);
        }else if(Class.equals("Media Informasi")){
            Intent intent = new Intent(ctx, MediaInformasiActivity.class);
            ctx.startActivity(intent);
        }else if(Class.equals("Loker")){
            Intent intent = new Intent(ctx, LowonganKerjaActivity.class);
            ctx.startActivity(intent);
        }else if(Class.equals("Eskul")){
            Intent intent = new Intent(ctx, EskulActivity.class);
            ctx.startActivity(intent);
        }else if(Class.equals("Ujian")){
            if (id.equals("guest")){
                Toast.makeText(ctx, "Guest Tidak bisa membuka Ujian", Toast.LENGTH_SHORT).show();
            }else{
                Intent intent = new Intent(ctx, UjianActivity.class);
                ctx.startActivity(intent);
            }
        }else if(Class.equals("Jumat Ngaji")){
            Intent intent = new Intent(ctx, JumatNgajiActivity.class);
            ctx.startActivity(intent);
        }else if(Class.equals("Media Informasi")){
            Intent intent = new Intent(ctx, MediaInformasiActivity.class);
            ctx.startActivity(intent);
        }else if(Class.equals("Kebudayaan")){
            Intent intent = new Intent(ctx, UjianActivity.class);
            ctx.startActivity(intent);
        }else if(Class.equals("Kabar Sekolah")){
            Intent intent = new Intent(ctx, KabarSekolahActivity.class);
            ctx.startActivity(intent);
        }else if(Class.equals("Teman")){
            Intent intent = new Intent(ctx, TemanActivity.class);
            ctx.startActivity(intent);
        }
    }
    public String BASE_URL(){
        String BASE_URL = "https://belajar.penaedu.id/";
        return BASE_URL;
    }
    public String AUTH(String auth){
        String authHeader = "Bearer "+auth;
        return authHeader;
    }
    public void Back(Context context){
        Intent intent = new Intent(context, HomeActivity.class);
        context.startActivity(intent);
    }
    private void initOPPO(Context ctx) {
        try {

            Intent i = new Intent(Intent.ACTION_MAIN);
            i.setComponent(new ComponentName("com.oppo.safe", "com.oppo.safe.permission.floatwindow.FloatWindowListActivity"));
            ctx.startActivity(i);
        } catch (Exception e) {
            e.printStackTrace();
            try {

                Intent intent = new Intent("action.coloros.safecenter.FloatWindowListActivity");
                intent.setComponent(new ComponentName("com.coloros.safecenter", "com.coloros.safecenter.permission.floatwindow.FloatWindowListActivity"));
                ctx.startActivity(intent);
            } catch (Exception ee) {

                ee.printStackTrace();
                try{

                    Intent i = new Intent("com.coloros.safecenter");
                    i.setComponent(new ComponentName("com.coloros.safecenter", "com.coloros.safecenter.sysfloatwindow.FloatWindowListActivity"));
                    ctx.startActivity(i);
                }catch (Exception e1){

                    e1.printStackTrace();
                }
            }

        }
    }
    private static void autoLaunchVivo(Context context) {
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.iqoo.secure",
                    "com.iqoo.secure.ui.phoneoptimize.AddWhiteListActivity"));
            context.startActivity(intent);
        } catch (Exception e) {
            try {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("com.vivo.permissionmanager",
                        "com.vivo.permissionmanager.activity.BgStartUpManagerActivity"));
                context.startActivity(intent);
            } catch (Exception ex) {
                try {
                    Intent intent = new Intent();
                    intent.setClassName("com.iqoo.secure",
                            "com.iqoo.secure.ui.phoneoptimize.BgStartUpManager");
                    context.startActivity(intent);
                } catch (Exception exx) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public String getToday(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String thisDay = dateFormat.format(date);
        String today = dayName(thisDay,"dd/MM/yyyy");
        String HariIni = "Senin";
        if(today.equals("Monday")){
            HariIni = "Senin";
        }else if(today.equals("Tuesday")){
            HariIni = "Selasa";
        }else if(today.equals("Wednesday")){
            HariIni = "Rabu";
        }else if(today.equals("Thursday")){
            HariIni = "Kamis";
        }else if(today.equals("Friday")){
            HariIni = "Jumat";
        }else if(today.equals("Saturday")){
            HariIni = "Sabtu";
        }else if(today.equals("Sunday")){
            HariIni = "Minggu";
        }
        return HariIni;
    }
    public  String DateChanges(String year,String month,String day){
        String MONTH = "Januari";
        if (month.equals("01") || month.equals("1")){
            MONTH = "Januari";
        }else if (month.equals("02") || month.equals("2")){
            MONTH = "Februari";
        }else if (month.equals("03") || month.equals("3")){
            MONTH = "Maret";
        }else if (month.equals("04") || month.equals("4")){
            MONTH = "April";
        }else if (month.equals("05") || month.equals("5")){
            MONTH = "Mei";
        }else if (month.equals("06") || month.equals("6")){
            MONTH = "Juni";
        }else if (month.equals("07") || month.equals("7")){
            MONTH = "Juli";
        }else if (month.equals("08") || month.equals("8")){
            MONTH = "Agustus";
        }else if (month.equals("09") || month.equals("9")){
            MONTH = "September";
        }else if (month.equals("10")){
            MONTH = "Oktober";
        }else if (month.equals("11")){
            MONTH = "November";
        }else if (month.equals("12")){
            MONTH = "Desember";
        }
        String Dates = day+"-"+MONTH+"-"+year;
        return Dates;
    }
    public String MagicDateChange(String dates){
        String result = "";
        String year = dates.substring(0,4);
        String month = dates.substring(5,7);
        String day = dates.substring(8,10);

        String MONTH = "Januari";
        if (month.equals("01") || month.equals("1")){
            MONTH = "Januari";
        }else if (month.equals("02") || month.equals("2")){
            MONTH = "Februari";
        }else if (month.equals("03") || month.equals("3")){
            MONTH = "Maret";
        }else if (month.equals("04") || month.equals("4")){
            MONTH = "April";
        }else if (month.equals("05") || month.equals("5")){
            MONTH = "Mei";
        }else if (month.equals("06") || month.equals("6")){
            MONTH = "Juni";
        }else if (month.equals("07") || month.equals("7")){
            MONTH = "Juli";
        }else if (month.equals("08") || month.equals("8")){
            MONTH = "Agustus";
        }else if (month.equals("09") || month.equals("9")){
            MONTH = "September";
        }else if (month.equals("10")){
            MONTH = "Oktober";
        }else if (month.equals("11")){
            MONTH = "November";
        }else if (month.equals("12")){
            MONTH = "Desember";
        }
        result = day+"-"+MONTH+"-"+year;
        return result;

    }

    public static String Today(){
        DateFormat dateFormat1 = new SimpleDateFormat("dd");
        DateFormat dateFormat2 = new SimpleDateFormat("MM");
        DateFormat dateFormat3 = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String dates = dateFormat3.format(date);
        return dates;
    }
    public static String thisDay(){
        DateFormat dateFormat1 = new SimpleDateFormat("dd");
        DateFormat dateFormat2 = new SimpleDateFormat("MM");
        DateFormat dateFormat3 = new SimpleDateFormat("yyyy");
        Date date = new Date();
        String Day = dateFormat1.format(date);
        String month = dateFormat2.format(date);
        String Year = dateFormat3.format(date);
        String MONTH = "Januari";
        if (month.equals("01") || month.equals("1")){
            MONTH = "Januari";
        }else if (month.equals("02") || month.equals("2")){
            MONTH = "Februari";
        }else if (month.equals("03") || month.equals("3")){
            MONTH = "Maret";
        }else if (month.equals("04") || month.equals("4")){
            MONTH = "April";
        }else if (month.equals("05") || month.equals("5")){
            MONTH = "Mei";
        }else if (month.equals("06") || month.equals("6")){
            MONTH = "Juni";
        }else if (month.equals("07") || month.equals("7")){
            MONTH = "Juli";
        }else if (month.equals("08") || month.equals("8")){
            MONTH = "Agustus";
        }else if (month.equals("09") || month.equals("9")){
            MONTH = "September";
        }else if (month.equals("10")){
            MONTH = "Oktober";
        }else if (month.equals("11")){
            MONTH = "November";
        }else if (month.equals("12")){
            MONTH = "Desember";
        }
        return Day+"-"+MONTH+"-"+Year;
    }
    public static String dayName(String inputDate, String format){
        Date date = null;
        try {
            date = new SimpleDateFormat(format).parse(inputDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date);
    }
}

