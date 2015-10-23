package com.gm.imagehandler;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;

import com.gm.ImageHandler;


/**
 * Created by gowtham on 21/10/15.
 */
public class AppConfig extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        initImgpic(getApplicationContext());
    }

    /**
     * initiate the file saving path by the user.
     * @param context
     */
    void initImgpic(Context context){

        String path = "/Android/data/"
                + context.getApplicationContext().getPackageName()
                + "/GmFiles";

        /**
         * The initTempFilePath() is used to save the contents by the user mentioned folder in the given path
         * if SD card is not available that contents should be stored in app catch folder.
         */
        ImageHandler.getInstance(this).initTempFilePath(path);

        ImageHandler.getInstance(this).initBitmapConfig(Bitmap.CompressFormat.PNG, 60);



    }
}
