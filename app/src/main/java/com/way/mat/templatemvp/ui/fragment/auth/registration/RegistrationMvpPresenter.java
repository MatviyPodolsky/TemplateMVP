package com.way.mat.templatemvp.ui.fragment.auth.registration;

import com.way.mat.templatemvp.ui.base.MvpPresenter;
import com.way.mat.templatemvp.ui.base.MvpView;

public interface RegistrationMvpPresenter<V extends MvpView> extends MvpPresenter<V> {

    void onValidateRegistration(String firstName, String lastName, String password, String repeatPassword, String email);

    void onSuccessValidatingRegistration();

}
