package com.way.mat.templatemvp.ui.base;

import android.content.Context;
import android.support.annotation.StringRes;

public interface MvpView {

    void onError(@StringRes int error);

    void onError(String message);

    void showProgress();

    void hideProgress();

    Context getContext();

    void showMessage(String message);

    void showMessage(@StringRes int resId);

    boolean isNetworkConnected();

    void hideKeyboard();

    void openActivityOnTokenExpire();

}
