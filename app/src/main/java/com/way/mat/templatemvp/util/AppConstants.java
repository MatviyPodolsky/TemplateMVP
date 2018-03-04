package com.way.mat.templatemvp.util;

public class AppConstants {

    //requests
    public static final int REQUEST_ALL = 3736;
    public static final int REQUEST_READWRITE_STORAGE = 3737;
    public static final int REQUEST_CONTACTS_SMS = 3741;
    public static final int REQUEST_READ_WRITE_RECORD = 3738;
    public static final int REQUEST_LOCATION = 3739;
    public static final int REQUEST_PHONE_NUMBER = 3740;
    public static final int REQUEST_CAMERA = 3741;
    public static final int REQUEST_GALLERY = 3742;

    public static final int SERVICE_NOTIFICATION_ID = 1734;
    public static final int NOTIFICATION_ERROR_ID = 1735;
    public static final int REGULAR_NOTIFICATION_ID = 1736;

    public static final int REQUEST_CAMERA_TAKE_PHOTO = 1123;
    public static final int REQUEST_GALLERY_TAKE_PICTURE = 1124;

    public static final int PASSWORD_LENGTH = 8;
    public static final int PHONE_LENGTH = 7;

    public static final int TIME_THIRTY_SECONDS = 30 * 1000;
    public static final int TIME_SECOND = 1 * 1000;
    public static final int TIME_TWO_SECONDS = 2 * 1000;
    public static final int TIME_FIVE_SECONDS = 5 * 1000;
    public static final int TIME_FIVE_MINUTES = 5 * 60 * 1000;
    public static final int TIME_HALF_AN_HOUR = 30 * 60 * 1000;
    public static final int TIME_DAY = 24 * 60 * 60 * 1000;

    public static final String DATE_PATTERN = "%1$te/%1$tm/%1$tY";

    public static final String TIMESTAMP_FORMAT = "yyyyMMdd_HHmmss";

    public final static String SIGN_IN_TAG = "sign_in_tag";

    private AppConstants() {
    }
}
