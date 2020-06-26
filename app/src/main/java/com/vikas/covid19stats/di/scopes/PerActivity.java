package com.vikas.covid19stats.di.scopes;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Vikas Suthar 26/06/2020.
 */

@Scope
@Retention(RUNTIME)
public @interface PerActivity {}
