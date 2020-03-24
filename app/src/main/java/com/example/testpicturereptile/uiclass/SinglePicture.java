package com.example.testpicturereptile.uiclass;

import android.graphics.Bitmap;

public class SinglePicture {
    private String name;
    private Bitmap image;

    public SinglePicture(String name, Bitmap image){
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public Bitmap getImage() {
        return image;
    }
}
