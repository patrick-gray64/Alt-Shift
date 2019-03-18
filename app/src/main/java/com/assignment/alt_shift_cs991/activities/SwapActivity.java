package com.assignment.alt_shift_cs991.activities;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TextView;

import com.assignment.alt_shift_cs991.model.Application;
import com.assignment.alt_shift_cs991.R;
import com.assignment.alt_shift_cs991.adapters.ShiftAdapter;
import com.assignment.alt_shift_cs991.model.Shift;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SwapActivity extends ToolbarActivity {

    public RecyclerView recyclerView;
    private ShiftAdapter shiftAdapter;
    private Shift shiftInfo;
    protected Application model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swap);
        initToolbar();

        model = (Application) getApplication();
        Bundle extras = getIntent().getExtras();
        shiftInfo = (Shift) extras.get("SHIFT");

        String swapDate = String.valueOf(shiftInfo.getDate());
        String swapUserName = shiftInfo.getUserName();
        String swapPassword = shiftInfo.getPassword();
        Shift shift = model.shiftManager.getShift(model.shiftManager.getShifter(swapUserName,swapPassword), swapDate);

        String swapText = String.format(getString(R.string.swap_info), shift.getDate());
        TextView swapInfo = findViewById(R.id.swapInfo);
        swapInfo.setText(swapText);

        shiftAdapter = new ShiftAdapter(model.shiftManager.getSwapableShifts(shift));
        shiftAdapter.shifterUserName = swapUserName;
        shiftAdapter.shifterPassword = swapPassword;
        shiftAdapter.shifterDate = swapDate;
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(shiftAdapter);

    }
}