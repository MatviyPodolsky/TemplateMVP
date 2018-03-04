package com.way.mat.templatemvp.ui.fragment.auth.login;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;

import com.androidnetworking.error.ANError;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.pixplicity.easyprefs.library.Prefs;
import com.way.mat.templatemvp.R;
import com.way.mat.templatemvp.data.DataManager;
import com.way.mat.templatemvp.data.network.listener.OnSubscribeResponseListener;
import com.way.mat.templatemvp.data.network.request.LoginRequest;
import com.way.mat.templatemvp.data.network.response.LoginResponse;
import com.way.mat.templatemvp.di.ApplicationContext;
import com.way.mat.templatemvp.ui.base.BaseActivity;
import com.way.mat.templatemvp.ui.base.BaseFragment;
import com.way.mat.templatemvp.ui.base.BasePresenter;
import com.way.mat.templatemvp.util.AppConstants;
import com.way.mat.templatemvp.util.PrefKeys;
import com.way.mat.templatemvp.util.rx.SchedulerProvider;

import java.net.HttpURLConnection;
import java.util.Arrays;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class LoginPresenter<V extends LoginMvpView> extends BasePresenter<V>
        implements LoginMvpPresenter<V>, OnSubscribeResponseListener<LoginResponse> {

    public static final int RC_SIGN_IN = 9001;

    private Disposable mLoginDisposable;

    private Context mContext;

    @Inject
    public LoginPresenter(@ApplicationContext Context context,
                          DataManager dataManager,
                          SchedulerProvider schedulerProvider,
                          CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
        mContext = context;
    }

    @Override
    public void onSuccessValidatingLogin(String email, String password) {
        getView().showProgress();
        mLoginDisposable = getDataManager()
                .doLogin(new LoginRequest(email, password))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(this::handleResponse, this::handleError);
        getCompositeDisposable().add(mLoginDisposable);
    }

    @Override
    public void handleError(Throwable error) {
        if (!isViewAttached()) {
            return;
        }
        getView().hideProgress();
        // handle the login error here
        if (error instanceof ANError) {
            if (((ANError) error).getErrorCode() == HttpURLConnection.HTTP_FORBIDDEN) {
                getView().onBadCredentials();
            } else if (((ANError) error).getErrorCode() == HttpURLConnection.HTTP_BAD_REQUEST) {
                getView().onError(mContext.getString(R.string.error_api_unknown));
            } else {
                super.handleApiError((ANError) error);
            }
        } else {
            getView().onError(mContext.getString(R.string.error_api_unknown));
        }
    }

    @Override
    public void handleResponse(LoginResponse response) {
        if (!isViewAttached()) {
            return;
        }
        getView().hideProgress();
        if (response == null) {
            getView().onError(R.string.error_api_unknown);
            return;
        }
        if (!TextUtils.isEmpty(response.getToken())) {
            Prefs.putString(PrefKeys.TOKEN, response.getToken());
            if (response.getUser() != null) {
//                    LoginUtils.updateProfileWithPushId(response);
            }
            getView().onSuccessLogin();
        } else {
            getView().onError(R.string.error_api_unknown);
        }
    }

    @Override
    public void onValidateLogin(String email, String password) {
        boolean isSuccess = true;
        if (email == null || TextUtils.isEmpty(email.trim())) {
            isSuccess = false;
            getView().onEmailError(mContext.getString(R.string.error_empty_email));
        }
        if (!TextUtils.isEmpty(email.trim()) && !android.util.Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches()) {
            isSuccess = false;
            getView().onEmailError(mContext.getString(R.string.error_invalid_email));
        }
        if (TextUtils.isEmpty(password)) {
            isSuccess = false;
            getView().onPasswordError(mContext.getString(R.string.error_empty_password));
        }
        if (password != null && !password.isEmpty() && password.length() < AppConstants.PASSWORD_LENGTH) {
            isSuccess = false;
            getView().onPasswordError(mContext.getString(R.string.error_short_password));
        }
        if (isSuccess) {
            onSuccessValidatingLogin(email, password);
        }
    }

}
