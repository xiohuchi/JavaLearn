package com.app.jwl;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import com.app.jwl.util.DBUtils;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import greendao.DaoMaster;
import greendao.DaoSession;

/**
 * Author：YangBin
 * Time：2017/2/21.
 * Email：1250211588@qq.com
 * explain：
 */

public class App extends Application {
    private static final String DBName = "yangbin";
    private static PackageInfo packInfo;
    private static List<Activity> activityList = new LinkedList();
    private static Context mContext;
    private static Toast mToast;
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;
    private static SQLiteDatabase db;
    /**
     * 单例对象
     */
    private static App instance;

    public static App getIns() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        instance = this;
//        copyRawDB();
    }

    //添加Activity到容器中
    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    //从容器中remove
    public void removeActivity(Activity activity) {
        activityList.remove(activity);
    }

    public static void finishActivityNokill() {
        try {
            for (Activity activity : activityList) {
                if (null != activity) {
                    activity.finish();
                }
            }
        } catch (Exception e) {
            Log.e("Exception", e + "");
        }
    }


    public static Context getmContext() {
        return mContext;
    }

    public static void showToast(String text, int duration) {
        if (mToast != null)
            mToast.setText(text);
        else
            mToast = Toast.makeText(mContext, text, duration);
        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.show();
    }

    public static void showToast(final String msg) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            new Handler(Looper.getMainLooper()).post(() -> showToast(msg, Toast.LENGTH_SHORT));
        } else {
            showToast(msg, Toast.LENGTH_SHORT);
        }
    }

    /**
     * 取得Db
     */
    public static SQLiteDatabase getDb() {
        if (db == null) {
            DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(new GreenDaoContextWrapper(mContext), DBName, null);
            db = helper.getWritableDatabase();
        }
        return db;
    }

    /**
     * 取得DaoMaster
     */
    public static DaoMaster getDaoMaster() {
        if (daoMaster == null) {
            daoMaster = new DaoMaster(getDb());
        }
        return daoMaster;
    }

    /**
     * 取得DaoSession
     */
    public static DaoSession getDaoSession() {
        if (daoSession == null) {
            if (daoMaster == null) {
                daoMaster = getDaoMaster();
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }

    /**
     * 获取apk包名路径
     */
    @SuppressLint("Override")
    public String getAppDataDir() {
        if (packInfo == null)
            getAppInfo();
        return packInfo != null && packInfo.applicationInfo != null ? packInfo.applicationInfo.dataDir : "";
    }

    private void getAppInfo() {
        // 获取packageManager的实例
        PackageManager packageManager = getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        try {
            packInfo = packageManager.getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void selDBData() {

    }

    private void copyRawDB() {
        try {
            // 拷贝res/raw/xxxxdb.zip 到
            // /data/data/com.xinhang.mobileclient/databases/ 目录下面
            boolean isSuccess = DBUtils.copyRawDBToApkDb(this, R.raw.javareference, DBUtils.APK_DB_PATH, DBUtils.ECMC_DB_NAME, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
