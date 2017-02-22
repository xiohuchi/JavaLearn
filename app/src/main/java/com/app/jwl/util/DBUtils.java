package com.app.jwl.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import android.annotation.SuppressLint;
import android.content.Context;

import com.app.jwl.App;

public class DBUtils {
    /**
     * apk安装路径
     */
    @SuppressLint("NewApi")
    public static final String APK_INSTALL_PATH = App.getIns().getAppDataDir();

    /**
     * apk目录下的数据库路径
     */
    public static final String APK_DB_PATH = APK_INSTALL_PATH + File.separator + "databases/";

    /**
     * db文件名
     */
    public static final String ECMC_DB_NAME = "school.db";

    /**
     * 将raw目录下的db，拷贝到apk安装路径的数据库目录下
     *
     * @param context上下文
     * @param copyRawDbResId将要拷贝的raw资源ID
     * @param apkDbPath                  APK数据库路径
     * @param dbName                     数据库文件名
     * @param refresh                    是否覆盖之前的db文件
     * @return 拷贝是否成功
     * @throws IOException
     */
    public static boolean copyRawDBToApkDb(Context context, int copyRawDbResId, String apkDbPath, String dbName, boolean refresh)
            throws IOException {
        boolean b = false;

        File f = new File(apkDbPath);
        if (!f.exists()) {
            f.mkdirs();
        }

        File dbFile = new File(apkDbPath + dbName);
        b = isDbFileExists(dbFile, refresh);
        if (!b) {
            InputStream is = context.getResources().openRawResource(copyRawDbResId);

            ZipInputStream zis = new ZipInputStream(new BufferedInputStream(is));
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                int size;
                byte[] buffer = new byte[1024 * 2];

                OutputStream fos = new FileOutputStream(apkDbPath + entry.getName());
                BufferedOutputStream bos = new BufferedOutputStream(fos, buffer.length);

                while ((size = zis.read(buffer, 0, buffer.length)) != -1) {
                    bos.write(buffer, 0, size);
                }
                bos.flush();
                bos.close();
            }
            zis.close();
            is.close();
        }
        return !b;
    }

    /**
     * 检查DB文件是否存在
     *
     * @param f       文件名
     * @param refresh 是否覆盖之前的db文件
     * @return
     */
    private static boolean isDbFileExists(File f, boolean refresh) {
        boolean b = false;
        if (f.exists()) {
            if (refresh) {
                f.delete();
                b = false;
            } else {
                b = true;
            }
        }
        return b;
    }
}
