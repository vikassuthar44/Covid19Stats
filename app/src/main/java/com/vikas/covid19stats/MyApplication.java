package com.vikas.covid19stats;



import com.vikas.covid19stats.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

/**
 * Created by Vikas Suthar 26/06/2020.
 */


public class MyApplication extends DaggerApplication {


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }


}
