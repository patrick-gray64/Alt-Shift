package com.assignment.alt_shift_cs991;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.assignment.alt_shift_cs991.databinding.AdapterItemBinding;

import java.util.ArrayList;
import java.util.List;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<ShiftModel> shiftModelList = new ArrayList<>();

    private StartActivityCallback startActivityCallback;

    public List<ShiftModel> getShiftModelList() {
        return shiftModelList;
    }

    public void setShiftModelList(List<ShiftModel> shiftModelList) {
        this.shiftModelList = shiftModelList;
    }

    public StartActivityCallback getStartActivityCallback() {
        return startActivityCallback;
    }

    public void setStartActivityCallback(StartActivityCallback startActivityCallback) {
        this.startActivityCallback = startActivityCallback;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AdapterItemBinding adapterItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.adapter_item,parent,false);
        return new ViewHolder(adapterItemBinding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.adapterItemBinding.setShiftModel(shiftModelList.get(holder.getAdapterPosition()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityCallback.startActivityIntent(v, shiftModelList.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return shiftModelList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        AdapterItemBinding adapterItemBinding;

        public ViewHolder( AdapterItemBinding adapterItemBinding) {
            this(adapterItemBinding.getRoot());
            this.adapterItemBinding = adapterItemBinding;
        }

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    interface StartActivityCallback{

        void startActivityIntent(View v, ShiftModel shiftModel);
    }
}
