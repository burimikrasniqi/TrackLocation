package net.zagzag.tracklocation.View.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import net.zagzag.tracklocation.Model.FirebaseConectionn;
import net.zagzag.tracklocation.Presenter.LoginPresenter;
import net.zagzag.tracklocation.R;
import net.zagzag.tracklocation.View.MainView.MainActivityPager;
import net.zagzag.tracklocation.View.SignUp.SignUp;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

/**
 * Created by burim on 12/27/2016.
 */

public class LoginActivity extends MvpActivity<LoginInterface, LoginPresenter> implements LoginInterface {
    @BindView(R.id.email)EditText email;
    @BindView(R.id.pass)EditText password;
    @BindView(R.id.login)Button login_button;
    private FirebaseConectionn f;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ButterKnife.bind(this);
        f = new FirebaseConectionn();
    }


    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @OnClick(R.id.login)
    public void login(){
        //startActivity(new Intent(this, MainActivityPager.class));
        f.init(this);

        //login
    }


    /*
    *    validate
    */
    @OnTextChanged(R.id.email)
    public void emailClicked() {
        validate();
    }

    @OnTextChanged(R.id.pass)
    public void passClicked() {
        validate();
    }

    public void validate() {
        boolean emailOk = !email.getText().toString().matches("");
        boolean passOk = !password.getText().toString().matches("");
        if (emailOk && passOk) {
            login_button.setEnabled(true);
        } else {
            login_button.setEnabled(false);
        }

    }

    @OnClick(R.id.signup)
    public void onSignup() {
        startActivity(new Intent(this, SignUp.class));
    }


    @NonNull
    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter();
    }
}
