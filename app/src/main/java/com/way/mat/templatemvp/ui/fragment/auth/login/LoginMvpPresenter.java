package com.way.mat.templatemvp.ui.fragment.auth.login;

import com.way.mat.templatemvp.di.PerActivity;
import com.way.mat.templatemvp.ui.base.MvpPresenter;
import com.way.mat.templatemvp.ui.base.MvpView;

@PerActivity
public interface LoginMvpPresenter<V extends MvpView> extends MvpPresenter<V> {

    void onValidateLogin(String email, String password);

    void onSuccessValidatingLogin(String email, String password);

}
