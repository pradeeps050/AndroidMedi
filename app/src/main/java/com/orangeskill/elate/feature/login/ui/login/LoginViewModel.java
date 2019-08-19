package com.orangeskill.elate.feature.login.ui.login;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Patterns;

import com.orangeskill.elate.R;
import com.orangeskill.elate.feature.login.data.LoginRepository;
import com.orangeskill.elate.feature.login.data.Result;
import com.orangeskill.elate.feature.login.data.model.LoggedInUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LoginViewModel extends ViewModel {

    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
    private LoginRepository loginRepository;

    public LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    public LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    public void login(String mobile, String password) {
        loginRepository.login(mobile, password);

    }


    public void loginDataChanged(String phone, String password) {
        if (!isUserPhoneValid(phone)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_phone, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    // A placeholder username validation check
    private boolean isUserPhoneValid(String phone) {
        if (phone == null) {
            return false;
        }
        boolean check = false;
        String expression = "^\\s*(?:\\+?(\\d{1,3}))?[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*(\\d{4})(?: *x(\\d+))?\\s*$";
        Pattern p = Pattern.compile(expression);
        Matcher matcher = p.matcher(phone);
        if (matcher.matches()) {
            check = true;
        }
        //Log.d(TAG, ">> phone " + String.valueOf(check));
        return check;
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }
}
