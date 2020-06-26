package com.vikas.covid19stats.ui.main;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.vikas.covid19stats.R;
import com.vikas.covid19stats.model.CountryListAdapter;
import com.vikas.covid19stats.model.CountryWiseData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;


public class MainActivity extends DaggerAppCompatActivity implements MainContract.View {

    @Inject
    MainContract.Presenter presenter;

    private CountryListAdapter countryListAdapter;
    private TextView tvTotalCases, tvTotalDeaths, tvTotalRecovred;

    private RecyclerView countryList;
    private ImageView ivFilter;
    private List<CountryWiseData> currentCountryWiseData;
    private int selectedFilterType = -1;
    private ConstraintLayout rootView;
    private Button tryAgain;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();
    }

    /**
     * <p>
     *     In this we initization ui
     * </p>
     */
    private void initUi() {
        currentCountryWiseData = new ArrayList<>();
        tvTotalCases = findViewById(R.id.tvTotalCases);
        tvTotalDeaths = findViewById(R.id.tvTotalDeaths);
        tvTotalRecovred = findViewById(R.id.tvTotalRecovered);
        rootView = findViewById(R.id.rootView);
        tryAgain = findViewById(R.id.tryAgain);
        countryList = findViewById(R.id.countryList);
        ivFilter = findViewById(R.id.ivFilter);
        progressBar = findViewById(R.id.progressBar);
        ivFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.showFilterDialog();
            }
        });
    }

    /**
     * <p>
     *     In this we show total cases, total deaths, total recovered on top in the activitiy
     * </p>
     * @param totalCases  number of total cases
     * @param totalDeaths  number of total deaths caes
     * @param totalRecovred  number of total recovred cases
     */
    @Override
    public void showTotalCases(Integer totalCases, Integer totalDeaths, Integer totalRecovred) {
        tvTotalCases.setText(String.valueOf(totalCases));
        tvTotalDeaths.setText(String.valueOf(totalDeaths));
        tvTotalRecovred.setText(String.valueOf(totalRecovred));
        Log.d("TAG", "showTotalCases: " + totalCases + "   " + totalDeaths + " " + totalRecovred);
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.VISIBLE);
        presenter.loadData(this);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                presenter.loadData(MainActivity.this);
            }
        }, 120000, 120000);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * <p>
     *     In this method we show all tha data total cases descending order
     * </p>
     * @param countryWiseData countrywise data's
     */
    @Override
    public void showData(List<CountryWiseData> countryWiseData) {
        tryAgain.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        currentCountryWiseData = countryWiseData;
        Collections.sort(currentCountryWiseData, new Comparator<CountryWiseData>() {
            @Override
            public int compare(CountryWiseData data1, CountryWiseData data2) {
                return data2.getTotalConfirmed().compareTo(data1.getTotalConfirmed());
            }
        });
        ;
        countryListAdapter = new CountryListAdapter(currentCountryWiseData, this.getResources().getConfiguration().locale.getDisplayCountry());
        countryList.setAdapter(countryListAdapter);
    }

    /**
     * <P>
     *     In this method we open popup for apply filter according
     *     to the ascending and descending order of total caes,
     *     total deaths, total recovered cases
     * </P>
     */
    @Override
    public void showDialog() {
        final Dialog dialogView = new Dialog(this);
        dialogView.setContentView(R.layout.dialog_view);
        dialogView.setTitle("Filter");
        WindowManager.LayoutParams wmlp = dialogView.getWindow().getAttributes();
        wmlp.gravity = Gravity.TOP | Gravity.RIGHT;
        wmlp.x = 0;   //x position
        wmlp.y = 100; //y position

        RadioGroup radioGroup = dialogView.findViewById(R.id.radioGroup);
        RadioButton countryAsc, countryDsc, totalCasesAsc, totalCasesDsc, totalDeathsAsc, totalDeathsDsc, totalRecoveredAsc, totalRecoveredDsc;
        countryAsc = dialogView.findViewById(R.id.countryAsc);
        countryDsc = dialogView.findViewById(R.id.countryDsc);
        totalCasesAsc = dialogView.findViewById(R.id.totalCasesAsc);
        totalCasesDsc = dialogView.findViewById(R.id.totalCasesDsc);
        totalDeathsAsc = dialogView.findViewById(R.id.totalDeathsAsc);
        totalDeathsDsc = dialogView.findViewById(R.id.totalDeathsDsc);
        totalRecoveredAsc = dialogView.findViewById(R.id.totalRecoveredAsc);
        totalRecoveredDsc = dialogView.findViewById(R.id.totalRecoveredDsc);


        ImageView closeIcon = dialogView.findViewById(R.id.closeIcon);
        closeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogView.dismiss();
            }
        });

        Button filterReset = dialogView.findViewById(R.id.filterReset);
        filterReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedFilterType = 3;
                presenter.applyfilter(currentCountryWiseData, 3);
                dialogView.dismiss();
            }
        });
        switch (selectedFilterType) {
            case -1:
                totalCasesDsc.setChecked(true);
                break;
            case 0:
                countryAsc.setChecked(true);
                break;
            case 1:
                countryDsc.setChecked(true);
                break;
            case 2:
                totalCasesAsc.setChecked(true);
                break;
            case 3:
                totalCasesDsc.setChecked(true);
                break;
            case 4:
                totalDeathsAsc.setChecked(true);
                break;
            case 5:
                totalDeathsDsc.setChecked(true);
                break;
            case 6:
                totalRecoveredAsc.setChecked(true);
                break;
            case 7:
                totalRecoveredDsc.setChecked(true);
                break;

        }
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.countryAsc:
                        selectedFilterType = 0;
                        presenter.applyfilter(currentCountryWiseData, 0);
                        break;
                    case R.id.countryDsc:
                        selectedFilterType = 1;
                        presenter.applyfilter(currentCountryWiseData, 1);
                        break;
                    case R.id.totalCasesAsc:
                        selectedFilterType = 2;
                        presenter.applyfilter(currentCountryWiseData, 2);
                        break;
                    case R.id.totalCasesDsc:
                        selectedFilterType = 3;
                        presenter.applyfilter(currentCountryWiseData, 3);
                        break;
                    case R.id.totalDeathsAsc:
                        selectedFilterType = 4;
                        presenter.applyfilter(currentCountryWiseData, 4);
                        break;
                    case R.id.totalDeathsDsc:
                        selectedFilterType = 5;
                        presenter.applyfilter(currentCountryWiseData, 5);
                        break;
                    case R.id.totalRecoveredAsc:
                        selectedFilterType = 6;
                        presenter.applyfilter(currentCountryWiseData, 6);
                        break;
                    case R.id.totalRecoveredDsc:
                        selectedFilterType = 7;
                        presenter.applyfilter(currentCountryWiseData, 7);
                        break;
                }
                dialogView.dismiss();
            }
        });
        dialogView.show();
    }

    /**
     * <p>
     *     In this we display sorted data on main activity.
     * </p>
     */
    @Override
    public void showSortData() {
        countryListAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrorMeassage(String error) {
        Snackbar.make(rootView, error, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
        tryAgain.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        tryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryAgain.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                presenter.loadData(MainActivity.this);
            }
        });
    }
}
