package com.assignment.alt_shift_cs991.activities;

import android.os.Bundle;

import com.assignment.alt_shift_cs991.R;
import com.assignment.alt_shift_cs991.adapters.ShifterAdapter;
import com.assignment.alt_shift_cs991.model.Application;
import com.assignment.alt_shift_cs991.model.Shifter;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Activity that enables the manager to assign employees to shifts.
 */
public class ShiftAddingActivity extends ToolbarActivity {

    protected Application model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shift);
        initToolbar();

        model = (Application) getApplication();
        List<Shifter> shifters = model.shiftManager.getShifters();
        ShifterAdapter shifterAdapter = new ShifterAdapter(shifters);
        shifterAdapter.dateOfNewShift = model.getDateClicked();
        RecyclerView recyclerView = findViewById(R.id.recycler_view2);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(shifterAdapter);

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
        shifterAdapter.setCallBack(callback);
    }

    /**
     * Callback interface which enables us to use these methods within the ShifterAdapter, where
     * it is necessary to use some of the methods from our Application class.
     */
    public interface Callback {

        void finishActivity();

        Application getModel();
    }
}
