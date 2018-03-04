package com.way.mat.templatemvp.ui.base;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;

import com.way.mat.templatemvp.R;

import butterknife.BindView;

public class BaseActivityWrapper extends AppCompatActivity {

    @Nullable
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @Nullable
    @BindView(R.id.tb_title)
    protected AppCompatTextView title;

    @Nullable
    @BindView(R.id.tb_back)
    protected AppCompatImageView back;

    @Nullable
    @BindView(R.id.tb_done)
    protected AppCompatButton done;

}
