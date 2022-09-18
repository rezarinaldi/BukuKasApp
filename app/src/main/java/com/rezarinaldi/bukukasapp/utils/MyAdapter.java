package com.rezarinaldi.bukukasapp.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.rezarinaldi.bukukasapp.R;
import com.rezarinaldi.bukukasapp.models.FinanceModel;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<FinanceModel> financeModelArrayList;

    public MyAdapter(Context context, ArrayList<FinanceModel> financeModelArrayList) {
        this.context = context;
        this.financeModelArrayList = financeModelArrayList;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_cash_flow, parent, false);
        return new MyViewHolder(view);
    }

    @SuppressLint("RecyclerView")
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        FinanceModel financeModel = financeModelArrayList.get(position);
        String date = financeModel.getDate();
        String nominal = financeModel.getNominal();
        String description = financeModel.getDescription();
        String category = financeModel.getCategory();

        if (category.equals("pengeluaran")) {
            holder.tv_category.setText("[-]");
            holder.im_category.setImageResource(R.drawable.right_arrow);
        } else {
            holder.tv_category.setText("[+]");
            holder.im_category.setImageResource(R.drawable.left_arrow);
        }

        holder.tv_nominal.setText(nominal);
        holder.tv_description.setText(description);
        holder.tv_date.setText(date);
    }

    @Override
    public int getItemCount() {
        return financeModelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_date, tv_nominal, tv_description, tv_category;
        ImageView im_category;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_category = itemView.findViewById(R.id.tv_category);
            tv_nominal = itemView.findViewById(R.id.tv_nominal);
            tv_description = itemView.findViewById(R.id.tv_description);
            tv_date = itemView.findViewById(R.id.tv_date);
            im_category = itemView.findViewById(R.id.im_category);
        }
    }
}
