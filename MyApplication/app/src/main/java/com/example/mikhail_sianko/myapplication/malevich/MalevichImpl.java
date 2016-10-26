package com.example.mikhail_sianko.myapplication.malevich;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.widget.ImageView;

import com.example.mikhail_sianko.myapplication.threads.OnResultCallback;
import com.example.mikhail_sianko.myapplication.threads.Operation;
import com.example.mikhail_sianko.myapplication.threads.ProgressCallback;
import com.example.mikhail_sianko.myapplication.threads.ThreadManager;
import com.training2016.android.http.HttpClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
        this.lruCache = new LruCache<String, Bitmap>(Math.min((int) (Runtime.getRuntime().maxMemory() / 4), MAX_MEMORY_FOR_IMAGES)) {

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
        threadManager.execute(bitmapOperation, new ImageData(imageUrl, imageView.getMeasuredWidth(), imageView.getMeasuredHeight()), new BitmapResultCallback(imageUrl, imageView) {

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

    private static class ImageData {
        String url;
        int w;
        int h;

        public ImageData(String url, int w, int h) {
            this.url = url;
            this.w = w;
            this.h = h;
        }
    }

    private static class BitmapOperation implements Operation<ImageData, Void, Bitmap> {

        @Override
        public Bitmap doing(final ImageData data
                , final ProgressCallback<Void> progressCallback) throws Exception {
            //TODO add file cache
            return new HttpClient().getResult(data.url, new HttpClient.ResultConverter<Bitmap>() {

                @Override
                public Bitmap convert(final InputStream inputStream) {

                    try {
                        return getScaleBitmap(inputStream, data.w, data.h);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return null;
                }


            });
        }

        private Bitmap getScaleBitmap(InputStream inputStream, int w, int h) throws IOException {

            // First decode with inJustDecodeBounds=true to check dimensions
            final BitmapFactory.Options options = new BitmapFactory.Options();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(inputStream.available());
            byte[] chunk = new byte[1 << 16];
            int bytesRead;
            while ((bytesRead = inputStream.read(chunk)) > 0) {
                byteArrayOutputStream.write(chunk, 0, bytesRead);
            }
            byte[] bytes = byteArrayOutputStream.toByteArray();

            options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);

            // Calculate inSampleSize
            options.inSampleSize = calculateInSampleSize(options, w, h);

            // Decode bitmap with inSampleSize set
            options.inJustDecodeBounds = false;
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
            return bitmap;
        }

        public static int calculateInSampleSize(
                BitmapFactory.Options options, int reqWidth, int reqHeight) {
            // Raw height and width of image
            final int height = options.outHeight;
            final int width = options.outWidth;
            int inSampleSize = 1;

            if (height > reqHeight || width > reqWidth) {

                int halfHeight = height / 2;
                int halfWidth = width / 2;

                // Calculate the largest inSampleSize value that is a power of 2 and keeps both
                // height and width larger than the requested height and width.
                while (halfHeight > reqHeight
                        && halfWidth > reqWidth) {
                    inSampleSize *= 2;
                    halfHeight /= 2;
                    halfWidth /= 2;
                }
            }
            Log.d(TAG, "calculateInSampleSize: " + inSampleSize);
            return inSampleSize;
        }

        private static final String TAG = "BitmapOperation";
    }
}
