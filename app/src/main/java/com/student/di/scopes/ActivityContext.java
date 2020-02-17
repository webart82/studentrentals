package com.student.di.scopes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;
/**
 * Copyright (c) $today.year.
 * This file was created by eli on 2/14/20
 * You are not allowed to copy it or use it in another project
 * Without permission from creator
 **/
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityContext {
}
