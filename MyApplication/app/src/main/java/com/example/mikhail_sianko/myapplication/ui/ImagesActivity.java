package com.example.mikhail_sianko.myapplication.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.mikhail_sianko.myapplication.App;
import com.example.mikhail_sianko.myapplication.R;
import com.example.mikhail_sianko.myapplication.malevich.Malevich;

public class ImagesActivity extends AppCompatActivity {

    private static String[] IMAGE_URLS =
            {
                    "http://makeitlast.se/wp-content/uploads/2015/10/loppis_12.jpg",
                    "https://images-na.ssl-images-amazon.com/images/G/01/img15/pet-products/small-tiles/30423_pets-products_january-site-flip_3-cathealth_short-tile_592x304._CB286975940_.jpg",
                    "https://s-media-cache-ak0.pinimg.com/236x/8a/1b/7c/8a1b7c35091025bf2417ce2d9a6b058d.jpg",
                    "https://cnet4.cbsistatic.com/hub/i/2011/10/27/a66dfbb7-fdc7-11e2-8c7c-d4ae52e62bcc/android-wallpaper5_2560x1600_1.jpg",
                    "https://www.android.com/static/img/home/more-from-2.png",
                    "http://www.howtablet.ru/wp-content/uploads/2016/04/%D0%9E%D0%B1%D0%BD%D0%BE%D0%B2%D0%BB%D0%B5%D0%BD%D0%B8%D0%B5-Android-6.0.1-Marshmallow.jpg",
                    "http://keddr.com/wp-content/uploads/2015/12/iOS-vs-Android.jpg",
                    "http://shushi168.com/data/out/8/37224223-android-wallpaper.jpg",
                    "https://www.android.com/static/img/history/features/feature_icecream_3.png",
                    "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcRfZ5OiAt7GIz57jyvjK8ca82pIvgd7pvD-3JyPG73ppN8FbqpbUA",
                    "http://androidwallpape.rs/content/02-wallpapers/131-night-sky/wallpaper-2707591.jpg"
            };

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);
        final ImageView topImageView = (ImageView) findViewById(R.id.imageView);
        final Malevich malevich = ((App) getApplication()).getMalevich();
        malevich.drawBitmap(topImageView, IMAGE_URLS[0]);
        ListView listView = (ListView) findViewById(android.R.id.list);
        //TODO pause on scroll
        listView.setAdapter(new ArrayAdapter<String>(this, R.layout.adapter_image, android.R.id.text1, IMAGE_URLS) {

            @NonNull
            @Override
            public View getView(final int position, final View convertView, final ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                ImageView imageView = (ImageView) view.findViewById(R.id.image);
                String imageUrl = IMAGE_URLS[position];
//                Picasso picasso = Picasso.with(imageView.getContext());
//                picasso.setIndicatorsEnabled(true);
//                picasso.load(imageUrl).error(R.color.colorAccent).fit().into(imageView);

//                ImageLoader.getInstance().displayImage(imageUrl, imageView);
                malevich.drawBitmap(imageView, imageUrl);
                return view;
            }
        });
    }



}
