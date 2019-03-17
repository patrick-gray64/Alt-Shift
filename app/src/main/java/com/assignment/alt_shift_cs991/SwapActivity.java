package com.assignment.alt_shift_cs991;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SwapActivity extends Toolbar_activity {

    public RecyclerView recyclerView;
    private ShiftAdapter shiftAdapter;
    private Shift shiftInfo;
    protected AltShift_Application model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swap);
        initToolbar();
        model = (AltShift_Application) getApplication();
        Bundle extras = getIntent().getExtras();
        shiftInfo = (Shift) extras.get("SHIFT");
        String swapDate = String.valueOf(shiftInfo.getDate());
        String swapUserName = shiftInfo.getUserName();
        String swapPassword = shiftInfo.getPassword();

        Shift shift = model.getShift(model.accessGetShifter(swapUserName,swapPassword), swapDate);
        shiftAdapter = new ShiftAdapter(model.getSwappableShifts(shift));
        shiftAdapter.shifterUserName = swapUserName;
        shiftAdapter.shifterPassword = swapPassword;
        shiftAdapter.shifterDate = swapDate;
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(shiftAdapter);

    }
}