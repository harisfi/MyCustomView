package com.hryzx.mycustomview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.res.ResourcesCompat;

public class MyEditText extends AppCompatEditText {
    Drawable clearButtonImage;

    public MyEditText(@NonNull Context context) {
        super(context);
        init();
    }

    public MyEditText(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyEditText(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setHint("Masukkan nama Anda");
        setTextAlignment(TEXT_ALIGNMENT_VIEW_START);
    }

    private void init() {
        clearButtonImage = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_close_24, null);

        setOnTouchListener(new OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if ((getCompoundDrawablesRelative()[2] != null)) {
                    float clearButtonStart;
                    float clearButtonEnd;
                    boolean isClearButtonClicked = false;
                    if (getLayoutDirection() == LAYOUT_DIRECTION_RTL) {
                        clearButtonEnd = clearButtonImage.getIntrinsicWidth() + getPaddingStart();
                        if (event.getX() < clearButtonEnd) {
                            isClearButtonClicked = true;
                        }
                    } else {
                        clearButtonStart = (getWidth() - getPaddingEnd() - clearButtonImage.getIntrinsicWidth());
                        if (event.getX() > clearButtonStart) {
                            isClearButtonClicked = true;
                        }
                    }
                    if (isClearButtonClicked) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
                            clearButtonImage = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_close_24, null);
                            showClearButton();
                            return true;
                        } else if (event.getAction() == MotionEvent.ACTION_UP) {
                            clearButtonImage = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_close_24, null);
                            if (getText() != null) {
                                getText().clear();
                            }
                            hideClearButton();
                            return true;
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                }
                return false;
            }
        });
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().isEmpty()) {
                    showClearButton();
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
    private void showClearButton() {
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, clearButtonImage, null);
    }
    private void hideClearButton() {
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, null, null);
    }
}
