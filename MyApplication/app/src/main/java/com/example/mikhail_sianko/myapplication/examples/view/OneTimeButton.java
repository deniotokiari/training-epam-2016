package com.example.mikhail_sianko.myapplication.examples.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

public class OneTimeButton extends Button {

    private OnClickListener onClickListener;
    private OnClickListener mineClickListener;

    public OneTimeButton(final Context context) {
        super(context);
        init();
    }

    public OneTimeButton(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public OneTimeButton(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public OneTimeButton(final Context context, final AttributeSet attrs, final int defStyleAttr, final int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        mineClickListener = new OnClickListener() {

            @Override
            public void onClick(final View v) {
                setVisibility(View.GONE);
                if (onClickListener != null) {
                    onClickListener.onClick(v);
                }
            }
        };
        setOnClickListener(mineClickListener);

    }

    @Override
    public void setOnClickListener(final OnClickListener l) {
        if (l.equals(mineClickListener)) {
            super.setOnClickListener(l);
        } else {
            onClickListener = l;
        }
    }
}
