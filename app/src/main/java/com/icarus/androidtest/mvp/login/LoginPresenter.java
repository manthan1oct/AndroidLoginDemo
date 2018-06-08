package com.icarus.androidtest.mvp.login;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.icarus.androidtest.R;
import com.icarus.androidtest.model.request.LoginRequest;
import com.icarus.androidtest.model.response.LoginResponse;
import com.icarus.androidtest.mvp.base.BasePresenter;
import com.icarus.androidtest.util.CheckConnection;
import com.icarus.androidtest.util.LogManager;
import com.icarus.androidtest.webapi.ApiHelper;
import com.icarus.androidtest.webapi.RxJava2ApiCallHelper;
import com.icarus.androidtest.webapi.RxJava2ApiCallback;
import com.icarus.androidtest.webapi.WebAPIService;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


public class LoginPresenter extends BasePresenter<LoginContract.LoginView> implements LoginContract.LoginActions {
    private static final String TAG = "LoginPresenter";

    private Context context;
    private CompositeDisposable mCompositeDisposable;

    public LoginPresenter(Context context) {
        this.context = context;

    }


    @Override
    public void onLoginEvent(String username, String password) {
        if (isValid(username, password)) {
            LogManager.v(TAG + " onLoginEvent: ");
            if (!isViewAttached())
                return;

            if (CheckConnection.getInstance(context).isConnectingToInternet()) {
                mView.showProgress();
                mCompositeDisposable = new CompositeDisposable();
                LoginRequest loginRequest = new LoginRequest();
                loginRequest.username = username;
                loginRequest.password = password;


                Observable<LoginResponse> observable = ApiHelper
                        .getInstance(context)
                        .getService(WebAPIService.class)
                        .login(loginRequest);

                Disposable disposable = RxJava2ApiCallHelper.call(observable, new RxJava2ApiCallback<LoginResponse>() {
                    @Override
                    public void onSuccess(LoginResponse response) {
                        mView.hideProgress();
                        if (!isViewAttached())
                            return;
                        LogManager.v(TAG + " onSuccess: ");
                        mView.onLoginSuccess(response);
                    }

                    @Override
                    public void onFailed(Throwable throwable) {
                        LogManager.v(TAG + " onFailed: " + throwable);
                        mView.hideProgress();
                        mView.onLoginFailed(throwable.getMessage());
                    }
                });
                mCompositeDisposable.add(disposable);


            } else {
                Toast.makeText(context, context.getResources().getString(R.string.connection_error), Toast.LENGTH_SHORT).show();
            }
        }
    }


    private boolean isValid(String email, String pass) {
        if (TextUtils.isEmpty(email)) {
            if (isViewAttached()) {
                mView.showValidationErrorDialogue(R.string.please_enter_username);
            }
            return false;
        }

        if (TextUtils.isEmpty(pass)) {
            if (isViewAttached()) {
                mView.showValidationErrorDialogue(R.string.please_enter_password);
            }
            return false;
        }

        return true;
    }
}
