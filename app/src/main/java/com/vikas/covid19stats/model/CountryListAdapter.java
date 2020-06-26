package com.vikas.covid19stats.model;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vikas.covid19stats.R;

import java.util.List;

/**
 * Created by Vikas Suthar 26/06/2020.
 */

public class CountryListAdapter extends RecyclerView.Adapter<CountryListAdapter.MyViewHolder> {

    private List<CountryWiseData> countryWiseData;
    private String country;
    private Context context;

    public CountryListAdapter(List<CountryWiseData> countryWiseData, String country) {
        this.countryWiseData = countryWiseData;
        this.country = country;
    }

    @NonNull
    @Override
    public CountryListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_list_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryListAdapter.MyViewHolder holder, int position) {

        holder.country.setText(countryWiseData.get(position).getCountry());
        holder.totalCases.setText(String.valueOf(countryWiseData.get(position).getTotalConfirmed()));
        holder.totalDeaths.setText(String.valueOf(countryWiseData.get(position).getTotalDeaths()));
        holder.totalRecovered.setText(String.valueOf(countryWiseData.get(position).getTotalRecovered()));

        if(position%2 == 0) {
            holder.itemView.setBackgroundColor(Color.rgb(242,242,242));
        } else {
            holder.itemView.setBackgroundColor(Color.WHITE);
        }
        for (int countrySize = 0; countrySize <  countryWiseData.size(); countrySize++) {
            if(countryWiseData.get(position).getCountry().equalsIgnoreCase(country)) {
                holder.country.setTextColor(Color.BLACK);
                holder.country.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                holder.totalCases.setTextColor(Color.BLACK);
                holder.totalCases.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                holder.totalDeaths.setTextColor(Color.BLACK);
                holder.totalDeaths.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                holder.totalRecovered.setTextColor(Color.BLACK);
                holder.totalRecovered.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            } else {
                holder.country.setTextColor(Color.rgb(80,80,80));
                holder.country.setTypeface(Typeface.DEFAULT);
                holder.totalCases.setTextColor(Color.rgb(80,80,80));
                holder.totalCases.setTypeface(Typeface.DEFAULT);
                holder.totalDeaths.setTextColor(Color.rgb(80,80,80));
                holder.totalDeaths.setTypeface(Typeface.DEFAULT);
                holder.totalRecovered.setTextColor(Color.rgb(80,80,80));
                holder.totalRecovered.setTypeface(Typeface.DEFAULT);
            }
        }
    }

    @Override
    public int getItemCount() {
        return countryWiseData.size();
    }

    public class  MyViewHolder extends RecyclerView.ViewHolder {

        TextView country, totalCases, totalDeaths, totalRecovered;
        public MyViewHolder(View itemView) {
            super(itemView);

            country = itemView.findViewById(R.id.country);
            totalCases = itemView.findViewById(R.id.tvCases);
            totalDeaths = itemView.findViewById(R.id.tvDeaths);
            totalRecovered = itemView.findViewById(R.id.tvRecovered);
        }
    }
}
