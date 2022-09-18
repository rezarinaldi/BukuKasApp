package com.rezarinaldi.bukukasapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import com.rezarinaldi.bukukasapp.models.FinanceModel;
import com.rezarinaldi.bukukasapp.utils.DatabaseHelper;
import com.rezarinaldi.bukukasapp.utils.MyAdapter;
import com.rezarinaldi.bukukasapp.utils.Utils;
import com.rezarinaldi.bukukasapp.databinding.ActivityDetailCashFlowBinding;

import java.util.ArrayList;

public class DetailCashFlowActivity extends AppCompatActivity {
    private ActivityDetailCashFlowBinding binding;
    private Utils utils;
    private DatabaseHelper dbHelper;
    private ArrayList<FinanceModel> financeModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_cash_flow);
        utils = new Utils();dbHelper = new DatabaseHelper(DetailCashFlowActivity.this);

        financeModelArrayList = new ArrayList<>();

        storeDataInArrays();

        MyAdapter adapter = new MyAdapter(DetailCashFlowActivity.this, financeModelArrayList);

        binding.rvFinance.setAdapter(adapter);
        binding.rvFinance.setLayoutManager(new LinearLayoutManager(
                DetailCashFlowActivity.this));

        binding.btKembaliCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                utils.moveToMenu(DetailCashFlowActivity.this, MainActivity.class);
            }
        });
    }

    private void storeDataInArrays(){
        Cursor cursor = dbHelper.readAllData();
        if(cursor.getCount() == 0){
            binding.imEmpty.setVisibility(View.VISIBLE);
            binding.tvEmpty.setVisibility(View.VISIBLE);
        } else {
            while (cursor.moveToNext()){
                int id_finance = cursor.getInt(0);
                String date = cursor.getString(1);
                double nominal = Double.parseDouble(cursor.getString(2));
                String nominal_str = utils.setFormatRupiah(nominal);
                String description = cursor.getString(3);
                String category = cursor.getString(4);

                financeModelArrayList.add(new FinanceModel(id_finance,
                        date,
                        nominal_str,
                        description,
                        category));
            }

            binding.imEmpty.setVisibility(View.GONE);
            binding.tvEmpty.setVisibility(View.GONE);
        }
    }
}