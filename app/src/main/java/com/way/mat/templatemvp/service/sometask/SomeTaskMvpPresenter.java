package com.way.mat.templatemvp.service.sometask;

import com.way.mat.templatemvp.ui.base.ServiceMvpPresenter;
import com.way.mat.templatemvp.ui.base.ServiceMvpView;

public interface SomeTaskMvpPresenter<V extends ServiceMvpView> extends ServiceMvpPresenter<V> {

    void doSomething();

    void finish();

}
