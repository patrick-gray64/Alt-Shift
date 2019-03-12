package com.assignment.alt_shift_cs991;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static android.content.Intent.EXTRA_TEXT;

public class SwapActivity extends Toolbar_activity  {

    public RecyclerView recyclerView;
    protected AltShift_Application model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swap);
        initToolbar();
        model = (AltShift_Application)getApplication();

        Bundle bundle = getIntent().getExtras();
        String message = bundle.getString(EXTRA_TEXT);

        recyclerView = findViewById(R.id.recycler_view);
        Adapter adapter = new Adapter();

        List<ShiftModel> shiftModelList = new ArrayList<ShiftModel>();
        shiftModelList.add(new ShiftModel("J Smith", "Employee Number One", "March 21 2019"));
        shiftModelList.add(new ShiftModel("D Anderson", "Employee Number Two", "March 21 2019"));
        shiftModelList.add(new ShiftModel("Steve", "Employee Number Three", "March 22 2019"));

        List<ShiftModel> filteredShiftModelList = new ArrayList<ShiftModel>();

        for (int i = 0; i < shiftModelList.size(); i++) {
            if (!shiftModelList.get(i).getWorkingDate().equals(message)) {
                filteredShiftModelList.add(shiftModelList.get(i));
            }
        }
        adapter.setShiftModelList(filteredShiftModelList);



        adapter.setStartActivityCallback(new Adapter.StartActivityCallback() {
            @Override
            public void startActivityIntent(View v, ShiftModel shiftModel) {
                animateIntent(v, shiftModel);
            }
        });
        recyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

    }


    public void animateIntent(View view, ShiftModel shiftModel) {

        // Ordinary Intent for launching a new activity
        Intent intent = new Intent(this, ShiftSwapActivity.class);
        //Bundle bundle = new Bundle();


        intent.putExtra("SHIFT_MODEL", shiftModel);
        // Get the transition name from the string
        String transitionName = "usercard";

        // Define the view that the animation will start from

        ActivityOptionsCompat options =

                ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                        view,   // Starting view
                        transitionName    // The String
                );
        //Start the Intent
        ActivityCompat.startActivity(this, intent, options.toBundle());

    }
}

