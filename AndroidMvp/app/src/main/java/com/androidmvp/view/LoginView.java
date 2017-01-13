package com.androidmvp.view;

/**
 * Created by zhairui on 2017/1/12.
 */

public interface LoginView {

    String  getUsername();

    String getPassword();

    boolean checkNull(String username,String passwrod);

    void setUserNullError();

    void setUserError();

    void ShowProgress();

    void hideProgress();
}
