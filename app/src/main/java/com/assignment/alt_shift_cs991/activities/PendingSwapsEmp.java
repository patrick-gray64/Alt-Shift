package com.assignment.alt_shift_cs991.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.assignment.alt_shift_cs991.CalendarActivity;
import com.assignment.alt_shift_cs991.R;
import com.assignment.alt_shift_cs991.adapters.PendingSwapAdapter;
import com.assignment.alt_shift_cs991.model.Application;
import com.assignment.alt_shift_cs991.model.PendingSwapItem;

import java.util.ArrayList;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

;


public class PendingSwapsEmp extends ToolbarActivity {

    private ArrayList<PendingSwapItem> ShiftArray;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter mAdapter;
    protected Application model;

    /**
     * Initialises PendingSwapsEmp activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pending_swaps_emp);
        ShiftArray = new ArrayList<PendingSwapItem>();
        recyclerView = findViewById(R.id.rcview);
        mAdapter = new PendingSwapAdapter(this.ShiftArray);
        recyclerView.setAdapter(mAdapter);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        ShiftArray.add(new PendingSwapItem("7th March"));
        ShiftArray.add(new PendingSwapItem("1st March"));
        initToolbar();
        model = (Application)getApplication();
    }

    /**
     * Returns user to the calendar activity
     * @param view
     */
    public void backToMain(View view){
        Intent intent = new Intent(this, CalendarActivity.class);
        startActivity(intent);
    }
}
