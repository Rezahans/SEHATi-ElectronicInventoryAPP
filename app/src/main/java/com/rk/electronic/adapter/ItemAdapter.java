package com.rk.electronic.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rk.electronic.R;
import com.rk.electronic.model.Item;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private List<Item> itemList;

    public ItemAdapter(List<Item> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_electronic, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Item item = itemList.get(position);

        // Tampilkan data ke dalam view
        holder.textBatchNo.setText(item.getBatchNo());
        holder.textExpired.setText(item.getExpired());
        holder.textName.setText(item.getName());
        holder.textLocation.setText(item.getLocation());
        holder.textQuantity.setText(item.getQuantity());
        holder.textType.setText(item.getType());

        // Tampilkan gambar menggunakan Glide (misalnya dari URL Firebase)
        Glide.with(holder.itemView.getContext())
                .load(item.getImageUrl())
                .into(holder.imageItem);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView imageItem;
        TextView textBatchNo;
        TextView textExpired;
        TextView textName;
        TextView textLocation;
        TextView textQuantity;
        TextView textType;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imageItem = itemView.findViewById(R.id.imageItem);
            textBatchNo = itemView.findViewById(R.id.textBatchNo);
            textExpired = itemView.findViewById(R.id.textExpired);
            textName = itemView.findViewById(R.id.textName);
            textLocation = itemView.findViewById(R.id.textLocation);
            textQuantity = itemView.findViewById(R.id.textQuantity);
            textType = itemView.findViewById(R.id.textType);
        }
    }
}
