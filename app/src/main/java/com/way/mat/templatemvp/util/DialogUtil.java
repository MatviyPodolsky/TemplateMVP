package com.way.mat.templatemvp.util;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.way.mat.templatemvp.R;


public class DialogUtil {

    public static void showAuthErrorDialog(final Activity activity, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.AppTheme_ErrorDialog);
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_auth_error, null);
        builder.setView(view);
        ((TextView)view.findViewById(R.id.tv_message)).setText(message);
        builder.setPositiveButton(android.R.string.ok, (dialog, which) -> {
            dialog.dismiss();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
//
//    public static void showLogoutConfirmationDialog(final Activity activity, DialogInterface.OnClickListener callback) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.AppTheme_ErrorDialog);
//        LayoutInflater inflater = activity.getLayoutInflater();
//        View view = inflater.inflate(R.layout.dialog_auth_error, null);
//        builder.setView(view);
//        ((TextView)view.findViewById(R.id.tv_title)).setText(R.string.dialog_logout_title);
//        ((TextView)view.findViewById(R.id.tv_message)).setText(R.string.dialog_logout_message);
//        builder.setPositiveButton(R.string.dialog_ok, callback);
//        builder.setNegativeButton(R.string.dialog_cancel, (dialog, which) -> dialog.dismiss());
//        builder.setCancelable(true);
//
//        AlertDialog alertDialog = builder.create();
//        alertDialog.setOnShowListener(dialog -> {
//            alertDialog.getButton(android.app.AlertDialog.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(activity, R.color.dialog_negative_color));
//        });
//        alertDialog.show();
//    }
}
