package com.rezarinaldi.bukukasapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.rezarinaldi.bukukasapp.databinding.ActivityAddExpenseBinding;
import com.rezarinaldi.bukukasapp.utils.DatabaseHelper;
import com.rezarinaldi.bukukasapp.utils.Utils;

public class AddExpenseActivity extends AppCompatActivity {
    private ActivityAddExpenseBinding binding;
    private Utils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_expense);
        utils = new Utils();

        binding.etTanggalPengeluaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                utils.showDateDialog(AddExpenseActivity.this
                        , binding.etTanggalPengeluaran);
            }
        });

        utils.setCurrency(binding.etNominalPengeluaran);

        binding.btSimpanPengeluaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkEditTextValidation()) {
                    addExpenseProcess();
                }

            }
        });

        binding.btKembaliPengeluaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                utils.moveToMenu(AddExpenseActivity.this, MainActivity.class);
            }
        });

    }

    private boolean checkEditTextValidation() {
        boolean status = true;
        if (binding.etTanggalPengeluaran.getText().length() == 0) {
            Toast.makeText(this, "Tanggal masih kosong!"
                    , Toast.LENGTH_SHORT).show();
            status = false;
        } else if (binding.etNominalPengeluaran.getText().length() == 0
                || binding.etNominalPengeluaran.getText().toString().equals("0")) {
            Toast.makeText(this, "Nomimal masih kosong!",
                    Toast.LENGTH_SHORT).show();
            status = false;
        } else if (binding.etKeteranganPengeluaran.getText().length() == 0) {
            Toast.makeText(this, "Keterangan masih kosong!",
                    Toast.LENGTH_SHORT).show();
            status = false;
        }

        return status;
    }

    private void addExpenseProcess() {
        String date = binding.etTanggalPengeluaran.getText().toString().trim();
        String nominal = binding.etNominalPengeluaran.getText().toString().trim();
        String description = binding.etKeteranganPengeluaran.getText().toString().trim();
        String category = "pengeluaran";

        nominal = nominal.replace(".","");

        DatabaseHelper dbHelper = new DatabaseHelper(AddExpenseActivity.this);
        dbHelper.addFinance(date, nominal, description, category);
    }
}