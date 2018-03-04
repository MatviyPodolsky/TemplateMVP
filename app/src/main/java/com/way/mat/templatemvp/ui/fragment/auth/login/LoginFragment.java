package com.way.mat.templatemvp.ui.fragment.auth.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.way.mat.templatemvp.R;
import com.way.mat.templatemvp.di.component.ActivityComponent;
import com.way.mat.templatemvp.ui.activity.main.MainActivity;
import com.way.mat.templatemvp.ui.base.BaseActivity;
import com.way.mat.templatemvp.ui.fragment.auth.forgotpassword.ForgotPasswordFragment;
import com.way.mat.templatemvp.ui.fragment.auth.registration.RegistrationFragment;
import com.way.mat.templatemvp.ui.widget.textwatchers.TilTextWatcher;

import javax.inject.Inject;

import butterknife.OnClick;

public class LoginFragment extends LoginFragmentWrapper implements LoginMvpView {

    public static final String TAG = LoginFragment.class.getSimpleName();

    @Inject
    LoginMvpPresenter<LoginMvpView> mPresenter;

    public static LoginFragment getInstance() {
        LoginFragment fragment = new LoginFragment();
        Bundle arg = new Bundle();
        fragment.setArguments(arg);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            mPresenter.onAttach(this);
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etEmail.addTextChangedListener(new TilTextWatcher(tilEmail));
        etPassword.addTextChangedListener(new TilTextWatcher(tilPassword));
    }

    @Nullable
    @OnClick(R.id.btn_login)
    void onClickLogin() {
        mPresenter.onValidateLogin(etEmail.getText().toString().trim(),
                etPassword.getText().toString().trim());
    }

    @Nullable
    @OnClick(R.id.tv_forgot_password)
    void onClickForgotPassword() {
        if (getActivity() != null) {
            ((BaseActivity) getActivity()).replaceWithBackStackFragment(
                    R.id.frame_container, ForgotPasswordFragment.getInstance());
        }
    }

    @Nullable
    @OnClick(R.id.tv_register)
    void onClickRegistration() {
        if (getActivity() != null) {
            ((BaseActivity) getActivity()).replaceWithBackStackFragment(
                    R.id.frame_container, RegistrationFragment.getInstance());
        }
    }

    @Override
    public void onEmailError(String error) {
        tilEmail.setError(error);
    }

    @Override
    public void onPasswordError(String error) {
        tilPassword.setError(error);
    }

    @Override
    public void onSuccessLogin() {
        if (getActivity() != null) {
            getContext().startActivity(new Intent(getContext(), MainActivity.class)
                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
            getActivity().finish();
        }
    }

    @Override
    public void onBadCredentials() {
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_login;
    }

    @Override
    public int getTitle() {
        return R.string.title_login;
    }

    @Override
    public boolean hasBackButton() {
        return false;
    }

    @Override
    public boolean hasDoneButton() {
        return false;
    }

    @Override
    protected void setUp() {

    }

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
