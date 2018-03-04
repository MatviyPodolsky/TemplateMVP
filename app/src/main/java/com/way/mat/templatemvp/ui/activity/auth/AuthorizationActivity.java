package com.way.mat.templatemvp.ui.activity.auth;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;


import com.way.mat.templatemvp.R;
import com.way.mat.templatemvp.ui.base.BaseActivity;
import com.way.mat.templatemvp.ui.base.BaseFragment;
import com.way.mat.templatemvp.ui.fragment.auth.login.LoginFragment;
import com.way.mat.templatemvp.ui.fragment.auth.login.LoginPresenter;
import com.way.mat.templatemvp.util.AppConstants;

import javax.inject.Inject;

public class AuthorizationActivity extends BaseActivity implements AuthorizationMvpView {

    @Inject
    AuthorizationMvpPresenter<AuthorizationMvpView> mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        mPresenter.onAttach(this);
        setUp();

        //makes UI fullscreen with transparent status bar
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
    }

    @Override
    protected int getLayoutResources() {
        return R.layout.activity_authorization;
    }

    @Override
    protected void setUp() {
        replaceNonBackStackFragment(R.id.frame_container, LoginFragment.getInstance(), AppConstants.SIGN_IN_TAG);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == LoginPresenter.RC_SIGN_IN) {
            BaseFragment fragment = (BaseFragment) getSupportFragmentManager()
                    .findFragmentByTag(AppConstants.SIGN_IN_TAG);
            if (fragment != null) {
                fragment.onActivityResult(requestCode, resultCode, data);
                super.onActivityResult(requestCode, resultCode, data);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}
