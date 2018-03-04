package com.way.mat.templatemvp.ui.fragment.auth.registration;

import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.widget.RadioButton;

import com.way.mat.templatemvp.R;
import com.way.mat.templatemvp.ui.base.BaseFragment;

import butterknife.BindView;

public abstract class RegistrationFragmentWrapper extends BaseFragment {

    @BindView(R.id.til_first_name)
    protected TextInputLayout tilFirstName;
    @BindView(R.id.et_first_name)
    protected AppCompatEditText etFirstName;
    @BindView(R.id.til_last_name)
    protected TextInputLayout tilLastName;
    @BindView(R.id.et_last_name)
    protected AppCompatEditText etLastName;
    @BindView(R.id.til_email)
    protected TextInputLayout tilEmail;
    @BindView(R.id.et_email)
    protected AppCompatEditText etEmail;
    @BindView(R.id.til_password)
    protected TextInputLayout tilPassword;
    @BindView(R.id.et_password)
    protected AppCompatEditText etPassword;
    @BindView(R.id.til_repeat_password)
    protected TextInputLayout tilRepeatPassword;
    @BindView(R.id.et_repeat_password)
    protected AppCompatEditText etRepeatPassword;

    @BindView(R.id.btn_registration)
    protected AppCompatButton btnRegistration;

}
