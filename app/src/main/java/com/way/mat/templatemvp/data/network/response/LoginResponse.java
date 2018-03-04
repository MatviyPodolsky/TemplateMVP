package com.way.mat.templatemvp.data.network.response;

import com.google.gson.annotations.Expose;
import com.way.mat.templatemvp.data.entity.User;

/**
 * Created by matviy on 12/27/17.
 */

public class LoginResponse extends BaseResponse {

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
