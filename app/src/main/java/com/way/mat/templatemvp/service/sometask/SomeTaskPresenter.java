package com.way.mat.templatemvp.service.sometask;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.PowerManager;
import android.util.Log;

import com.way.mat.templatemvp.data.DataManager;
import com.way.mat.templatemvp.data.db.controllers.RealmFacade;
import com.way.mat.templatemvp.di.ApplicationContext;
import com.way.mat.templatemvp.ui.base.BaseServicePresenter;
import com.way.mat.templatemvp.util.rx.SchedulerProvider;

import java.io.File;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.realm.Realm;

import static android.content.Context.POWER_SERVICE;


public class SomeTaskPresenter<V extends SomeTaskMvpView> extends BaseServicePresenter<V>
        implements SomeTaskMvpPresenter<V> {

    public static final String TAG = "Service";

    private Context mContext;

    private WifiManager.WifiLock mWifiLock;
    private PowerManager.WakeLock mWakeLock;

    @Inject
    public SomeTaskPresenter(@ApplicationContext Context context,
                             DataManager dataManager,
                             SchedulerProvider schedulerProvider,
                             CompositeDisposable compositeDisposable,
                             Realm realm,
                             RealmFacade realmFacade) {
        super(dataManager, schedulerProvider, compositeDisposable, realm, realmFacade);
        mContext = context;
    }

    @Override
    public void onAttach(V view) {
        super.onAttach(view);

        PowerManager powerManager = (PowerManager) mContext.getApplicationContext().getSystemService(POWER_SERVICE);
        mWakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
                "wakelock");
        mWifiLock = ((WifiManager) mContext.getApplicationContext().getSystemService(Context.WIFI_SERVICE))
                .createWifiLock(WifiManager.WIFI_MODE_FULL, "uAmp_lock");
        acquireLock();
    }

    @Override
    public void onDetach() {
        releaseLock();
        super.onDetach();
    }

    private void acquireLock() {
        if (mWifiLock != null && !mWifiLock.isHeld()) {
            mWifiLock.acquire();
        }
        if ((mWakeLock != null) && (!mWakeLock.isHeld())) mWakeLock.acquire();
    }

    private void releaseLock() {
        if (mWifiLock != null && mWifiLock.isHeld()) {
            mWifiLock.release();
        }
        if ((mWakeLock != null) && (mWakeLock.isHeld())) mWakeLock.release();
    }

    @Override
    public void doSomething() {
        if (true) {
            getView().onComplete();
        }
    }

    @Override
    public void finish() {
        getView().onFinished();
    }

}
