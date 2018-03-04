package com.way.mat.templatemvp.ui.base;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;


import com.way.mat.templatemvp.App;
import com.way.mat.templatemvp.R;
import com.way.mat.templatemvp.di.component.ActivityComponent;
import com.way.mat.templatemvp.di.component.DaggerActivityComponent;
import com.way.mat.templatemvp.di.module.ActivityModule;
import com.way.mat.templatemvp.ui.activity.auth.AuthorizationActivity;
import com.way.mat.templatemvp.util.CommonUtils;
import com.way.mat.templatemvp.util.NetworkUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public abstract class BaseActivity extends BaseActivityWrapper
        implements MvpView, BaseFragment.Callback {

    protected FragmentManager fragmentManager;
    private Unbinder unbinder;
    private ProgressDialog mProgressDialog;
    protected ActionBar actionBar;

    private ActivityComponent mActivityComponent;

    protected BaseActivity() {
        fragmentManager = getSupportFragmentManager();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(((App) getApplication()).getComponent())
                .build();

        setContentView(getLayoutResources());

        unbinder = ButterKnife.bind(this);
        actionBar = getSupportActionBar();
    }

    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroy();
    }

    public void addFragment(int containerViewId, Fragment fragment) {
        fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit)
                .addToBackStack(fragment.getClass().getName())
                .add(containerViewId, fragment, fragment.getClass().getName())
                .commitAllowingStateLoss();
    }

    public void replaceWithBackStackFragment(int containerViewId, Fragment fragment) {
        replaceWithBackStackFragment(containerViewId, fragment, fragment.getClass().getName());
    }

    public void replaceWithBackStackFragment(int containerViewId, Fragment fragment, String tag) {
        fragmentManager.beginTransaction()
                .addToBackStack(tag)
                .setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit)
                .replace(containerViewId, fragment, tag)
                .commit();
    }

    public void replaceNonBackStackFragment(int containerViewId, Fragment fragment) {
        replaceNonBackStackFragment(containerViewId, fragment, fragment.getClass().getName());
    }

    public void replaceNonBackStackFragment(int containerViewId, Fragment fragment, String tag) {
        fragmentManager.beginTransaction()
                .replace(containerViewId, fragment, tag)
                .commit();
    }

    protected void showSnackBarMessage(View view, @StringRes int message) {
        if (view != null) {
            Snackbar.make(view, message, Snackbar.LENGTH_LONG).setAction("Action", null).show();
        }
    }

    protected void showSnackBarMessage(View view, String message) {
        if (view != null) {
            Snackbar.make(view, message, Snackbar.LENGTH_LONG).setAction("Action", null).show();
        }
    }

    @Override
    public void onError(int error) {
        onError(getString(error));
    }

    @Override
    public void onError(String error) {
        showSnackBarMessage(getCurrentFocus(), error);
    }

    @Override
    public void showProgress() {
        runOnUiThread(() -> {
            hideProgress();
            mProgressDialog = CommonUtils.showLoadingDialog(BaseActivity.this);
        });
    }

    @Override
    public void hideProgress() {
        runOnUiThread(() -> {
            if (mProgressDialog != null && mProgressDialog.isShowing()) {
                mProgressDialog.cancel();
            }
        });
    }

    @Override
    public Context getContext() {
        return super.getApplicationContext();
    }

    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkConnected(getApplicationContext());
    }

    @Override
    public void showMessage(final String message) {
        runOnUiThread(() -> Toast.makeText(BaseActivity.this, message, Toast.LENGTH_SHORT).show());
    }

    @Override
    public void showMessage(int resId) {
        Toast.makeText(this, resId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }

    @Override
    public void openActivityOnTokenExpire() {
        startActivity(new Intent(this, AuthorizationActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
        finish();
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissionsSafely(String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean hasPermission(String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    public void setActionBarTitle(@StringRes int titleRes) {
        if (title != null) {
            title.setText(titleRes);
        }
    }

    public void showBackButton(boolean isShow) {
        if (back != null) {
            back.setVisibility(isShow ? View.VISIBLE : View.GONE);
        }
    }

    public void showDoneButton(boolean hasDone) {
        if (done != null) {
            done.setVisibility(hasDone ? View.VISIBLE : View.GONE);
        }
    }

    protected abstract int getLayoutResources();

    protected abstract void setUp();

}
