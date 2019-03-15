package com.assignment.alt_shift_cs991;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;


public class Pending_Swap_Adapter extends RecyclerView.Adapter<Pending_Swap_Adapter.MyViewHolder>{


    private ArrayList<Pending_Swap_Item> shiftArray;


    public Pending_Swap_Adapter(ArrayList<Pending_Swap_Item> shiftArray) {

        this.shiftArray = shiftArray;
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

       // TextView name;
        TextView index_text;

        public MyViewHolder(View view) {

            super(view);
            //name = (TextView) view.findViewById(R.id.your_shift);
            index_text = (TextView) view.findViewById(R.id.index_text);
            view.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), ShiftSwapActivity.class);
                    v.getContext().startActivity(intent);
                }
            });
        }

    }

    @Override
    public Pending_Swap_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflate= LayoutInflater.from(parent.getContext());
        view = inflate.inflate(R.layout.schedule_item, parent, false);
        MyViewHolder viewH = new MyViewHolder(view);
        return viewH;
    }



    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.index_text.setText(shiftArray.get(position).getMyString());
        String index = String.valueOf(shiftArray.indexOf(shiftArray.get(position)));
       // holder.index_text.setText("2nd March");
    }


    @Override
    public int getItemCount() {

        return shiftArray.size();
    }

}

