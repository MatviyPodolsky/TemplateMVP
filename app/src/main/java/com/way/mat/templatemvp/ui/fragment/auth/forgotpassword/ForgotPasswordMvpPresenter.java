package com.way.mat.templatemvp.ui.fragment.auth.forgotpassword;

import com.way.mat.templatemvp.di.PerActivity;
import com.way.mat.templatemvp.ui.base.MvpPresenter;
import com.way.mat.templatemvp.ui.base.MvpView;

@PerActivity
public interface ForgotPasswordMvpPresenter<V extends MvpView> extends MvpPresenter<V> {

    void onValidateForgotPassword(String email);

     void onSuccessValidatingForgotPassword(String email);
}
