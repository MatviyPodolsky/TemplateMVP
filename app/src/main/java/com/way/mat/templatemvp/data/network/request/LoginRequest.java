package com.way.mat.templatemvp.data.network.request;

import com.google.gson.annotations.Expose;

/**
 * Created by matviy on 12/27/17.
 */

public class LoginRequest {

    @Expose
    private String email;
    @Expose
    private String password;

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
