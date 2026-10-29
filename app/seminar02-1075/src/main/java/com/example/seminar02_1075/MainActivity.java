package com.example.seminar02_1075;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "LifecycleLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v(TAG, "onCreate: Activitatea a fost creată");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: Activitatea este vizibilă");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: Activitatea interacționează");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.w(TAG, "onPause: Activitatea este parțial ascunsă");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop: Activitatea a fost oprită");
    }
}