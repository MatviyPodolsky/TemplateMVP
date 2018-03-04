package com.way.mat.templatemvp.ui.fragment.auth.registration;

import android.support.annotation.StringRes;

import com.way.mat.templatemvp.ui.base.MvpView;

public interface RegistrationMvpView extends MvpView {

    void onFirstNameError(@StringRes int error);

    void onFirstNameError(String error);

    void onLastNameError(@StringRes int error);

    void onLastNameError(String error);

    void onEmailError(@StringRes int error);

    void onEmailError(String error);

    void onPasswordError(@StringRes int error);

    void onPasswordError(String error);

    void onRepeatPasswordError(@StringRes int error);

    void onSuccessRegistration();

    void onErrorRegistration();

}
