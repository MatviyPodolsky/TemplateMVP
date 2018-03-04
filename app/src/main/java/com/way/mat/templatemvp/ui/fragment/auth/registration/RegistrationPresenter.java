package com.way.mat.templatemvp.ui.fragment.auth.registration;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.util.Log;

import com.androidnetworking.error.ANError;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.pixplicity.easyprefs.library.Prefs;
import com.way.mat.templatemvp.R;
import com.way.mat.templatemvp.data.DataManager;
import com.way.mat.templatemvp.data.network.listener.OnSubscribeResponseListener;
import com.way.mat.templatemvp.data.network.request.RegistrationRequest;
import com.way.mat.templatemvp.data.network.response.LoginResponse;
import com.way.mat.templatemvp.data.network.response.RegistrationResponse;
import com.way.mat.templatemvp.di.ApplicationContext;
import com.way.mat.templatemvp.ui.base.BasePresenter;
import com.way.mat.templatemvp.util.AppConstants;
import com.way.mat.templatemvp.util.DateUtils;
import com.way.mat.templatemvp.util.NetworkUtils;
import com.way.mat.templatemvp.util.PrefKeys;
import com.way.mat.templatemvp.util.rx.SchedulerProvider;

import java.net.HttpURLConnection;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class RegistrationPresenter<V extends RegistrationMvpView> extends BasePresenter<V>
        implements RegistrationMvpPresenter<V>, OnSubscribeResponseListener<RegistrationResponse> {

    private Context mContext;

    @Inject
    public RegistrationPresenter(@ApplicationContext Context context,
                                 DataManager dataManager,
                                 SchedulerProvider schedulerProvider,
                                 CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
        mContext = context;
    }

    @Override
    public void onSuccessValidatingRegistration() {
        getView().onSuccessRegistration();
    }

    @Override
    public void handleError(Throwable error) {
        getView().hideProgress();
        if (error instanceof ANError) {
            super.handleApiError((ANError) error);
        } else {
            getView().onError(R.string.error_api_unknown);
        }
    }

    @Override
    public void handleResponse(RegistrationResponse response) {
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
//                LoginUtils.updateProfileWithPushId(response);
            }
            getView().onSuccessRegistration();
        } else {
            getView().onError(R.string.error_api_unknown);
        }
    }

    @Override
    public void onValidateRegistration(String firstName, String lastName, String password, String repeatPassword, String email) {
        boolean isSuccess = true;

        if (TextUtils.isEmpty(firstName)) {
            isSuccess = false;
            getView().onFirstNameError(R.string.error_empty_username);
        }

        if (TextUtils.isEmpty(lastName)) {
            isSuccess = false;
            getView().onLastNameError(R.string.error_empty_username);
        }

        if (TextUtils.isEmpty(email)) {
            isSuccess = false;
            getView().onEmailError(R.string.error_empty_email);
        }

        if (!TextUtils.isEmpty(email) && !android.util.Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches()) {
            isSuccess = false;
            getView().onEmailError(R.string.error_invalid_email);
        }

        if (TextUtils.isEmpty(password)) {
            isSuccess = false;
            getView().onPasswordError(R.string.error_empty_password);
        }

        if (TextUtils.isEmpty(repeatPassword)) {
            isSuccess = false;
            getView().onRepeatPasswordError(R.string.error_empty_password);
        }

        if (!TextUtils.isEmpty(password) && password.length() < AppConstants.PASSWORD_LENGTH) {
            isSuccess = false;
            getView().onPasswordError(R.string.error_short_password);
        }

        if (!TextUtils.isEmpty(repeatPassword) && repeatPassword.length() < AppConstants.PASSWORD_LENGTH) {
            isSuccess = false;
            getView().onRepeatPasswordError(R.string.error_short_password);
        }

        if (!TextUtils.isEmpty(password) && !TextUtils.isEmpty(repeatPassword)
                && !password.equals(repeatPassword)) {
            isSuccess = false;
            getView().onRepeatPasswordError(R.string.error_password_not_match);
        }

        if (isSuccess) {
            doSignUp(firstName, lastName, password, email);
        }
    }

    private void doSignUp(String firstName, String lastName, String password, String email) {
        if (NetworkUtils.isNetworkConnected(mContext)) {
            getView().showProgress();
            final RegistrationRequest request = new RegistrationRequest(firstName, lastName, password, email);
            getCompositeDisposable().add(getDataManager()
                    .doSignUp(request)
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .subscribe(response -> {

                        getView().hideProgress();

                        handleResponse(response);
                    }, throwable -> {

                        if (!isViewAttached()) {
                            return;
                        }

                        getView().hideProgress();

                        // handle the login error here

                        if (throwable instanceof ANError
                                && ((ANError) throwable).getErrorCode() == HttpURLConnection.HTTP_BAD_REQUEST) {

                            ANError anerror = (ANError) throwable;
                        } else {
                            handleError(throwable);
                        }
                    }));
        } else {
            getView().onError(R.string.error_no_connection);
        }
    }

}
