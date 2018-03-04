package com.way.mat.templatemvp.ui.base;


import com.way.mat.templatemvp.data.DataManager;
import com.way.mat.templatemvp.data.db.controllers.RealmFacade;
import com.way.mat.templatemvp.util.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.realm.Realm;

public class BaseServicePresenter<V extends ServiceMvpView> implements ServiceMvpPresenter<V> {

    private V mView;

    private final DataManager mDataManager;
    private final SchedulerProvider mSchedulerProvider;
    private final CompositeDisposable mCompositeDisposable;
    private final Realm mRealm;
    private final RealmFacade mRealmFacade;

    @Inject
    public BaseServicePresenter(DataManager dataManager,
                                SchedulerProvider schedulerProvider,
                                CompositeDisposable compositeDisposable,
                                Realm realm,
                                RealmFacade realmFacade) {
        this.mDataManager = dataManager;
        this.mSchedulerProvider = schedulerProvider;
        this.mCompositeDisposable = compositeDisposable;
        this.mRealm = realm;
        this.mRealmFacade = realmFacade;
    }

    @Override
    public void onAttach(final V view) {
        mView = view;
    }

    @Override
    public void onDetach() {
        mCompositeDisposable.dispose();
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

    public Realm getRealm() {
        return mRealm;
    }

    public RealmFacade getRealmFacade() {
        return mRealmFacade;
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.onAttach(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }

}
