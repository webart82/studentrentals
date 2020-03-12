package com.student.rentals.ui.activities.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.student.Utils.Constants;
import com.student.rentals.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MediaContentsActivity extends AppCompatActivity {

    private static final String TAG = "MediaContentsActivity";
    @BindView(R.id.lnrImages)
    LinearLayout lnrImages;
    private ArrayList<String> imagesPathList;
    private Bitmap yourbitmap;
    private Bitmap resized;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_contents);
        ButterKnife.bind(this);

        Intent intent = new Intent(this,CustomPhotoGalleryActivity.class);
        startActivityForResult(intent,Constants.INSTANCE.getPICK_IMAGE_MULTIPLE());
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, @Nullable final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if(requestCode == Constants.INSTANCE.getPICK_IMAGE_MULTIPLE()){
                imagesPathList = new ArrayList<String>();
                String[] imagesPath = data.getStringExtra("data").split("\\|");
                try{
                    lnrImages.removeAllViews();
                }catch (Throwable e){
                    e.printStackTrace();
                }
                for (int i=0;i<imagesPath.length;i++){
                    imagesPathList.add(imagesPath[i]);
                    yourbitmap = BitmapFactory.decodeFile(imagesPath[i]);
                    ImageView imageView = new ImageView(this);
                    imageView.setImageBitmap(yourbitmap);
                    imageView.setAdjustViewBounds(true);
                    lnrImages.addView(imageView);
                }
            }
        }
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
