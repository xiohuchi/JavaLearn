package com.app.jwl;

import android.content.Context;
import android.content.ContextWrapper;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.app.jwl.util.DBUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Author：YangBin
 * Time：2017/2/21.
 * Email：1250211588@qq.com
 * explain：
 */

public class GreenDaoContextWrapper extends ContextWrapper {

    private Context mContext;

    public GreenDaoContextWrapper(Context base) {
        super(base);
        this.mContext = base;
    }

    @Override
    public File getDatabasePath(String name) {
        Log.d("GreenDao", "getDatabasePath");
        Log.d("GreenDao", mContext.getDatabasePath(name).getAbsolutePath());
        String filePath = mContext.getDatabasePath(name).getAbsolutePath();
        File file = new File(filePath);
        if (!file.exists()) {
            File f = new File(mContext.getDatabasePath(name).getParentFile().getPath());
            if (!f.exists()) {
                f.mkdirs();
            }
            buildDatabase(filePath);
        }
        return file;
    }

    /**
     * 创建数据库文件，其实就是将raw文件夹下的数据库文件复制到应用的database文件夹下：
     * /data/data/com.xxxx/databases/
     *
     * @param filePath
     */

    private void buildDatabase(String filePath) {
        Log.d("GreenDao", "buildDatabase");
        InputStream inputStream = mContext.getResources().openRawResource(R.raw.javareference);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(filePath);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }
            fos.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory) {
        Log.d("GreenDao", "openOrCreateDatabase");
        SQLiteDatabase result = SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), factory);
        return result;
    }

    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory, DatabaseErrorHandler errorHandler) {
        Log.d("GreenDao", "openOrCreateDatabase");
        SQLiteDatabase result = SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), factory);
        return result;
    }
}