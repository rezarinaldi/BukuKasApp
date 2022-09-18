package com.rezarinaldi.bukukasapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;

import com.rezarinaldi.bukukasapp.databinding.ActivityMainBinding;
import com.rezarinaldi.bukukasapp.utils.DatabaseHelper;
import com.rezarinaldi.bukukasapp.utils.Utils;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private Utils util;
    private DatabaseHelper dbHelper;
    private boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        util = new Utils();
        dbHelper = new DatabaseHelper(getApplicationContext());

        String incomeSummary = dbHelper.getFinancialSummary("pemasukan");
        String expenseSummary = dbHelper.getFinancialSummary("pengeluaran");

        incomeSummary = util.setFormatRupiah(Double.parseDouble(incomeSummary));
        expenseSummary = util.setFormatRupiah(Double.parseDouble(expenseSummary));

        binding.tvIncomeInfoValue.setText(incomeSummary);
        binding.tvExpenseInfoValue.setText(expenseSummary);

        binding.clAddIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                util.moveToMenu(MainActivity.this, AddIncomeActivity.class);
            }
        });

        binding.clAddExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                util.moveToMenu(MainActivity.this, AddExpenseActivity.class);
            }
        });

        binding.clDetailCashFlow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                util.moveToMenu(MainActivity.this, DetailCashFlowActivity.class);
            }
        });

        binding.clSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                util.moveToMenu(MainActivity.this, SettingActivity.class);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Lakukan lagi untuk keluar", Toast.LENGTH_SHORT).show();

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}