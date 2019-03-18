package com.assignment.alt_shift_cs991.adapters;

import android.content.Context;
import android.view.View;

import com.assignment.alt_shift_cs991.activities.PendingSwapsEmp;
import com.assignment.alt_shift_cs991.model.ShiftSwap;

import java.util.List;

public class Requested_Swap_Adapter extends Available_Swap_Adapter {

    public Requested_Swap_Adapter(Context context, List<ShiftSwap> shiftArray) {
        super(context, shiftArray);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.yourDate.setText(getShiftArray().get(position).getUnwantedShift().getDate());
        holder.offeredDate.setText(getShiftArray().get(position).getWantedShift().getDate());
        holder.otherShifter.setText(getShiftArray().get(position).getWantedShift().getShifter().getFirstName()+"' s Shift");
        holder.delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ((PendingSwapsEmp)getmContext()).getModel().removeSwap(getShiftArray().get(position));
                //System.out.println(((Pending_Swaps_EMP)getmContext()).model.getRequestedSwaps());/** just for testing*/
                getShiftArray().remove(position);
                notifyDataSetChanged();
            }
        });
    }
}
