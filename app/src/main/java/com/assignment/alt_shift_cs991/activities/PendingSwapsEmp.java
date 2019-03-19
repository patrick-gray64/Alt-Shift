package com.assignment.alt_shift_cs991.activities;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.assignment.alt_shift_cs991.R;
import com.assignment.alt_shift_cs991.adapters.AvailableSwapAdapter;
import com.assignment.alt_shift_cs991.adapters.RequestedSwapAdapter;
import com.assignment.alt_shift_cs991.model.Application;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class PendingSwapsEmp extends ToolbarActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter availableSwapRequestAdapter;
    private RecyclerView.Adapter offeredSwapAdapter;
    protected Application model;
    private TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pending_swaps_emp);
        model = (Application)getApplication();
        description = (TextView) findViewById(R.id.description);
        recyclerView = (RecyclerView) findViewById(R.id.rcview);
        availableSwapRequestAdapter = new AvailableSwapAdapter(this, model.shiftManager.getAvailableSwaps(model.getLoggedInShifter()));
        offeredSwapAdapter = new RequestedSwapAdapter(this, model.shiftManager.getRequestedSwaps(model.getLoggedInShifter()));
        recyclerView.setAdapter(availableSwapRequestAdapter);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        initToolbar();
        getSupportActionBar().setTitle("My Shifts Swaps");
        Switch mySwitch = findViewById(R.id.switch1);

        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean on) {
                if(on)
                {
                    recyclerView.setAdapter(offeredSwapAdapter);
                    description.setText(R.string.pending_requests);
                }
                else{
                    recyclerView.setAdapter(availableSwapRequestAdapter);
                    description.setText(R.string.pending_available);
                }
            }
        });
    }


    public Application getModel() {
        return model;
    }
}
