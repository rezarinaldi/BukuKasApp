package com.rezarinaldi.bukukasapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.rezarinaldi.bukukasapp.databinding.ActivityAddIncomeBinding;
import com.rezarinaldi.bukukasapp.utils.DatabaseHelper;
import com.rezarinaldi.bukukasapp.utils.Utils;

public class AddIncomeActivity extends AppCompatActivity {
    private ActivityAddIncomeBinding binding;
    private Utils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_income);
        utils = new Utils();

        binding.etTanggalPemasukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                utils.showDateDialog(AddIncomeActivity.this
                        , binding.etTanggalPemasukan);
            }
        });

        utils.setCurrency(binding.etNominalPemasukan);

        binding.btSimpanPemasukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkEditTextValidation()) {
                    addIncomeProcess();
                    Toast.makeText(getApplicationContext()
                            , "Data berhasil disimpan"
                            , Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.btKembaliPemasukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                utils.moveToMenu(AddIncomeActivity.this, MainActivity.class);
            }
        });
    }

    private boolean checkEditTextValidation() {
        boolean status = true;
        if (binding.etTanggalPemasukan.getText().length() == 0) {
            Toast.makeText(this, "Tanggal masih kosong!"
                    , Toast.LENGTH_SHORT).show();
            status = false;
        } else if (binding.etNominalPemasukan.getText().length() == 0
                || binding.etNominalPemasukan.getText().toString().equals("0")) {
            Toast.makeText(this, "Nomimal masih kosong!",
                    Toast.LENGTH_SHORT).show();
            status = false;
        } else if (binding.etKeteranganPemasukan.getText().length() == 0) {
            Toast.makeText(this, "Keterangan masih kosong!",
                    Toast.LENGTH_SHORT).show();
            status = false;
        }

        return status;
    }

    private void addIncomeProcess(){
        String date = binding.etTanggalPemasukan.getText().toString().trim();
        String nominal = binding.etNominalPemasukan.getText().toString().trim();
        String description = binding.etKeteranganPemasukan.getText().toString().trim();
        String cateogry = "pemasukan";

        nominal = nominal.replace(".","");

        DatabaseHelper dbHelper = new DatabaseHelper(AddIncomeActivity.this);
        dbHelper.addFinance(date, nominal, description, cateogry);
    }
}