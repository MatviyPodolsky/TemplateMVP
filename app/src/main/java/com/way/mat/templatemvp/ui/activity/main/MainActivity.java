package com.way.mat.templatemvp.ui.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.MenuItem;

import com.pixplicity.easyprefs.library.Prefs;
import com.way.mat.templatemvp.R;
import com.way.mat.templatemvp.ui.activity.auth.AuthorizationActivity;
import com.way.mat.templatemvp.ui.base.BaseFragment;
import com.way.mat.templatemvp.ui.base.interfaces.IResultable;
import com.way.mat.templatemvp.ui.fragment.core.home.HomeFragment;
import com.way.mat.templatemvp.util.AppConstants;
import com.way.mat.templatemvp.util.PrefKeys;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import butterknife.OnClick;

public class MainActivity extends MainActivityWrapper
        implements MainMvpView {

    @Inject
    MainMvpPresenter<MainMvpView> mPresenter;

    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        mPresenter.onAttach(this);
        EventBus.getDefault().register(this);
        setUp();
    }

    @Override
    protected int getLayoutResources() {
        return R.layout.activity_main;
    }

    @Override
    protected void setUp() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        replaceNonBackStackFragment(R.id.frame_container, HomeFragment.getInstance());

        getSupportFragmentManager().addOnBackStackChangedListener(() -> {
            BaseFragment currentFragment = (BaseFragment) fragmentManager.findFragmentById(R.id.frame_container);
//            if (currentFragment instanceof ExampleFragment) {
//                ((ExampleFragment) currentFragment).refresh();
//            }
        });
    }

    private void updateActionBar(BaseFragment fragment) {
        setActionBarTitle(fragment.getTitle());
        showBackButton(fragment.hasBackButton());
    }

    @Override
    public void onLogout() {
        startActivity(new Intent(this, AuthorizationActivity.class));
        finish();
    }

    @Nullable
    @OnClick(R.id.tb_done)
    public void onDoneClick() {
        BaseFragment currentFragment = (BaseFragment) fragmentManager.findFragmentById(R.id.frame_container);
        if (currentFragment instanceof IResultable) {
            ((IResultable) currentFragment).onResult();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == AppConstants.REQUEST_CAMERA_TAKE_PHOTO || requestCode == AppConstants.REQUEST_GALLERY_TAKE_PICTURE) {
            BaseFragment currentFragment = (BaseFragment) fragmentManager.findFragmentById(R.id.frame_container);
            if (currentFragment != null) {
                currentFragment.onActivityResult(requestCode, resultCode, data);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

}
