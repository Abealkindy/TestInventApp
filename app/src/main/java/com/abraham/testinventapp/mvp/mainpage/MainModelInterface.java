package com.abraham.testinventapp.mvp.mainpage;

import com.abraham.testinventapp.models.CityModel;
import com.abraham.testinventapp.models.ProvinceModel;
import com.abraham.testinventapp.models.ResultLoginModel;

/*
 * Created by Rosinante24 on 2019-07-22.
 */
public interface MainModelInterface {
    void onSuccessProvinceData(ProvinceModel provinceData);

    void onSuccessCityData(CityModel cityData);

    void onFailed(String errorMessage);
}
