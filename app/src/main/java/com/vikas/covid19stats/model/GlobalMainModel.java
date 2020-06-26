package com.vikas.covid19stats.model;

import android.provider.Settings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GlobalMainModel {
    @SerializedName("Global")
    @Expose
    private GlobalData global;
    @SerializedName("Countries")
    @Expose
    private List<CountryWiseData> countries = null;
    @SerializedName("Date")
    @Expose
    private String date;

    public GlobalData getGlobal() {
        return global;
    }

    public void setGlobal(GlobalData global) {
        this.global = global;
    }

    public List<CountryWiseData> getCountries() {
        return countries;
    }

    public void setCountries(List<CountryWiseData> countries) {
        this.countries = countries;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
