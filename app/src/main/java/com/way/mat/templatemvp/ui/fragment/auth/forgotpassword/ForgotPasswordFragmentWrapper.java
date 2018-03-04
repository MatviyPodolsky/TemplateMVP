package com.way.mat.templatemvp.ui.fragment.auth.forgotpassword;

import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;

import com.way.mat.templatemvp.R;
import com.way.mat.templatemvp.ui.base.BaseFragment;

import butterknife.BindView;

public abstract class ForgotPasswordFragmentWrapper extends BaseFragment {

    @BindView(R.id.et_email)
    protected AppCompatEditText etEmail;
    @BindView(R.id.til_email)
    protected TextInputLayout tilEmail;
    @BindView(R.id.btn_submit)
    protected AppCompatButton btnSubmit;

}
