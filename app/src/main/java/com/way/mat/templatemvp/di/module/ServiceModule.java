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

import android.app.Service;
import android.content.Context;

import com.way.mat.templatemvp.data.db.controllers.RealmFacade;
import com.way.mat.templatemvp.di.ActivityContext;
import com.way.mat.templatemvp.service.sometask.SomeTaskMvpPresenter;
import com.way.mat.templatemvp.service.sometask.SomeTaskMvpView;
import com.way.mat.templatemvp.service.sometask.SomeTaskPresenter;
import com.way.mat.templatemvp.util.rx.AppSchedulerProvider;
import com.way.mat.templatemvp.util.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import io.realm.Realm;

@Module
public class ServiceModule {

    private final Service mService;

    public ServiceModule(Service service) {
        mService = service;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mService;
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
    SomeTaskMvpPresenter<SomeTaskMvpView> provideSomeTaskPresenter(
            SomeTaskPresenter<SomeTaskMvpView> presenter) {
        return presenter;
    }

}
