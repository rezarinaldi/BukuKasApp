package com.rezarinaldi.bukukasapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.rezarinaldi.bukukasapp.databinding.ActivitySettingBinding;
import com.rezarinaldi.bukukasapp.utils.Preferences;
import com.rezarinaldi.bukukasapp.utils.Utils;

public class SettingActivity extends AppCompatActivity {
    private ActivitySettingBinding binding;
    private Utils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_setting);
        utils = new Utils();

        binding.btSimpanPengaturan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkEditTextValidation()) {
                    changePassword();
                }
            }
        });

        binding.btKembaliPengaturan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                utils.moveToMenu(SettingActivity.this, MainActivity.class);
            }
        });
    }

    private Boolean checkEditTextValidation() {
        boolean status = true;
        if (binding.etPasswordPengaturanLama.getText().length() == 0) {
            Toast.makeText(this, "Password lama masih kosong!"
                    , Toast.LENGTH_SHORT).show();
            status = false;
        } else if (binding.etPasswordPengaturanBaru.getText().length() == 0) {
            Toast.makeText(this, "Password baru masih kosong!",
                    Toast.LENGTH_SHORT).show();
            status = false;
        }

        return status;
    }

    private void changePassword(){
        String password_lama = Preferences.getKeyPassword(getApplicationContext());
        String et_password_lama_value = binding.etPasswordPengaturanLama.getText().toString();
        String et_password_baru_value = binding.etPasswordPengaturanBaru.getText().toString();

        if (password_lama.equals(et_password_lama_value)){
            Preferences.setKeyPassword(getApplicationContext(), et_password_baru_value);
            Toast.makeText(getApplicationContext(), "Password berhasil diubah"
                    , Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Password lama salah!"
                    , Toast.LENGTH_SHORT).show();
        }
    }
}