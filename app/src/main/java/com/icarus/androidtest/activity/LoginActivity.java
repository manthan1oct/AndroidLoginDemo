package com.icarus.androidtest.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.icarus.androidtest.R;
import com.icarus.androidtest.customdialog.CustomDialog;
import com.icarus.androidtest.customdialog.CustomProgressDialog;
import com.icarus.androidtest.model.Login;
import com.icarus.androidtest.model.response.LoginResponse;
import com.icarus.androidtest.mvp.login.LoginContract;
import com.icarus.androidtest.mvp.login.LoginPresenter;
import com.icarus.androidtest.realm.RealmController;

import io.realm.Realm;


public class LoginActivity extends AppCompatActivity implements LoginContract.LoginView {

    private EditText etEmail, etPassword;
    private LoginPresenter mPresenter;
    private CustomProgressDialog progressDialog;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mPresenter = new LoginPresenter(this);
        mPresenter.attachView(this);

        getFindViewbyId();

        //get realm instance
        this.realm = RealmController.with(LoginActivity.this).getRealm();

        // refresh the realm instance
        RealmController.with(this).refresh();

        ((TextView) findViewById(R.id.mLoginButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onLoginEvent(etEmail.getText().toString().trim(), etPassword.getText().toString().trim());

            }
        });

    }

    private void getFindViewbyId() {
        etEmail = (EditText) findViewById(R.id.et_email);
        etPassword = (EditText) findViewById(R.id.et_password);
    }

    @Override
    protected void onDestroy() {
        mPresenter.detachView();
        realm.close();

        super.onDestroy();
    }

    @Override
    public void onLoginSuccess(LoginResponse loginResponse) {

        int mStatus = 0;
        if (loginResponse != null)
            if (loginResponse.errorCode.equalsIgnoreCase("00"))
                mStatus = 1;
            else
                mStatus = 2;
        if (mStatus == 1) {

            Login data = new Login();
            data.setId(System.currentTimeMillis());
            data.setPassword(etPassword.getText().toString());
            data.setUsername(etEmail.getText().toString());

            //save data in DB
            realm.beginTransaction();
            realm.copyToRealm(data);
            realm.commitTransaction();

            Toast.makeText(this, loginResponse.errorMessage, Toast.LENGTH_LONG).show();
        } else if (mStatus == 2) {
            CustomDialog.getInstance().errorDialog(LoginActivity.this, loginResponse.errorMessage);
        } else {
            CustomDialog.getInstance().errorDialog(LoginActivity.this, getString(R.string.error_message500));
        }
    }

    @Override
    public void onLoginFailed(String message) {
        //If ws failed
        CustomDialog.getInstance().errorDialog(LoginActivity.this, getString(R.string.error_message));
    }

    @Override
    public void showProgress() {
        progressDialog = new CustomProgressDialog(LoginActivity.this, R.style.progress_dialog_text_style);
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();
    }


    @Override
    public void showValidationErrorDialogue(int messageID) {
        CustomDialog.getInstance().errorDialog(this, getString(messageID));
    }

    @Override
    public void showUnauthorizedError() {

    }

    @Override
    public void showEmpty() {

    }


}
