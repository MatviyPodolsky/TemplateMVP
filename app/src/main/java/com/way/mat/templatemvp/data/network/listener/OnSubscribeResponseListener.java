package com.way.mat.templatemvp.data.network.listener;

public interface OnSubscribeResponseListener<V> {

    void handleError(Throwable error);

    void handleResponse(V v);
}
