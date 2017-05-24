package com.madrapps.eyedropper;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class EyeDropper {

    private static final Matrix INVERT_MATRIX = new Matrix();
    private static final int NO_COLOR = Color.TRANSPARENT;

    private View targetView;
    private ColorSelectionListener colorListener;
    private int xTouch;
    private int yTouch;
    private SelectionListener selectionListener;

    public EyeDropper(@NonNull View view, @NonNull ColorSelectionListener listener) {
        colorListener = listener;
        setTargetView(view);
        setTouchListener();
    }

    private boolean shouldDrawingCacheBeEnabled(@NonNull View targetView) {
        return !(targetView instanceof ImageView) && !targetView.isDrawingCacheEnabled();
    }

    private void setTouchListener() {
        targetView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int action = event.getActionMasked();
                xTouch = (int) event.getX();
                yTouch = (int) event.getY();

                handleSelectionStart(event, action);
                notifyColorSelected(getColorAtPoint(xTouch, yTouch));
                handleSelectionEnd(event, action);
                return true;
            }
        });
    }

    private void handleSelectionEnd(MotionEvent event, int action) {
        if (selectionListener != null && action == MotionEvent.ACTION_UP) {
            selectionListener.onSelectionEnd(event);
        }
    }

    private void handleSelectionStart(MotionEvent event, int action) {
        if (selectionListener != null && action == MotionEvent.ACTION_DOWN) {
            selectionListener.onSelectionStart(event);
        }
    }

    private int getColorAtPoint(int x, int y) {
        if (targetView instanceof ImageView) {
            return handleIfImageView(x, y);
        } else {
            final Bitmap drawingCache = targetView.getDrawingCache();
            return getPixelAtPoint(drawingCache, x, y);
        }
    }

    private int handleIfImageView(int x, int y) {
        final ImageView targetImageView = (ImageView) this.targetView;
        final Drawable drawable = targetImageView.getDrawable();
        if (drawable instanceof BitmapDrawable) {
            targetImageView.getImageMatrix().invert(INVERT_MATRIX);
            final float[] mappedPoints = new float[]{x, y};
            INVERT_MATRIX.mapPoints(mappedPoints);

            final Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            return getPixelAtPoint(bitmap, (int) mappedPoints[0], (int) mappedPoints[1]);
        }
        return NO_COLOR;
    }

    private int getPixelAtPoint(Bitmap bitmap, int x, int y) {
        if (isValidPoint(x, y, bitmap)) {
            return bitmap.getPixel(x, y);
        }
        return NO_COLOR;
    }

    private boolean isValidPoint(int x, int y, Bitmap bitmap) {
        final int width = bitmap.getWidth();
        final int height = bitmap.getHeight();

        return isValidCoordinate(x, width) && isValidCoordinate(y, height);
    }

    private boolean isValidCoordinate(int coordinate, int size) {
        return coordinate > 0 && coordinate < size;
    }

    private void notifyColorSelected(int color) {
        if (colorListener != null) {
            colorListener.onColorSelected(color);
        }
    }

    private void setTargetView(@NonNull final View targetView) {
        this.targetView = targetView;
        if (shouldDrawingCacheBeEnabled(targetView)) {
            targetView.setDrawingCacheEnabled(true);
            targetView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);
        }
    }

    /**
     * Register a callback to be invoked when the color selection begins or ends.
     */
    public void setSelectionListener(@NonNull SelectionListener listener) {
        this.selectionListener = listener;
    }

    /**
     * Optional listener to listen to before and after selection events
     */
    public interface SelectionListener {
        /**
         * Invoked when the user touches the view to select a color. This corresponds to the {@link
         * MotionEvent#ACTION_DOWN} event.
         */
        void onSelectionStart(@NonNull MotionEvent event);

        /**
         * Invoked when the color selection is finished. This corresponds to the {@link MotionEvent#ACTION_UP}
         * event.
         */
        void onSelectionEnd(@NonNull MotionEvent event);
    }

    public interface ColorSelectionListener {
        /**
         * Invoked when a color is selected from the given view
         *
         * @param color the selected color
         */
        void onColorSelected(@ColorInt int color);
    }
}
