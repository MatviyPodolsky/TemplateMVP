package com.way.mat.templatemvp.ui.fragment.auth.forgotpassword;

import android.content.Context;
import android.text.TextUtils;

import com.androidnetworking.error.ANError;
import com.way.mat.templatemvp.R;
import com.way.mat.templatemvp.data.DataManager;
import com.way.mat.templatemvp.data.network.listener.OnSubscribeResponseListener;
import com.way.mat.templatemvp.data.network.response.ForgotPasswordResponse;
import com.way.mat.templatemvp.di.ApplicationContext;
import com.way.mat.templatemvp.ui.base.BasePresenter;
import com.way.mat.templatemvp.util.rx.SchedulerProvider;

import java.net.HttpURLConnection;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class ForgotPasswordPresenter<V extends ForgotPasswordMvpView> extends BasePresenter<V>
        implements ForgotPasswordMvpPresenter<V>, OnSubscribeResponseListener<ForgotPasswordResponse> {

    private Disposable mForgotPasswordDisposable;
    private Context mContext;

    @Inject
    public ForgotPasswordPresenter(@ApplicationContext Context context,
                                   DataManager dataManager,
                                   SchedulerProvider schedulerProvider,
                                   CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
        mContext = context;
    }

    @Override
    public void onSuccessValidatingForgotPassword(String email) {
        getView().showProgress();
        mForgotPasswordDisposable = getDataManager()
                .sendForgotPassword(email)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(this::handleResponse, this::handleError);
        getCompositeDisposable().add(mForgotPasswordDisposable);
    }

    @Override
    public void handleError(Throwable error) {
        if (!isViewAttached()) {
            return;
        }

        getView().hideProgress();

        // handle the login error here
        if (error instanceof ANError) {
            if (((ANError) error).getErrorCode() == HttpURLConnection.HTTP_FORBIDDEN
                    || ((ANError) error).getErrorCode() == HttpURLConnection.HTTP_BAD_REQUEST) {
                getView().onEmailError(mContext.getString(R.string.error_no_such_user));
            } else {
                getView().onError(mContext.getString(R.string.error_api_unknown));
            }
        } else {
            getView().onError(mContext.getString(R.string.error_api_unknown));
        }
    }

    @Override
    public void handleResponse(ForgotPasswordResponse response) {
        if (!isViewAttached()) {
            return;
        }

        getView().hideProgress();

        if (response == null) {
            getView().onError(R.string.error_api_unknown);
            return;
        }

        getView().showMessage(R.string.auth_fp_success);
        getView().onSuccessReset();
    }

    @Override
    public void onValidateForgotPassword(String email) {
        boolean isSuccess = true;

        if (TextUtils.isEmpty(email)) {
            isSuccess = false;
            getView().onEmailError(mContext.getString(R.string.error_forgot_password_invalid_email));
        }

        if (email != null && !email.isEmpty() && !android.util.Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches()) {
            isSuccess = false;
            getView().onEmailError(mContext.getString(R.string.error_forgot_password_invalid_email));
        }

        if (isSuccess) {
            getView().onEmailValid();
        }
    }
}
