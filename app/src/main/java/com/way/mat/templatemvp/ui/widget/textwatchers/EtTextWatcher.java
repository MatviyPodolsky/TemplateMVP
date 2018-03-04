package com.way.mat.templatemvp.ui.widget.textwatchers;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.ViewParent;

/**
 * Created by matviy on 12/29/17.
 */

public class EtTextWatcher implements TextWatcher {

    private AppCompatEditText mEditText;

    public EtTextWatcher(AppCompatEditText editText) {
        mEditText = editText;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        ViewParent parent = mEditText.getParent();
        if (parent != null && parent instanceof TextInputLayout) {
            if (!TextUtils.isEmpty(mEditText.getError())) {
                mEditText.setError("");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mEditText.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
                }
            }
        }
    }

}
