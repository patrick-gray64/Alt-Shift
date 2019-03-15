package com.assignment.alt_shift_cs991;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SwapActivity extends Toolbar_activity {

    public RecyclerView recyclerView;
    private ShiftAdapter shiftAdapter;
    private Shift shift;
    protected AltShift_Application model;
    private Shift shift1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swap);
        initToolbar();
        model = (AltShift_Application) getApplication();

        shift = getIntent().getExtras().getParcelable("SHIFT");

        shift1 = new Shift(shift.getDate(), model.getLoggedInUser());
        shiftAdapter = new ShiftAdapter(model.getSwappableShifts(shift1));
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(shiftAdapter);

    }
}