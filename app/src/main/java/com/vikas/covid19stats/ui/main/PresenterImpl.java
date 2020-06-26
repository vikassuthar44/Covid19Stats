package com.vikas.covid19stats.ui.main;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import com.vikas.covid19stats.model.CountryWiseData;
import com.vikas.covid19stats.model.GlobalMainModel;
import com.vikas.covid19stats.util.CommonClass;
import com.vikas.covid19stats.util.NetworkCall;
import com.vikas.covid19stats.util.RetrofitClient;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Vikas Suthar 26/06/2020.
 */

public class PresenterImpl implements MainContract.Presenter {

    private static final String TAG = "PresenterImpl";
    private GlobalMainModel globalMainModel;

    private MainContract.View view;

    public PresenterImpl(MainContract.View view) {
        this.view = view;
    }


    @Override
    public void loadData(Activity activity) {

        if(CommonClass.isNetworkAvailable(activity)) {
            Retrofit retrofit = RetrofitClient.getRetrofitInstance();

            NetworkCall networkCall = retrofit.create(NetworkCall.class);

            Call<GlobalMainModel> call = networkCall.getCovid19Data();
            call.enqueue(new Callback<GlobalMainModel>() {
                @Override
                public void onResponse(Call<GlobalMainModel> call, Response<GlobalMainModel> response) {
                    Log.d(TAG, "onResponse: " + response.body().toString());
                    globalMainModel = response.body();
                    view.showTotalCases(globalMainModel.getGlobal().getTotalConfirmed(),globalMainModel.getGlobal().getTotalDeaths(),globalMainModel.getGlobal().getTotalRecovered());
                    view.showData(globalMainModel.getCountries());
                }

                @Override
                public void onFailure(Call<GlobalMainModel> call, Throwable t) {
                    view.showErrorMeassage(t.toString());
                }
            });
        } else  {
            view.showErrorMeassage("No Internet Connection. Please check internet connection!");
        }

    }

    /**
     * <p>
     *
     * </p>
     */
    @Override
    public void showFilterDialog() {
        view.showDialog();
    }


    /**
     * <p>
     *     In this method we calculate sort data according to the filter type.
     * </p>
     * @param currentCountryWiseData  current country wise data
     * @param filterType filter type
     */
    @Override
    public void applyfilter(List<CountryWiseData> currentCountryWiseData, int filterType) {
        switch (filterType) {
            case 0:
                Collections.sort(currentCountryWiseData, new Comparator<CountryWiseData>() {
                    @Override
                    public int compare(CountryWiseData data1, CountryWiseData data2) {
                        return data1.getCountry().compareToIgnoreCase(data2.getCountry());
                    }
                });
                break;
            case 1:
                Collections.sort(currentCountryWiseData, new Comparator<CountryWiseData>() {
                    @Override
                    public int compare(CountryWiseData data1, CountryWiseData data2) {
                        return data2.getCountry().compareToIgnoreCase(data1.getCountry());
                    }
                });
                break;
            case 2:
                Collections.sort(currentCountryWiseData, new Comparator<CountryWiseData>() {
                    @Override
                    public int compare(CountryWiseData data1, CountryWiseData data2) {
                        return data1.getTotalConfirmed().compareTo(data2.getTotalConfirmed());
                    }
                });
                break;
            case 3:
                Collections.sort(currentCountryWiseData, new Comparator<CountryWiseData>() {
                    @Override
                    public int compare(CountryWiseData data1, CountryWiseData data2) {
                        return data2.getTotalConfirmed().compareTo(data1.getTotalConfirmed());
                    }
                });
                break;
            case 4:
                Collections.sort(currentCountryWiseData, new Comparator<CountryWiseData>() {
                    @Override
                    public int compare(CountryWiseData data1, CountryWiseData data2) {
                        return data1.getTotalDeaths().compareTo(data2.getTotalDeaths());
                    }
                });
                break;
            case 5:
                Collections.sort(currentCountryWiseData, new Comparator<CountryWiseData>() {
                    @Override
                    public int compare(CountryWiseData data1, CountryWiseData data2) {
                        return data2.getTotalDeaths().compareTo(data1.getTotalDeaths());
                    }
                });
                break;
            case 6:
                Collections.sort(currentCountryWiseData, new Comparator<CountryWiseData>() {
                    @Override
                    public int compare(CountryWiseData data1, CountryWiseData data2) {
                        return data1.getTotalRecovered().compareTo(data2.getTotalRecovered());
                    }
                });
                break;
            case 7:
                Collections.sort(currentCountryWiseData, new Comparator<CountryWiseData>() {
                    @Override
                    public int compare(CountryWiseData data1, CountryWiseData data2) {
                        return data2.getTotalRecovered().compareTo(data1.getTotalRecovered());
                    }
                });
                break;
        }
        view.showSortData();
    }

}
