package com.madrapps.eyedropperapp;

import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.madrapps.eyedropper.EyeDropper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUp();
    }

    private void setUp() {
        final View targetView = findViewById(R.id.colorSample);
        final EyeDropper eyeDropper = new EyeDropper(targetView, new EyeDropper.ColorSelectionListener() {
            @Override
            public void onColorSelected(@ColorInt int color) {
                (findViewById(R.id.colorSlot)).setBackgroundColor(color);
            }
        });

        eyeDropper.setSelectionListener(new EyeDropper.SelectionListener() {
            @Override
            public void onSelectionStart(@NonNull MotionEvent event) {
                Log.d("MainActivity", "selection started");
            }

            @Override
            public void onSelectionEnd(@NonNull MotionEvent event) {
                Log.d("MainActivity", "selection ended");
            }
        });
    }
}
