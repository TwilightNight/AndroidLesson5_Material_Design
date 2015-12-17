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
        String data = convertStreamToString(getStreamAtLocation());
        if (data == null || data.length() == 0) {
            return Optional.absent();
        }
        return Optional.of(data);
    }

    private InputStream getStreamAtLocation() {
        return context.getResources().openRawResource(context.getResources().getIdentifier(fileName, "raw", context.getPackageName()));
    }

    private String convertStreamToString(InputStream inputStream) {
        java.util.Scanner s = new java.util.Scanner(inputStream).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
