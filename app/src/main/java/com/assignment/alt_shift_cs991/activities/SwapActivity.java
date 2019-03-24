package com.assignment.alt_shift_cs991.activities;

import android.os.Bundle;
import android.widget.TextView;

import com.assignment.alt_shift_cs991.R;
import com.assignment.alt_shift_cs991.adapters.ShiftAdapter;
import com.assignment.alt_shift_cs991.model.Shift;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Activity for a shifter to choose another shifter's shift they want to swap.
 */
public class SwapActivity extends ToolbarActivity {

    public RecyclerView recyclerView;
    protected Application model;

    /**
     * Initialises activity with a list of available shifts for swapping.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swap);
        initToolbar();

        model = (Application) getApplication();

        Shift shift = model.selectedCurrentShift;
        String swapDate = shift.getDate();
        String swapUserName = shift.getUserName();
        String swapPassword = shift.getPassword();


        String swapText = String.format(getString(R.string.swap_info), shift.getDate());
        TextView swapInfo = findViewById(R.id.swapInfo);
        swapInfo.setText(swapText);

        ShiftAdapter shiftAdapter = new ShiftAdapter(model.shiftManager.getSwapableShifts(shift));
        shiftAdapter.shifterUserName = swapUserName;
        shiftAdapter.shifterPassword = swapPassword;
        shiftAdapter.shifterDate = swapDate;
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(shiftAdapter);

    }
}