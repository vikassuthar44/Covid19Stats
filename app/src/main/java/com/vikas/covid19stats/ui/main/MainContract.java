package com.vikas.covid19stats.ui.main;

import android.app.Activity;
import android.view.View;

import com.vikas.covid19stats.model.CountryWiseData;

import java.util.List;


/**
 * Created by Vikas Suthar 26/06/2020.
 */

public interface MainContract {

    interface View {
       void showTotalCases(Integer totalCases, Integer totalDeaths, Integer totalRecovered);
       void showData(List<CountryWiseData> countryWiseData);
       void showDialog();
       void showSortData();
       void showErrorMeassage(String error);
    }

    interface Presenter  {
        void loadData(Activity activity);
        void showFilterDialog();
        void applyfilter(List<CountryWiseData> countryWiseData,int filterType);
    }

}