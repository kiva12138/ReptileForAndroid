package com.example.testpicturereptile.dataoperation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import com.example.testpicturereptile.R;
import com.example.testpicturereptile.dao.PictureDataDao;
import com.example.testpicturereptile.uiclass.SinglePicture;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class PictureDataOperation {
    private Context context;
    private PictureDataDao pictureDataDao;

    public PictureDataOperation(Context context) {
        this.context = context;
        this.pictureDataDao = new PictureDataDao();
    }

    public List<SinglePicture> handleSearch(String query, int page, String scriptName){
        // int count = 0;
        // Test Save Pictures (Download pictures)
        List<SinglePicture> newData = new ArrayList<>();

        Bitmap b1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.test1);
        newData.add(new SinglePicture("Name" + UUID.randomUUID().toString(), b1));
        // this.pictureDataDao.savePicture(scriptName, query, String.valueOf(page) + "_" + String.valueOf(count), b1);
        // count ++;

        Bitmap b2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.test4);
        newData.add(new SinglePicture("Name"+ UUID.randomUUID().toString(), b2));
        // this.pictureDataDao.savePicture(scriptName, query, String.valueOf(page) + "_" + String.valueOf(count), b2);
        // count ++;

        Bitmap b3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.test5);
        newData.add(new SinglePicture("Name"+ UUID.randomUUID().toString(), b3));
        // this.pictureDataDao.savePicture(scriptName, query, String.valueOf(page) + "_" + String.valueOf(count), b3);
        // count ++;

        Bitmap b4 = BitmapFactory.decodeResource(context.getResources(), R.drawable.test6);
        newData.add(new SinglePicture("Name"+ UUID.randomUUID().toString(), b4));
        // this.pictureDataDao.savePicture(scriptName, query, String.valueOf(page) + "_" + String.valueOf(count), b4);
        // count ++;

        Bitmap b5 = BitmapFactory.decodeResource(context.getResources(), R.drawable.test7);
        newData.add(new SinglePicture("Name"+ UUID.randomUUID().toString(), b5));
        // this.pictureDataDao.savePicture(scriptName, query, String.valueOf(page) + "_" + String.valueOf(count), b5);
        // count ++;

        return newData;
    }

    public void saveToGallery (Bitmap bitmap, String name) {
        this.pictureDataDao.saveToGallery(bitmap, name);
    }

    public void clearCache() {
        this.pictureDataDao.clearCache();
        Toast.makeText(context, R.string.clear_success, Toast.LENGTH_SHORT).show();
    }
}
