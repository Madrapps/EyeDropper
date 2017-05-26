package com.madrapps.eyedropperapp;

import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.madrapps.eyedropper.EyeDropper;

import org.jetbrains.annotations.NotNull;

public class CustomViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
        setUp();
    }

    private void setUp() {
        final View targetView = findViewById(R.id.customView);
        final EyeDropper eyeDropper = new EyeDropper(targetView, new EyeDropper.ColorSelectionListener() {
            @Override
            public void onColorSelected(@ColorInt int color) {
                (findViewById(R.id.colorSlot)).setBackgroundColor(color);
            }
        });

        eyeDropper.setSelectionListener(new EyeDropper.SelectionListener() {
            @Override
            public void onSelectionStart(@NotNull MotionEvent event) {
                Log.d("MainActivity", "selection started");
            }

            @Override
            public void onSelectionEnd(@NotNull MotionEvent event) {
                Log.d("MainActivity", "selection ended");
            }
        });
    }
}
