package com.example.testpicturereptile.dataoperation;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.example.testpicturereptile.R;
import com.example.testpicturereptile.dao.PictureDataDao;

public class PictureDataOperation {
    private Context context;
    private PictureDataDao pictureDataOperation;

    public PictureDataOperation(Context context) {
        this.context = context;
        this.pictureDataOperation = new PictureDataDao();
    }
    public void handleSearch(String query){
        // Test
        this.pictureDataOperation.savePicture("Hello", query, String.valueOf(0), BitmapFactory.decodeResource(context.getResources(), R.drawable.test1));
        this.pictureDataOperation.savePicture("Hello", query, String.valueOf(1), BitmapFactory.decodeResource(context.getResources(), R.drawable.test4));
        this.pictureDataOperation.savePicture("Hello", query, String.valueOf(2), BitmapFactory.decodeResource(context.getResources(), R.drawable.test5));
        this.pictureDataOperation.savePicture("Hello", query, String.valueOf(3), BitmapFactory.decodeResource(context.getResources(), R.drawable.test6));
        this.pictureDataOperation.savePicture("Hello", query, String.valueOf(4), BitmapFactory.decodeResource(context.getResources(), R.drawable.test7));
    }

    public void clearCache() {
        this.pictureDataOperation.clearCache();
    }
}
