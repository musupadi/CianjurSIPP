package com.destiny.cianjursipp.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;



import com.destiny.cianjursipp.Method.Destiny;
import com.destiny.cianjursipp.R;
import com.destiny.cianjursipp.SharedPreferance.DB_Helper;
import com.destiny.cianjursipp.Splash.SplashActivity;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.appopen.AppOpenAd;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private InterstitialAd mInterstitialAd;
    Destiny destiny;
    private AppOpenAd appOpenAd = null;
    private AppOpenAd.AppOpenAdLoadCallback loadCallback;
    private static boolean isShowingAd = false;
    private long loadTime = 0;
    String Count = "0";
    DB_Helper dbHelper = new DB_Helper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        destiny = new Destiny();

        Cursor cursor2 = dbHelper.checkADS();
        if (cursor2.getCount()>0){
            while (cursor2.moveToNext()){
                Count = cursor2.getString(0);
            }
        }
        try {
            if (Integer.parseInt(Count)>0){
                MobileAds.initialize(this, new OnInitializationCompleteListener() {
                    @Override
                    public void onInitializationComplete(InitializationStatus initializationStatus) {
                        AD();
                    }
                });
            }else{
                Logic();
            }
        }catch (Exception e){
            Log.d("Checker : ",e.toString());
            Logic();
        }
//        ApiRequest api = DestinyServer.getClient().create(ApiRequest.class);
//        Call<ResponseDestiny> Check = api.Checkers("Basic YWRtaW46MTIzNA==",
//                "destinykitabelajarkey");
//        Check.enqueue(new Callback<ResponseDestiny>() {
//            @Override
//            public void onResponse(Call<ResponseDestiny> call, Response<ResponseDestiny> response) {
//                try {
//                    if (response.body().getData().equals("1")){
//
//                    }else{
//                        Toast.makeText(MainActivity.this, "Kesalahan Server Jaringan", Toast.LENGTH_SHORT).show();
//                    }
//                }catch (Exception e){
////                    Toast.makeText(MainActivity.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseDestiny> call, Throwable t) {
////                Toast.makeText(MainActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
//            }
//        });
    }
//    public void showAdIfAvailable() {
//        // Only show ad if there is not already an app open ad currently showing
//        // and an ad is available.
//        if (!isShowingAd && isAdAvailable()) {
//            FullScreenContentCallback fullScreenContentCallback =
//                    new FullScreenContentCallback() {
//                        @Override
//                        public void onAdDismissedFullScreenContent() {
//                            // Set the reference to null so isAdAvailable() returns false.
//                            appOpenAd = null;
//                            isShowingAd = false;
//                            Logic();
//                        }
//
//                        @Override
//                        public void onAdFailedToShowFullScreenContent(AdError adError) {
//                            Logic();
//                        }
//
//                        @Override
//                        public void onAdShowedFullScreenContent() {
//                            isShowingAd = true;
//                        }
//                    };
//
//            appOpenAd.setFullScreenContentCallback(fullScreenContentCallback);
//            appOpenAd.show(MainActivity.this);
//        } else {
//            fetchAd();
//        }
//    }
//    public boolean isAdAvailable() {
//        return appOpenAd != null;
//    }
//    public void fetchAd() {
//        // Have unused ad, no need to fetch another.
//        if (isAdAvailable()) {
//            return;
//        }
//
//        loadCallback =
//                new AppOpenAd.AppOpenAdLoadCallback() {
//                    /**
//                     * Called when an app open ad has loaded.
//                     *
//                     * @param ad the loaded app open ad.
//                     */
//                    @Override
//                    public void onAdLoaded(AppOpenAd ad) {
//                        appOpenAd = ad;
//                        loadTime = (new Date()).getTime();
//                        appOpenAd.show(MainActivity.this);
//                        Logic();
//                    }
//
//                    /**
//                     * Called when an app open ad has failed to load.
//                     *
//                     * @param loadAdError the error.
//                     */
//                    @Override
//                    public void onAdFailedToLoad(LoadAdError loadAdError) {
//                        // Handle the error.
//                        Log.d("AD ERROR : ",loadAdError.toString());
//                        Logic();
//                    }
//
//                };
//        AdRequest request = getAdRequest();
//        AppOpenAd.load(
//                MainActivity.this, destiny.BelajarOpening(), request,
//                AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT, loadCallback);
//    }
//    /** Creates and returns ad request. */
//    private AdRequest getAdRequest() {
//        return new AdRequest.Builder().build();
//    }
    private void AD(){
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(this,destiny.BelajarADIntersential(), adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                // The mInterstitialAd reference will be null until
                // an ad is loaded.
                mInterstitialAd = interstitialAd;
                Log.i("<ADMOB>", "onAdLoaded");
                if (mInterstitialAd !=null){
                    mInterstitialAd.show(MainActivity.this);
                }else{
                    Logic();
                }
                mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                    @Override
                    public void onAdDismissedFullScreenContent() {
                        // Called when fullscreen content is dismissed.
                        Log.d("TAG", "The ad was dismissed.");
                        Logic();
//                        Intent intent = new Intent(MainActivity.this,HomeActivity.class);
//                        startActivity(intent);
//                        finishAffinity();
                    }

                    @Override
                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                        // Called when fullscreen content failed to show.
                        Log.d("AD Error : ", adError.toString());
                        dbHelper.SaveCountADS(String.valueOf(Integer.parseInt(Count+1)));
                        if (Integer.parseInt(Count+1) > 3){
                            dbHelper.ResetADS();
                            dbHelper.SaveCountADS("0");
                        }
                        Logic();
                    }

                    @Override
                    public void onAdShowedFullScreenContent() {
                        // Called when fullscreen content is shown.
                        // Make sure to set your reference to null so you don't
                        // show it a second time.
                        mInterstitialAd = null;
                        Log.d("TAG", "The ad was shown.");
//                        Logic();
                    }
                });
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                // Handle the error
//                Toast.makeText(MainActivity.this, "AD ERROR ?", Toast.LENGTH_SHORT).show();
                Log.i("TAG", loadAdError.getMessage());
                mInterstitialAd = null;
                Logic();
            }
        });

    }
    private void Logic(){
        dbHelper.SaveCountADS(String.valueOf(Integer.parseInt(Count+1)));
        if (Integer.parseInt(Count+1) > 3){
            dbHelper.ResetADS();
            dbHelper.SaveCountADS("0");
        }
        final Handler handler = new Handler();
        Cursor cursor = dbHelper.checkUser();
        if (cursor.getCount()>0){
            handler.postDelayed(new Runnable() {
                public void run() {
                    Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 0);
        }else{
            handler.postDelayed(new Runnable() {
                public void run() {
                    Intent intent = new Intent(MainActivity.this, SplashActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 3000); //3000 L = 3 detik
        }
    }
}