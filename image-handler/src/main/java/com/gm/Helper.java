package com.gm;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by gowtham on 21/10/15.
 */
public class Helper {

    /**
     *
     * @param context
     * @return
     */
    static public File getPath(Context context) {

        File cacheDir;
        if (android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED)) {
            cacheDir = new File(
                    Environment.getExternalStorageDirectory(),
                    getApplicationName(context)+"CatchTemp");
            //context.getResources().getString(R.string.app_name)+"CatchTemp");//"ImagePickCacheTemp");
        } else {
            cacheDir = context.getCacheDir();
        }
        if (!cacheDir.exists()) {
            cacheDir.mkdirs();
        }
        return cacheDir;
    }

    /**
     *
     * @param context
     * @return
     */
    public static String getApplicationName(Context context) {
        return context.getApplicationInfo().loadLabel(context.getPackageManager()).toString();
    }

    /**
     *
     * @param imageUrl
     * @return
     */
    public static ArrayList<String> filter_available_files(
            ArrayList<String> imageUrl) {
        ArrayList<String> temp = new ArrayList<String>();
        for (int i = 0; i < imageUrl.size(); i++) {
            File image = new File(imageUrl.get(i));
            if (image.exists())
                temp.add(imageUrl.get(i));
        }
        return temp;
    }


    /**
     *
     * @param imageFile
     * @param context
     */

    public static void loadImageDetail(File imageFile, Context context) {

        ContentValues image = new ContentValues();

        image.put(MediaStore.Images.Media.MIME_TYPE, "image/png");
        image.put(MediaStore.Images.Media.ORIENTATION, 0);

        File parent = imageFile.getParentFile();
        String path = parent.toString().toLowerCase();
        String name = parent.getName().toLowerCase();
        image.put(MediaStore.Images.ImageColumns.BUCKET_ID, path.hashCode());
        image.put(MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME, name);
        image.put(MediaStore.Images.Media.SIZE, imageFile.length());

        image.put(MediaStore.Images.Media.DATA, imageFile.getAbsolutePath());

        Uri result = context.getContentResolver().insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, image);
    }
}
