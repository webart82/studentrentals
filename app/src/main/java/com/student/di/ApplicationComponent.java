package com.student.di;

import com.student.Api.ApiClient;
import com.student.rentals.ui.activities.LoginActivity.View.LoginActivity;
import com.student.rentals.ui.activities.signUp.view.SignUpActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Copyright (c) $today.year.
 * This file was created by eli on 2/13/20
 * You are not allowed to copy it or use it in another project
 * Without permission from creator
 **/
@ApplicationScope
@Singleton
@Component(modules = ApiClient.class)
public interface ApplicationComponent {

    void inject(LoginActivity loginActivity);
    void inject(SignUpActivity signUpActivity);
}
