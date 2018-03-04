package com.way.mat.templatemvp.ui.fragment.auth.login;

import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatEditText;

import com.way.mat.templatemvp.R;
import com.way.mat.templatemvp.ui.base.BaseFragment;

import butterknife.BindView;

public abstract class LoginFragmentWrapper extends BaseFragment {

    @BindView(R.id.til_email)
    protected TextInputLayout tilEmail;
    @BindView(R.id.et_email)
    protected AppCompatEditText etEmail;
    @BindView(R.id.til_password)
    protected TextInputLayout tilPassword;
    @BindView(R.id.et_password)
    protected AppCompatEditText etPassword;

}
