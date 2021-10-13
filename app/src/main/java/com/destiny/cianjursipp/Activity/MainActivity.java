package com.destiny.cianjursipp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
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
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private InterstitialAd mInterstitialAd;
    Destiny destiny;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        destiny = new Destiny();
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                AD();
            }
        });



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
                        Logic();
                    }

                    @Override
                    public void onAdShowedFullScreenContent() {
                        // Called when fullscreen content is shown.
                        // Make sure to set your reference to null so you don't
                        // show it a second time.
                        mInterstitialAd = null;
                        Log.d("TAG", "The ad was shown.");
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
        final Handler handler = new Handler();
        final DB_Helper dbHelper = new DB_Helper(MainActivity.this);
        Cursor cursor = dbHelper.checkUser();
        if (cursor.getCount()>0){
            Intent intent = new Intent(MainActivity.this,HomeActivity.class);
            startActivity(intent);
            finish();
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