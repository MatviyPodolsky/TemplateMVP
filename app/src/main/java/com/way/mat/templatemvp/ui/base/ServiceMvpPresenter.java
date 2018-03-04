package com.way.mat.templatemvp.ui.base;

public interface ServiceMvpPresenter<V extends ServiceMvpView> {

    void onAttach(final V view);

    void onDetach();

}