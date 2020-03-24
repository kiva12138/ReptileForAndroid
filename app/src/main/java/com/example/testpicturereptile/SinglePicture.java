package com.example.testpicturereptile;

public class SinglePicture {
    private String name;
    private int imageId;

    public SinglePicture(String name, int imageId){
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }
}
