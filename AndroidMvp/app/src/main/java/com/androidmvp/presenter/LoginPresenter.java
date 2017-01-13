package com.androidmvp.presenter;

import android.os.Handler;

import com.androidmvp.model.LoginModel;
import com.androidmvp.view.LoginView;

/**
 * Created by zhairui on 2017/1/12.
 */

public class LoginPresenter implements ILoginPresenter{
    LoginView loginView;

    public LoginPresenter(LoginView loginView,LoginStatus loginStatus){
        this.loginView=loginView;
        this.loginStatus=loginStatus;
    }
    @Override
    public void login(final String username, final String password) {
        if(loginView==null){
            return;
        }
        if(loginView.checkNull(username,password)){
            loginView.ShowProgress();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    loginView.hideProgress();
                    if(username.equals("zhangsan") && password.equals("123")){
                        if(loginStatus!=null){
                            LoginModel loginModel=new LoginModel(username,password);
                            loginStatus.LoginSuccess(loginModel);
                        }
                    }else {
                        if(loginStatus!=null){
                            loginStatus.LoginFail();
                        }
                    }
                }
            },2000);
        }else{
            loginView.setUserNullError();
        }
    }
    public interface LoginStatus{
        void LoginSuccess(LoginModel loginModel);

        void LoginFail();
    }
    private LoginStatus loginStatus;

}
