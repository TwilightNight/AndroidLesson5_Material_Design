package com.example.shana.androidlesson5_material_design.Utils;

import java.io.InputStream;

/**
 * Created by shana on 2015/12/19.
 */
public class StringConvert {
    public static String readInputStream(InputStream inputStream) {
        java.util.Scanner s = new java.util.Scanner(inputStream).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
