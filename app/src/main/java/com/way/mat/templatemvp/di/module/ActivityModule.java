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

package com.way.mat.templatemvp.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;


import com.way.mat.templatemvp.data.db.controllers.RealmFacade;
import com.way.mat.templatemvp.di.ActivityContext;
import com.way.mat.templatemvp.ui.activity.auth.AuthorizationMvpPresenter;
import com.way.mat.templatemvp.ui.activity.auth.AuthorizationMvpView;
import com.way.mat.templatemvp.ui.activity.auth.AuthorizationPresenter;
import com.way.mat.templatemvp.ui.activity.main.MainMvpPresenter;
import com.way.mat.templatemvp.ui.activity.main.MainMvpView;
import com.way.mat.templatemvp.ui.activity.main.MainPresenter;
import com.way.mat.templatemvp.ui.activity.splash.SplashMvpPresenter;
import com.way.mat.templatemvp.ui.activity.splash.SplashMvpView;
import com.way.mat.templatemvp.ui.activity.splash.SplashPresenter;
import com.way.mat.templatemvp.ui.fragment.auth.forgotpassword.ForgotPasswordMvpPresenter;
import com.way.mat.templatemvp.ui.fragment.auth.forgotpassword.ForgotPasswordMvpView;
import com.way.mat.templatemvp.ui.fragment.auth.forgotpassword.ForgotPasswordPresenter;
import com.way.mat.templatemvp.ui.fragment.auth.login.LoginMvpPresenter;
import com.way.mat.templatemvp.ui.fragment.auth.login.LoginMvpView;
import com.way.mat.templatemvp.ui.fragment.auth.login.LoginPresenter;
import com.way.mat.templatemvp.ui.fragment.auth.registration.RegistrationMvpPresenter;
import com.way.mat.templatemvp.ui.fragment.auth.registration.RegistrationMvpView;
import com.way.mat.templatemvp.ui.fragment.auth.registration.RegistrationPresenter;
import com.way.mat.templatemvp.ui.fragment.core.home.HomeMvpPresenter;
import com.way.mat.templatemvp.ui.fragment.core.home.HomeMvpView;
import com.way.mat.templatemvp.ui.fragment.core.home.HomePresenter;
import com.way.mat.templatemvp.util.rx.AppSchedulerProvider;
import com.way.mat.templatemvp.util.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import io.realm.Realm;

@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    Realm provideRealm() {
        return Realm.getDefaultInstance();
    }

    @Provides
    RealmFacade provideRealmFacade() {
        return new RealmFacade();
    }

    @Provides
    SplashMvpPresenter<SplashMvpView> provideSplashPresenter(
            SplashPresenter<SplashMvpView> presenter) {
        return presenter;
    }

    @Provides
    AuthorizationMvpPresenter<AuthorizationMvpView> provideAuthorizationPresenter(
            AuthorizationPresenter<AuthorizationMvpView> presenter) {
        return presenter;
    }

    @Provides
    LoginMvpPresenter<LoginMvpView> provideLoginPresenter(
            LoginPresenter<LoginMvpView> presenter) {
        return presenter;
    }

    @Provides
    RegistrationMvpPresenter<RegistrationMvpView> provideRegistrationPresenter(
            RegistrationPresenter<RegistrationMvpView> presenter) {
        return presenter;
    }

    @Provides
    ForgotPasswordMvpPresenter<ForgotPasswordMvpView> provideForgotPasswordPresenter(
            ForgotPasswordPresenter<ForgotPasswordMvpView> presenter) {
        return presenter;
    }

    @Provides
    MainMvpPresenter<MainMvpView> provideMainPresenter(
            MainPresenter<MainMvpView> presenter) {
        return presenter;
    }

    @Provides
    HomeMvpPresenter<HomeMvpView> provideHomePresenter(
            HomePresenter<HomeMvpView> presenter) {
        return presenter;
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity) {
        return new LinearLayoutManager(activity);
    }
}
