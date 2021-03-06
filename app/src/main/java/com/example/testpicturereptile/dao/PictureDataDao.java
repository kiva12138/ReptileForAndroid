package com.example.testpicturereptile.dao;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import com.example.testpicturereptile.staticdata.StaticData;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PictureDataDao {

    public PictureDataDao () {
        String fileContextPath = Environment.getExternalStorageDirectory().getPath() + StaticData.FILE_PATH;
        File fileContext = new File(fileContextPath);
        if (!fileContext.exists()){
            fileContext.mkdirs();
        }

        String systemPath = Environment.getExternalStorageDirectory().getPath() + StaticData.SYS_PATH;
        File systemContext = new File(systemPath);
        if (!systemContext.exists()){
            systemContext.mkdirs();
        }
    }

    public void saveToGallery(Bitmap bitmap, String name) {
        String filePath = Environment.getExternalStorageDirectory().getPath()
                + StaticData.SYS_PATH + File.separator
                + name + "." + StaticData.JPEG_FILE_NAME;
        File pictureFile = new File(filePath);
        if (pictureFile.exists()){
            pictureFile.delete();
        }
        try {
            pictureFile.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(pictureFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();

        } catch (IOException e) {
            Log.i("IOError", "不能写入文件"+filePath);
            e.printStackTrace();
        }
    }

    public void savePicture(String scriptName, String searchContent, String number, Bitmap bitmap) {
        String filePath = Environment.getExternalStorageDirectory().getPath()
                + StaticData.FILE_PATH + File.separator
                + scriptName + "_"
                + searchContent + "_"
                + number + "."
                + StaticData.PIC_FILE_NAME;
        File pictureFile = new File(filePath);
        if (pictureFile.exists()){
            return;
        }
        try {
            pictureFile.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(pictureFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();

        } catch (IOException e) {
            Log.i("IOError", "不能写入文件"+filePath);
            e.printStackTrace();
        }
    }

    public Bitmap getPicture(String scriptName, String searchContent, String number) {
        Bitmap bitmap = null;
        String filePath = Environment.getExternalStorageDirectory().getPath()
                + StaticData.FILE_PATH + File.separator
                + scriptName + "_"
                + searchContent + "_"
                + number + "."
                + StaticData.PIC_FILE_NAME;
        if (filePath == null || filePath == "") {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        bitmap = BitmapFactory.decodeFile(filePath, options);
        return bitmap;
    }

    public void clearCache() {
        String fileContextPath = Environment.getExternalStorageDirectory().getPath() + StaticData.FILE_PATH + File.separator;
        File fileContext = new File(fileContextPath);
        if (!fileContext.exists()){
            return;
        }
        File[] files = fileContext.listFiles();
        for (int i = 0; i < files.length; i++) {
            File f = files[i];
            f.delete();
        }
    }
}
