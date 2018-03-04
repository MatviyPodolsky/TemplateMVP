package com.way.mat.templatemvp;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.support.multidex.MultiDex;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.gsonparserfactory.GsonParserFactory;
import com.pixplicity.easyprefs.library.Prefs;
import com.way.mat.templatemvp.data.DataManager;
import com.way.mat.templatemvp.di.component.ApplicationComponent;
import com.way.mat.templatemvp.di.component.DaggerApplicationComponent;
import com.way.mat.templatemvp.di.module.ApplicationModule;
import com.way.mat.templatemvp.util.L;
import com.way.mat.templatemvp.util.PrefKeys;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by matviy on 3/4/18.
 */

public class App extends Application {

    @Inject
    DataManager mDataManager;

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();

        mApplicationComponent.inject(this);

        buildPrefs();
        initRealm();

        L.init();

        AndroidNetworking.initialize(getApplicationContext());
        AndroidNetworking.setParserFactory(new GsonParserFactory());
        //enable if no file uploading. Uploading doesn't work with logs.
//        if (BuildConfig.DEBUG) {
//            AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BODY);
//        }

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    private void initRealm() {
        Realm.init(getApplicationContext());
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }

    protected void buildPrefs() {
        new Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(getPackageName())
                .setUseDefaultSharedPreference(true)
                .build();
        if (!Prefs.contains(PrefKeys.INSTALL_TIME)) {
            Prefs.putLong(PrefKeys.INSTALL_TIME, System.currentTimeMillis());
        }
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }

}
