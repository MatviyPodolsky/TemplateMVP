package com.way.mat.templatemvp.ui.fragment.auth.login;

import com.way.mat.templatemvp.ui.base.MvpView;

public interface LoginMvpView extends MvpView {

    void onEmailError(String error);

    void onPasswordError(String error);

    void onSuccessLogin();

    void onBadCredentials();
}
