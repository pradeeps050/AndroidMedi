package com.orangeskill.elate.feature.signup;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.orangeskill.elate.R;
import com.orangeskill.elate.databinding.ActivitySignUpBinding;
import com.orangeskill.elate.feature.login.ui.login.LoginActivity;
import com.orangeskill.elate.feature.signup.data.model.SignUpResponse;
import com.orangeskill.elate.feature.signup.ui.data.SignUpFormState;
import com.orangeskill.elate.feature.signup.ui.data.SignUpViewModel;

public class SignUpActivity extends AppCompatActivity {
    private static final String TAG = SignUpActivity.class.getSimpleName();

    private SignUpViewModel viewModel;
    private ActivitySignUpBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);
        init();
        viewModel = ViewModelProviders.of(SignUpActivity.this).get(SignUpViewModel.class);
        viewModel.getSignUpFormState().observe(this, new Observer<SignUpFormState>() {
            @Override
            public void onChanged(@Nullable SignUpFormState signUpFormState) {
                if (signUpFormState == null) {
                    return;
                }
                binding.submit.setEnabled(signUpFormState.isDataValid());
                if (signUpFormState.getPhoneError() != null) {
                    binding.phone.setError(getString(signUpFormState.getPhoneError()));
                }
                if (signUpFormState.getPasswordError() != null) {
                    binding.password.setError(getString(signUpFormState.getPasswordError()));
                }
                if (signUpFormState.getConfPasswordError() != null) {
                    binding.confirmPassword.setError(getString(signUpFormState.getConfPasswordError()));
                }
            }
        });

        viewModel.getSignUpLiveData().observe(this, new Observer<SignUpResponse>() {
            @Override
            public void onChanged(@Nullable SignUpResponse signUpResponse) {
                binding.progressbar.setVisibility(View.GONE);
                if (signUpResponse != null) {
                    Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }

            }
        });

        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.register(binding.phone.getText().toString(), binding.password.getText().toString());
            }
        });

    }

    private void init() {
        binding.submit.setEnabled(false);
        binding.phone.addTextChangedListener(textWatcher);
        binding.password.addTextChangedListener(textWatcher);
        binding.confirmPassword.addTextChangedListener(textWatcher);
    }

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            viewModel.signUpDataChanged(binding.phone.getText().toString(), binding.password.getText().toString(),
                    binding.confirmPassword.getText().toString());

        }
    };


}
