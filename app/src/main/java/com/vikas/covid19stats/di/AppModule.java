package com.vikas.covid19stats.di;
import android.app.Application;
import android.content.Context;

import dagger.Binds;
import dagger.Module;
/**
 * Created by Vikas Suthar 26/06/2020.
 */


@Module
public abstract class AppModule {

    // same as provides but this returns injected parameter
    @Binds
    abstract Context bindContext(Application application);

}
