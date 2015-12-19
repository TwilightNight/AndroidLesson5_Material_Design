package com.example.shana.androidlesson5_material_design.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.google.common.base.Optional;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by shana on 2015/12/19.
 */
public class ImageDataRequest implements DataRequest{
    String urlAddress;
    public ImageDataRequest(String urlAddress) {
        this.urlAddress = urlAddress;
    }

    @Override
    public Optional<Bitmap> execute() {
        InputStream inputStream = null;
        try {
            inputStream = new URL(urlAddress).openConnection().getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            return Optional.of(bitmap);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return Optional.absent();
    }
}
