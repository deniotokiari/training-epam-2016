package com.example.mikhail_sianko.myapplication.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.mikhail_sianko.myapplication.R;

public class MyCustomViewWithLayout extends FrameLayout {

    public MyCustomViewWithLayout(final Context context) {
        super(context);
        init(context, null);
    }

    public MyCustomViewWithLayout(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MyCustomViewWithLayout(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyCustomViewWithLayout(final Context context, final AttributeSet attrs, final int defStyleAttr, final int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }



    private void init(final Context pContext, final AttributeSet pAttrs) {
        View.inflate(pContext, R.layout.view_my_custom_view_with_layout, this);
        Toast.makeText(pContext, "I'm here", Toast.LENGTH_SHORT).show();
        //TODO any custom logic
    }

}
