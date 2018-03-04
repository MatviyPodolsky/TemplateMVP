package com.way.mat.templatemvp.ui.fragment.auth.forgotpassword;

import com.way.mat.templatemvp.ui.base.MvpView;

public interface ForgotPasswordMvpView extends MvpView {

    void onEmailError(String error);

    void onEmailValid();

    void onSuccessReset();
}
