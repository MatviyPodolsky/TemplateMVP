package com.way.mat.templatemvp.ui.widget.textwatchers;

import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

/**
 * Created by matviy on 12/29/17.
 */

public class TilTextWatcher implements TextWatcher {

    private TextInputLayout mTIL;

    public TilTextWatcher(TextInputLayout til) {
        mTIL = til;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
            if (!TextUtils.isEmpty((mTIL.getError()))) {
                mTIL.setError("");
                mTIL.setErrorEnabled(false);
            }
    }

}
