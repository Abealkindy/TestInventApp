package com.abraham.testinventapp.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.abraham.testinventapp.R;
import com.abraham.testinventapp.models.RequestLoginModel;
import com.abraham.testinventapp.models.ResultLoginModel;
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
            editTextUsernameLogin.setError(getResources().getString(R.string.isi_username_anda_terlebih_dahulu_alert));
        } else if (password.isEmpty() || password.equals(" ")) {
            editTextPasswordLogin.setFocusable(true);
            editTextPasswordLogin.setError(getResources().getString(R.string.isi_password_anda_terlebih_dahulu_alert));
        } else {
            requestLogin(username, password);
        }
    }

    private void requestLogin(final String username, final String password) {
        if (InternetConnection.checkConnection(LoginActivity.this)) {
            ApiService apiService = RetrofitConfig.getInitRetrofit();
            showProgress();
            Call<ResultLoginModel> loginModelCall = apiService.requestLogin(
                    new RequestLoginModel(
                            username,
                            password,
                            "1234567890",
                            "ANDROID",
                            "Customer"
                    )
            );
            loginModelCall.enqueue(new Callback<ResultLoginModel>() {
                @Override
                public void onResponse(@NonNull Call<ResultLoginModel> call, @NonNull Response<ResultLoginModel> response) {
                    hideProgress();
                    if (!Objects.requireNonNull(response.body()).getStatusCode().equals("01")) {
                        Toast.makeText(LoginActivity.this, R.string.string_some_thing_wrong_login, Toast.LENGTH_SHORT).show();
                    } else {
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ResultLoginModel> call, @NonNull Throwable throwable) {
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
