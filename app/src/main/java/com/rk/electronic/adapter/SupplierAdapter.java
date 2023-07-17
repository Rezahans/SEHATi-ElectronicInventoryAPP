package com.rk.electronic.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rk.electronic.R;
import com.rk.electronic.model.Supplier;

import java.util.ArrayList;
import java.util.List;

public class SupplierAdapter extends RecyclerView.Adapter<SupplierAdapter.SupplierViewHolder> {
    private List<Supplier> supplierList;
    private List<Supplier> supplierListFull;

    public SupplierAdapter(List<Supplier> supplierList) {
        this.supplierList = supplierList;
        this.supplierListFull = new ArrayList<>(supplierList);
    }

    @NonNull
    @Override
    public SupplierViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_supplier, parent, false);
        return new SupplierViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SupplierViewHolder holder, int position) {
        Supplier supplier = supplierList.get(position);

        // Bind the data to the view
        holder.textBatchNo.setText(supplier.getBatchNo());
        holder.textAddress.setText(supplier.getAddress());
        holder.textName.setText(supplier.getName());
        holder.textPhone.setText(supplier.getPhone());
        holder.textProvince.setText(supplier.getProvince());
    }

    @Override
    public int getItemCount() {
        return supplierList.size();
    }

    public static class SupplierViewHolder extends RecyclerView.ViewHolder {
        TextView textBatchNo;
        TextView textAddress;
        TextView textName;
        TextView textPhone;
        TextView textProvince;

        public SupplierViewHolder(@NonNull View itemView) {
            super(itemView);
            textBatchNo = itemView.findViewById(R.id.textBatchNo);
            textAddress = itemView.findViewById(R.id.textAddress);
            textName = itemView.findViewById(R.id.textName);
            textPhone = itemView.findViewById(R.id.textPhone);
            textProvince = itemView.findViewById(R.id.textProvince);
        }
    }
}
