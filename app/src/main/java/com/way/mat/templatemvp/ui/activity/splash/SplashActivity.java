package com.way.mat.templatemvp.ui.activity.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.pixplicity.easyprefs.library.Prefs;
import com.way.mat.templatemvp.R;
import com.way.mat.templatemvp.ui.activity.auth.AuthorizationActivity;
import com.way.mat.templatemvp.ui.activity.main.MainActivity;
import com.way.mat.templatemvp.ui.base.BaseActivity;
import com.way.mat.templatemvp.util.PrefKeys;

import javax.inject.Inject;

public class SplashActivity extends BaseActivity implements SplashMvpView {

    public static final String TAG = SplashActivity.class.getSimpleName();

    @Inject
    SplashMvpPresenter<SplashMvpView> mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        mPresenter.onAttach(this);
        setUp();
    }

    @Override
    protected int getLayoutResources() {
        return R.layout.activity_splash;
    }

    @Override
    protected void setUp() {
        if (Prefs.contains(PrefKeys.TOKEN)) {
            startActivity(new Intent(this, MainActivity.class)
                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
            finish();
        } else {
            startActivity(new Intent(this, AuthorizationActivity.class)
                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
            finish();
        }
    }

}
