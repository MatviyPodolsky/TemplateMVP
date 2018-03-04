package com.way.mat.templatemvp.ui.base;

import com.androidnetworking.error.ANError;
import com.pixplicity.easyprefs.library.Prefs;
import com.way.mat.templatemvp.R;
import com.way.mat.templatemvp.data.DataManager;
import com.way.mat.templatemvp.util.rx.SchedulerProvider;

import javax.inject.Inject;
import javax.net.ssl.HttpsURLConnection;

import io.reactivex.disposables.CompositeDisposable;

public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private V mView;

    private final DataManager mDataManager;
    private final SchedulerProvider mSchedulerProvider;
    private final CompositeDisposable mCompositeDisposable;

    @Inject
    public BasePresenter(DataManager dataManager,
                         SchedulerProvider schedulerProvider,
                         CompositeDisposable compositeDisposable) {
        this.mDataManager = dataManager;
        this.mSchedulerProvider = schedulerProvider;
        this.mCompositeDisposable = compositeDisposable;
    }

    @Override
    public void onAttach(final V view) {
        mView = view;
    }

    @Override
    public void onDetach() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
        mView = null;
    }

    public final V getView() {
        return mView;
    }

    public boolean isViewAttached() {
        return mView != null;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public DataManager getDataManager() {
        return mDataManager;
    }

    public SchedulerProvider getSchedulerProvider() {
        return mSchedulerProvider;
    }

    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.onAttach(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }

    @Override
    public void handleApiError(ANError error) {
        if (error == null || error.getErrorBody() == null) {
            getView().onError(R.string.error_api_unknown);
            return;
        }

        switch (error.getErrorCode()) {
            case HttpsURLConnection.HTTP_UNAUTHORIZED:
            case HttpsURLConnection.HTTP_FORBIDDEN:
                getView().onError(R.string.error_logged_out);
                setUserAsLoggedOut();
                getView().openActivityOnTokenExpire();
                break;
            case HttpsURLConnection.HTTP_INTERNAL_ERROR:
                getView().onError(R.string.error_internal_server_error);
                break;
            case HttpsURLConnection.HTTP_NOT_FOUND:
                getView().onError(R.string.error_not_found);
                break;
            default:
                getView().onError(R.string.error_api_unknown);
                break;
        }

    }

    @Override
    public void setUserAsLoggedOut() {
        Prefs.clear();
    }

}
