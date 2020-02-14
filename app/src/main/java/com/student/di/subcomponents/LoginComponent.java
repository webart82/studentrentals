package com.student.di.subcomponents;

import com.student.di.PerActivity;
import com.student.rentals.ui.activities.LoginActivity.View.LoginActivity;

import dagger.Subcomponent;

/**
 * Copyright (c) $today.year.
 * This file was created by eli on 2/14/20
 * You are not allowed to copy it or use it in another project
 * Without permission from creator
 **/
@PerActivity
@Subcomponent
public interface LoginComponent {

    @Subcomponent.Factory
    interface Factory {
        LoginComponent create();
    }
    void inject(LoginActivity loginActivity);
}
