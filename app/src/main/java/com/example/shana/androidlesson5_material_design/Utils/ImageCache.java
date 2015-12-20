package com.example.shana.androidlesson5_material_design.Utils;

import android.graphics.Bitmap;

import com.google.common.base.Optional;

import java.util.LinkedHashMap;

/**
 * Created by shana on 2015/12/19.
 */
public class ImageCache {
    private static final int MAX_CAPACITY = 30;
    private static final ImageCache mInstance = new ImageCache();
    private LinkedHashMap<String, Bitmap> cacheMap;
    public static ImageCache getInstance() {
        return mInstance;
    }

    private ImageCache() {
        cacheMap = new LinkedHashMap<>(MAX_CAPACITY + 1, .75f, true);
    }


    public void set(String url, Bitmap bitmap) {
        cacheMap.put(url, bitmap);
        while (cacheMap.size() > MAX_CAPACITY){
            cacheMap.remove(cacheMap.size() - 1);
        }
    }

    public Optional<Bitmap> get(String url) {
        Bitmap bitmap = cacheMap.get(url);
        if (bitmap == null) {
            return Optional.absent();
        }
        return Optional.of(bitmap);
    }
}
