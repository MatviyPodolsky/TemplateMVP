/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.way.mat.templatemvp.di.component;

import com.way.mat.templatemvp.di.PerActivity;
import com.way.mat.templatemvp.di.module.ActivityModule;
import com.way.mat.templatemvp.ui.activity.auth.AuthorizationActivity;
import com.way.mat.templatemvp.ui.activity.main.MainActivity;
import com.way.mat.templatemvp.ui.activity.splash.SplashActivity;
import com.way.mat.templatemvp.ui.fragment.auth.forgotpassword.ForgotPasswordFragment;
import com.way.mat.templatemvp.ui.fragment.auth.login.LoginFragment;
import com.way.mat.templatemvp.ui.fragment.auth.registration.RegistrationFragment;
import com.way.mat.templatemvp.ui.fragment.core.home.HomeFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity activity);

    void inject(AuthorizationActivity activity);

    void inject(SplashActivity activity);

    void inject(LoginFragment fragment);

    void inject(RegistrationFragment fragment);

    void inject(ForgotPasswordFragment fragment);

    void inject(HomeFragment fragment);

}
