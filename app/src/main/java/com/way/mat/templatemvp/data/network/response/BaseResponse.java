package com.way.mat.templatemvp.data.network.response;

import com.google.gson.annotations.Expose;

public class BaseResponse {

    @Expose
    private String status;

    public String getStatus() {
        return status;
    }

}
