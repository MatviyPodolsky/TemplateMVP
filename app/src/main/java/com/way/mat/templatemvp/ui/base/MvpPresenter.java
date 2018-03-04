package com.way.mat.templatemvp.ui.base;

import com.androidnetworking.error.ANError;

public interface MvpPresenter<V extends MvpView> {

    void onAttach(final V view);

    void onDetach();

    void handleApiError(ANError error);

    void setUserAsLoggedOut();

}