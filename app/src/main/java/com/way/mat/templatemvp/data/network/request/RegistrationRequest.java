package com.way.mat.templatemvp.data.network.request;

import com.google.gson.annotations.Expose;

/**
 * Created by matviy on 12/27/17.
 */

public class RegistrationRequest {

    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private String password;
    @Expose
    private String email;

    public RegistrationRequest(String firstName, String lastName, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
    }
}
