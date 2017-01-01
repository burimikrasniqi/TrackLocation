package net.zagzag.tracklocation.View.SignUp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Button;
import android.widget.EditText;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import net.zagzag.tracklocation.Presenter.SignUpPresenter;
import net.zagzag.tracklocation.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

/**
 * Created by burim on 12/27/2016.
 */

public class SignUp extends MvpActivity<SignUpInterface, SignUpPresenter> implements SignUpInterface {
    @BindView(R.id.button)
    Button signup;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.password)
    EditText pass;
    @BindView(R.id.rpassword)
    EditText rpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.button)
    public void signup(){
        if(!pass.getText().toString().matches(rpass.getText().toString())){
            rpass.setError("password not match");
        }
    }
    /*
    * validation
    *
    * */
    @OnTextChanged(R.id.email)
    public void email() {
        validate();
    }

    @OnTextChanged(R.id.password)
    public void password() {
        validate();
    }

    @OnTextChanged(R.id.rpassword)
    public void re_password() {
        validate();

    }

    public void validate() {
        boolean emailOk = !email.getText().toString().matches("");
        boolean passwordOk = !pass.getText().toString().matches("");
        boolean re_passwordOk = !rpass.getText().toString().matches("");

        if (emailOk && passwordOk && re_passwordOk) {
                signup.setEnabled(true);
        } else {
            signup.setEnabled(false);
        }

    }

    @NonNull
    @Override
    public SignUpPresenter createPresenter() {
        return new SignUpPresenter();
    }
}
