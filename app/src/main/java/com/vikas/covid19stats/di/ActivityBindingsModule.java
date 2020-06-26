package com.vikas.covid19stats.di;

import com.vikas.covid19stats.di.main.MainActivityModule;
import com.vikas.covid19stats.di.scopes.PerActivity;
import com.vikas.covid19stats.ui.main.MainActivity;

import dagger.Module;
import dagger.android.AndroidInjectionModule;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Vikas Suthar 26/06/2020.
 */


@Module(includes = AndroidInjectionModule.class)
abstract class ActivityBindingsModule {
    @PerActivity
    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity mainActivityInjector();

}

