package com.baahubuddy.support;

import android.os.Environment;
import android.util.Log;

import java.io.File;

public class FileReadWriter {

    String TAG = FileReadWriter.class.getName(), fileName = "";

    public FileReadWriter(String fileName) {
        this.fileName = fileName;
    }

    private static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    private static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }
        return false;
    }
}
