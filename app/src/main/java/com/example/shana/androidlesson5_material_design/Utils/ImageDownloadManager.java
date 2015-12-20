package com.example.shana.androidlesson5_material_design.Utils;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.google.common.base.Optional;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Created by shana on 2015/12/19.
 */
public class ImageDownloadManager {
    private static HashMap<ImageView, String> imageMap = new HashMap();
    public static void download(ImageView imageView, String urlAddress) {
        imageView.setImageBitmap(null);
        removeImage(imageView);
        Optional<Bitmap> cacheImage = ImageCache.getInstance().get(urlAddress);
        if (cacheImage.isPresent()) {
            imageView.setImageBitmap(cacheImage.get());
            return;
        }
        prepareDownload(imageView, urlAddress);
    }

    private static void prepareDownload(ImageView imageView, String urlAddress) {
        if (!imageMap.containsValue(urlAddress)){
            startDownload(imageView, urlAddress);
        }
        imageMap.put(imageView, urlAddress);
    }

    private static void startDownload(ImageView imageView, final String urlAddress){
        new DataDownloader<Bitmap>(new DataDownloader.OnDataDownloadFinishListener<Bitmap>() {
            @Override
            public void onFinish(Bitmap result) {
                for (ImageView imageView: getKeysByValue(imageMap, urlAddress)) {
                    imageView.setImageBitmap(result);
                    removeImage(imageView);
                }
            }

            @Override
            public void onFailed(String errorMessage) {
                for (ImageView imageView: getKeysByValue(imageMap, urlAddress)) {
                    removeImage(imageView);
                }
            }
        }).download(new ImageDataRequest(urlAddress));
    }

    private static void removeImage(ImageView imageView) {
        imageMap.remove(imageView);
    }

    public static <T, E> Set<T> getKeysByValue(Map<T, E> map, E value) {
        Set<T> keys = new HashSet<T>();
        for (Map.Entry<T, E> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                keys.add(entry.getKey());
            }
        }
        return keys;
    }
}
