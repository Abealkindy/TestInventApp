package com.abraham.testinventapp.mvp.loginpage;

import com.abraham.testinventapp.models.RequestLoginModel;
import com.abraham.testinventapp.models.ResultLoginModel;
import com.abraham.testinventapp.networks.ApiService;
import com.abraham.testinventapp.networks.RetrofitConfig;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/*
 * Created by Rosinante24 on 2019-07-22.
 */
public class LoginPresenter {

    private LoginModelInterface loginModelInterface;

    public LoginPresenter(LoginModelInterface loginModelInterface) {
        this.loginModelInterface = loginModelInterface;
    }

    public void requestLogin(String username, String password) {
        ApiService apiService = RetrofitConfig.getInitRetrofit();
        apiService.requestLogin(
                new RequestLoginModel(
                        username,
                        password,
                        "1234567890",
                        "ANDROID",
                        "Customer"
                )
        )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultLoginModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultLoginModel resultLoginModel) {
                        loginModelInterface.onSuccessLoginAuth(resultLoginModel);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        loginModelInterface.onFailed(throwable.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
