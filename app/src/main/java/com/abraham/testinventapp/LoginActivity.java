package com.abraham.testinventapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.abraham.testinventapp.models.LoginModel;
import com.abraham.testinventapp.networks.ApiService;
import com.abraham.testinventapp.networks.InternetConnection;
import com.abraham.testinventapp.networks.RetrofitConfig;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.edit_text_username_login)
    TextInputEditText editTextUsernameLogin;
    @BindView(R.id.edit_text_password_login)
    TextInputEditText editTextPasswordLogin;
    @BindView(R.id.button_login)
    Button buttonLogin;

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mProgressDialog = new ProgressDialog(LoginActivity.this);
    }

    @OnClick(R.id.button_login)
    public void onViewClicked() {
        loginValidation();
    }

    private void loginValidation() {
        String username = Objects.requireNonNull(editTextUsernameLogin.getText()).toString();
        String password = Objects.requireNonNull(editTextPasswordLogin.getText()).toString();
        if (username.isEmpty() || username.equals(" ")) {
            editTextUsernameLogin.setFocusable(true);
            editTextUsernameLogin.setError("Isi username anda terlebih dahulu!");
        } else if (password.isEmpty() || password.equals(" ")) {
            editTextPasswordLogin.setFocusable(true);
            editTextPasswordLogin.setError("Isi password anda terlebih dahulu!");
        } else {
            requestLogin(username, password);
        }
    }

    private void requestLogin(String username, String password) {
        if (InternetConnection.checkConnection(LoginActivity.this)) {
            ApiService apiService = RetrofitConfig.getInitRetrofit();
            showProgress();
            Call<LoginModel> loginModelCall = apiService.request_login(
                    username,
                    password,
                    "1234567890",
                    "ANDROID",
                    "Customer"
            );
            loginModelCall.enqueue(new Callback<LoginModel>() {
                @Override
                public void onResponse(@NonNull Call<LoginModel> call, @NonNull Response<LoginModel> response) {
                    hideProgress();
                }

                @Override
                public void onFailure(@NonNull Call<LoginModel> call, @NonNull Throwable throwable) {
                    hideProgress();
                    Toast.makeText(LoginActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            hideProgress();
            Toast.makeText(this, R.string.string_internet_connection_not_available, Toast.LENGTH_SHORT).show();
        }
    }

    private void showProgress() {
        mProgressDialog.show();
        mProgressDialog.setMessage("Loading......");
    }

    private void hideProgress() {
        mProgressDialog.dismiss();
    }
}
