package com.way.mat.templatemvp.util;

/**
 * Created by matviy on 13.04.17.
 */

public class TimeUtils {

    public static String convertTime(long seconds) {
        String timeStr = "";
        if (seconds == 60) {
            timeStr = "01:00";
        } else {
            if (seconds < 10) {
                timeStr = "00:0" + seconds;
            } else {
                timeStr = "00:" + seconds;
            }
        }
        return timeStr;
    }

    public static String getTimeString(long seconds) {
        final int SECONDS_IN_A_MINUTE = 60;
        final int SECONDS_IN_A_HOUR = 60 * 60;

        long hours = seconds / SECONDS_IN_A_HOUR;
        long minutes = (seconds % SECONDS_IN_A_HOUR) / SECONDS_IN_A_MINUTE;
        seconds = seconds % SECONDS_IN_A_MINUTE;

        String strSec = String.valueOf(seconds);
        if (seconds < 10) {
            strSec = "0" + seconds;
        }
        String strMin = String.valueOf(minutes) + ":";
        if (minutes < 10) {
            strMin = "0" + minutes + ":";
        }
        String strHour;
        if (hours <= 0) {
            strHour = "";
        } else {
            strHour = hours + ":";
        }
        return strHour + strMin + strSec;
    }

}
