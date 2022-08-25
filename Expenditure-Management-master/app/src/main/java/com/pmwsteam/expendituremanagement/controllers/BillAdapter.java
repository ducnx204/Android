package com.pmwsteam.expendituremanagement.controllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pmwsteam.expendituremanagement.R;
import com.pmwsteam.expendituremanagement.models.Bill;

import java.util.ArrayList;
import java.util.List;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.RecyclerViewHolder> {
    private Context context;
    private List<Bill> list = new ArrayList<>();
    public BillAdapter(@NonNull Context context, List<Bill> bills) {
        this.list = bills;
        this.context = context;
    }

    @NonNull
    @Override
    public BillAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_bill, parent, false);

        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BillAdapter.RecyclerViewHolder holder, int position) {
        holder.idNameItem.setText(list.get(position).getNameBill());
        holder.idPriceItem.setText(list.get(position).getMoneyBill());
        holder.idDateItem.setText(list.get(position).getDateBill());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView idNameItem, idPriceItem, idDateItem;

        RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            idNameItem = itemView.findViewById(R.id.idNameItem);
            idPriceItem = itemView.findViewById(R.id.idPriceItem);
            idDateItem = itemView.findViewById(R.id.idDateItem);
        }
    }
}
