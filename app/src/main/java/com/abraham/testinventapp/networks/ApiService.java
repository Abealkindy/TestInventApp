package com.abraham.testinventapp.networks;


import com.abraham.testinventapp.models.LoginModel;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


public interface ApiService {

    @FormUrlEncoded
    @POST("login")
    Call<LoginModel> request_login(
            @Field("Username") String username,
            @Field("Password") String password,
            @Field("Token") String token,
            @Field("OsType") String osType,
            @Field("Role") String role
    );

}
