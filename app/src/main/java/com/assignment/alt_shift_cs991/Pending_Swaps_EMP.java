package com.assignment.alt_shift_cs991;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class Pending_Swaps_EMP extends Toolbar_activity {

    private ArrayList<Pending_Swap_Item> ShiftArray;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pending_swaps_emp);
        ShiftArray = new ArrayList<Pending_Swap_Item>();
        recyclerView = (RecyclerView) findViewById(R.id.rcview);
        mAdapter = new Pending_Swap_Adapter(this.ShiftArray);
        recyclerView.setAdapter(mAdapter);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        ShiftArray.add(new Pending_Swap_Item("7th March"));
        ShiftArray.add(new Pending_Swap_Item("1st March"));
        initToolbar();

    }

    public void backToMain(View view){
        Intent intent = new Intent(this, CalendarActivity.class);
        startActivity(intent);
    }
}
