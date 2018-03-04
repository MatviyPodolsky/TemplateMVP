package com.way.mat.templatemvp.ui.fragment.auth.forgotpassword;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.way.mat.templatemvp.R;
import com.way.mat.templatemvp.di.component.ActivityComponent;
import com.way.mat.templatemvp.ui.widget.textwatchers.EtTextWatcher;

import javax.inject.Inject;

import butterknife.OnClick;

public class ForgotPasswordFragment extends ForgotPasswordFragmentWrapper implements ForgotPasswordMvpView {

    public static final String TAG = ForgotPasswordFragment.class.getSimpleName();

    @Inject
    ForgotPasswordMvpPresenter<ForgotPasswordMvpView> mPresenter;

    public static ForgotPasswordFragment getInstance() {
        ForgotPasswordFragment fragment = new ForgotPasswordFragment();
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
        etEmail.addTextChangedListener(new EtTextWatcher(etEmail));
    }

    @Nullable
    @OnClick(R.id.btn_submit)
    void onClickForgotPassword() {
        mPresenter.onSuccessValidatingForgotPassword(etEmail.getText().toString().trim());
    }

    @Nullable
    @OnClick(R.id.tv_back)
    void onClickBack() {
        if (getActivity() != null) {
            getActivity().getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public void onEmailError(String error) {
        if (tilEmail != null && btnSubmit != null) {
            tilEmail.setError(error);
            if (btnSubmit.isEnabled()) {
                btnSubmit.setEnabled(false);
            }
        }
    }

    @Override
    public void onEmailValid() {
        if (btnSubmit != null && tilEmail != null) {
            if (!btnSubmit.isEnabled()) {
                btnSubmit.setEnabled(true);
            }
            if (!TextUtils.isEmpty(tilEmail.getError())) {
                tilEmail.setError("");
            }
        }
    }

    @Override
    public void onSuccessReset() {
        if (getActivity() != null) {
            getActivity().getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_forgot_password;
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
}
