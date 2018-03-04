/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.way.mat.templatemvp.ui.activity.main;

import android.content.Context;
import android.content.Intent;

import com.way.mat.templatemvp.data.DataManager;
import com.way.mat.templatemvp.data.db.controllers.RealmFacade;
import com.way.mat.templatemvp.data.network.listener.OnSubscribeResponseListener;
import com.way.mat.templatemvp.data.network.response.LogoutResponse;
import com.way.mat.templatemvp.di.ApplicationContext;
import com.way.mat.templatemvp.ui.base.BasePresenter;
import com.way.mat.templatemvp.util.AppConstants;
import com.way.mat.templatemvp.util.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.realm.Realm;

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V> implements
        MainMvpPresenter<V>, OnSubscribeResponseListener<LogoutResponse> {

    private static final String TAG = "MainPresenter";

    private Disposable mMainDisposable;

    private Realm mRealm;
    private RealmFacade mRealmFacade;

    private Context mContext;

    @Inject
    public MainPresenter(@ApplicationContext Context context,
                         DataManager dataManager,
                         SchedulerProvider schedulerProvider,
                         CompositeDisposable compositeDisposable,
                         Realm realm,
                         RealmFacade realmFacade) {
        super(dataManager, schedulerProvider, compositeDisposable);
        mContext = context;
        mRealm = realm;
        mRealmFacade = realmFacade;

    }

    @Override
    public void logout() {
        getView().showProgress();
        if (getView().isNetworkConnected()) {
            mMainDisposable = getDataManager()
                    .logout()
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .subscribe(this::handleResponse, this::handleError);
            getCompositeDisposable().add(mMainDisposable);
        } else {
            logoutLocal();
        }
    }

    @Override
    public void handleError(Throwable error) {
        logoutLocal();
    }

    @Override
    public void handleResponse(LogoutResponse logoutResponse) {
        logoutLocal();
    }

    private void logoutLocal() {
        getView().hideProgress();
        setUserAsLoggedOut();
        getView().onLogout();
    }

}
