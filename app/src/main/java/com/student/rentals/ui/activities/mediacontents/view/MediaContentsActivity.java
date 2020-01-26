package com.student.rentals.ui.activities.mediacontents.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.student.rentals.R;

public class MediaContentsActivity extends AppCompatActivity {
    private static final String TAG = "MediaContentsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_contents);
    }



    /**
     * Make activity visible to the user
     * Prepare the app to enter foreground and become interactive
     **/
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, getResources().getString(R.string.on_start));
    }

    /**
     * It comes to the foreground
     * The app is now interactive to user
     **/
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, getResources().getString(R.string.on_resume));

    }

    /**
     * User is leaving us
     **/
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, getResources().getString(R.string.on_pause));
    }

    /**
     * Activity is no longer visible to user
     **/
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, getResources().getString(R.string.on_stop));
    }

    /**
     * before activity is destroyed
     **/
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, getResources().getString(R.string.on_destroy));
    }
}
