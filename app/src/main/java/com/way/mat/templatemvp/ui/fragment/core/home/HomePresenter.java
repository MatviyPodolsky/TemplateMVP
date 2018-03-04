package com.way.mat.templatemvp.ui.fragment.core.home;

import android.content.Context;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.pixplicity.easyprefs.library.Prefs;
import com.way.mat.templatemvp.R;
import com.way.mat.templatemvp.data.DataManager;
import com.way.mat.templatemvp.data.network.listener.OnSubscribeResponseListener;
import com.way.mat.templatemvp.di.ApplicationContext;
import com.way.mat.templatemvp.ui.base.BasePresenter;
import com.way.mat.templatemvp.util.AppConstants;
import com.way.mat.templatemvp.util.PermissionsUtil;
import com.way.mat.templatemvp.util.PrefKeys;
import com.way.mat.templatemvp.util.rx.SchedulerProvider;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter<V extends HomeMvpView> extends BasePresenter<V> implements
        HomeMvpPresenter<V>, OnSubscribeResponseListener {

    private Context mContext;

    @Inject
    public HomePresenter(@ApplicationContext Context context,
                         DataManager dataManager,
                         SchedulerProvider schedulerProvider,
                         CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
        mContext = context;
    }

    @Override
    public void handleError(Throwable error) {

    }

    @Override
    public void handleResponse(Object o) {
    }

    @Override
    public void onAttach(V view) {
        super.onAttach(view);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
