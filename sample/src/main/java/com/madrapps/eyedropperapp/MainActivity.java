package com.madrapps.eyedropperapp;

import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

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
        targetView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("MainActivity", "Touch Working");
                return false;
            }
        });
        final EyeDropper eyeDropper = new EyeDropper(targetView, new EyeDropper.ColorSelectionListener() {
            @Override
            public void onColorSelected(@ColorInt int color) {
                ((ImageView)findViewById(R.id.colorSlot)).setBackgroundColor(color);
            }
        });
    }
}
