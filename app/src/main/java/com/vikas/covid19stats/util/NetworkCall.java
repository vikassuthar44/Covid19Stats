package com.vikas.covid19stats.util;

import com.vikas.covid19stats.model.GlobalMainModel;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Vikas Suthar 26/06/2020.
 */

public interface NetworkCall {

    @GET("summary")
    Call<GlobalMainModel> getCovid19Data();
}
