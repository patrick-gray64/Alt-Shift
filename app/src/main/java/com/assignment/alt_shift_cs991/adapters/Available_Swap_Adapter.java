package com.assignment.alt_shift_cs991.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.assignment.alt_shift_cs991.R;
import com.assignment.alt_shift_cs991.activities.PendingSwapsEmp;
import com.assignment.alt_shift_cs991.activities.ShiftSwapActivity;
import com.assignment.alt_shift_cs991.model.ShiftSwap;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;


public class Available_Swap_Adapter extends RecyclerView.Adapter<Available_Swap_Adapter.MyViewHolder>{
    private List<ShiftSwap> shiftArray;
    private Context mContext;

    public Available_Swap_Adapter(Context context, List<ShiftSwap> shiftArray) {
        this.mContext=context;
        this.shiftArray = shiftArray;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView yourDate;
        TextView offeredDate;
        TextView otherShifter;
        ImageButton delete;

        public MyViewHolder(View view) {
            super(view);
            yourDate = view.findViewById(R.id.your_date);
            offeredDate = view.findViewById(R.id.offered_date);
            otherShifter = view.findViewById(R.id.other_shifter);
            delete = view.findViewById(R.id.delete);
            view.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), ShiftSwapActivity.class);
                    v.getContext().startActivity(intent);
                }
            });
        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflate= LayoutInflater.from(parent.getContext());
        view = inflate.inflate(R.layout.schedule_item, parent, false);
        MyViewHolder viewH = new MyViewHolder(view);
        return viewH;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        System.out.println(mContext);
        holder.yourDate.setText(shiftArray.get(position).getWantedShift().getDate());
        holder.offeredDate.setText(shiftArray.get(position).getUnwantedShift().getDate());
        holder.otherShifter.setText(shiftArray.get(position).getUnwantedShift().getShifter().getFirstName() + "' s Shift");
        holder.delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ((PendingSwapsEmp)getmContext()).getModel().removeSwap(shiftArray.get(position));
                shiftArray.remove(position);  // remove the item from list
                notifyDataSetChanged(); // notify the adapter about the removed item
            }
        });
    }

    @Override
    public int getItemCount() {
        return shiftArray.size();
    }

    public List<ShiftSwap> getShiftArray() {
        return shiftArray;
    }

    public Context getmContext() {
        return mContext;
    }
}

