package com.abraham.testinventapp.mvp.mainpage;

import com.abraham.testinventapp.models.CityModel;
import com.abraham.testinventapp.models.ProvinceModel;
import com.abraham.testinventapp.models.RequestProvinceIDModel;
import com.abraham.testinventapp.networks.ApiService;
import com.abraham.testinventapp.networks.RetrofitConfig;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/*
 * Created by Rosinante24 on 2019-07-22.
 */
public class MainPresenter {
    private MainModelInterface mainModelInterface;
    private ApiService apiService = RetrofitConfig.getInitRetrofit();

    public MainPresenter(MainModelInterface mainModelInterface) {
        this.mainModelInterface = mainModelInterface;
    }

    public void getProvinceData() {
        apiService.getProvince()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProvinceModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ProvinceModel provinceModel) {
                        mainModelInterface.onSuccessProvinceData(provinceModel);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        mainModelInterface.onFailed(throwable.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getCityData(String provinceID) {
        apiService.getCity(new RequestProvinceIDModel(provinceID))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CityModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CityModel cityModel) {
                        mainModelInterface.onSuccessCityData(cityModel);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        mainModelInterface.onFailed(throwable.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
