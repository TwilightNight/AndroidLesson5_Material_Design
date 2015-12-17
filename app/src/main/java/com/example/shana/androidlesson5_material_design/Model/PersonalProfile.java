package com.example.shana.androidlesson5_material_design.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shana on 2015/12/16.
 */
public class PersonalProfile {
    @SerializedName("name")
    private String name;
    @SerializedName("age")
    private int age;
    @SerializedName("photo")
    private String photo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
