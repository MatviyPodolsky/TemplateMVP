package com.way.mat.templatemvp.data.network.request;

import com.google.gson.annotations.Expose;

/**
 * Created by matviy on 12/27/17.
 */

public class ForgotPasswordRequest {

    @Expose
    private String email;

    public ForgotPasswordRequest(String email) {
        this.email = email;
    }
}
