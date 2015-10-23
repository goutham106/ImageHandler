package com.gm;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * Created by gowtham on 22/10/15.
 */
public class AndroidPermission extends AppCompatActivity{

    private static AndroidPermission androidPermission = null;
    private Context context;
   // private Activity activity;
    private static final int PERMISSION_REQUEST_CODE = 1;
    private View view;

    public static AndroidPermission getInstance() {
        if (androidPermission == null) {
            androidPermission = new AndroidPermission();
            return androidPermission;
        }
        return androidPermission;
    }


    public boolean checkPermission(Context context,int result){
    this.context = context;
        if (result == PackageManager.PERMISSION_GRANTED){

            return true;

        } else {

            return false;

        }
    }

    public void requestPermission(Activity activity,String permissions,String description){

        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permissions)){

            Toast.makeText(context, description, Toast.LENGTH_LONG).show();
            ActivityCompat.requestPermissions(activity, new String[]{permissions}, PERMISSION_REQUEST_CODE);
        } else {

            ActivityCompat.requestPermissions(activity,new String[]{permissions},PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                   Toast.makeText(AndroidPermission.this, "Permission Granted", Toast.LENGTH_SHORT).show();
                } else {


                     Toast.makeText(AndroidPermission.this,"Permission Denied",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
