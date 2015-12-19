package com.example.shana.androidlesson5_material_design.Utils;

import com.google.common.base.Optional;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by shana on 2015/12/19.
 */
public class UrlDataRequest implements DataRequest {
    String urlAddress;
    public UrlDataRequest(String urlAddress) {
        this.urlAddress = urlAddress;
    }

    @Override
    public Optional<String> execute() {
        InputStream inputStream = null;
        try {
            inputStream = new URL(urlAddress).openConnection().getInputStream();
            return Optional.of(StringConvert.readInputStream(inputStream));
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
