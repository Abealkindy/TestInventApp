package com.abraham.testinventapp.mvp.mainpage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.abraham.testinventapp.R;
import com.abraham.testinventapp.adapters.SpinnerCityAdapter;
import com.abraham.testinventapp.adapters.SpinnerProvinceAdapter;
import com.abraham.testinventapp.models.CityModel;
import com.abraham.testinventapp.models.ProvinceModel;
import com.abraham.testinventapp.mvp.loginpage.LoginPresenter;
import com.abraham.testinventapp.networks.ApiService;
import com.abraham.testinventapp.networks.InternetConnection;
import com.abraham.testinventapp.networks.RetrofitConfig;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainModelInterface {

    @BindView(R.id.spinner_province)
    Spinner spinnerProvince;
    @BindView(R.id.spinner_city)
    Spinner spinnerCity;

    private String provinceID;
    MainPresenter mainPresenter = new MainPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (InternetConnection.checkConnection(MainActivity.this)) {
            getProvinceData();
        } else {
            Toast.makeText(this, R.string.string_internet_connection_not_available, Toast.LENGTH_SHORT).show();
        }
    }

    private void getProvinceData() {
        mainPresenter.getProvinceData();
    }

    private void getCityDataByProvinceID(String provinceID) {
        mainPresenter.getCityData(provinceID);
    }

    @Override
    public void onSuccessProvinceData(final ProvinceModel provinceData) {
        if (provinceData.getStatusCode().equals("01")) {
            spinnerProvince.setAdapter(new SpinnerProvinceAdapter(MainActivity.this, android.R.layout.simple_spinner_item, provinceData.getValue()));
            spinnerProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    provinceID = provinceData.getValue().get(position).getProvinceID();
                    getCityDataByProvinceID(provinceID);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    provinceID = provinceData.getValue().get(0).getProvinceID();
                    getCityDataByProvinceID(provinceID);
                }
            });

        } else {
            Toast.makeText(MainActivity.this, R.string.string_some_thing_wrong, Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public void onSuccessCityData(CityModel cityData) {
        spinnerCity.setAdapter(new SpinnerCityAdapter(MainActivity.this, android.R.layout.simple_spinner_item, cityData.getValue()));
    }

    @Override
    public void onFailed(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }
}
