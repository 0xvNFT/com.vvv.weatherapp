package com.vvv.weatherapp;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout bottomNavigation = findViewById(R.id.bottomNavigation);
        getWindow().setBackgroundDrawable(null);
        Drawable windowBackground = getWindow().getDecorView().getBackground();
        BitmapDrawable blurredBackground = blurDrawable(windowBackground, 25); // Adjust the blur intensity as needed
        bottomNavigation.setBackground(blurredBackground);
    }

    private BitmapDrawable blurDrawable(Drawable drawable, int radius) {
        if (drawable == null) {
            return null;
        }
        Bitmap bitmap = drawableToBitmap(drawable);
        RenderScript rs = RenderScript.create(this);
        Allocation input = Allocation.createFromBitmap(rs, bitmap, Allocation.MipmapControl.MIPMAP_NONE, Allocation.USAGE_SCRIPT);
        Allocation output = Allocation.createTyped(rs, input.getType());
        ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        script.setInput(input);
        script.setRadius(radius);
        script.forEach(output);
        output.copyTo(bitmap);
        return new BitmapDrawable(getResources(), bitmap);
    }

    private Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        if (width <= 0 || height <= 0) {
            width = 1;
            height = 1;
        }
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        return bitmap;
    }
}

