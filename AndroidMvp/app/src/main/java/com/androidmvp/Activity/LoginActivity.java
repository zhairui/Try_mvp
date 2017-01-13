package com.androidmvp.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.androidmvp.R;
import com.androidmvp.model.LoginModel;
import com.androidmvp.presenter.ILoginPresenter;
import com.androidmvp.presenter.LoginPresenter;
import com.androidmvp.view.LoginView;

/**
 * Created by zhairui on 2017/1/12.
 */

public class LoginActivity extends Activity implements LoginView,LoginPresenter.LoginStatus,View.OnClickListener{
    EditText username,password;
    ProgressBar progressBar;
    ILoginPresenter iLoginPresenter;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        username= (EditText) findViewById(R.id.username);
        password= (EditText) findViewById(R.id.password);
        progressBar= (ProgressBar) findViewById(R.id.progress);
        button= (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        iLoginPresenter=new LoginPresenter(this,this);
    }

    @Override
    public String getUsername() {
        return username.getText().toString();
    }

    @Override
    public String getPassword() {
        return password.getText().toString();
    }

    public void showToast(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean checkNull(String username, String passwrod) {
        if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(passwrod)){
            return true;
        }
        return false;
    }

    @Override
    public void setUserNullError() {
        showToast("账号或者密码不能为空");
    }

    @Override
    public void setUserError() {
        showToast("账号跟密码不匹配");
    }

    @Override
    public void ShowProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }
    @Override
    public void LoginSuccess(LoginModel loginModel) {
        showToast("登录成功:"+loginModel.getUsername());
    }

    @Override
    public void LoginFail() {
        setUserError();
    }

    @Override
    public void onClick(View v) {
        iLoginPresenter.login(getUsername(),getPassword());
    }
}
