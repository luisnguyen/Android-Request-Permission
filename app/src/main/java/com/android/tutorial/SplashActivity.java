package com.android.tutorial;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.tutorial.utilities.Constants;
import com.android.tutorial.utilities.Utils;

public class SplashActivity extends AppCompatActivity {

    private static String TAG = SplashActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { // No need because minSdkVersion is greater than 23
            if (Utils.checkPermissions(getApplicationContext())) {
                launchCameraApplication();
            } else{
                Log.e(TAG, getResources().getString(R.string.error_permission));
                Utils.requestPermissions(this);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case Constants.FULL_PERMISSIONS:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    launchCameraApplication();
                } else {
                    Log.e(TAG, getResources().getString(R.string.error_permission));
                }
                break;
        }
    }

    private void launchCameraApplication() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePictureIntent, Constants.CAMERA_REQUEST);
    }
}
