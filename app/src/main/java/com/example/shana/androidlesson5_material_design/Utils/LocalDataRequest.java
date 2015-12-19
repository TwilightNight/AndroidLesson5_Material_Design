package com.example.shana.androidlesson5_material_design.Utils;

import android.content.Context;

import com.google.common.base.Optional;

import java.io.InputStream;

/**
 * Created by shana on 2015/12/17.
 */
public class LocalDataRequest implements DataRequest {
    String fileName;
    Context context;

    public LocalDataRequest(Context context, String location) {
        this.context = context;
        this.fileName = location;
    }

    @Override
    public Optional<String> execute() {
        String data = StringConvert.readInputStream(getStreamAtLocation());
        if (data == null || data.length() == 0) {
            return Optional.absent();
        }
        return Optional.of(data);
    }

    private InputStream getStreamAtLocation() {
        delayTime(1);
        return context.getResources().openRawResource(context.getResources().getIdentifier(fileName, "raw", context.getPackageName()));
    }

    private void delayTime(int delaySecond) {
        try {
            Thread.sleep(delaySecond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
