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

    private List<Shifter> shifters = new ArrayList<>();
    private StartActivityCallback startActivityCallback;

    public List<Shifter> getShifterlList() {
        return shifters;
    }

    public void setShifterList(List<Shifter> shifters) {
        this.shifters = shifters;
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
        holder.adapterItemBinding.setShifter(shifters.get(holder.getAdapterPosition()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityCallback.startActivityIntent(v, shifters.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return shifters.size();
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

        void startActivityIntent(View v, Shifter shifter);
    }
}
