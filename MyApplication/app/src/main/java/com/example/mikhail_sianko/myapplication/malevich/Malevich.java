package com.example.mikhail_sianko.myapplication.malevich;

import android.widget.ImageView;

public interface Malevich {

    void drawBitmap(final ImageView imageView, final String imageUrl);

    class Impl {

        public static Malevich newInstance() {
            return new MalevichImpl();
        }

    }
}
