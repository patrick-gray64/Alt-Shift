package com.assignment.alt_shift_cs991.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.assignment.alt_shift_cs991.R;
import com.assignment.alt_shift_cs991.activities.ManagerCalendarActivity;
import com.assignment.alt_shift_cs991.model.Shift;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Adapter for the list contained in the managers calendar view.
 */
public class ManagerAdapter extends RecyclerView.Adapter<ManagerAdapter.MyViewHolder> {

    private List<Shift> shifts;
    public ManagerCalendarActivity.Callback callback;

    /**
     * A constructor for the ManagerAdapter class.
     */
    public ManagerAdapter(List<Shift> shifts) {
        super();
        setHasStableIds(true);
        this.shifts = shifts;
    }

    public void setCallBack(ManagerCalendarActivity.Callback callback) {
        this.callback = callback;
    }


    /**
     * Returns the hash value of the item in a specific position in the list.
     *
     * @param position the index position of the item
     * @return the hashCode of the item
     */
    @Override
    public long getItemId(int position) {
        return shifts.get(position).hashCode();
    }

    /**
     * Updates items.
     *
     * @param shifts - shifts to be updated in the list
     */
    public void setItems(List<Shift> shifts) {
        this.shifts = shifts;
        notifyDataSetChanged();
    }

    /**
     * Creates the viewHolder and places it inside the correct viewGroup.
     *
     * @param viewGroup the viewGroup in question
     * @param viewType  the viewType which the holder should be placed in
     * @return the viewHolder
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        return ManagerAdapter.MyViewHolder.createHolder(viewGroup);
    }

    /**
     * Sets the item from the list into a specific holder in the recyclerView and remove shifts if bin button clicked.
     *
     * @param viewHolder the viewHolder
     * @param position   the position within the list
     */
    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {
        Shift shift = shifts.get(position);
        viewHolder.shifterName.setText(shift.getShifter().getFirstName());
        viewHolder.date.setText(shift.getDate());
        viewHolder.delete.setOnClickListener(v -> {
            callback.getModel().shiftManager.removeShift(shifts.get(position));
            shifts.remove(position);
            callback.getCalendar().removeAllEvents();
            callback.getCalendarManager().shiftPopulate(callback.getCalendar(), callback.getModel().shiftManager.getAllShiftsDates());
            notifyDataSetChanged();
        });
    }

    /**
     * A method to show the number of items within the list.
     *
     * @return the number of values in the list
     */
    @Override
    public int getItemCount() {
        return shifts.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView shifterName;
        private TextView date;
        private ImageButton delete;


        /**
         * A constructor which initiates the views which will be inside the textView.
         *
         * @param itemView the current view
         */
        public MyViewHolder(View itemView) {
            super(itemView);
            shifterName = itemView.findViewById(R.id.name_field);
            date = itemView.findViewById(R.id.description_field);
            delete = itemView.findViewById(R.id.deleteM);

        }

        /**
         * A method to create the view holder within the current view group.
         *
         * @param viewGroup the current view group
         * @return a view holder which holds list items
         */
        public static ManagerAdapter.MyViewHolder createHolder(ViewGroup viewGroup) {
            LayoutInflater viewInflater = LayoutInflater.from(viewGroup.getContext());
            View listItemView = viewInflater.inflate(R.layout.manager_shift_item, viewGroup, false);
            return new ManagerAdapter.MyViewHolder(listItemView);
        }
    }
}



