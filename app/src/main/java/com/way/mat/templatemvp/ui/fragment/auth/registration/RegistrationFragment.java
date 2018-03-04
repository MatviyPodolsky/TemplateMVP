package com.way.mat.templatemvp.ui.fragment.auth.registration;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pixplicity.easyprefs.library.Prefs;
import com.way.mat.templatemvp.R;
import com.way.mat.templatemvp.di.component.ActivityComponent;
import com.way.mat.templatemvp.ui.activity.main.MainActivity;
import com.way.mat.templatemvp.ui.widget.textwatchers.TilTextWatcher;
import com.way.mat.templatemvp.util.DialogUtil;
import com.way.mat.templatemvp.util.PrefKeys;

import javax.inject.Inject;

import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class RegistrationFragment extends RegistrationFragmentWrapper implements RegistrationMvpView {

    public static final String TAG = RegistrationFragment.class.getSimpleName();

    @Inject
    RegistrationMvpPresenter<RegistrationMvpView> mPresenter;

    public static RegistrationFragment getInstance() {
        RegistrationFragment fragment = new RegistrationFragment();
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
        initTextWatchers();
    }

    private void initTextWatchers() {
        etFirstName.addTextChangedListener(new TilTextWatcher(tilFirstName));
        etLastName.addTextChangedListener(new TilTextWatcher(tilLastName));
        etEmail.addTextChangedListener(new TilTextWatcher(tilEmail));
        etPassword.addTextChangedListener(new TilTextWatcher(tilPassword));
        etRepeatPassword.addTextChangedListener(new TilTextWatcher(tilRepeatPassword));
    }

    @OnClick(R.id.btn_registration)
    void onClickRegistration() {
        mPresenter.onValidateRegistration(
                etFirstName.getText().toString().trim(),
                etLastName.getText().toString().trim(),
                etPassword.getText().toString().trim(),
                etRepeatPassword.getText().toString().trim(),
                etEmail.getText().toString().trim());
    }

    @OnCheckedChanged(R.id.cb_terms)
    void onTermsChecked(boolean isChecked) {
        btnRegistration.setEnabled(isChecked);
    }

    @Override
    public void onFirstNameError(int error) {
        onFirstNameError(getString(error));
    }

    @Override
    public void onFirstNameError(String error) {
        tilFirstName.setError(error);
        etFirstName.requestFocus();
    }

    @Override
    public void onLastNameError(int error) {
        onLastNameError(getString(error));
    }

    @Override
    public void onLastNameError(String error) {
        tilLastName.setError(error);
    }

    @Override
    public void onEmailError(int error) {
        onEmailError(getString(error));
    }

    @Override
    public void onEmailError(String error) {
        tilEmail.setError(error);
    }

    @Override
    public void onPasswordError(int error) {
        onPasswordError(getString(error));
    }

    @Override
    public void onPasswordError(String error) {
        tilPassword.setError(error);
    }

    @Override
    public void onRepeatPasswordError(int error) {
        tilRepeatPassword.setError(getString(error));
    }

    @Override
    public void onSuccessRegistration() {
        if (getActivity() != null) {
            getContext().startActivity(new Intent(getContext(), MainActivity.class)
                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
            getActivity().finish();
        }
    }

    @Override
    public void onErrorRegistration() {
        DialogUtil.showAuthErrorDialog(getActivity(), getString(R.string.error_dialog_message));
    }

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_registration;
    }

    @Override
    public int getTitle() {
        return R.string.title_registration;
    }

    @Override
    public boolean hasBackButton() {
        return true;
    }

    @Override
    public boolean hasDoneButton() {
        return false;
    }

    @Override
    protected void setUp() {

    }
}
