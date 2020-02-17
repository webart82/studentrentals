package com.student.Api;

import com.student.di.scopes.ApplicationScope;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Copyright (c) $today.year.
 * This file was created by eli on 2/14/20
 * You are not allowed to copy it or use it in another project
 * Without permission from creator
 **/
@Module
public class NetworkModule {
    @Singleton
    @Provides
    public ApiInterface getApiClient(){
        return ApiClient.getInstance().create(ApiInterface.class);
    }


}

