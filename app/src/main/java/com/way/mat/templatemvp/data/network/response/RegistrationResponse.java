package com.way.mat.templatemvp.data.network.response;

import com.google.gson.annotations.Expose;
import com.way.mat.templatemvp.data.entity.User;

public class RegistrationResponse extends BaseResponse {

    @Expose
    private String token;

    @Expose
    private User user;

    public String getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }

}
