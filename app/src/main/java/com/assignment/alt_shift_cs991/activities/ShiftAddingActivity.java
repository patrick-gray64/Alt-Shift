package com.assignment.alt_shift_cs991.activities;

import android.os.Bundle;

import com.assignment.alt_shift_cs991.R;
import com.assignment.alt_shift_cs991.adapters.ShifterAdapter;
import com.assignment.alt_shift_cs991.model.Application;
import com.assignment.alt_shift_cs991.model.Shifter;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ShiftAddingActivity extends ToolbarActivity {

    protected Application model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shift);
        initToolbar();

        model = (Application) getApplication();
        List<Shifter> shifters = model.shiftManager.getShifters();
        ShifterAdapter shifterAdapter = new ShifterAdapter(this, shifters);
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

    public interface Callback {

        void finishActivity();

        Application getModel();
    }
}
