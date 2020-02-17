package com.student.di;

import com.student.Api.NetworkModule;
import com.student.di.scopes.ApplicationScope;
import com.student.rentals.ui.activities.LoginActivity.View.LoginActivity;
import com.student.rentals.ui.activities.MainActivity;
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
@Component
public interface ApplicationComponent {
    void inject(SignUpActivity signUpActivity);
    void inject(MainActivity mainActivity);
    void inject(LoginActivity loginActivity);

}
