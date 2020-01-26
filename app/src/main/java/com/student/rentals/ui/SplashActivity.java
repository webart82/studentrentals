package com.student.rentals.ui;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.model.LatLng;
import com.student.Utils.SharedPreferencesManager;
import com.student.rentals.ui.activities.LoginActivity.View.LoginActivity;
import com.student.rentals.ui.activities.MainActivity;

import java.io.IOException;
import java.util.List;

public class SplashActivity extends AppCompatActivity {
    private SharedPreferencesManager preferencesManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferencesManager = new SharedPreferencesManager(getApplicationContext());

        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {
                Intent i;
                if (preferencesManager.getBoolean(SharedPreferencesManager.Key.IS_USER_LOGGED_IN)) {
                    i = new Intent(SplashActivity.this, MainActivity.class);
                }else {
                    i = new Intent(SplashActivity.this, LoginActivity.class);
                }
                startActivity(i);
                finish();
            }
        }, 1000);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    public String getUserLocationName(LatLng latLng){
        String localityString = null;
        Geocoder geocoder = new Geocoder(this);

        try {
            List<Address> addresses = geocoder.getFromLocation(latLng.latitude,latLng.longitude,1);


            Log.d("ADDRESS", addresses.toString());
            if (geocoder.isPresent()) {
                StringBuilder stringBuilder = new StringBuilder();

                    Address returnAddress = addresses.get(0);

                    localityString = returnAddress.getLocality();

            } else {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return localityString;
    }
}
