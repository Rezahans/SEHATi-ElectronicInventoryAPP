package com.rk.electronic.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rk.electronic.R;
import com.rk.electronic.model.Driver;

import java.util.List;

public class DriverAdapter extends RecyclerView.Adapter<DriverAdapter.DriverViewHolder> {
    private List<Driver> driverList;

    public DriverAdapter(List<Driver> driverList) {
        this.driverList = driverList;
    }

    @NonNull
    @Override
    public DriverViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_driver, parent, false);
        return new DriverViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DriverViewHolder holder, int position) {
        Driver driver = driverList.get(position);

        holder.textBatchNo.setText(driver.getBatchno());
        holder.textName.setText(driver.getName());
        holder.textPhone.setText(driver.getPhone());
        holder.textPoliceNo.setText(driver.getPoliceno());
        holder.textAddress.setText(driver.getAddress());
    }

    @Override
    public int getItemCount() {
        return driverList.size();
    }

    public static class DriverViewHolder extends RecyclerView.ViewHolder {
        TextView textBatchNo;
        TextView textName;
        TextView textPhone;
        TextView textPoliceNo;
        TextView textAddress;

        public DriverViewHolder(@NonNull View itemView) {
            super(itemView);
            textBatchNo = itemView.findViewById(R.id.textBatchNo);
            textName = itemView.findViewById(R.id.textName);
            textPhone = itemView.findViewById(R.id.textPhone);
            textPoliceNo = itemView.findViewById(R.id.textPoliceNo);
            textAddress = itemView.findViewById(R.id.textAddress);
        }
    }
}
