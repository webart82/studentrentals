package com.student.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Copyright (c) $today.year.
 * This file was created by eli on 2/13/20
 * You are not allowed to copy it or use it in another project
 * Without permission from creator
 **/
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ApplicationScope {}