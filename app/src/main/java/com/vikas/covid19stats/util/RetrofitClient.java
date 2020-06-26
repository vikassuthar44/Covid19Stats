package com.vikas.covid19stats.util;

import com.vikas.covid19stats.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Vikas Suthar 26/06/2020.
 */

public class RetrofitClient {

    private static Retrofit  retrofit;

    public static Retrofit getRetrofitInstance() {
        if(retrofit == null) {

             return retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASEURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        } else {
            return retrofit;
        }
    }
}
