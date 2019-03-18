package com.assignment.alt_shift_cs991.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.assignment.alt_shift_cs991.model.PendingSwapItem;
import com.assignment.alt_shift_cs991.R;
import com.assignment.alt_shift_cs991.activities.ShiftSwapActivity;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;


public class PendingSwapAdapter extends RecyclerView.Adapter<PendingSwapAdapter.MyViewHolder>{


    private ArrayList<PendingSwapItem> shiftArray;


    public PendingSwapAdapter(ArrayList<PendingSwapItem> shiftArray) {

        this.shiftArray = shiftArray;
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        // TextView name;
        TextView index_text;

        public MyViewHolder(View view) {

            super(view);
            //name = (TextView) view.findViewById(R.id.your_shift);
            index_text = view.findViewById(R.id.index_text);
            view.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), ShiftSwapActivity.class);
                    v.getContext().startActivity(intent);
                }
            });
        }

    }

    @Override
    public PendingSwapAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflate= LayoutInflater.from(parent.getContext());
        view = inflate.inflate(R.layout.schedule_item, parent, false);
        MyViewHolder viewH = new MyViewHolder(view);
        return viewH;
    }



    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.index_text.setText(shiftArray.get(position).getName());
        String index = String.valueOf(shiftArray.indexOf(shiftArray.get(position)));
        // holder.index_text.setText("2nd March");
    }


    @Override
    public int getItemCount() {

        return shiftArray.size();
    }

}

