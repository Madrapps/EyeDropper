package com.madrapps.eyedropperapp.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.madrapps.eyedropperapp.R;

public class CustomView extends View {

    private Bitmap bitmap;

    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        bitmap = ((BitmapDrawable) context.getResources().getDrawable(R.drawable.colorwheel)).getBitmap();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.scale(0.7f, 0.7f);
        if (bitmap != null) {
            canvas.drawBitmap(bitmap, 0, 0, null);
        }
    }
}
