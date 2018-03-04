package com.way.mat.templatemvp.ui.activity.main;

import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;


import com.way.mat.templatemvp.R;
import com.way.mat.templatemvp.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public abstract class MainActivityWrapper extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Nullable
    @OnClick(R.id.tb_back)
    public void onBackClick() {
        onBackPressed();
    }

    @Nullable
    @OnClick(R.id.tb_cancel)
    public void onCancelClick() {
        onBackPressed();
    }

}
