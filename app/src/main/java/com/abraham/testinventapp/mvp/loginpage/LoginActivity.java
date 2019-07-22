package com.abraham.testinventapp.mvp.loginpage;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.abraham.testinventapp.R;
import com.abraham.testinventapp.mvp.mainpage.MainActivity;
import com.abraham.testinventapp.models.ResultLoginModel;
import com.abraham.testinventapp.networks.InternetConnection;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginModelInterface {

    @BindView(R.id.edit_text_username_login)
    TextInputEditText editTextUsernameLogin;
    @BindView(R.id.edit_text_password_login)
    TextInputEditText editTextPasswordLogin;
    @BindView(R.id.button_login)
    Button buttonLogin;

    private ProgressDialog mProgressDialog;
    LoginPresenter loginPresenter = new LoginPresenter(this);

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
            showProgress();
            loginPresenter.requestLogin(username, password);
        } else {
            hideProgress();
            Toast.makeText(this, R.string.string_internet_connection_not_available, Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onSuccessLoginAuth(ResultLoginModel loginbody) {
        hideProgress();
        if (!loginbody.getStatusCode().equals("01")) {
            Toast.makeText(LoginActivity.this, R.string.string_some_thing_wrong_login, Toast.LENGTH_SHORT).show();
        } else {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
    }

    @Override
    public void onFailed(String errorMessage) {
        hideProgress();
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    private void showProgress() {
        mProgressDialog.show();
        mProgressDialog.setMessage("Loading......");
    }

    private void hideProgress() {
        mProgressDialog.dismiss();
    }
}
