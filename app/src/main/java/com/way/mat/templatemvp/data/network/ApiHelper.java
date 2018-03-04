package com.way.mat.templatemvp.data.network;

import com.way.mat.templatemvp.data.network.request.LoginRequest;
import com.way.mat.templatemvp.data.network.request.RegistrationRequest;
import com.way.mat.templatemvp.data.network.response.BaseResponse;
import com.way.mat.templatemvp.data.network.response.ForgotPasswordResponse;
import com.way.mat.templatemvp.data.network.response.LoginResponse;
import com.way.mat.templatemvp.data.network.response.LogoutResponse;
import com.way.mat.templatemvp.data.network.response.RegistrationResponse;

import java.io.File;

import io.reactivex.Observable;

public interface ApiHelper {

    Observable<BaseResponse> doUploadFile(File file);

    Observable<LoginResponse> doLogin(LoginRequest request);

    Observable<RegistrationResponse> doSignUp(RegistrationRequest request);

    Observable<ForgotPasswordResponse> sendForgotPassword(String email);

    Observable<LogoutResponse> logout();

}