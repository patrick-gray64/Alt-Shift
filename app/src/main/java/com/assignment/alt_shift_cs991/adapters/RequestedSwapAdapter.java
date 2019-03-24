package com.assignment.alt_shift_cs991.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.assignment.alt_shift_cs991.R;
import com.assignment.alt_shift_cs991.activities.PendingSwapsEmp;
import com.assignment.alt_shift_cs991.model.ShiftSwap;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Adapter for requested swaps.
 */
public class RequestedSwapAdapter extends RecyclerView.Adapter<RequestedSwapAdapter.MyViewHolder> {

    private List<ShiftSwap> shiftArray;
    public PendingSwapsEmp.Callback callback;

    /**
     * Constructor for RequestedSwapAdapter.
     *
     * @param shiftArray list of shift swaps
     */
    public RequestedSwapAdapter(List<ShiftSwap> shiftArray) {
        super();
        setHasStableIds(true);
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
        }
    }

    public void setCallBack(PendingSwapsEmp.Callback callback) {
        this.callback = callback;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflate = LayoutInflater.from(parent.getContext());
        view = inflate.inflate(R.layout.schedule_item, parent, false);
        MyViewHolder viewH = new MyViewHolder(view);
        return viewH;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.yourDate.setText(shiftArray.get(position).getUnwantedShift().getDate());
        holder.offeredDate.setText(shiftArray.get(position).getWantedShift().getDate());
        holder.otherShifter.setText(shiftArray.get(position).getWantedShift().getShifter().getFirstName() + "' s Shift");
        holder.delete.setOnClickListener(v -> {
            callback.getModel().shiftManager.removeSwap(shiftArray.get(position));
            shiftArray.remove(position);
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return shiftArray.size();
    }
}

