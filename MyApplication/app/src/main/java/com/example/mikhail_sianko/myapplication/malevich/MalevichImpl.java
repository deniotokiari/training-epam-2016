package com.example.mikhail_sianko.myapplication.malevich;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;
import android.widget.ImageView;

import com.example.mikhail_sianko.myapplication.threads.OnResultCallback;
import com.example.mikhail_sianko.myapplication.threads.Operation;
import com.example.mikhail_sianko.myapplication.threads.ProgressCallback;
import com.example.mikhail_sianko.myapplication.threads.ThreadManager;
import com.training2016.android.http.HttpClient;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.concurrent.Executors;

class MalevichImpl implements Malevich {

    public static final int MAX_MEMORY_FOR_IMAGES = 64 * 1000 * 1000;

    //TODO queue LIFO
    private ThreadManager threadManager = new ThreadManager(Executors.newFixedThreadPool(ThreadManager.COUNT_CORE));
    private BitmapOperation bitmapOperation = new BitmapOperation();
    private final LruCache<String, Bitmap> lruCache;
    private final Object lock = new Object();

    MalevichImpl() {
        this.lruCache = new LruCache<String, Bitmap>(Math.min((int) (Runtime.getRuntime().maxMemory()/4), MAX_MEMORY_FOR_IMAGES)) {

            @Override
            protected int sizeOf(final String key, final Bitmap value) {
                return key.length() + value.getByteCount();
            }

        };
    }

    @Override
    public void drawBitmap(final ImageView imageView, final String imageUrl) {
        synchronized (lock) {
            final Bitmap bitmap = lruCache.get(imageUrl);
            if (bitmap != null) {
                imageView.setImageBitmap(bitmap);
            }
        }
        //TODO check if we already started loading
        threadManager.execute(bitmapOperation, imageUrl, new BitmapResultCallback(imageUrl, imageView) {

            @Override
            public void onSuccess(final Bitmap bitmap) {
                synchronized (lock) {
                    if (bitmap != null) {
                        lruCache.put(imageUrl, bitmap);
                    }
                }
                super.onSuccess(bitmap);
            }
        });
    }

    private static class BitmapResultCallback implements OnResultCallback<Bitmap, Void> {

        private String value;
        private final WeakReference<ImageView> imageViewReference;

        public BitmapResultCallback(final String value, final ImageView imageView) {
            this.value = value;
            this.imageViewReference = new WeakReference<ImageView>(imageView);
            imageView.setTag(value);
        }

        @Override
        public void onSuccess(final Bitmap bitmap) {
            ImageView imageView = this.imageViewReference.get();
            if (imageView != null) {
                Object tag = imageView.getTag();
                if (tag != null && tag.equals(value)) {
                    imageView.setImageBitmap(bitmap);
                }
            }
        }

        @Override
        public void onError(final Exception e) {
            e.printStackTrace();
        }

        @Override
        public void onProgressChanged(final Void aVoid) {

        }
    }

    private static class BitmapOperation implements Operation<String, Void, Bitmap> {

        @Override
        public Bitmap doing(final String s, final ProgressCallback<Void> progressCallback) throws Exception {
            //TODO add file cache
            //TODO add resizing
            return new HttpClient().getResult(s, new HttpClient.ResultConverter<Bitmap>() {

                @Override
                public Bitmap convert(final InputStream inputStream) {
                    return BitmapFactory.decodeStream(inputStream);
                }

            });
        }

    }
}
