package com.abraham.testinventapp.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.abraham.testinventapp.models.RequestProvinceIDModel;
import com.abraham.testinventapp.networks.ApiService;
import com.abraham.testinventapp.networks.InternetConnection;
import com.abraham.testinventapp.networks.RetrofitConfig;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.spinner_province)
    Spinner spinnerProvince;
    @BindView(R.id.spinner_city)
    Spinner spinnerCity;

    private String provinceID;
    private ApiService apiService;

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
        apiService = RetrofitConfig.getInitRetrofit();
        Call<ProvinceModel> provinceModelCall = apiService.getProvince();
        provinceModelCall.enqueue(new Callback<ProvinceModel>() {
            @Override
            public void onResponse(@NonNull Call<ProvinceModel> call, @NonNull final Response<ProvinceModel> response) {
                if (Objects.requireNonNull(response.body()).getStatusCode().equals("01")) {
                    spinnerProvince.setAdapter(new SpinnerProvinceAdapter(MainActivity.this, android.R.layout.simple_spinner_item, Objects.requireNonNull(response.body()).getValue()));
                    spinnerProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            provinceID = response.body().getValue().get(position).getProvinceID();
                            getCityDataByProvinceID(provinceID);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            provinceID = response.body().getValue().get(0).getProvinceID();
                            getCityDataByProvinceID(provinceID);
                        }
                    });

                } else {
                    Toast.makeText(MainActivity.this, R.string.string_some_thing_wrong, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(@NonNull Call<ProvinceModel> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getCityDataByProvinceID(String provinceID) {
        apiService = RetrofitConfig.getInitRetrofit();
        Call<CityModel> cityModelCall = apiService.getCity(new RequestProvinceIDModel(provinceID));
        cityModelCall.enqueue(new Callback<CityModel>() {
            @Override
            public void onResponse(@NonNull Call<CityModel> call, @NonNull final Response<CityModel> response) {
                if (Objects.requireNonNull(response.body()).getStatusCode().equals("01")) {
                    spinnerCity.setAdapter(new SpinnerCityAdapter(MainActivity.this, android.R.layout.simple_spinner_item, Objects.requireNonNull(response.body()).getValue()));
                } else {
                    Toast.makeText(MainActivity.this, R.string.string_some_thing_wrong, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<CityModel> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
