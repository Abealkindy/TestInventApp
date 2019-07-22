package com.abraham.testinventapp.mvp.loginpage;

import com.abraham.testinventapp.models.ResultLoginModel;

/*
 * Created by Rosinante24 on 2019-07-22.
 */
public interface LoginModelInterface {
    void onSuccessLoginAuth(ResultLoginModel loginbody);

    void onFailed(String errorMessage);
}
