package com.rezarinaldi.bukukasapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.rezarinaldi.bukukasapp.utils.Preferences;
import com.rezarinaldi.bukukasapp.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        binding.btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkEditTextValidation()) {
                    checkAuth();
                }
            }
        });
    }

    private Boolean checkEditTextValidation() {
        boolean status = true;
        if (binding.etUsername.getText().length() == 0) {
            Toast.makeText(this, "Username masih kosong", Toast.LENGTH_SHORT).show();
            status = false;
        } else if(binding.etPassword.getText().length() == 0) {
            Toast.makeText(this, "Username masih kosong", Toast.LENGTH_SHORT).show();
            status = false;
        }

        return status;
    }

    private void checkIsFirstTime(String isFirstTime) {
        Log.i("IS_FIRST_TIME", "checkIsFirstTime: " + isFirstTime);

        if (isFirstTime.equals("")){
            Preferences.setKeyIsFirstTime(getApplicationContext(), "NO");
            Preferences.setKeyUsername(getApplicationContext(), "user");
            Preferences.setKeyPassword(getApplicationContext(), "user");
        }
    }

    private void checkAuth() {
        String isFirstTime = Preferences.getKeyIsFirstTime(getApplicationContext());
        checkIsFirstTime(isFirstTime);

        String username = Preferences.getKeyUsername(getApplicationContext());
        String password = Preferences.getKeyPassword(getApplicationContext());

        if (!binding.etUsername.getText().toString().equals(username)
                || !binding.etPassword.getText().toString().equals(password)) {
            Toast.makeText(getApplicationContext(), "Username atau Password tidak sesuai"
                    , Toast.LENGTH_SHORT).show();

        } else {
            Intent intent = new Intent(LoginActivity.this
                    , MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
    }
}