package com.way.mat.templatemvp.ui.base;

import android.app.Service;
import android.content.Context;

import com.way.mat.templatemvp.App;
import com.way.mat.templatemvp.di.component.DaggerServiceComponent;
import com.way.mat.templatemvp.di.component.ServiceComponent;
import com.way.mat.templatemvp.di.module.ServiceModule;
import com.way.mat.templatemvp.util.NetworkUtils;

public abstract class BaseService extends Service
        implements ServiceMvpView {

    private ServiceComponent mServiceComponent;

    protected BaseService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mServiceComponent = DaggerServiceComponent.builder()
                .serviceModule(new ServiceModule(this))
                .applicationComponent(((App) getApplication()).getComponent())
                .build();
    }

    public ServiceComponent getServiceComponent() {
        return mServiceComponent;
    }

    @Override
    public Context getContext() {
        return super.getApplicationContext();
    }

    @Override
    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkConnected(getApplicationContext());
    }

}
