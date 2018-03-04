package com.way.mat.templatemvp.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by mpodolsky on 23.05.2016.
 */
public class PermissionsUtil {

    private static String[] ALL_PERMISSIONS = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
    };

    private static String[] CONTACTS_PERMISSIONS = new String[]{
            Manifest.permission.SEND_SMS,
            Manifest.permission.READ_CONTACTS
    };

    private static String[] CAMERA_PERMISSIONS = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    private static String[] GALLERY_PERMISSIONS = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    public static boolean needAnyPermission(Context context) {
        for (int i = 0; i < ALL_PERMISSIONS.length; i++) {
            if (ContextCompat.checkSelfPermission(context, ALL_PERMISSIONS[i]) != PackageManager.PERMISSION_GRANTED) {
                return true;
            }
        }
        return false;
    }

    public static boolean needContactsPermission(Context context) {
        for (int i = 0; i < CONTACTS_PERMISSIONS.length; i++) {
            if (ContextCompat.checkSelfPermission(context, CONTACTS_PERMISSIONS[i]) != PackageManager.PERMISSION_GRANTED) {
                return true;
            }
        }
        return false;
    }

    public static boolean needReadWritePermissions(Context context) {
        int permissionCheck1 = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE);
        int permissionCheck2 = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        return (permissionCheck1 != PackageManager.PERMISSION_GRANTED || permissionCheck2 != PackageManager.PERMISSION_GRANTED);
    }

    public static boolean needRecordPermissions(Context context) {
        int permissionCheck1 = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE);
        int permissionCheck2 = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permissionCheck3 = ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO);
        return (permissionCheck1 != PackageManager.PERMISSION_GRANTED || permissionCheck2 != PackageManager.PERMISSION_GRANTED || permissionCheck3 != PackageManager.PERMISSION_GRANTED);
    }

    public static boolean needPhoneStatePermissions(Context context) {
        int permissionCheck1 = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE);
        return (permissionCheck1 != PackageManager.PERMISSION_GRANTED);
    }

    public static boolean needLocationsPermissions(Context context) {
        int permissionCheck1 = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION);
        int permissionCheck2 = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION);
        return (permissionCheck1 != PackageManager.PERMISSION_GRANTED || permissionCheck2 != PackageManager.PERMISSION_GRANTED);
    }

    public static void requestContactsAndSMS(Activity activity) {
        ActivityCompat.requestPermissions(activity,
                CONTACTS_PERMISSIONS,
                AppConstants.REQUEST_CONTACTS_SMS);
    }

    public static void requestReadWritePermissions(Activity activity) {
        ActivityCompat.requestPermissions(activity,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE},
                AppConstants.REQUEST_READWRITE_STORAGE);
    }

    public static void requestRecordPermissions(Activity activity) {
        ActivityCompat.requestPermissions(activity,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.RECORD_AUDIO},
                AppConstants.REQUEST_READ_WRITE_RECORD);
    }

    public static String[] getRecordPermissions() {
        return new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO};
    }

    public static String[] getPhoneStatePermissions() {
        return new String[]{Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.READ_SMS};
    }

    public static String[] getLocationPermissions() {
        return new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};
    }

    public static String[] getAllPermissions() {
        return ALL_PERMISSIONS;
    }

    public static String[] getContactsPermissions() {
        return CONTACTS_PERMISSIONS;
    }

    public static String[] getCameraPermissions() {
        return CAMERA_PERMISSIONS;
    }

    public static String[] getGalleryPermissions() {
        return GALLERY_PERMISSIONS;
    }

}