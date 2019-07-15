package com.abraham.testinventapp.networks;


import com.abraham.testinventapp.models.CityModel;
import com.abraham.testinventapp.models.RequestLoginModel;
import com.abraham.testinventapp.models.RequestProvinceIDModel;
import com.abraham.testinventapp.models.ResultLoginModel;
import com.abraham.testinventapp.models.ProvinceModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;


public interface ApiService {
    @Headers({"Content-Type: application/json"})
    @POST("login")
    Call<ResultLoginModel> requestLogin(
            @Body RequestLoginModel requestLoginModel
    );

    @POST("getprovince")
    Call<ProvinceModel> getProvince();

    @Headers({"Content-Type: application/json"})
    @POST("getcity")
    Call<CityModel> getCity(
            @Body RequestProvinceIDModel provinceID
    );
}
