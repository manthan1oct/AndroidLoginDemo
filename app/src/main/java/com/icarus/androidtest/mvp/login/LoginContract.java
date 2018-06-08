package com.icarus.androidtest.mvp.login;


import com.icarus.androidtest.model.response.LoginResponse;
import com.icarus.androidtest.mvp.base.RemoteView;

public class LoginContract {

    public interface LoginActions {
        void onLoginEvent(String email, String pass);
    }

    public interface LoginView extends RemoteView {
        void onLoginSuccess(LoginResponse loginResponse);

        void onLoginFailed(String message);

        void showProgress();

        void hideProgress();

        void showValidationErrorDialogue(int message);

    }
}
