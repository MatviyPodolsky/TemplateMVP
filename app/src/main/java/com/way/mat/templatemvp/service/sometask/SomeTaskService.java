package com.way.mat.templatemvp.service.sometask;

import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.way.mat.templatemvp.R;
import com.way.mat.templatemvp.di.component.ServiceComponent;
import com.way.mat.templatemvp.ui.base.BaseService;
import com.way.mat.templatemvp.util.NotificationHelper;

import javax.inject.Inject;

public class SomeTaskService extends BaseService implements SomeTaskMvpView {

    public static final int S_ID = 123425;

    @Inject
    SomeTaskMvpPresenter<SomeTaskMvpView> mPresenter;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ServiceComponent component = getServiceComponent();
        if (component != null) {
            component.inject(this);
            mPresenter.onAttach(this);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mPresenter.doSomething();
        startForeground(S_ID,
                NotificationHelper.makeNotification(this, getContext().getString(R.string.app_name), getContext().getString(R.string.app_name))
        );
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    public void onComplete() {
        mPresenter.finish();
    }

    @Override
    public void onFinished() {
        stopForeground(true);
        stopSelf();
    }

}
