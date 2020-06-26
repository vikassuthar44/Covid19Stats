package com.vikas.covid19stats.di.main;

import com.vikas.covid19stats.ui.main.MainActivity;
import com.vikas.covid19stats.ui.main.MainContract;
import com.vikas.covid19stats.ui.main.PresenterImpl;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Vikas Suthar 26/06/2020.
 */


@Module
public abstract class MainActivityModule {
    @Binds
    public abstract MainContract.View view(MainActivity mainActivity);

    @Provides
    static MainContract.Presenter  provideAuthPresenter(MainContract.View view) {

        return new PresenterImpl(view);
    }
}
