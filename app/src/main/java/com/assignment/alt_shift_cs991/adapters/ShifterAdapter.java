package com.assignment.alt_shift_cs991.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.assignment.alt_shift_cs991.R;
import com.assignment.alt_shift_cs991.activities.ManagerCalendarActivity;
import com.assignment.alt_shift_cs991.activities.ShiftAddingActivity;
import com.assignment.alt_shift_cs991.model.Shifter;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Adapter for shifter information.
 */
public class ShifterAdapter extends RecyclerView.Adapter<ShifterAdapter.MyViewHolder> {

    private List<Shifter> shifters;
    public String dateOfNewShift;
    protected ShiftAddingActivity.Callback callback;

    /**
     * A constructor for the MyStackAdapter class.
     */
    public ShifterAdapter(List<Shifter> shifters) {
        super();
        setHasStableIds(true);
        this.shifters = shifters;
    }

    /**
     * Returns the hash value of the item in a specific position in the list.
     *
     * @param position the index position of the item
     * @return the hashCode of the item
     */
    @Override
    public long getItemId(int position) {
        return shifters.get(position).hashCode();
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
        return MyViewHolder.createHolder(viewGroup);
    }

    /**
     * Sets the item from the list into a specific holder in the recyclerView.
     *
     * @param viewHolder the viewHolder
     * @param position   the position within the list
     */
    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {
        Shifter shifter = shifters.get(position);
        viewHolder.name.setText(shifter.getFirstName());
        viewHolder.surname.setText(shifter.getSurname());
        viewHolder.itemView.setOnClickListener(v -> {
            int a = viewHolder.getAdapterPosition();
            Intent intent = new Intent(v.getContext(), ManagerCalendarActivity.class);
            if (callback != null) {
                callback.finishActivity();
            }
            v.getContext().startActivity(intent);
            Toast.makeText(callback.getModel().getApplicationContext(), "Shift added to calendar!", Toast.LENGTH_SHORT).show();
            callback.getModel().shiftManager.addShift(dateOfNewShift, shifters.get(a));
            notifyDataSetChanged();
        });
    }

    public void setCallBack(ShiftAddingActivity.Callback callback) {
        this.callback = callback;
    }

    /**
     * A method to show the number of items within the list.
     *
     * @return the number of values in the list
     */
    @Override
    public int getItemCount() {
        return shifters.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView surname;

        /**
         * A constructor which initiates the views which will be inside the textView.
         *
         * @param itemView the current view
         */
        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_field);
            surname = itemView.findViewById(R.id.description_field);
        }

        /**
         * A method to create the view holder within the current view group.
         *
         * @param viewGroup the current view group
         * @return a view holder which holds list items
         */
        public static MyViewHolder createHolder(ViewGroup viewGroup) {
            LayoutInflater viewInflater = LayoutInflater.from(viewGroup.getContext());
            View listItemView = viewInflater.inflate(R.layout.adapter_item, viewGroup, false);
            return new MyViewHolder(listItemView);
        }
    }
}



