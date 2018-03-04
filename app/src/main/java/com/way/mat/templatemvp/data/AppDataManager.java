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

package com.way.mat.templatemvp.data;


import android.content.Context;

import com.way.mat.templatemvp.data.network.ApiHelper;
import com.way.mat.templatemvp.data.network.request.LoginRequest;
import com.way.mat.templatemvp.data.network.request.RegistrationRequest;
import com.way.mat.templatemvp.data.network.response.BaseResponse;
import com.way.mat.templatemvp.data.network.response.ForgotPasswordResponse;
import com.way.mat.templatemvp.data.network.response.LoginResponse;
import com.way.mat.templatemvp.data.network.response.LogoutResponse;
import com.way.mat.templatemvp.data.network.response.RegistrationResponse;
import com.way.mat.templatemvp.di.ApplicationContext;

import java.io.File;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class AppDataManager implements DataManager {

    private static final String TAG = "AppDataManager";

    private final Context mContext;
    private final ApiHelper mApiHelper;

    @Inject
    public AppDataManager(@ApplicationContext Context context,
                          ApiHelper apiHelper) {
        mContext = context;
        mApiHelper = apiHelper;
    }

    @Override
    public Observable<BaseResponse> doUploadFile(File file) {
        return mApiHelper.doUploadFile(file);
    }

    @Override
    public Observable<LoginResponse> doLogin(LoginRequest request) {
        return mApiHelper.doLogin(request);
    }

    @Override
    public Observable<RegistrationResponse> doSignUp(RegistrationRequest request) {
        return mApiHelper.doSignUp(request);
    }

    @Override
    public Observable<ForgotPasswordResponse> sendForgotPassword(String email) {
        return mApiHelper.sendForgotPassword(email);
    }

    @Override
    public Observable<LogoutResponse> logout() {
        return mApiHelper.logout();
    }
}
