package com.orangeskill.elate.framework.permission;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;





public class PermissionUtils {

    public static final int REQUEST_CONTACTS = 1;

    public static final int REQUEST_EXTERNAL_STORAGE = 2;

    private static final int REQUEST_CALL = 3;

    public static final int REQUEST_CAMERA = 4;

    public static final int REQUEST_AUDIO = 5;

    private static String[] PERMISSIONS_CONTACT = {Manifest.permission.READ_CONTACTS};



    private static String[] PERMISSIONS_EXTERNAL_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE};

    private static  String[] PERMISSIONS_CAMERA = {Manifest.permission.CAMERA};


    public static boolean isContactPermissionRequired(Context activity){

        int readPermissionCheck = ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_CONTACTS);
        return readPermissionCheck == -1;
    }



    public static boolean isContactPermissionDeniedByUser(Context activity){

        int readPermissionCheck = ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_CONTACTS);
        return readPermissionCheck == -2;
    }



    public static boolean isExternalStoragePermissionRequired(Context activity){

        int readPermissionCheck = ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);
        return readPermissionCheck == -1 ;
    }

    public static boolean isCameraPermissionRequired(Context activity){

        int callPermissionCheck = ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA);
        return callPermissionCheck == -1;
    }

    /**
     * Requests the Contacts permissions.
     * If the permission has been denied previously, a SnackBar will prompt the user to grant the
     * permission, otherwise it is requested directly.
     */
    public static void requestContactsPermissions(Activity activity) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                Manifest.permission.READ_CONTACTS)) {
            ActivityCompat.requestPermissions(activity, PERMISSIONS_CONTACT, REQUEST_CONTACTS);
        }else{

            ActivityCompat.requestPermissions(activity, PERMISSIONS_CONTACT, REQUEST_CONTACTS);
        }
    }



    public static void requestContactsPermissionsForCall(Activity activity) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                Manifest.permission.READ_CONTACTS)) {
            ActivityCompat.requestPermissions(activity, PERMISSIONS_CONTACT, REQUEST_CONTACTS);
        }else{
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                if (neverAskAgainSelectedAboveM(activity, Manifest.permission.READ_CONTACTS)) {
                } else {
                    ActivityCompat.requestPermissions(activity, PERMISSIONS_CONTACT, REQUEST_CONTACTS);
                }
            }else {
                if (neverAskAgainSelected(activity, Manifest.permission.READ_CONTACTS)) {
                } else {
                    ActivityCompat.requestPermissions(activity, PERMISSIONS_CONTACT, REQUEST_CONTACTS);
                }
            }
         //   AlertDialogHelper.showDialog(activity,"",activity.getResources().getString(R.string.Call_permission_missing)).show();
            //  ActivityCompat.requestPermissions(activity, PERMISSIONS_CONTACT, REQUEST_CONTACTS);
        }
    }

    /**
     * Requests the Contacts permissions.
     * If the permission has been denied previously, a SnackBar will prompt the user to grant the
     * permission, otherwise it is requested directly.
     */
    public static void requestExternalStoragePermissions(Activity activity) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                ) {
            ActivityCompat.requestPermissions(activity, PERMISSIONS_EXTERNAL_STORAGE, REQUEST_EXTERNAL_STORAGE);
        }else{
            ActivityCompat.requestPermissions(activity, PERMISSIONS_EXTERNAL_STORAGE, REQUEST_EXTERNAL_STORAGE);
        }
    }

    public static void requestCameraPermissions(Activity activity) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                Manifest.permission.CAMERA)
                || ActivityCompat.shouldShowRequestPermissionRationale(activity,
                Manifest.permission.CAMERA)) {
            ActivityCompat.requestPermissions(activity, PERMISSIONS_CAMERA, REQUEST_CAMERA);
        }else{
            ActivityCompat.requestPermissions(activity, PERMISSIONS_CAMERA, REQUEST_CAMERA);
        }
    }

    public static boolean neverAskAgainSelected(final Activity activity, final String permission) {
        final boolean prevShouldShowStatus = getRatinaleDisplayStatus(activity,permission);
        final boolean currShouldShowStatus = ActivityCompat.shouldShowRequestPermissionRationale(activity,permission);
        return prevShouldShowStatus != currShouldShowStatus;
    }

    public static void setShouldShowStatus(final Context context, final String permission) {
        SharedPreferences genPrefs = context.getSharedPreferences("GENERIC_PREFERENCES", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = genPrefs.edit();
        editor.putBoolean(permission, true);
        editor.commit();
    }
    public static boolean getRatinaleDisplayStatus(final Context context, final String permission) {
        SharedPreferences genPrefs =     context.getSharedPreferences("GENERIC_PREFERENCES", Context.MODE_PRIVATE);
        return genPrefs.getBoolean(permission, false);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public static boolean neverAskAgainSelectedAboveM(final Activity activity, final String permission) {
        final boolean prevShouldShowStatus = getRatinaleDisplayStatus(activity,permission);
        final boolean currShouldShowStatus = activity.shouldShowRequestPermissionRationale(permission);
        return prevShouldShowStatus != currShouldShowStatus;
    }


}
