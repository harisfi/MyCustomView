package com.hryzx.mycustomview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

public class MyButton extends AppCompatButton {
    private Drawable edabledBg, disabledBg;
    private int textColor;

    public MyButton(@NonNull Context context) {
        super(context);
        init();
    }

    public MyButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setBackground(isEnabled() ? edabledBg : disabledBg);
        setTextColor(textColor);
        setTextSize(12.f);
        setGravity(Gravity.CENTER);
        setText(isEnabled() ? "Submit" : "Isi Dulu");
    }
    private void init(){
        textColor = ContextCompat.getColor(getContext(), android.R.color.background_light);
        edabledBg = getResources().getDrawable(R.drawable.bg_button);
        disabledBg = getResources().getDrawable(R.drawable.bg_button_disabled);
    }
}
