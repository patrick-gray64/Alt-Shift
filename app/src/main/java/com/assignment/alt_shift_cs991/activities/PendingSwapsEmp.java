package com.assignment.alt_shift_cs991.activities;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.assignment.alt_shift_cs991.R;
import com.assignment.alt_shift_cs991.adapters.AvailableSwapAdapter;
import com.assignment.alt_shift_cs991.adapters.RequestedSwapAdapter;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Activity that handles the pending swaps.
 */
public class PendingSwapsEmp extends ToolbarActivity {

    private RecyclerView recyclerView;
    private AvailableSwapAdapter availableSwapRequestAdapter;
    private RecyclerView.Adapter offeredSwapAdapter;
    protected Application model;
    private TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pending_swaps_emp);

        model = (Application) getApplication();
        description = findViewById(R.id.description);
        recyclerView = findViewById(R.id.rcview);
        availableSwapRequestAdapter = new AvailableSwapAdapter(model.shiftManager.getAvailableSwaps(model.getLoggedInShifter()));
        offeredSwapAdapter = new RequestedSwapAdapter(model.shiftManager.getRequestedSwaps(model.getLoggedInShifter()));
        recyclerView.setAdapter(availableSwapRequestAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        initToolbar();
        getSupportActionBar().setTitle("My Shifts Swaps");
        Switch mySwitch = findViewById(R.id.switch1);

        Callback callback = new Callback() {
            @Override
            public void finishActivity() {
                finish();
            }

            @Override
            public Application getModel() {
                return model;
            }
        };
        availableSwapRequestAdapter.setCallback(callback);

        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean on) {
                if (on) {
                    recyclerView.setAdapter(availableSwapRequestAdapter);
                    description.setText(R.string.pending_requests);
                } else {
                    recyclerView.setAdapter(offeredSwapAdapter);
                    description.setText(R.string.pending_available);
                }
            }
        });
    }

    /**
     * Callback interface which enables us to use these methods within the AvailableSwapAdapter, where
     * it is necessary to use some of the methods from our Application class.
     */
    public interface Callback {

        void finishActivity();

        Application getModel();
    }
}
