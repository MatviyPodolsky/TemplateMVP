package com.way.mat.templatemvp.ui.activity.auth;

import com.way.mat.templatemvp.data.DataManager;
import com.way.mat.templatemvp.ui.base.BasePresenter;
import com.way.mat.templatemvp.ui.base.MvpView;
import com.way.mat.templatemvp.util.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class AuthorizationPresenter<V extends MvpView> extends BasePresenter<V> implements
        AuthorizationMvpPresenter<V> {

    private static final String TAG = "AuthorizationPresenter";

    @Inject
    public AuthorizationPresenter(DataManager dataManager,
                                  SchedulerProvider schedulerProvider,
                                  CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
