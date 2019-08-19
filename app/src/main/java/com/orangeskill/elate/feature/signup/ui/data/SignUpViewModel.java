package com.orangeskill.elate.feature.signup.ui.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.orangeskill.elate.R;
import com.orangeskill.elate.feature.signup.data.SignUpDataSource;
import com.orangeskill.elate.feature.signup.data.model.SignUpResponse;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpViewModel extends ViewModel {
    private static final String TAG = SignUpViewModel.class.getSimpleName();
    private SignUpDataSource dataSource;

    private MutableLiveData<SignUpFormState> signUpFormState = new MutableLiveData<>();
    private MutableLiveData<SignUpResponse> signUpResponseLiveData = new MutableLiveData<>();

    public SignUpViewModel(){
        dataSource = SignUpDataSource.getInstance();
    }

    public LiveData<SignUpFormState> getSignUpFormState() {
        return signUpFormState;
    }

    public LiveData<SignUpResponse> getSignUpLiveData() {
        return signUpResponseLiveData;
    }

    public void register(String mobile, String password) {
        dataSource.register(mobile, password, signUpResponseLiveData);
    }

    public void signUpDataChanged(String phone, String password, String confPassword) {
        if (!isUserPhoneValid(phone)){
            signUpFormState.setValue(new SignUpFormState(null, R.string.invalid_phone, null, null));
        } else if (! isPasswordValid(password)) {
            signUpFormState.setValue(new SignUpFormState(null, null, R.string.invalid_password, null));
        } else if (! isPasswordSame(password, confPassword)){
            signUpFormState.setValue(new SignUpFormState(null, null, null, R.string.password_match));
        } else {
            signUpFormState.setValue(new SignUpFormState(true));
        }
    }

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

    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 8;
    }

    private boolean isPasswordSame(String password, String confPassword) {
        if (! (password != null && confPassword != null)) {
            return false;
        }
        return password.equals(confPassword);
    }
}
