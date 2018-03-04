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

package com.way.mat.templatemvp.data.network;

import com.androidnetworking.common.Priority;
import com.pixplicity.easyprefs.library.Prefs;
import com.rx2androidnetworking.Rx2AndroidNetworking;
import com.way.mat.templatemvp.data.network.request.LoginRequest;
import com.way.mat.templatemvp.data.network.request.RegistrationRequest;
import com.way.mat.templatemvp.data.network.response.BaseResponse;
import com.way.mat.templatemvp.data.network.response.ForgotPasswordResponse;
import com.way.mat.templatemvp.data.network.response.LoginResponse;
import com.way.mat.templatemvp.data.network.response.LogoutResponse;
import com.way.mat.templatemvp.data.network.response.RegistrationResponse;
import com.way.mat.templatemvp.util.PrefKeys;

import java.io.File;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class AppApiHelper implements ApiHelper {

    @Inject
    public AppApiHelper() {

    }

    @Override
    public Observable<BaseResponse> doUploadFile(File file) {
        return Rx2AndroidNetworking.upload(ApiEndPoint.ENDPOINT_UPLOAD_FILE)
                .addMultipartFile("file", file)
                .addHeaders("Authorization", "Bearer " + Prefs.getString(PrefKeys.TOKEN, ""))
                .setTag("uploadAvatar")
                .setPriority(Priority.MEDIUM)
                .build()
                .getObjectObservable(BaseResponse.class);
    }

    @Override
    public Observable<LoginResponse> doLogin(LoginRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_LOGIN)
                .addApplicationJsonBody(request)
                .setTag("login")
                .setPriority(Priority.MEDIUM)
                .build()
                .getObjectObservable(LoginResponse.class);
    }

    @Override
    public Observable<RegistrationResponse> doSignUp(RegistrationRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_SIGNUP)
                .addApplicationJsonBody(request)
                .setTag("register")
                .setPriority(Priority.MEDIUM)
                .build()
                .getObjectObservable(RegistrationResponse.class);
    }

    @Override
    public Observable<ForgotPasswordResponse> sendForgotPassword(String email) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_FORGOT_PASSWORD)
                .addQueryParameter("email", email)
                .setTag("forgot password")
                .setPriority(Priority.MEDIUM)
                .build()
                .getObjectObservable(ForgotPasswordResponse.class);
    }

    @Override
    public Observable<LogoutResponse> logout() {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_LOGOUT)
                .addHeaders("Authorization", "Bearer " + Prefs.getString(PrefKeys.TOKEN, ""))
                .setTag("logout")
                .setPriority(Priority.MEDIUM)
                .build()
                .getObjectObservable(LogoutResponse.class);
    }

}

